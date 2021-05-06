package cn.stylefeng.guns.cloud.system.modular.sys.service;

import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysArea;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysAreaParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysAreaResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 基础字典 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface SysAreaService extends IService<SysArea> {

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void add(SysAreaParam param);

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void delete(SysAreaParam param);

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void update(SysAreaParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    SysAreaResult findBySpec(SysAreaParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    List<SysAreaResult> findListBySpec(SysAreaParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    PageResult findPageBySpec(SysAreaParam param);

}
