package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysArea;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.SysAreaMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysAreaParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysAreaResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysAreaService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 基础字典 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements SysAreaService {

    @Override
    public void add(SysAreaParam param) {
        SysArea entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SysAreaParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(SysAreaParam param) {
        SysArea oldEntity = getOldEntity(param);
        SysArea newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SysAreaResult findBySpec(SysAreaParam param) {
        return null;
    }

    @Override
    public List<SysAreaResult> findListBySpec(SysAreaParam param) {
        return null;
    }

    @Override
    public PageResult findPageBySpec(SysAreaParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult(page);
    }

    private Serializable getKey(SysAreaParam param) {
        return param.getAreaId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private SysArea getOldEntity(SysAreaParam param) {
        return this.getById(getKey(param));
    }

    private SysArea getEntity(SysAreaParam param) {
        SysArea entity = new SysArea();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
