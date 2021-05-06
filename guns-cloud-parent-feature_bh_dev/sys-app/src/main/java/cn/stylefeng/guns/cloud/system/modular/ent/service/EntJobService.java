package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompApp;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompany;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntJob;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompanyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntJobParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompanyResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntJobResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业信息 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntJobService extends IService<EntJob> {

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntJobResult> findPageBySpec(EntJobParam param);

}
