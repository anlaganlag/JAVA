import HttpHandler from "@/handler/HttpHandler";

export default class DeptManageApi {

  constructor() {
    this.http = new HttpHandler();
  }

  // 获取公司列表
  queryListPage(data) {
    let url = '/guns-cloud-system/entDept/queryListPage';
    return this.http.get(url, data);
  }
  // 新增
  add(data) {
    let url = '/guns-cloud-system/entDept/add';
    return this.http.post(url, data);
  }
  // 修改
  update(data) {
    let url = '/guns-cloud-system/entDept/update';
    return this.http.post(url, data);
  }
  // 详情
  detail(data) {
    let url = '/guns-cloud-system/entDept/detail';
    return this.http.get(url, data);
  }
  // 获取部门树列表
  queryDeptTree(data) {
    let url = '/guns-cloud-system/entDept/queryDeptTree';
    return this.http.get(url, data);
  }
  // 禁用启用部门
  changeStatus(data) {
    let url = '/guns-cloud-system/entDept/changeStatus';
    return this.http.get(url, data);
  }
  // 删除
  delete(data) {
    let url = '/guns-cloud-system/entDept/delete';
    return this.http.post(url, data);
  }
}
