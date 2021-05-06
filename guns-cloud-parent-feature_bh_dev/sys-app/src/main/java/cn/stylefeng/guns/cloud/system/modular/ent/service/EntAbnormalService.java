package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntAbnormal;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntJob;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntAbnormalParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntJobParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntAbnormalResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntJobResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;

/**
 * <p>
 * 企业信息 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntAbnormalService extends IService<EntAbnormal> {

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntAbnormalResult> findPageBySpec(EntAbnormalParam param);


    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void add(EntAbnormalParam param) throws ParseException;

}
