<template>
  <div>
    <el-card>
      <el-row :gutter="10">
        <el-col>
          <el-form inline>
            <el-form-item label="任务名称:">
              <el-input v-model="searchData.resourceName"></el-input>
            </el-form-item>
            <el-form-item>
              <label slot="label">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label> 
              <el-input v-model="searchData.shopname"   suffix-icon="el-icon-location-outline" ></el-input>
            </el-form-item>
            <el-form-item>
              <label slot="label">站&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点:</label> 
              <el-input v-model="searchData.site"  suffix-icon="el-icon-location-outline"></el-input>
            </el-form-item>
            <el-form-item label="开始时间:">
              <el-date-picker v-model="searchData.startdate"  value-format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间:">
              <el-date-picker v-model="searchData.enddate" value-format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
          </el-form>
          <el-form inline>
            <el-form-item label="汇款银行:">
              <el-input v-model="searchData.banknum"></el-input>
            </el-form-item>
            <el-form-item label="审核状态:">
              <el-select v-model="searchData.auditstatus" placeholder="请选择审核状态">
                <el-option label="全部" value=""></el-option>
                <el-option label="已审核" value="已审核"></el-option>
                <el-option label="未通过" value="未通过"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="解析状态:">
              <el-select v-model="searchData.handlestatus" placeholder="请选择解析状态">
                <el-option label="全部" value=""></el-option>
                <el-option label="已解析" value="已解析"></el-option>
                <el-option label="未解析" value="未解析"></el-option>
                <el-option label="解析失败" value="解析失败"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item   style="margin-left:72px ">
              <el-button type="primary" @click="searchList">&nbsp;&nbsp;搜&nbsp;&nbsp;&nbsp;&nbsp;索&nbsp;&nbsp;</el-button>
              <el-button type="warning" @click="clearList">清空搜索</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-table border :data="dataList" v-loading="tableLoading" class="resource-table">
        <el-table-column type="index" label="序号" align="center" width="50"></el-table-column>
        <el-table-column label="源仓任务名称" prop="taskname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="平台" width="90" prop="platname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="账号" width="60" prop="shopname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="站点" width="60" prop="site" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="开始时间" width="110" prop="startdate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="结束时间" width="110" prop="enddate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="币种" width="60" prop="currency" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="汇款日期" width="110" prop="depositdate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="汇款银行" width="110" prop="banknum" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="金额" width="80" prop="amount" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="总金额" width="80" prop="totalamount" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="状态" width="80" prop="auditstatus" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="解析状态" width="80" prop="handlestatus" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="原因" width="120" prop="remark" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="审核人员" width="60" prop="personname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" align="center" width="150">
          <template slot-scope="scope" v-if="scope.row.auditstatus == ''">
            <el-button type="text"  @click="disEnable(scope.row.id, '已审核','确定审核通过吗？')">审核通过</el-button>
            <el-button type="text"  @click="disEnable(scope.row.id, '未通过','确定审核不通过吗？')">不通过</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        style="text-align: right; margin-top: 10px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="[10, 15, 20, 50]"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        prev-text="上一页"
        next-text="下一页"
        :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

import CompanyManageApi from '@/api/orgBuild/CompanyManageApi'
export default {
  name: 'home',
  data(){
    return{
      searchData:{
        appId:'', //应用id
        resourceName:'', //任务名称
        shopname:'', //账号
        site:'', //站点
        startdate:'', //开始时间
        enddate:'', //结束时间
        banknum:'', //汇款银行
        auditstatus:'', //审核状态
        handlestatus:'' //解析状态
      },
      activeName:'N',//页面资源 -- N是api资源
      name:'',
      taskname:'',
      code:'',
      tableLoading:false,
      dataList:[],
      page: 1, //当前页面
      pageSize: 10, // 当前页数
      totalRows: 0, // 总条数
      current: 1, //当前页面
      size: 10, // 当前页数
      total: 0, // 总条数
    }
  },
  created(){
    this.getData()
  },
  methods:{
    //获取表格数据源
    getData(){
      let http = new CompanyManageApi()
      this.tableLoading = true
      let data = {
        taskname:this.searchData.resourceName,
        shopname:this.searchData.shopname,
        site:this.searchData.site,
        startdate:this.searchData.startdate,
        enddate:this.searchData.enddate,
        auditstatus:this.searchData.auditstatus,
        handlestatus:this.searchData.handlestatus,
        banknum:this.searchData.banknum,
        code:this.code,
        pageSize:this.pageSize,
        page:this.current,
      }
      http.queryTaskAuditList(data).then(res => {
        this.dataList = res.data.rows
        this.page = res.data.page
        this.pageSize = res.data.pageSize
        this.total = res.data.totalRows
        this.tableLoading = false
      })
    },
    // 当前页码修改时
    handleCurrentChange(pageNo) {
      this.current = pageNo
      this.getData()
    },
    // 当前页数修改时
    handleSizeChange(pageSize) {
      this.size = pageSize
      this.getData()
    },
    //搜索
    searchList() {
      this.current = 1
      this.getData()
    },
    //清空搜索
    clearList() {
      this.searchData.appId = ''
      this.searchData.resourceName = ''
      this.searchData.shopname=''
      this.searchData.site=''
      this.searchData.startdate=''
      this.searchData.enddate=''
      this.searchData.banknum=''
      this.searchData.auditstatus=''
      this.searchData.handlestatus=''
      this.getData()
    },
    //切换tab标签
    handleClick(val){
      this.clearList()
      this.current = 1
      this.getData()
    },
    //禁用或启用
    disEnable(Id, auditstatus, msg){

        this.$confirm(msg, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
      const loading = this.$loading({
        lock: true,
        text: `${auditstatus}中，请耐心等待.....`,
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      let http = new CompanyManageApi()
      http.changeAuditStatus({Id, auditstatus}).then(res => {
        this.$message.success(`${auditstatus}成功!`)
        loading.close()
        this.getData()
      }).catch(e => {
        loading.close()
      })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消审核'
          });          
        });
    },
  }
}
</script>

<style lang="scss" scoped>
.home-img{
  width: 100%;
  height: 100%;
  background: #fff;
  padding-top: 200px;
  img{
    display: block;
    margin: 0px auto;
  }
}
</style>

