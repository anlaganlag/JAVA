package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntAbnormalParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompanyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntTaskAuditParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompanyResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntTaskAuditResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntTaskAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 企业信息控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "settlement报告审核", path = "/entTaskAudit")
@Api(tags = "settlement报告审核")
public class EntTaskAuditController {

    @Autowired
    private EntTaskAuditService entTaskAuditService;

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @GetResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntTaskAuditResult.class)
    public ResponseData queryListPage(EntTaskAuditParam param) {
        PageResult<EntTaskAuditResult> pageBySpec = entTaskAuditService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

    /**
     * 分页查询列表
     *employee
     * @author stylefeng
     * @Date 2019-10-10
     */
    @GetResource(name = "settlement报告记录", path = "/queryRecord")
    @ApiOperation(value = "settlement报告记录d", response = EntTaskAuditResult.class)
    public ResponseData queryRecord(EntTaskAuditParam param) {
        PageResult<EntTaskAuditResult> pageBySpec = entTaskAuditService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

    /**
     * 禁用启用
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @GetResource(name = "是否审核", path = "/changeAuditStatus")
    @ApiOperation("是否审核")
    public ResponseData changeAuditStatus(@RequestParam Long Id,@RequestParam(value="auditstatus",required = false,defaultValue = "已审核") String auditstatus) {
        this.entTaskAuditService.changeAuditStatus(Id,auditstatus);
        return ResponseData.success();
    }


}
