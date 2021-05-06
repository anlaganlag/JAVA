<template>
  <div>
    <el-card>
      <el-form inline>
        <el-form-item label="名称:" class="mgb-0">
          <el-input v-model="name"></el-input>
        </el-form-item>
        <el-form-item class="mgb-0">
          <el-button type="primary" icon="el-icon-search" @click="searchData"></el-button>
          <el-button type="success" icon="el-icon-refresh" @click="clearSearch"></el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card style="margin-top: 10px">
      <div style="margin-bottom: 10px">
        <el-button type="danger" @click="deleteAllBizLog()">清空日志</el-button>
      </div>
      <el-table border :data="dataList" v-loading="tableLoading">
        <el-table-column type="index" label="序号" align="center" width="50"></el-table-column>
        <el-table-column label="名称" prop="name" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作类型" prop="opType" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="是否成功" prop="success" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="具体消息" prop="message" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="ip" prop="ip" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="浏览器" prop="browser" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作系统" prop="os" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作时间" prop="opTime" align="center" show-overflow-tooltip></el-table-column>
      </el-table>
      <el-pagination
        class="sj-pagination"
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
  </div>
</template>

<script>
  import BizLogApi from '@/api/log/BizLogApi.js'

  export default {
    name: "login_log",
    data() {
      return {
        name: '', //名称
        tableLoading: false,
        appList: [],
        dataList: [],
        pageNo: 1, //当前页面
        pageSize: 10, // 当前页数
        total: 0, // 总条数
      }
    },
    created() {
      this.getData()
    },
    methods: {
      //获取表格数据源
      getData() {
        this.tableLoading = true
        let data = {
          name: this.name, //名称
          pageNo: this.pageNo,
          pageSize: this.pageSize
        }
        this.getTableListData(new BizLogApi(), data, 'pageList').then(res => {
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
      searchData() {
        this.pageNo = 1
        this.getData()
      },
      //清空搜索
      clearSearch() {
        this.name = ''
        this.opType = ''
        this.success = ''
      },
      //清空日志
      deleteAllBizLog(){
        this.$confirm('是否清空全部业务日志？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const loading = this.$loading({
            lock: true,
            text: '请耐心等待.....',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          let http = new BizLogApi()
          http.deleteAll({}).then(res => {
            if(res.code === 200){
              this.$message({ type: 'success', message: '清除成功!' })
              this.getData()
            }else{
              this.$message.error(`清除失败!`)
            }
            loading.close()
          }).catch(e => {
            loading.close()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
    }
  }
</script>

<style scoped>

</style>
