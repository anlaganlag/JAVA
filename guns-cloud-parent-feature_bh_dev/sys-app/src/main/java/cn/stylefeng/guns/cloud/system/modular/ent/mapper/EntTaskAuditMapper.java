package cn.stylefeng.guns.cloud.system.modular.ent.mapper;

import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntTaskAudit;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntAbnormalParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntTaskAuditParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntAbnormalResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntTaskAuditResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业信息 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntTaskAuditMapper extends BaseMapper<EntTaskAudit> {

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    Page<EntTaskAuditResult> customPageList(@Param("page") Page page, @Param("paramCondition") EntTaskAuditParam paramCondition);

}
