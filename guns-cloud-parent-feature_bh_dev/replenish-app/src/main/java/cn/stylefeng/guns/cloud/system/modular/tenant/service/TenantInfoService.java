package cn.stylefeng.guns.cloud.system.modular.tenant.service;

import cn.stylefeng.guns.cloud.system.modular.tenant.entity.TenantInfo;
import cn.stylefeng.guns.cloud.system.modular.tenant.params.TenantInfoParam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 租户表 服务类
 *
 * @author stylefeng
 * @since 2019-06-16
 */
public interface TenantInfoService extends IService<TenantInfo> {

    /**
     * 新增租户
     *
     * @param param 添加参数
     * @author stylefeng
     * @date 2019-06-16
     */
    void add(TenantInfoParam param);

    /**
     * 删除租户
     *
     * @param param 删除参数
     * @author stylefeng
     * @date 2019-06-16
     */
    void delete(TenantInfoParam param);

    /**
     * 更新租户
     *
     * @param param 更新参数
     * @author stylefeng
     * @date 2019-06-16
     */
    void update(TenantInfoParam param);

    /**
     * 分页查询租户列表
     *
     * @param param 查询参数
     * @return 查询结果
     * @author fengshuonan
     * @date 2020/9/3
     */
    Page<TenantInfo> page(TenantInfoParam param);

    /**
     * 获取租户信息，通过租户编码
     *
     * @param code 租户编码
     * @return 租户信息
     * @author fengshuonan
     * @date 2019-06-19 14:17
     */
    TenantInfo getByCode(String code);

}
