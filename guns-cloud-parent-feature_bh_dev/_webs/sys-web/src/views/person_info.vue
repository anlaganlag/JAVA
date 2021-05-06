<template>
    <div>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-card>
            <div style="width: 100%; margin: 20px auto">
              <img-upload v-model="userData.avatarUrl" :isDetail="!isEdit" style="text-align: center"></img-upload>
            </div>
            <div class="person-userInfo">
              <div class="user-name">{{userData.lastName + userData.firstName}}</div>
              <div class="user-role">超级管理员</div>
              <div class="user-email">
                <i class="el-icon-bell"></i>
                <span>{{userData.email}}</span>
              </div>
              <div class="user-phone">
                <i class="el-icon-mobile-phone"></i>
                <span>{{userData.phone}}</span>
              </div>
              <div class="user-position">
                <i class="el-icon-location-outline"></i>
                <span>中国&nbsp北京市&nbsp朝阳区&nbspxxx街道&nbsp000号</span>
              </div>
            </div>
            <div class="user-tag">标签</div>
            <el-tag>Guns</el-tag>
            <el-tag>Roses</el-tag>
          </el-card>
        </el-col>
        <el-col :span="16">
          <el-card v-loading="isLoading">
            <el-tabs v-model="activeName">
              <el-tab-pane label="基本信息" name="first">
                <el-form label-width="84px" :model="userData" :rules="rules" ref="userData">
                  <el-form-item label="账号:">
                    <span>{{userData.account}}</span>
                  </el-form-item>
                  <el-form-item label="性别:">
                    <span v-show="userData.sex === 'M' && !isEdit">男</span>
                    <span v-show="userData.sex === 'F' && !isEdit">女</span>
                    <el-radio-group v-model="userData.sex" v-show="isEdit">
                      <el-radio label="M">男</el-radio>
                      <el-radio label="F">女</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="邮箱:" prop="email" id="email">
                    <span v-show="!isEdit">{{userData.email}}</span>
                    <el-input v-model="userData.email" v-show="isEdit"></el-input>
                  </el-form-item>
                  <el-form-item label="姓:" prop="lastName" id="lastName">
                    <span v-show="!isEdit">{{userData.lastName}}</span>
                    <el-input v-model="userData.lastName" v-show="isEdit"></el-input>
                  </el-form-item>
                  <el-form-item label="名:" prop="firstName" id="firstName">
                    <span v-show="!isEdit">{{userData.firstName}}</span>
                    <el-input v-model="userData.firstName" v-show="isEdit"></el-input>
                  </el-form-item>
                  <el-form-item label="出生日期:">
                    <span v-show="!isEdit">{{userData.birthday}}</span>
                    <el-date-picker
                      v-show="isEdit"
                      v-model="userData.birthday"
                      type="date"
                      value-format="yyyy-MM-dd"
                      placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="联系电话:">
                    <span v-show="!isEdit">{{userData.phone}}</span>
                    <el-input v-model="userData.phone" v-show="isEdit"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" v-if="!isEdit" @click="editPersonInfo">编辑个人信息</el-button>
                    <el-button type="primary" v-if="isEdit" @click="savePersonInfo">保存</el-button>
                  </el-form-item>
                </el-form>
              </el-tab-pane>
              <!--          <el-tab-pane label="其他" name="second">其他</el-tab-pane>-->
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
</template>

<script>
  import UserApi from '@/api/UserApi'
  export default {
    name: 'person_info',
    data(){
      return{
        activeName:'first',
        isEdit:false,
        userData:{},
        isLoading:false,
        rules:{
          lastName: [{ required: true, message: '请输入姓', trigger: 'blur' }],
          firstName: [{ required: true, message: '请输入名', trigger: 'blur' }],
          email: [
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ],
        },
        avatar:'',
      }
    },
    created(){
      this.isLoading = true
      let http = new UserApi()
      http.getCurrentUser({}).then(res => {
        this.userData = res.data
        // this.userData.avatarUrl = 'http://188.131.253.106:8002/preview/1190640934862196738'
        this.isLoading = false
      })
    },
    methods:{
      //编辑
      editPersonInfo(){
        this.isEdit = true
      },
      //保存
      savePersonInfo(){
        this.$refs['userData'].validate((valid, object) => {
          if (valid) {
            const loading = this.$loading({
              lock: true,
              text: '请耐心等待.....',
              spinner: 'el-icon-loading',
              background: 'rgba(0, 0, 0, 0.7)'
            })
            let http = new UserApi()
            this.userData.name = this.userData.lastName + this.userData.firstName
            http.saveCurrentUser(this.userData).then(res => {
              loading.close()
              if(res.code === 200){
                this.$message.success('保存成功!')
                this.isEdit = false
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
    }
  }
</script>

<style scoped lang="scss">
.person-userInfo{
  width: 100%;
  border-bottom: 1px dashed #747676;
  .user-name{
    width: 100%;
    text-align: center;
    font-size: 18px;
    font-weight: 600;
  }
  .user-role{
    width: 100%;
    text-align: center;
    color: #747676;
  }
  .user-email{
    color: #747676;
  }
  .user-phone{
    color: #747676;
  }
  .user-position{
    color: #747676;
    margin-bottom: 20px;
  }
}
  .user-tag{
    width: 100%;
    font-size: 18px;
    font-weight: 600;
    margin: 20px 0 10px;
  }
</style>
