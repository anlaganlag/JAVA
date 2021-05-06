package cn.stylefeng.guns.cloud.auth.modular.sso.controller;

import cn.stylefeng.guns.cloud.auth.modular.sso.model.SsoLoginReq;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.SsoService;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 单点登陆
 *
 * @author fengshuonan
 * @Date 2019/9/25 22:02
 */
@Api(tags = "单点登陆")
@Controller
@RequestMapping("/sso")
@Slf4j
public class SsoController {

    @Autowired
    private SsoService ssoService;

    /**
     * SSO登录（从别的应用redirect过来的入口）
     *
     * @author fengshuonan
     * @Date 2019/9/25 22:02
     */
    @ApiOperation("SSO登录（从别的应用redirect过来的入口）")
    @RequestMapping(value = "/login")
    public String ssoLogin(@Validated SsoLoginReq ssoLoginReq) {
        return ssoService.ssoLogin(ssoLoginReq);
    }


    @ApiOperation("SSO登录（获取token）")
    @ResponseBody
    @PostMapping(value = "/getToken")
    public ResponseData ssoGetToken(@RequestBody @Validated SsoLoginReq ssoLoginReq) {
        return ResponseData.success(ssoService.ssoToken(ssoLoginReq));
    }
    /**
     * SSO退出（从别的应用redirect过来的入口）
     *
     * @author fengshuonan
     * @Date 2019/9/25 22:03
     */
    @ApiOperation("SSO退出（从别的应用redirect过来的入口）")
    @RequestMapping(value = "/logout")
    public String ssoLogout(@Validated SsoLoginReq ssoLoginReq) {
        return ssoService.ssoLogout(ssoLoginReq);
    }

}
