<template>
  <el-dialog title="更换主题" :before-close="closeDialog" :visible.sync="isShowDialog"
             :width="width" center :close-on-click-modal="false">
    <el-card>
      <div style="display: flex">
        <el-button style="margin:0 20px 20px 0; background-color: #6BC3E1; border: none" type="primary"
                   @click="handleChangeTheme('#4E9EC5', '#6BC3E1')">主题一
        </el-button>
        <el-button style="margin:0 20px 20px 0; background-color: #24262f; border: none" type="primary"
                   @click="handleChangeTheme('#3e464c', '#24262f')">主题二
        </el-button>
        <el-button style="margin:0 20px 20px 0; background-color: #008df7; border: none" type="primary"
                   @click="handleChangeTheme('#0270c1', '#008df7')">主题三
        </el-button>
        <el-button style="margin:0 20px 20px 0; background-color: #f38240; border: none" type="primary"
                   @click="handleChangeTheme('#df6e2c', '#f38240')">主题四
        </el-button>
        <el-button style="margin:0 20px 20px 0; background-color: #91b198; border: none" type="primary"
                   @click="handleChangeTheme('#697d6d', '#91b198')">主题五
        </el-button>
        <el-button style="margin:0 20px 20px 0; background-color: #ff7f9d; border: none" type="primary"
                   @click="handleChangeTheme('#f46f8e', '#ff7f9d')">主题六
        </el-button>
        <el-button style="margin:0 20px 20px 0; background-color: #c65278; border: none" type="primary"
                   @click="handleChangeTheme('#943b58', '#c65278')">主题七
        </el-button>
      </div>
      <div>
        <el-form label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="主颜色:">
                <el-color-picker v-model="mainColor" @change="handleChangeMainTheme"></el-color-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="次要颜色:">
                <el-color-picker v-model="secondColor" @change="handleChangeSecondTheme"></el-color-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="saveTheme">保存</el-button>
      <el-button @click="cancleDialog">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {mapGetters} from 'vuex'
  import StorageHandler from "@/utils/StorageHandler"
  import SystemConfig from "@/config/SystemConfig"
  export default {
    name: "change-theme",
    props: {
      width: {
        type: String,
        default: '60vw'
      },
    },
    computed: {
      ...mapGetters([])
    },
    data() {
      return {
        isShowDialog: true,
        theme: '',
        mainColor:SystemConfig.defaultColor.mainColor,
        secondColor:SystemConfig.defaultColor.secondColor,
        beforMainColor:'',
        beforSecondColor:'',
      }
    },
    created(){
      this.beforMainColor = document.documentElement.style.getPropertyValue('--mainThemeColor')
      this.beforSecondColor = document.documentElement.style.getPropertyValue('--secondThemeColor')
      this.mainColor = this.beforMainColor
      this.secondColor = this.beforSecondColor
    },
    methods: {
      closeDialog() {
        this.$emit('close')
      },
      //选择主题
      handleChangeTheme(mainThemeColor, secondThemeColor){
        this.mainColor = mainThemeColor
        this.secondColor = secondThemeColor
        document.documentElement.style.setProperty('--mainThemeColor', mainThemeColor)
        document.documentElement.style.setProperty('--secondThemeColor', secondThemeColor)
      },
      handleChangeMainTheme(val) {
        document.documentElement.style.setProperty('--mainThemeColor', val)
      },
      handleChangeSecondTheme(val){
        document.documentElement.style.setProperty('--secondThemeColor', val)
      },
      //保存
      saveTheme() {
        let storageHandler = new StorageHandler()
        storageHandler.setSessionStorage('--mainThemeColor', this.mainColor)
        storageHandler.setSessionStorage('--secondThemeColor', this.secondColor)
        this.closeDialog()
      },
      //取消
      cancleDialog() {
        document.documentElement.style.setProperty('--mainThemeColor', this.beforMainColor)
        document.documentElement.style.setProperty('--secondThemeColor', this.beforSecondColor)
        this.closeDialog()
      },
    },
  }
</script>

<style scoped>

</style>
