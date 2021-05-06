package cn.stylefeng.guns.cloud.system.modular.ent.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("TBDWDailyTask")
public class EntJob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司id
     */
    @TableId(value = "sysDwDailyID", type = IdType.ID_WORKER)
    private Long companyId;

    /**
     * 公司名称
     */
    @TableField("dwdailyTaskName")
    private String name;

    /**
     * 平台
     */
    @TableField("dwPlatName")
    private String dwPlatName;

    /**
     * 店铺
     */
    @TableField("dwShopNameSimple")
    private String dwShopNameSimple;

    /**
     * 站点
     */
    @TableField("dwPlatName")
    private String dwCountryCode;
    /**
     * 站点
     */
    @TableField("dwDlPersonName")
    private String dwDlPersonName;
    /**
     * 站点
     */
    @TableField("dwDlType")
    private String dwDlType;
    /**
     * 站点
     */
    @TableField("dwFileUploadStatus")
    private String dwFileUploadStatus;
    /**
     * 站点
     */
    @TableField("dwFileUploadResult")
    private String dwFileUploadResult;
    /**
     * 站点
     */
    @TableField("dwFileHandleResult")
    private String dwFileHandleResult;
    /**
     * 站点
     */
    @TableField("dwHasData")
    private String dwHasData;


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getDwPlatName() {
        return dwPlatName;
    }

    public void setDwPlatName(String dwPlatName) {
        this.dwPlatName = dwPlatName;
    }

    public String getDwShopNameSimple() {
        return dwShopNameSimple;
    }

    public void setDwShopNameSimple(String dwShopNameSimple) {
        this.dwShopNameSimple = dwShopNameSimple;
    }

    public String getDwCountryCode() {
        return dwCountryCode;
    }

    public void setDwCountryCode(String dwCountryCode) {
        this.dwCountryCode = dwCountryCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDwDlPersonName() {
        return dwDlPersonName;
    }

    public void setDwDlPersonName(String dwDlPersonName) {
        this.dwDlPersonName = dwDlPersonName;
    }

    public String getDwDlType() {
        return dwDlType;
    }

    public void setDwDlType(String dwDlType) {
        this.dwDlType = dwDlType;
    }

    public String getDwFileUploadStatus() {
        return dwFileUploadStatus;
    }

    public void setDwFileUploadStatus(String dwFileUploadStatus) {
        this.dwFileUploadStatus = dwFileUploadStatus;
    }

    public String getDwFileUploadResult() {
        return dwFileUploadResult;
    }

    public void setDwFileUploadResult(String dwFileUploadResult) {
        this.dwFileUploadResult = dwFileUploadResult;
    }

    public String getDwFileHandleResult() {
        return dwFileHandleResult;
    }

    public void setDwFileHandleResult(String dwFileHandleResult) {
        this.dwFileHandleResult = dwFileHandleResult;
    }

    public String getDwHasData() {
        return dwHasData;
    }

    public void setDwHasData(String dwHasData) {
        this.dwHasData = dwHasData;
    }

    @Override
    public String toString() {
        return "EntCompany{" +
                "companyId=" + companyId +
                ", name=" + name +
                "}";
    }
}
