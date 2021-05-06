<template>
    <div>
      <el-dialog :title="title" :width="width"
                 :visible.sync="isShowVisible"
                 center :close-on-click-modal="false"
                 :before-close="() => $emit('close')">
        <el-card v-loading="isLoading">
          <el-form label-width="125px" :model="abnormal" ref="abnormal" :rules="rules">
            <el-row>
              <el-col :span="12">
                <el-form-item label="平台:" prop="platname" id="platname">
                  <el-input v-model="abnormal.platname"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="店铺:" prop="shopname" id="shopname">
                  <el-input v-model="abnormal.shopname"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="站点:" prop="site" id="site">
                  <el-input v-model="abnormal.site"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="币种:" prop="currency" id="currency">
                  <el-input v-model="abnormal.currency"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="开始时间:" prop="startdate" id="startdate">
                  <el-date-picker
                    v-model="abnormal.startdate"
                    type="date"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
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
  export default {
    name: 'person_addEdit',
    props:{
      width:{
        type:String,
        default:'60vw'
      },
      userId:{
        type:String,
      },
    },
    data(){
      return{
        title:'店铺初始新增',
        isShowVisible:true,
        isLoading:false,
        rules:{
          platname: [{required: true, message: '请填写平台', tirgger: 'blur'}],
          shopname: [{required: true, message: '请填写店铺', tirgger: 'blur'}],
          site: [{required: true, message: '请填写站点', tirgger: 'blur'}],
          currency: [{required: true, message: '请填写币别', tirgger: 'change'}],
          startdate: [{required: true, message: '请选择开始时间', tirgger: 'change'}],
        },
        abnormal:{
          id:1,
          platname:null,
          shopname:null,
          site:null,
         // startdate:null,
          enddate:null,
          depositdate:null,
          currency:null,
          status:null,
          handlestatus:null,
          remark:null,
          createtime:null,
          updateTime:null,
          fileuploadpath:null,
        },
      }
    },
    methods:{
      saveAbnormal(){
        this.$refs['abnormal'].validate((valid, object) => {
          if (valid) {
            const loading = this.$loading({
              lock: true,
              text: '请耐心等待.....',
              spinner: 'el-icon-loading',
              background: 'rgba(0, 0, 0, 0.7)'
            })
            let http = new CompanyManageApi()
            http.AbnormalAdd(this.abnormal).then(res => {
                loading.close()
                if(res.code === 200){
                  this.$message.success('保存成功!')
                  this.$emit('close')
                }else{
                  this.$message.error('保存失败!')
                }
              }).catch(e => {
                loading.close()
              })
          } else {
            let validArr = []
            Object.keys(object).forEach(function (key) {
              validArr.push(key)
            });
            let validVal = "#" + validArr[0];
            document.querySelector(validVal).scrollIntoView(true);
          }
        })
      },
    },
  }
</script>

<style scoped>

</style>
