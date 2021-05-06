package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompApp;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompanyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntJobParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompanyResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntJobResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntCompanyService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 企业信息控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "每日下载任务", path = "/entJob")
@Api(tags = "下载任务")
public class RepJobController {

    @Autowired
    private EntJobService entJobService;

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @GetResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntJobResult.class)
    public ResponseData queryListPage(EntJobParam param) {
        PageResult<EntJobResult> pageBySpec = entJobService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

}
