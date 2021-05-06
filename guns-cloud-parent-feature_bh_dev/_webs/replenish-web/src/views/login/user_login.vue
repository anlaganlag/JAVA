<template>
  <el-form class="login-form" :rules="loginRules" ref="loginForm" :model="loginForm" label-width="0"
           v-loading="isLoginLoading">
    <el-form-item prop="username">
      <el-input @keyup.enter.native="handleLogin" v-model="loginForm.username" auto-complete="off"
                size="large"
                placeholder="请输入用户名">
        <i slot="prefix" class="el-icon-user-solid"></i>
      </el-input>
    </el-form-item>
    <el-form-item prop="password" style="margin-bottom: 20px">
      <el-input @keyup.enter.native="handleLogin" :type="passwordType" v-model="loginForm.password"
                size="large"
                auto-complete="off" placeholder="请输入密码">
        <i slot="prefix" class="el-icon-unlock"></i>
        <i slot="suffix" class="el-icon-view el-input__icon" @click="showPassword"></i>
      </el-input>
    </el-form-item>
    <el-form-item prop="code">
      <el-button type="primary" @click.native.prevent="handleLogin" class="login-submit">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import {isvalidUsername} from "@/utils/validate";
import LoginApi from '@/api/LoginApi'
import NumberUtil from "../../utils/NumberUtil.js";
import StorageHandler from "@/utils/StorageHandler.js";

export default {
  name: "userlogin",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error("请输入正确的用户名"));
      } else {
        callback();
      }
    };
    const validateCode = (rule, value, callback) => {
      if (this.code.value != value) {
        this.loginForm.code = "";
        this.refreshCode();
        callback(new Error("请输入正确的验证码"));
      } else {
        callback();
      }
    };
    return {
      loginApi: new LoginApi(),
      storageHandler: new StorageHandler(),
      loginForm: {
        username: "admin",
        password: "111111",
        code: "",
        redomStr: ""
      },
      checked: false,
      code: {
        src: "",
        value: "",
        len: 4,
        type: "text"
      },
      loginRules: {
        username: [
          /*{ required: true, trigger: "blur", validator: validateUsername }*/
          {required: true, message: "请输入用户名", trigger: "blur"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: "blur"}/*,
                        {min: 6, message: "密码长度最少为6位", trigger: "blur"}*/
        ],
        code: [
          {required: true, message: "请输入验证码", trigger: "blur"},
          {min: 4, max: 4, message: "验证码长度为4位", trigger: "blur"},
          {required: true, trigger: "blur", validator: validateCode}
        ]
      },
      passwordType: "password",
      isLoginLoading: false, //控制登录按钮动画加载
    };
  },
  created() {
    this.refreshCode();
  },
  methods: {
    refreshCode() {
      this.loginForm.redomStr = NumberUtil.randomLenNum(this.code.len, true);
      this.code.type == "text"
        ? (this.code.value = NumberUtil.randomLenNum(this.code.len))
        : (this.code.src = `${this.codeUrl}/${this.loginForm.redomStr}`);
      this.loginForm.code = this.code.value;
    },
    showPassword() {
      this.passwordType == ""
        ? (this.passwordType = "password")
        : (this.passwordType = "");
    },
    //点击登录
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          let http = new LoginApi()
          let data = {
            account: this.loginForm.username,
            password: this.loginForm.password,
          }
          http.getAuthCode(data).then(res => {
            console.log(res)
            let ssoHost = process.env.VUE_APP_SSO_HOST
            let appSsoId = process.env.VUE_APP_SSO_APP_ID
            window.location.href = `${ssoHost}/sso/login?clientId=${appSsoId}&code=${res.data.code}`
          }).catch(e => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
  }
};
</script>

<style lang="scss" scoped>
.mobile-check {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 99;
}

.mobile-check-container {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
  width: 570px;
}

.login-form .el-input .el-input__prefix i {
  font-size: 24px !important;
  line-height: 2;
}

@import "./index";
</style>
