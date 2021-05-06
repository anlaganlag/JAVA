<template>
  <el-header class="sj-header">
    <div class="sj-toolbar">
      <!-- 图标左半部分 -->
      <div class="sj-header-left">
        <div class="sj-collapse" @click="collapseFlagChange">
          <div class="top-line"></div>
          <i class="el-icon-s-fold" v-show="!collapseFlag"></i>
          <i class="el-icon-s-unfold" v-show="collapseFlag"></i>
        </div>
        <template v-for="item in appList">
          <div class="app-list" v-if="item.appCode !== 'guns-cloud-system'" @click="appJump(item)">
            <div class="top-line"></div>
            <span>
              <i :class="item.appIcon"></i>
              {{item.appName}}
            </span>
          </div>
        </template>
      </div>
      <!-- 图标右半部分 -->
      <div class="sj-header-right">
        <div style="width: 100%;display: flex;align-items: center;justify-content: center">
          <el-tag class="topTag">当前公司</el-tag>
          <span class="color-fff">|</span>
          <el-tag class="topTag">{{userInfo.name}}</el-tag>
          <span class="color-fff">|</span>
          <el-dropdown @command="handlePersonCommand">
            <span class="el-dropdown-link" style="margin: 0">
              <el-tag class="topTag" style="cursor: pointer">个人中心</el-tag>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="gerenxinxi">查看个人信息</el-dropdown-item>
              <el-dropdown-item command="genghuanpifu">更换皮肤</el-dropdown-item>
              <el-dropdown-item command="xiugaimima">修改密码</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span class="color-fff">|</span>
          <el-tooltip class="item" effect="dark" content="全屏" placement="bottom">
            <i class="el-icon-full-screen color-fff" @click="handleScreen"></i>
          </el-tooltip>
          <span class="color-fff">|</span>
          <el-tooltip class="item" effect="dark" content="退出系统" placement="bottom">
<!--            <i class="el-icon-right color-fff" @click="logout"></i>-->
            <i class="iconfont icon-logout color-fff" @click="logout"></i>
          </el-tooltip>
        </div>
      </div>
    </div>
    <!-- 标签页 -->
    <tags></tags>
    <!--更换主题-->
    <change-theme v-if="isShowChangeThemeDialog" @close="closeThemeDialog"></change-theme>
<!--    修改密码-->
    <modify-password v-if="isShowModifyPassword" @close="closeModifyPassword"></modify-password>
  </el-header>
</template>

<script>
  import {mapGetters} from 'vuex'
  import StorageHandler from "@/utils/StorageHandler.js";
  import tags from './tags';
  import changeTheme from '@/layout/change-theme'
  import screenfull from 'screenfull'
  import ModifyPassword from './modify_password'
  import UserApi from '@/api/UserApi'
  import {validateHttpURL} from '@/utils/validate'

  export default {
    name: "index",
    components: {tags, changeTheme, ModifyPassword},
    data() {
      return {
        // collapseFlag: false,
        storageHandler: new StorageHandler(),
        // userInfo: {
        //   name: 'xxx测试员'
        // },
        isShowChangeThemeDialog: false, //控制更换主题弹窗是否显示
        isFullscreen: false,
        isShowModifyPassword: false,//控制修改密码弹窗是否显示
        appList:[],
      };
    },
    created() {
      let http = new UserApi()
      http.appList({pageSize:9999}).then(res => {
        this.appList = res.data.records
      })
    },
    mounted() {
    },
    computed: {
      ...mapGetters(['selectedTags', 'userInfo', 'collapseFlag']),
    },
    props: [],
    methods: {
      collapseFlagChange(){
        this.$store.dispatch('setCollapse', !this.collapseFlag)
      },
      logout() {
        this.$confirm('此操作将退出系统, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.storageHandler.clearSessionStorage()
          window.location.href = `${process.env.VUE_APP_SSO_HOST}/sso/logout?clientId=${process.env.VUE_APP_SSO_APP_ID}`
          // this.$router.push('/login')
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          })
        })
      },
      //关闭更换主题弹窗
      closeThemeDialog() {
        this.isShowChangeThemeDialog = false
      },
      handlePersonCommand(command) {
        if (command == 'gerenxinxi') {
          this.$router.push('/personInfo')
        } else if (command == 'genghuanpifu') {
          this.isShowChangeThemeDialog = true
        } else if (command == 'xiugaimima') {
          this.isShowModifyPassword = true
        }
      },
      //关闭修改密码弹窗
      closeModifyPassword(){
        this.isShowModifyPassword = false
      },
      handleScreen() {
        if (!screenfull.isEnabled) {
          this.$message({
            message: 'you browser can not work',
            type: 'warning'
          })
          return false
        }
        screenfull.toggle()
      },
      appJump(val){
        if(val.appJumpAddress){
          if(validateHttpURL(val.appJumpAddress)){
            window.open(val.appJumpAddress)
          }else {
            this.$message.warning('请联系管理员填写正确的应用url！')
          }
        }else {
          this.$message.warning('请联系管理员配置应用url！')
        }
      },
    }
  };
</script>

<style lang="scss" scoped>
  .sj-header {
    width: 100%;
    height: auto !important;
    background: #ebeff2;

    .sj-toolbar {
      width: 100%;
      height: 60px !important;
      line-height: 60px;

      .sj-header-left {
        float: left;
        /*width: 300px;*/
        height: 100%;
        display: flex;
        .sj-collapse {
          margin-left: 10px;
          /*float: left;*/
          /*width: 50px;*/
          cursor: pointer;
          align-items: center;
          position: relative;
          i {
            font-size: 30px;
            color: #fff;
            vertical-align: middle;
          }
        }
        .sj-collapse:hover{
          .top-line{
            width: 100%;
          }
        }
      }

      /deep/ .sj-header-right {
        float: right;
        height: 100%;
        padding: 0 10px;
        text-align: right;

        .topTag {
          color: white;
          border: none;
        }

        .el-dropdown {
          color: white !important;
        }

        i {
          font-size: 18px;
          margin: 0 5px;
          cursor: pointer;
        }

        span {
          margin: 0 10px;
        }
      }
    }
  }
  .top-line{
    display: block;
    position: absolute;
    width: 0%;
    height: 3px;
    background: white;
    transition: width 0.5s;
  }
  .app-list{
    box-sizing: border-box;
    height: 60px;
    cursor: pointer;
    color: #fff;
    margin-left: 25px;
    font-size: 16px;
    width: fit-content;
    position: relative;
    /*padding: 0 10px;*/
  }
  .app-list:hover{
    .top-line{
      width: 100%;
    }
  }

  .sj-lang-item {
    top: 54px !important;

    .el-dropdown-menu__item {
      padding: 0 10px;

      div {
        width: 60px;
        height: 28px;
        margin-top: 8px;
        text-align: center;
      }
    }
  }

  // 选择应用
  .sj-app-item {
    //padding: 30px 70px;
    top: 68px;
    left: 1031px;
    /*width: 45%;*/

    .el-dropdown-menu__item {
      width: 70px;
      height: 105px;
      float: left;
      margin: 40px;
      //margin-bottom: 56px;
      padding: 0;
      text-align: center;

      .img {
        width: 70px;
        height: 70px;
        //margin-bottom: 18px;
      }
    }
  }

  @media screen and (max-width: 1024px) {
    .sj-app-item {
      //padding: 30px 70px;
      top: 68px;
      left: 1031px;
      /*width: 45%;*/
    }
  }

  .sj-user-item {
    top: 54px !important;

    .el-dropdown-menu__item {
      padding: 0 10px;
      width: 60px;
      height: 28px;
      text-align: center;
    }
  }

  .sj-logo * {
    vertical-align: middle;
  }
  .color-fff{
    color: #fff;
  }
</style>
