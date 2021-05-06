import HttpHandler from "@/handler/HttpHandler";

export default class CompanyManageApi {

  constructor() {
    this.http = new HttpHandler();
  }

  // 获取公司列表
  queryListPage(data) {
    let url = '/guns-cloud-system/entCompany/queryListPage';
    return this.http.get(url, data);
  }
  // 新增
  add(data) {
    let url = '/guns-cloud-system/entCompany/add';
    return this.http.post(url, data);
  }
  // 修改
  update(data) {
    let url = '/guns-cloud-system/entCompany/update';
    return this.http.post(url, data);
  }
  // 详情
  detail(data) {
    let url = '/guns-cloud-system/entCompany/detail';
    return this.http.get(url, data);
  }
  // 获取公司树列表
  queryCompTree(data) {
    let url = '/guns-cloud-system/entCompany/queryCompTree';
    return this.http.get(url, data);
  }
  // 禁用启用公司
  changeStatus(data) {
    let url = '/guns-cloud-system/entCompany/changeStatus';
    return this.http.get(url, data);
  }
  // 删除
  delete(data) {
    let url = '/guns-cloud-system/entCompany/delete';
    return this.http.post(url, data);
  }
  //获取应用列表
  getAppList(data) {
    let url = '/guns-cloud-system/entCompany/getAppList';
    return this.http.get(url, data);
  }
  //保存公司配置的应用
  saveCompApp(data) {
    let url = '/guns-cloud-system/entCompany/saveCompApp';
    return this.http.post(url, data);
  }
}
