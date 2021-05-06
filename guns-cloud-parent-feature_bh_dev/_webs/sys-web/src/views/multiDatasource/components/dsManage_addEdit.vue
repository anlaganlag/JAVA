<template>
  <div>
    <el-dialog :title="title" :width="width"
               :visible.sync="isShowVisible"
               center :close-on-click-modal="false"
               :before-close="() => $emit('close')">
      <el-card>
        <el-form label-width="115px" :model="dsData" ref="dsData" :rules="rules" v-loading="formLoading">
          <el-form-item label="数据库名:" prop="dbName" id="dbName">
            <el-input v-model="dsData.dbName" placeholder="请输入数据库名"></el-input>
          </el-form-item>
          <el-form-item label="数据库驱动:" prop="jdbcDriver" id="jdbcDriver">
            <el-input v-model="dsData.jdbcDriver" placeholder="请输入数据库驱动"></el-input>
          </el-form-item>
          <el-form-item label="JDBC:" prop="jdbcUrl" id="jdbcUrl">
            <el-input v-model="dsData.jdbcUrl" placeholder="请输入JDBC URL"></el-input>
          </el-form-item>
          <el-form-item label="数据库账号:" prop="userName" id="userName">
            <el-input v-model="dsData.userName" placeholder="请输入数据库账号"></el-input>
          </el-form-item>
          <el-form-item label="数据库密码:" prop="password" id="password">
            <el-input v-model="dsData.password" placeholder="请输入数据库密码"></el-input>
          </el-form-item>
          <el-form-item label="备注:">
            <el-input type="textarea" :autosize="{ minRows: 2}" placeholder="请输入备注" v-model="dsData.remarks"></el-input>
          </el-form-item>
          <el-form-item v-if="dsId" label="创建时间:" prop="createTime" id="createTime">
            <span>{{dsData.createTime}}</span>
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
import DbManageApi from "@/api/systemManage/DbManageApi"

export default {
  name: "ds_manage_add_edit",
  props: {
    width: {
      type: String,
      default: '60vw'
    },
    dsId: {
      type: String,
    },
  },
  data() {
    return {
      title: '新增数据源',
      isShowVisible: true,
      formLoading: false, //formLoading动画
      dsData: {},
      rules: {
        dbName: [{required: true, message: '请输入数据库名称', tirgger: 'blur'}],
        jdbcDriver: [{required: true, message: '请输入JDBC驱动', tirgger: 'blur'}],
        jdbcUrl: [{required: true, message: '请输入JDBC URL', tirgger: 'blur'}],
        userName: [{required: true, message: '请输入数据库用户', tirgger: 'blur'}],
        password: [{required: true, message: '请输入数据库密码', tirgger: 'blur'}],
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
      let dsId = this.dsId
      if (dsId) {
        //获取详情
        this.title = '修改数据源'
        let http = new DbManageApi();
        let dsData = await http.detail({id: dsId})
        this.dsData = dsData.data
      } else {
        this.title = '添加数据源'
      }
      this.formLoading = false
    },
    //保存
    saveDs() {
      this.$refs['dsData'].validate((valid, object) => {
        if (valid) {
          const loading = this.$loading({
            lock: true,
            text: '请耐心等待.....',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          let http = new DbManageApi()
          //若dsId存在证明编辑否则新增
          if (this.dsId) {
            http.edit(this.dsData).then(res => {
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
            http.add(this.dsData).then(res => {
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
