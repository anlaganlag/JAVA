<template>
  <div class="login-container pull-height"
       @keyup.enter.native="handleLogin">
    <div>
      <div class="login-main">
        <div class="div_left">
          <div class="title_top">
            <div class="title_main">
              <div class="title_line"></div>
              <h3>BSC补货管理系统</h3>
              <span>BSC SYSTEM PLATFORM</span>
            </div>
          </div>
        </div>
        <div class="div_right">
          <div class="login_top">
            <!--            <img src="../../assets/image/login/loginBGC.jpg"/>-->
          </div>
          <div class="login_center">
            <span style="text-align: center;display: block;font-size: 30px;font-weight: 600;color: rgb(38, 65, 112);">登录 | LOGIN</span>
            <div class="login_content">
              <userLogin></userLogin>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import UserLogin from "@/views/login/user_login";
import CodeLogin from "./code_login";

export default {
  name: "login",
  components: {
    UserLogin,
    CodeLogin
  },
  data() {
    return {};
  },
  created() {
  },
  mounted() {
  },
  props: [],
  methods: {
    /**
     * @description:
     * @param {type}
     * @return:
     */
    doSome() {
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./index";
</style>
