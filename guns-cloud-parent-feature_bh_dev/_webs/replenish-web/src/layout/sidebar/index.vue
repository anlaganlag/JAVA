<template>
  <el-aside class="sj-aside" :class="{'is-collapse': collapseFlag, 'no-collapse': !collapseFlag}">
    <!-- 系统logo -->
    <div class="sj-logo" :class="{'is-collapse': collapseFlag, 'no-collapse': !collapseFlag}">
<!--      <img src="../../../assets/image/header/logo.png">-->
      <span :class="{'is-show': !collapseFlag, 'no-show': collapseFlag}">补货管理系统</span>
    </div>
    <el-menu :default-active="activeIndex"
             mode="vertical"
             style="margin-top: 60px"
             :show-timeout="200"
             text-color="#fff"
             :unique-opened="true"
             active-text-color="#fff"
             :collapse="collapseFlag"
             class="sj-sidebar">
      <sidebar-item :menus="menus"
                    :isCollapse="collapseFlag">
      </sidebar-item>
    </el-menu>
  </el-aside>
</template>

<script>
  import { mapGetters } from "vuex";
  import SidebarItem from './sidebarItem'
  import MenuApi from "@/api/MenuApi";
  import StorageHandler from "@/utils/StorageHandler.js";
  import SystemUtil from "@/utils/SystemUtil";
  export default {
    name: "SjSidebar",
    components: {SidebarItem},
    data() {
      return {
        menuApi: new MenuApi(),
        activeIndex: '',
        storageHandler: new StorageHandler(),
        menus: [],
        appId: '1174270350789906434',
        // collapseFlag:false,
      };
    },
    props: [],
    computed: {
      ...mapGetters(["collapseFlag"])
    },
    created(){},
    mounted(){
      this.initData()
    },
    methods: {
      // 初始化页面数据
      initData(){
        // 获取菜单数据
        this.menuApi.getLeftMenuList({appId: this.appId}).then(res=>{
          if(!_.isEmpty(res.data)){
            this.menus =  SystemUtil.convertMenus(res.data);
            this.$store.dispatch("setMenus", res.data);
          }
        });
        let currentTag = this.storageHandler.getSessionStorage('currentTag');
        if(currentTag){
          this.activeIndex = currentTag.id;
        }
      }
    },
    watch:{
      $route(to) {
        let currentTag = this.storageHandler.getSessionStorage('currentTag');
        if(currentTag){
          this.activeIndex = currentTag.id;
        }
      }
    }
  };
</script>

<style lang="scss" scoped>
  .sj-aside {
    background:white url("../../assets/image/asideImg.png") no-repeat;
    background-position: center 100%;
    background-size: 100%;
    height: 100%;
    width: 250px;
    text-align: left;
    .sj-logo {
      position: absolute;
      top: 0px;
      z-index: 10;
      float: left;
      width: 250px;
      height: 60px;
      line-height: 60px;
      cursor: pointer;
      text-align: center;
      overflow: hidden;
      img {
        height: 40px;
        vertical-align: middle;
      }
      span {
        color: #fff;
        font-size: 20px;
        letter-spacing: 4px;
        margin-left: 14px;
        vertical-align: middle;
      }
    }
    .sj-sidebar{
      width: 100%;
      height: auto;
      margin-top: 60px;
    }
  }

</style>
