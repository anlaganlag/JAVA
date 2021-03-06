package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.libs.context.auth.LoginContext;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.enums.YesOrNotEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.core.constant.SystemConstants;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysPermission;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysPermissionResource;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysResource;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysRolePermission;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.SysPermissionMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.SysRolePermissionMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.RoleInfo;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysPermissionParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysPermissionResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysPermissionResourceService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysPermissionService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysPermissionResourceService sysPermissionResourceService;

    @Autowired
    private SysResourceService sysResourceService;

    @Resource
    private SysPermissionMapper permissionMapper;

    @Override
    public void add(SysPermissionParam param) {
        SysPermission entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SysPermissionParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(SysPermissionParam param) {
        SysPermission oldEntity = getOldEntity(param);
        SysPermission newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SysPermissionResult findBySpec(SysPermissionParam param) {
        return null;
    }

    @Override
    public List<SysPermissionResult> findListBySpec(SysPermissionParam param) {
        return null;
    }

    @Override
    public PageResult findPageBySpec(SysPermissionParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult(page);
    }

    @Override
    public Set<String> getUserResources(List<RoleInfo> roleInfos) {

        HashSet<Long> roleIds = new HashSet<>();
        HashSet<Long> permissionIds = new HashSet<>();
        HashSet<String> resourceIds = new HashSet<>();
        HashSet<String> resourceUrls = new HashSet<>();

        for (RoleInfo roleInfo : roleInfos) {
            roleIds.add(roleInfo.getRoleId());
        }

        //????????????????????????
        List<SysRolePermission> rolePermissions =
                sysRolePermissionMapper.selectList(new QueryWrapper<SysRolePermission>().in("role_id", roleIds));
        for (SysRolePermission rolePermission : rolePermissions) {
            permissionIds.add(rolePermission.getPermissionId());
        }

        //????????????????????????
        List<SysPermissionResource> permissionResources =
                sysPermissionResourceService.list(
                        new QueryWrapper<SysPermissionResource>().in("permission_id", permissionIds));
        for (SysPermissionResource permissionResource : permissionResources) {
            resourceIds.add(permissionResource.getResourceId());
        }

        //????????????
        List<SysResource> sysResources = sysResourceService.list(
                new QueryWrapper<SysResource>().in("resource_id", resourceIds));
        for (SysResource resrResource : sysResources) {
            resourceUrls.add(resrResource.getUrl());
        }

        return resourceUrls;
    }

    private Serializable getKey(SysPermissionParam param) {
        return param.getPermissionId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private SysPermission getOldEntity(SysPermissionParam param) {
        return this.getById(getKey(param));
    }

    private SysPermission getEntity(SysPermissionParam param) {
        SysPermission entity = new SysPermission();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addPermission(SysPermission permission) {

        // ????????????
        if (ObjectUtil.isEmpty(permission.getPermissionName())) {
            throw new RequestEmptyException("??????????????????");
        }

        // ??????ID
        if (ObjectUtil.isEmpty(permission.getAppId())) {
            throw new RequestEmptyException("appId??????");
        }

        // ????????????????????????????????????
        if (checkPermissionName(permission.getAppId(), permission.getPermissionName())) {
            throw new RequestEmptyException("????????????????????????");
        }

        // ?????????????????????0
        if (ObjectUtil.isEmpty(permission.getParentId())) {
            permission.setParentId(SystemConstants.PARENT_ID);
        }

        // ??????
        if (permission.getStatus() == null) {
            permission.setStatus(StatusEnum.ENABLE.getCode());
        }

        permission.setPermissionId(IdWorker.getId());
        permission.setCreateTime(new Date());
        permission.setCreateUser(LoginContext.me().getAccountId());
        permission.setDelFlag(YesOrNotEnum.N.name());

        if (this.save(permission)) {
            return permission.getPermissionId();
        }
        return null;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setPermissionStatus(List<Long> permissionIds, StatusEnum status) {

        if (status == null) {
            throw new RequestEmptyException("?????????????????????");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("permissionIds", permissionIds);
        param.put("status", status.getCode());
        param.put("updateTime", new Date());
        param.put("updateUser", LoginContext.me().getAccountId());

        return permissionMapper.setPermissionStatus(param);
    }

    @Override
    public Page getPermissionPageList(Page<Map<String, Object>> page, Long appId, String permissionName,
                                      Integer status) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("appId", appId);
        param.put("permissionName", permissionName);
        param.put("delFlag", YesOrNotEnum.N.name());
        param.put("status", status);
        List<Map<String, Object>> permissionPageList = permissionMapper.selectPermissionPageList(page, param);
        page.setRecords(permissionPageList);
        return page;
    }

    @Override
    public List<Map<String, Object>> getPermissionList(Long appId, Integer status) {

        Map<String, Object> param = new HashMap();
        param.put("appId", appId);
        param.put("delFlag", YesOrNotEnum.N.name());
        param.put("status", status);

        return permissionMapper.selectPermissionList(param);
    }

    @Override
    public List<Map<String, Object>> getPermissionList4Level(Long appId, Long parentId, Integer status) {

        // ??????ID
        if (ObjectUtil.isEmpty(appId)) {
            throw new RequestEmptyException("??????id??????");
        }

        if (ObjectUtil.isEmpty(parentId)) {
            parentId = SystemConstants.PARENT_ID;
        }
        QueryWrapper<SysPermission> entityWrapper = new QueryWrapper<>();
        // ??????????????????
        entityWrapper.select(
                "permission_id AS permissionId",
                "app_id AS appId",
                "permission_name AS permissionName",
                "permission_desc AS permissionDesc",
                "parent_id AS parentId",
                "order_no AS orderNo",
                "status AS status");
        entityWrapper.eq("app_id", appId);
        entityWrapper.eq("parent_id", parentId);
        if (!ObjectUtil.isEmpty(status)) {
            entityWrapper.eq("status", status);
        }
        entityWrapper.eq("del_flag", YesOrNotEnum.N.name());

        return listMaps(entityWrapper);
    }

    /**
     * ???????????????????????????????????????
     *
     * @param appId          ??????ID
     * @param permissionName ????????????
     * @author fengshuonan
     * @Date 2019/9/26 23:02
     */
    private boolean checkPermissionName(Long appId, String permissionName) {
        QueryWrapper<SysPermission> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("app_id", appId);
        entityWrapper.eq("permission_name", permissionName);
        entityWrapper.eq("del_flag", YesOrNotEnum.N.name());
        int count = count(entityWrapper);
        return count > 0;
    }

    @Override
    public List<Map<String, Object>> getPermissionTree(Long appId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("appId", appId);
        param.put("delFlag", YesOrNotEnum.N.name());
        param.put("status", StatusEnum.ENABLE.getCode());
        return permissionMapper.selectPermissionTree(param);
    }

    /**
     * ????????????
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removePermission(Long permissionId) {
        //???????????????????????????,??????????????????
        Integer count = this.baseMapper.selectCount(new QueryWrapper<SysPermission>().eq("parent_id",
                permissionId));
        if (count != 0) {
            throw new RequestEmptyException("???????????????????????????");
        }
        SysPermission permission = this.getById(permissionId);
        if (StatusEnum.ENABLE.getCode().equals(permission.getStatus())) {
            throw new RequestEmptyException("??????????????????????????????");
        }
        this.removeById(permissionId);
        Map<String, Object> param = MapUtil
                .builder(new HashMap<String, Object>())
                .put("permission_id", permissionId)
                .build();
        //?????????????????????
        this.sysPermissionResourceService.removeByMap(param);
        //?????????????????????
        this.sysRolePermissionMapper.deleteByMap(param);
    }

    /**
     * ??????????????????
     *
     * @author stylefeng
     * @date 2019/11/3
     */
    @Override
    public List<Map<String, Object>> getAppButtonStatus(String appCode) {
        Set<String> resourceUrls = LoginContext.me().getLoginUser().getResourceUrls();

        if (ObjectUtil.isEmpty(resourceUrls)) {
            return new ArrayList<>();
        }

        //???????????????????????????
        List<Map<String, Object>> buttons = this.baseMapper.getButtonByAppCode(appCode);

        buttons.forEach(button -> {
            //???????????????????????????????????????????????????,????????????,???????????????
            button.put("enable", resourceUrls.contains(button.get("resourceUrl")));
        });
        return buttons;
    }


}
