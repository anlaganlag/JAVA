<template>
    <div>
      <el-dialog :title="title" :width="width"
                 :visible.sync="isShowVisible"
                 center :close-on-click-modal="false"
                 :before-close="() => $emit('close')">
        <el-card v-loading="isLoading">
          <el-form label-width="125px" :model="abnormal" ref="abnormal" :rules="rules">
            <el-row>
              <el-col :span="18">
                <el-form-item label="店铺:" prop="platname" id="platname">
                  <el-input v-model="shopcode" :disabled="true"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="18">
                <el-form-item label="日期区间:" prop="startdate" id="startdate">
                  <span>{{startdate}}</span>
                  <span>~</span>
                  <span>{{enddate}}</span>
                </el-form-item>
              </el-col>
            </el-row>
          <el-upload
              class="upload-demo"
              ref="upload"
              :data="formdata"
              :headers="headers"
              :on-success="handleSuccess"
              :on-error="handleError"
              :limit="1"
              :multiple="false"
              action="/guns-cloud-system/entAbnormal/AbnormalUpload"
              :auto-upload="false">
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
              <div slot="tip" class="el-upload__tip">请尽量上传涵盖区间范围大一些的数据！</div>
          </el-upload>
          </el-form>
        </el-card>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="saveAbnormal">保存</el-button>
          <el-button @click="$emit('close')">取消</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
  import CompanyManageApi from '@/api/orgBuild/CompanyManageApi'
  import { getToken } from '@/utils/auth'
  import Cookies from 'js-cookie'
  export default {
    name: 'abnormal_deal',
    props:{
      width:{
        type:String,
        default:'30vw'
      },
      userId:{
        type:String,
      },
      shopcode:{
        type:String,
      },
      multipleSelection:{
        type:Array
      }
    },
    data(){
      return{
        title:'异常处理',
        isShowVisible:true,
        isLoading:false,
        rules:{
          site: [{required: true, message: '请填写站点', tirgger: 'blur'}],
        },
        headers:{
        'Authorization':'eyJhbGciOiJIUzUxMiJ9.eyJhY2NvdW50SWQiOjExODQyODU0NDUyMDQ0MTg1NjEsInN1YiI6ImFkbWluIiwiZXhwIjoxNjIwMjkyODUwLCJpYXQiOjE2MjAyODU2NTB9.7OXAuqEPSYlCKR5SIaCuOiwZ-4kQ0mP-xp3T9MNe15Nmu7rBxvObJUrRyno1ApQIziOIAzyYwdykiOiyfMP6zA'
      },
        startdate:"",
        enddate:"",
        abnormal:{
          id:'1',
          platname:null,
         startdate:null,
      }
    }},
    computed:{
        formdata:function(){
          return{
             id: this.abnormal.id
            }
        }
    },
    created(){
      console.log(this.multipleSelection)
      this.startdate=this.multipleSelection[0].startdate.substr(0,10)
      this.enddate=this.multipleSelection[0].enddate.substr(0,10)
      this.multipleSelection.forEach(function (item) {
        if(item.startdate<this.startdate){
          this.startdate=item.startdate.substr(0,10)
        }
        if(item.enddate>this.enddate){
          this.enddate=item.enddate.substr(0,10)
        }
    });

    },
    methods:{
      saveAbnormal(){
          console.log(localStorage.getItem('access_token'));
          this.$refs.upload.submit();
      },
      handleSuccess(response, file, fileList) {
      this.$refs.upload.clearFiles()
      this.$message.success('上传成功!')
      this.$emit('close')
    },
    // 监听上传失败
    handleError(e, file, fileList) {
      const msg = JSON.parse(e.message)
      this.$message.success('上传失败!')
    }
    },
  }
</script>

<style scoped>
.el-input {
  background: white;
}
</style>
