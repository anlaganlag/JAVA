<template>
  <div>
    <el-dialog :title="title" :width="width"
               :visible.sync="isShowVisible"
               center :close-on-click-modal="false"
               :before-close="() => $emit('close')">
      <el-card>
        <el-form label-width="200px" :model="tenantData" ref="tenantData" :rules="rules" v-loading="formLoading">
          <el-form-item label="租户名称:" prop="name" id="name">
            <el-input v-model="tenantData.name" placeholder="请输入租户名称"></el-input>
          </el-form-item>
          <el-form-item label="租户编码:" prop="code" id="code">
            <el-input v-model="tenantData.code" placeholder="请输入租户编码"></el-input>
          </el-form-item>
          <el-form-item label="关联的数据库名称:" prop="dbName" id="dbName">
            <el-input v-model="tenantData.dbName" placeholder="请输入租户名称"></el-input>
          </el-form-item>
          <el-form-item v-if="!id" label="租户管理员账号:" prop="adminUsername" id="adminUsername">
            <el-input v-model="tenantData.adminUsername" placeholder="请输入租户管理员账号"></el-input>
          </el-form-item>
          <el-form-item v-if="!id" label="租户管理员密码:" prop="adminPassword" id="adminPassword">
            <el-input v-model="tenantData.adminPassword" placeholder="请输入租户管理员密码"></el-input>
          </el-form-item>
          <el-form-item v-if="id" label="创建时间:" prop="createTime" id="createTime">
            <span>{{tenantData.createTime}}</span>
          </el-form-item>
        </el-form>
      </el-card>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveDs">保存</el-button>
        <el-button @click="$emit('close')">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TenantManageApi from "@/api/systemManage/TenantManageApi"

export default {
  name: "tenant_manage_add_edit",
  props: {
    width: {
      type: String,
      default: '60vw'
    },
    id: {
      type: String,
    },
  },
  data() {
    return {
      title: '新增数据源',
      isShowVisible: true,
      formLoading: false, //formLoading动画
      tenantData: {},
      rules: {
        dbName: [{required: true, message: '请输入数据库名称', tirgger: 'blur'}],
        name: [{required: true, message: '请输入租户名称', tirgger: 'blur'}],
        code: [{required: true, message: '请输入租户编码', tirgger: 'blur'}],
        // adminUsername: [{required: true, message: '请输入租户管理员账号', tirgger: 'blur'}],
        // adminPassword: [{required: true, message: '请输入租户管理员密码', tirgger: 'blur'}],
      }
    }
  },
  created() {
    this.initData()
  },
  methods: {
    //初始化数据
    async initData() {
      this.formLoading = true
      //判断是否有传入id
      let id = this.id
      if (id) {
        //获取详情
        this.title = '修改租户'
        let http = new TenantManageApi();
        let tenantData = await http.detail(id)
        this.tenantData = tenantData.data
      } else {
        this.title = '添加租户'
        this.rules.adminUsername= [{required: true, message: '请输入租户管理员账号', tirgger: 'blur'}]
        this.rules.adminPassword= [{required: true, message: '请输入租户管理员密码', tirgger: 'blur'}]
      }
      this.formLoading = false
    },
    //保存
    saveDs() {
      this.$refs['tenantData'].validate((valid, object) => {
        if (valid) {
          const loading = this.$loading({
            lock: true,
            text: '请耐心等待.....',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          let http = new TenantManageApi()
          //若Id存在证明编辑否则新增
          if (this.id) {
            http.edit(this.tenantData).then(res => {
              loading.close()
              if (res.code === 200) {
                this.$message.success('保存成功!')
                this.$emit('close')
              } else {
                this.$message.error('保存失败!')
              }
            }).catch(e => {
              loading.close()
            })
          } else {
            http.add(this.tenantData).then(res => {
              loading.close()
              if (res.code === 200) {
                this.$message.success('保存成功!')
                this.$emit('close')
              } else {
                this.$message.error('保存失败!')
              }
            }).catch(e => {
              loading.close()
            })
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
  },
}
</script>

<style scoped>

</style>
