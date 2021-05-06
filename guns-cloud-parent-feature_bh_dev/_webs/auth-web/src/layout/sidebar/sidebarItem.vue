<template>
  <div class="menu-wrapper">
    <template v-for="(item,index) in menus">
      <el-menu-item v-if="item.children.length===0 " :index="item.id" @click="open(item)" :key="item.id">
        <i class="iconfont" :class="item.icon"  v-if="item.icon"></i>
        <i class="el-icon-location" v-else></i>
        <span slot="title">{{item.name}}</span>
      </el-menu-item>
      <el-submenu v-else :index="item.id" :key="item.id">
        <template slot="title">
          <i class="iconfont" :class="item.icon"  v-if="item.icon"></i>
          <i class="el-icon-location" v-else></i>
          <span slot="title" :class="{'el-menu--display':isCollapse}">{{item.name}}</span>
        </template>
        <template v-for="(child,cindex) in item.children">
          <el-menu-item :index="child.id" @click="open(child)" v-if="child.children.length==0" :key="child.id">
            <i class="iconfont" :class="child.icon"  v-if="child.icon"></i>
            <i class="el-icon-location" v-else></i>
            <span slot="title">{{child.name}}</span>
          </el-menu-item>
          <sidebar-item v-else :menus="[child]" :key="cindex" :isCollapse="isCollapse"></sidebar-item>
        </template>
      </el-submenu>
    </template>
  </div>
</template>

<script>
  import StorageHandler from '@/utils/StorageHandler'
  export default {
    name: 'SidebarItem',
    data() {
      return {
        storageHandler: new StorageHandler()
      }
    },
    props: {
      menus: {
        type: Array
      },
      isCollapse: {
        type: Boolean
      }
    },
    created() {},
    mounted() {},
    computed: {},
    methods: {
      open(item) {
        let reqUrl = item.code;
        if (item.url.indexOf("http") != -1 || item.url.indexOf("https") != -1) {
          reqUrl = `/myiframe/urlPath?src=${reqUrl}&name=${item.name}`;
        }
        this.$router.push({
          path: reqUrl,
          query: item.query
        })
      }
    }

  }
</script>
<style lang="scss">
</style>

