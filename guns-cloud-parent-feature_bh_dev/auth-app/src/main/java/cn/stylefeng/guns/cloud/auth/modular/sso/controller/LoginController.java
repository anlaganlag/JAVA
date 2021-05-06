package cn.stylefeng.guns.cloud.auth.modular.sso.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.auth.modular.sso.factory.RedirectUrlBuilder;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.AuthCode;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.AuthReq;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.SsoLoginReq;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.AuthService;
import cn.stylefeng.guns.cloud.libs.context.HttpContext;
import cn.stylefeng.guns.cloud.model.exp.AccessCodeException;
import cn.stylefeng.guns.cloud.model.exp.SpsAuthException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 登录退出控制器
 *
 * @author fengshuonan
 * @Date 2019/9/25 22:02
 */
@Api(tags = "登录 退出控制器")
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private AuthService authService;

    /**
     * 登陆页面
     *
     * @author fengshuonan
     * @Date 2019-04-19 14:30
     */
    @ApiOperation("登陆页面")
    @RequestMapping(value = "/loginView", method = RequestMethod.GET)
    public String loginPage(@Validated SsoLoginReq ssoLoginReq) {

        //渲染客户端传来的参数
        HttpContext.getRequest().setAttribute("clientInfo", ssoLoginReq);

        //跳转到登录页面
        return "/sps/login.html";
    }

    /**
     * 登录认证（登陆页面表单提交）
     *
     * @author fengshuonan
     * @Date 2019/4/19 21:07
     */
    @ApiOperation("登录认证（登陆页面表单提交）")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String doAuth( AuthReq authReq) {

        //获取授权码
        AuthCode authCode;
        try {
            authCode = authService.accessCode(authReq.getAccount(), authReq.getPassword());
        } catch (AccessCodeException e) {
            Map<String, Object> paramsMap = BeanUtil.beanToMap(authReq);
            throw new SpsAuthException(e.getAuthExceptionEnum(), paramsMap);
        }

        //渲染到单点登录的地址
        return RedirectUrlBuilder
                .create("/sso/login")
                .add("clientId", authReq.getClientId().toString())
                .add("redirectUrl", authReq.getRedirectUrl())
                .add("code", authCode.getCode())
                .build();
    }

    /**
     * 登录认证（登陆页面表单提交）
     *
     * @author fengshuonan
     * @Date 2019/4/19 21:07
     */
    @ApiOperation("登录认证（登陆页面表单提交2）")
    @RequestMapping(value = "/submit2", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData doAuth2( @RequestBody AuthReq authReq) {

        //获取授权码
        AuthCode authCode;
        try {
            authCode = authService.accessCode(authReq.getAccount(), authReq.getPassword());
        } catch (AccessCodeException e) {
            Map<String, Object> paramsMap = BeanUtil.beanToMap(authReq);
            throw new SpsAuthException(e.getAuthExceptionEnum(), paramsMap);
        }

        return ResponseData.success(authCode);
    }


    /**
     * 退出页面
     *
     * @author fengshuonan
     * @Date 2019/9/25 22:02
     */
    @ApiOperation("退出页面")
    @RequestMapping(value = "/logoutView", method = RequestMethod.GET)
    public String logoutPage(@Validated SsoLoginReq ssoLoginReq, Model model) {
        model.addAttribute("clientInfo", ssoLoginReq);
        return "/sps/logout.html";
    }



}
