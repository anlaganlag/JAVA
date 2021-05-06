package cn.stylefeng.guns.cloud.system.modular.ent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 * 企业信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@TableName("TbSettlementAbnormal")
public class EntAbnormal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    @TableField("id")
    private int id;

    /**
     * platname
     */
    @TableField("platname")
    private String platname;

    /**
     * shopname
     */
    @TableField("shopname")
    private String shopname;

    /**
     * site
     */
    @TableField("site")
    private String site;

    /**
     * start_date
     */
    @TableField("start_date")
    private String startdate;

    /**
     * end_date
     */
    @TableField("end_date")
    private String enddate;

    /**
     * deposit_date
     */
    @TableField("deposit_date")
    private String depositdate;


    /**
     * currency
     */
    @TableField("currency")
    private String currency;
    /**
     * status
     */
    @TableField("status")
    private String status;

    /**
     * handle_status
     */
    @TableField("handle_status")
    private String handlestatus;

    /**
     * remark
     */
    @TableField("remark")
    private String remark;

    /**
     * create_time
     */
    @TableField("create_time")
    private String createtime;

    /**
     * update_time
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * update_time
     */
    @TableField("fileuploadpath")
    private String fileuploadpath;

    /**
     * update_time
     */
    @TableField("blFile")
    private MultipartFile blFile;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatname() {
        return platname;
    }

    public void setPlatname(String platname) {
        this.platname = platname;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(String depositdate) {
        this.depositdate = depositdate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandlestatus() {
        return handlestatus;
    }

    public void setHandlestatus(String handlestatus) {
        this.handlestatus = handlestatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFileuploadpath() {
        return fileuploadpath;
    }

    public void setFileuploadpath(String fileuploadpath) {
        this.fileuploadpath = fileuploadpath;
    }

    public MultipartFile getBlFile() {
        return blFile;
    }

    public void setBlFile(MultipartFile blFile) {
        this.blFile = blFile;
    }
}
