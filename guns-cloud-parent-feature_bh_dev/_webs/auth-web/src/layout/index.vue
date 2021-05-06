<template>
  <el-container class="sj-container">
    <sj-sidebar></sj-sidebar>
    <el-container class="sj-content" direction="vertical">
      <!-- 左侧导航栏 -->
      <sj-header></sj-header>
      <el-main class="sj-main">
        <el-scrollbar class="sj-main-scrollbar" wrap-style="overflow-x: hidden; overflow-y: auto;" style="height: 100%;">
          <!-- 主体视图层 -->
          <keep-alive>
            <transition name="fade-transverse">
              <router-view class="sj-view" v-if="$route.meta.keepAlive"/>
            </transition>
          </keep-alive>
          <transition name="fade-transverse">
            <router-view class="sj-view" v-if="!$route.meta.keepAlive"/>
          </transition>
        </el-scrollbar>
      </el-main>
    </el-container>

  </el-container>
</template>

<script>
  import SjHeader from './header/index'
  import SjSidebar from './sidebar/index'
  import StorageHandler from "../utils/StorageHandler"

  export default {
    components: {SjHeader, SjSidebar},
    name: "index",
    data() {
      return {};
    },
    created(){},
    mounted() {
    },
    computed: {},
    props: [],
    methods: {}
  };
</script>

<style lang="scss">
  .sj-main-scrollbar{
    .is-horizontal{
      display: none;
    }
    .el-scrollbar__view{
      height: 100%;
    }
  }
</style>
