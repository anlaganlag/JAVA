<template>
  <div>

    <el-card>
      <el-form inline>
        <el-form-item label="数据库名称:" class="mgb-0">
          <el-input v-model="searchData.dbName"></el-input>
        </el-form-item>
        <el-form-item class="mgb-0">
          <el-button type="primary" icon="el-icon-search" @click="searchList"></el-button>
          <el-button type="success" icon="el-icon-refresh" @click="clearList"></el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card style="margin-top: 10px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="openAddEdit">新增</el-button>
      </div>

      <el-table border :data="dataList" v-loading="tableLoading" :show-overflow-tooltip="true">
        <el-table-column label="序号" align="center" type="index" width="50"></el-table-column>
        <el-table-column label="数据库名称" prop="dbName" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="Jdbc驱动" prop="jdbcDriver" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="账号" prop="userName" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="Jdbc Url" prop="jdbcUrl" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="创建时间" prop="createTime" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="备注" prop="remarks" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" align="center" width="257">
          <template slot-scope="scope">
            <el-button type="text" @click="openDetail(scope.row.id)">详情</el-button>
            <el-button type="text" @click="openEdit(scope.row.id)">修改</el-button>
            <el-button type="text" style="color: red" @click="deleteDs(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        style="float:right; margin: 10px 0;"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNo"
        :page-sizes="[10, 15, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        prev-text="上一页"
        next-text="下一页"
        :total="total">
      </el-pagination>

    </el-card>

    <!--详情弹窗-->
    <ds-manage-detail v-if="isShowDetail" :dsId="dsId" @close="closeDetail"></ds-manage-detail>

    <!--新增编辑弹窗-->
    <ds-manage-add-edit v-if="isShowAddEdit" :dsId="dsId" @close="closeAddEdit"></ds-manage-add-edit>

  </div>
</template>

<script>
import DbManageApi from "@/api/systemManage/DbManageApi"

export default {
  name: "db_manage",
  components: {
    DsManageDetail: resolve => require(['./components/dsManage_detail'], resolve),
    DsManageAddEdit: resolve => require(['./components/dsManage_addEdit'], resolve),
  },
  data() {
    return {
      searchData: {},
      tableLoading: false, //表格loading动画
      dataList: [],
      pageNo: 1,    //当前页面
      pageSize: 10, // 当前页数
      total: 0,     // 总条数
      isShowAddEdit: false,         //控制新增编辑弹窗是否显示
      isShowDetail: false,         //控制详情弹窗是否显示
    }
  },
  created() {
    this.initData()
  },
  methods: {
    //基础数据渲染
    async initData() {
      this.tableLoading = true;
      this.getData()
    },

    //获取表格数据源
    getData() {
      this.tableLoading = true
      this.searchData.pageNo = this.pageNo
      this.searchData.pageSize = this.pageSize
      this.getTableListData(new DbManageApi(), this.searchData, 'page').then(res => {
        this.dataList = res.dataList
        this.pageNo = res.pageNo
        this.pageSize = res.pageSize
        this.total = res.total
        this.tableLoading = false
      })
    },

    // 当前页码修改时
    handleCurrentChange(pageNo) {
      this.pageNo = pageNo
      this.getData()
    },

    // 当前页数修改时
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.getData()
    },

    //搜索
    searchList() {
      this.pageNo = 1
      this.getData()
    },

    //清空搜索
    clearList() {
      this.searchData.appId = ''
      this.searchData.dbName = ''
      this.searchList()
    },

    //打开新增弹窗
    openAddEdit() {
      this.dsId = ''
      this.isShowAddEdit = true
    },

    //关闭新增编辑弹窗
    closeAddEdit() {
      this.dsId = ''
      this.isShowAddEdit = false
      this.getData()
    },

    //打开编辑弹窗
    openEdit(dsId) {
      this.dsId = dsId
      this.isShowAddEdit = true
    },

    //打开详情弹窗
    openDetail(dsId) {
      this.dsId = dsId
      this.isShowDetail = true
    },
    //关闭详情弹窗
    closeDetail() {
      this.dsId = ''
      this.isShowDetail = false
      this.getData()
    },

    //删除
    deleteDs(dsId) {
      this.$confirm('您确定要删除此项吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        const loading = this.$loading({
          lock: true,
          text: '正在删除.....',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        })
        let http = new DbManageApi()
        http.delete({id: dsId}).then(res => {
          if (res.code === 200) {
            loading.close()
            this.$message.success('删除成功！')
            this.getData()
          } else {
            this.$message.error('删除失败！')
          }
        }).catch(err => {
          loading.close()
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    }
  },
}
</script>

<style scoped>

</style>

