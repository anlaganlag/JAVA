<template>
  <div>
    <el-card>
      <el-row :gutter="10">
        <el-col>
          <el-form inline>
            <el-form-item>
              <label slot="label">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label> 
              <el-input v-model="searchData.shopname" ></el-input>
            </el-form-item>
            <el-form-item>
              <label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;站&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点:</label> 
              <el-input v-model="searchData.site"></el-input>
            </el-form-item>
            <el-form-item>
              <label slot="label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;汇款银行:</label> 
              <el-input v-model="searchData.banknum"></el-input>
            </el-form-item>
          </el-form>
          <el-form inline>
            <el-form-item label="开始时间:">
              <el-date-picker v-model="searchData.startdate"  value-format=" yyyy-MM-dd"></el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间:">
              <el-date-picker v-model="searchData.enddate" value-format=" yyyy-MM-dd"></el-date-picker>
            </el-form-item>
            <el-form-item   style="margin-left:72px ">
              <el-button type="primary" @click="searchList">&nbsp;&nbsp;搜&nbsp;&nbsp;索&nbsp;&nbsp;</el-button>
              <el-button type="warning" @click="clearList">清空搜索</el-button>
            </el-form-item>
          </el-form>
            <el-form-item>
              <el-button type="primary" @click="searchList">搜索</el-button>
              <el-button type="success" @click="clearList">清空搜索</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-table border :data="dataList" v-loading="tableLoading" class="resource-table">
        <el-table-column type="index" label="序号" align="center" width="50"></el-table-column>
        <el-table-column label="平台" width="80" prop="platname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="账号" width="80" prop="shopname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="站点" width="80" prop="site" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="开始时间" width="200" prop="startdate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="结束时间" width="200" prop="enddate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="汇款日期" width="200" prop="depositdate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="币种"  prop="currency" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="回款银行" width="200" prop="banknum" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="amount" width="200" prop="amount" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="total-amount" width="200" prop="totalamount" align="center" show-overflow-tooltip></el-table-column>
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
      resourceName:'', //资源名称
      shopname:'', //账号
      site:'', //站点
      startdate:'', //开始时间
      enddate:'', //结束时间
      banknum:'', //汇款银行
    },
    activeName:'N',//页面资源 -- N是api资源
    name:'',
    code:'',
    tableLoading:false,
    dataList:[],
    page: 1, //当前页面
    pageSize: 10, // 当前页数
    totalRows: 0, // 总条数
    companyId:'',
    isShowCompanyAddEdit:false,
    isShowCompanyDetail:false,
    isShowConfigApp:false,
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
        name:this.searchData.resourceName,
        code:this.code,
        shopname:this.searchData.shopname,
        site:this.searchData.site,
        startdate:this.searchData.startdate,
        enddate:this.searchData.enddate,
        auditstatus:this.searchData.auditstatus,
        handlestatus:this.searchData.handlestatus,
        banknum:this.searchData.banknum,
        pageSize:this.pageSize,
        page:this.current,
        auditstatus:'已审核'
      }
      http.queryList(data).then(res => {
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
      this.getData()
    },
    //切换tab标签
    handleClick(val){
      this.clearList()
      this.current = 1
      this.getData()
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
