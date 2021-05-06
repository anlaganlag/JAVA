<template>
  <el-dialog :title="title" :width="width"
             :visible.sync="isShowVisible"
             center :close-on-click-modal="false"
             :before-close="() => $emit('close')">
    <el-card>
      <el-form label-width="105px" :model="passwordData" ref="passwordData" :rules="rules">
        <el-form-item label="旧密码:" prop="oldPassword" id="oldPassword">
          <el-input v-model="passwordData.oldPassword" type="password"></el-input>
        </el-form-item>
        <el-form-item label="新密码:" prop="newPassword" id="newPassword">
          <el-input v-model="passwordData.newPassword" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" prop="repeatPassword" id="repeatPassword">
          <el-input v-model="passwordData.repeatPassword" type="password"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="savePassword">确认修改</el-button>
      <el-button @click="$emit('close')">取消</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import UserApi from '@/api/UserApi'
  export default {
    name: 'modify_password',
    props:{
      width:{
        type:String,
        default:'60vw'
      },
    },
    data(){
      return{
        title:'修改密码',
        isShowVisible:true,
        rules:{
          oldPassword: [{required: true, message: '请填写旧密码', tirgger: 'blur'}],
          newPassword: [
            {required: true, message: '请填写新密码', tirgger: 'blur'},
            {min: 6, message: "密码长度最少为6位", trigger: "blur"},
          ],
          repeatPassword: [
            {required: true, message: '请确认密码', tirgger: 'blur'},
            {min: 6, message: "密码长度最少为6位", trigger: "blur"},
          ],
        },
        passwordData:{},
      }
    },
    methods:{
      savePassword(){
        this.$refs['passwordData'].validate((valid, object) => {
          if (valid) {
            if(this.passwordData.newPassword === this.passwordData.oldPassword){
              this.$message.error('新老密码不能一致!')
            }else{
              if(this.passwordData.newPassword === this.passwordData.repeatPassword){
                const loading = this.$loading({
                  lock: true,
                  text: '请耐心等待.....',
                  spinner: 'el-icon-loading',
                  background: 'rgba(0, 0, 0, 0.7)'
                })
                let http = new UserApi()
                http.updatePassword(this.passwordData).then(res => {
                  loading.close()
                  if(res.code === 200){
                    this.$message.success('修改成功!')
                    this.$emit('close')
                  }else{
                    this.$message.error('修改失败!')
                  }
                }).catch(e => {
                  loading.close()
                })
              }else{
                this.$message.error('两次输入的新密码必须一致!')
              }
            }
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
    }
  }
</script>

<style scoped>

</style>
