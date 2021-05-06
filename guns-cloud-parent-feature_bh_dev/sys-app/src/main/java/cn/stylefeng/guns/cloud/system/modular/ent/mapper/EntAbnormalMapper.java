package cn.stylefeng.guns.cloud.system.modular.ent.mapper;

import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntAbnormal;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntJob;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntAbnormalParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntJobParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntAbnormalResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntJobResult;
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
public interface EntAbnormalMapper extends BaseMapper<EntAbnormal> {

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    Page<EntAbnormalResult> customPageList(@Param("page") Page page, @Param("paramCondition") EntAbnormalParam paramCondition);

}
