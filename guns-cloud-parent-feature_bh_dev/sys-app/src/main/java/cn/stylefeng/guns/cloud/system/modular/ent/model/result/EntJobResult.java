package cn.stylefeng.guns.cloud.system.modular.ent.model.result;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 企业信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class EntJobResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String name;

    /**
     * 平台
     */
    @ApiModelProperty("平台")
    private String dwPlatName;

    /**
     * 店铺
     */
    @ApiModelProperty("店铺")
    private String dwShopNameSimple;

    /**
     * 站点
     */
    @ApiModelProperty("站点")
    private String dwCountryCode;

    /**
     * 站点
     */
    @ApiModelProperty("dwDlPersonName")
    private String dwDlPersonName;
    /**
     * 站点
     */
    @ApiModelProperty("dwDlType")
    private String dwDlType;
    /**
     * 站点
     */
    @ApiModelProperty("dwFileUploadStatus")
    private String dwFileUploadStatus;
    /**
     * 站点
     */
    @ApiModelProperty("dwFileUploadResult")
    private String dwFileUploadResult;
    /**
     * 站点
     */
    @ApiModelProperty("dwFileHandleResult")
    private String dwFileHandleResult;
    /**
     * 站点
     */
    @ApiModelProperty("dwHasData")
    private String dwHasData;
}
