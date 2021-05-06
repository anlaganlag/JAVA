package cn.stylefeng.guns.cloud.system.modular.sys.mapper;

import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysArea;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysAreaParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysAreaResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基础字典 Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface SysAreaMapper extends BaseMapper<SysArea> {

    /**
     * 获取列表
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    List<SysAreaResult> customList(@Param("paramCondition") SysAreaParam paramCondition);

    /**
     * 获取map列表
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") SysAreaParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    Page<SysAreaResult> customPageList(@Param("page") Page page, @Param("paramCondition") SysAreaParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") SysAreaParam paramCondition);

}
