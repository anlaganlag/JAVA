<template>
  <div>
    <el-dialog :title="title" :width="width"
               :visible.sync="isShowVisible"
               center :close-on-click-modal="false"
               :before-close="() => $emit('close')">
      <el-card>
        <el-form label-width="200px">
          <el-form-item label="租户名称:">
            <span>{{tenantData.name}}</span>
          </el-form-item>
          <el-form-item label="租户编码:">
            <span>{{tenantData.code}}</span>
          </el-form-item>
          <el-form-item label="关联的数据库名称:">
            <span>{{tenantData.dbName}}</span>
          </el-form-item>
          <el-form-item label="创建时间:">
            <span>{{tenantData.createTime}}</span>
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
import TenantManageApi from "@/api/systemManage/TenantManageApi"

export default {
  name: "tenant_manage_detail",
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
      title: '租户详情',
      isShowVisible: true,
      formLoading: false, //formLoading动画
      tenantData: {},
    }
  },
  async created() {
    this.formLoading = true
    let https = new TenantManageApi()
    let tenantData = await https.detail(this.id)
    this.tenantData = tenantData.data
    this.formLoading = false
  },
}
</script>

<style scoped>

</style>
