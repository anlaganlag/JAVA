<template>
  <div>
    <el-dialog :title="title" :width="width"
               :visible.sync="isShowVisible"
               center :close-on-click-modal="false"
               :before-close="() => $emit('close')">
      <el-card>
        <el-form label-width="115px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="数据库名称:">
                <span>{{dsData.dbName}}</span>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="数据库驱动:">
            <span>{{dsData.jdbcDriver}}</span>
          </el-form-item>
          <el-form-item label="JDBC:">
            <span>{{dsData.jdbcUrl}}</span>
          </el-form-item>
          <el-form-item label="账号:">
            <span>{{dsData.userName}}</span>
          </el-form-item>
          <el-form-item label="密码:">
            <span>{{dsData.password}}</span>
          </el-form-item>
          <el-form-item label="备注:">
            <span>{{dsData.remarks}}</span>
          </el-form-item>
          <el-form-item label="创建时间:">
            <span>{{dsData.createTime}}</span>
          </el-form-item>
        </el-form>
      </el-card>
      <div slot="footer" class="dialog-footer">
        <el-button @click="$emit('close')">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import DbManageApi from "@/api/systemManage/DbManageApi"

export default {
  name: "ds_manage_detail",
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
      title: '数据源详情',
      isShowVisible: true,
      formLoading: false, //formLoading动画
      dsData: {},
    }
  },
  async created() {
    this.formLoading = true
    let https = new DbManageApi()
    let dsData = await https.detail({id: this.dsId})
    this.dsData = dsData.data
    this.formLoading = false
  },
}
</script>

<style scoped>

</style>
