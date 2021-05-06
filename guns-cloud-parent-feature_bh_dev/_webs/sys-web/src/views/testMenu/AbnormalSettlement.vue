<template>
  <div>
    <el-card>
      <el-row :gutter="10">
        <el-col>
          <el-form inline>
            <el-form-item label="资源名称:">
              <el-input v-model="searchData.resourceName"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="searchList">搜索</el-button>
              <el-button type="success" @click="clearList">清空搜索</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      </el-card>
      <el-card style="margin-top: 10px">
        <div style="margin-bottom: 10px">
          <el-button type="primary" size="medium" @click="abnormalAdd">初始新增</el-button>
          <el-button type="warning" size="medium" @click="abnormalDeal">异常处理</el-button>
        </div>
      <el-table border :data="dataList" v-loading="tableLoading" class="resource-table" ref="multipleTable"  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column type="index" label="序号" align="center" width="50"></el-table-column>
        <el-table-column label="平台" prop="platname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="账号" prop="shopname" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="站点" prop="site" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="开始时间" prop="startdate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="结束时间" prop="enddate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="汇款日期" prop="depositdate" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="币种" prop="currency" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="data range路径" prop="fileuploadpath" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="数据状态" prop="status" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="解析状态" prop="handlestatus" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column label="失败原因" prop="remark" align="center" show-overflow-tooltip></el-table-column>
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
  <abnormal-addEdit v-if="isShowAddEdit"
                      :userId="userId"
  @close="closeAddEdit"></abnormal-addEdit>
  <abnormal-deal v-if="isShowDeal"
                      :userId="userId" :multipleSelection="multipleSelection" :shopcode="shopcode"
  @close="closeDeal"></abnormal-deal>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

import CompanyManageApi from '@/api/orgBuild/CompanyManageApi'
export default {
  name: 'home',
  components:{
      AbnormalAddEdit: resolve => require(['./components/abnormal_addEdit'], resolve),
      AbnormalDeal: resolve => require(['./components/abnormal_deal'], resolve),
  },
  data(){
    return{
      searchData:{
        appId:'', //应用id
        resourceName:'', //资源名称
      },
      userId:'',
      isShowDeal:false,
      isShowAddEdit:false,
      isLoading:false,
      activeName:'N',//页面资源 -- N是api资源
      name:'',
      dwdailytaskname:'',
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
      multipleSelection: [
        
      ],
      shopcode:''
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
        dwdailytaskname:this.searchData.resourceName,
        code:this.code,
        pageSize:this.pageSize, 
        page:this.current,
      }
      http.queryAbnormalList(data).then(res => {
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
    handleSelectionChange(val){
      this.multipleSelection = val
      if(this.multipleSelection.length==1){
            this.shopcode=val[0].shopcode
      }
      if(this.multipleSelection.length !=0 && val[this.multipleSelection.length-1].shopcode!=this.shopcode){
            this.$refs.multipleTable.toggleRowSelection(val[this.multipleSelection.length-1]);
      }
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
      this.getData()
    },
    //切换tab标签
    handleClick(val){
      this.clearList()
      this.current = 1
      this.getData()
    },
    //关闭人员新增弹窗
    closeAddEdit(){
        this.userId = ''
        this.isShowAddEdit = false
    },
    closeDeal(){
        this.userId = ''
        this.isShowDeal = false
    },
    abnormalAdd(){
      this.isShowAddEdit = true
    },
    abnormalDeal(){
      this.isShowDeal = true
    }
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

