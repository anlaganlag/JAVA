<template>
  <div class="sj-tags">
    <!-- tag盒子 -->
    <div class="tags-box" ref="tagBox">
      <div class="tags-list" ref="tagsList" @mousewheel="hadelMousewheel">
        <div ref="tagsPageOpened" class="tag-item" :class="{'is-active':nowTagValue==item.value}" :name="item.value" v-for="(item,index) in selectedTags" :key="index"
             @click="openUrl(item)">
          <i v-if="item.value === '/home'" class="el-icon-s-home"></i>
          <span class="tag-text" :class="{'no-close': item.close != true}">{{item.label}}</span>
          <i class="el-icon-close tag-close" @click.stop="closeTag(item)" v-if="item.close"></i>
        </div>
      </div>
      <el-dropdown class="tags-menu">
        <el-button type="primary" size="mini" class="nav-tags">
          更多
          <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="closeOthersTags">关闭其他</el-dropdown-item>
          <el-dropdown-item @click.native="closeAllTags">关闭全部</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <ul class='contextmenu'>
      <li @click="closeTag(currTag)">关闭</li>
      <li @click="closeOthersTags">关闭其他</li>
      <li @click="closeAllTags">关闭全部</li>
    </ul>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'

  export default {
    name: 'tags',
    data() {
      return {
        visible: false,
        lock: false,
        currTag: {}
      }
    },
    created() {
    },
    mounted() {
      this.init()
    },

    computed: {
      ...mapGetters(['homeTag', 'selectedTags', 'currentTag']),
      nowTagValue: function () {
        return this.$route.path;
      }
    },
    methods: {
      init() {
        this.refsTag = this.$refs.tagsPageOpened
        setTimeout(() => {
          this.refsTag.forEach((item, index) => {
            if (this.currentTag.value === item.attributes.name.value) {
              let tag = this.refsTag[index]
              this.moveToView(tag)
            }
          })
        }, 1)
      },

      hadelMousewheel(e) {
        const step = 0.8 * 90 //一个tag长度
        const boundarystart = 0,
          boundaryend =
            this.$refs.tagsList.offsetWidth - this.$refs.tagBox.offsetWidth + 100
        // Y>0向左滑动
        if (e.deltaY > 0 && this.tagBodyLeft >= -boundaryend) {
          this.tagBodyLeft = this.tagBodyLeft - step
          // Y<0向右滑动
        } else if (e.deltaY < 0 && this.tagBodyLeft < boundarystart) {
          this.tagBodyLeft = this.tagBodyLeft + step
        }
      },

      closeOthersTags() {
        this.$store.commit('DEL_OTHER_TAG')
      },
      closeMenu() {
        this.visible = false
      },
      closeAllTags() {
        this.$store.commit('DEL_ALL_TAG')
        this.$router.push({
          path: this.homeTag.value,
          query: this.homeTag.query
        })
      },
      moveToView(tag) {
        if (tag.offsetLeft < -this.tagBodyLeft) {
          // 标签在可视区域左侧
          this.tagBodyLeft = -tag.offsetLeft + 10
        } else if (
          tag.offsetLeft + 10 > -this.tagBodyLeft &&
          tag.offsetLeft + tag.offsetWidth <
          -this.tagBodyLeft + this.$refs.tagBox.offsetWidth
        ) {
          // 标签在可视区域
        } else {
          // 标签在可视区域右侧
          this.tagBodyLeft = -(
            tag.offsetLeft -
            (this.$refs.tagBox.offsetWidth - 100 - tag.offsetWidth) +
            20
          )
        }
      },
      openUrl(item) {
        this.$router.push({
          path: item.value,
          query: item.query
        })
      },
      eachTag(tag) {
        for (let key in this.selectedTags) {
          if (this.selectedTags[key].value == tag.value) {
            return key
          }
        }
        return -1
      },
      closeTag(item) {
        const key = this.eachTag(item)
        let tag;
        this.$store.commit('DEL_TAG', item)
        if (item.value == this.currentTag.value) {
          tag = this.selectedTags[key == 0 ? key : key - 1]
          this.openUrl(tag)
        }
      }
    },
    watch: {
      $route(to) {
        this.init()
      },

      tagBodyLeft(value) {
        this.$refs.tagsList.style.left = value + 'px'
      }
    }
  }
</script>
<style lang="scss">
  .sj-tags {
    width: 100%;
    height: 40px;
    position: relative;
    box-sizing: border-box;
    overflow: hidden;
    /*box-shadow: 0 1px 4px rgba(51, 51, 51, .15);*/
    .tags-box {
      width: 100%;
      height: 100%;
      position: relative;
      box-sizing: border-box;
      padding-right: 106px;
      border-bottom: 1px solid #499ddb;
    }
    .tags-list {
      position: absolute;
      padding: 2px 10px;
      overflow: visible;
      white-space: nowrap;
      transition: left .3s ease;
    }
    .tag-item {
      position: relative;
      display: inline-block;
      height: 30px;
      line-height: 30px;
      margin: 2px 4px 2px 0;
      padding: 0 10px;
      border-radius: 3px;
      font-size: 12px;
      vertical-align: middle;
      opacity: 1;
      overflow: hidden;
      cursor: pointer;
      background: #fff;
    }
    .tag-text {
      margin-left: 8px;
    }
    .no-close{
      margin-right : 8px;
    }
    .tag-close {
      margin-left: 8px;
    }
    .tag-item:hover {
      opacity: .85;
    }
    .contextmenu{
      left: 0px;
      top: 0px;
    }
    .tags-menu {
      position: absolute;
      top: 0;
      right: 0;
      display: flex;
      align-items: center;
      padding: 0 15px;
      height: 100%;
      box-sizing: border-box;
      background-color: #ebeff2;
      box-shadow: -3px 0 15px 3px rgba(0, 0, 0, 0.1);
    }
  }
</style>


