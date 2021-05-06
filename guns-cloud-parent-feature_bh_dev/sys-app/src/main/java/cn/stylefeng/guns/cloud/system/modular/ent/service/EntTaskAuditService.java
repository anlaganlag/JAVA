package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntTaskAudit;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntTaskAuditParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntTaskAuditResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 企业信息 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntTaskAuditService extends IService<EntTaskAudit> {

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntTaskAuditResult> findPageBySpec(EntTaskAuditParam param);

    /**
     * 启用禁用
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    void changeAuditStatus(Long Id, String auditstatus);


}
