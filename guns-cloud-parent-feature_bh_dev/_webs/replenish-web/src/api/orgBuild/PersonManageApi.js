import HttpHandler from "@/handler/HttpHandler";

export default class PersonManageApi {

  constructor() {
    this.http = new HttpHandler();
  }
  // 获取公司列表
  queryListPage(data) {
    let url = '/guns-cloud-system/entUser/queryListPage';
    return this.http.get(url, data);
  }
  // 新增
  add(data) {
    let url = '/guns-cloud-system/entUser/add';
    return this.http.post(url, data);
  }
  // 修改
  update(data) {
    let url = '/guns-cloud-system/entUser/update';
    return this.http.post(url, data);
  }
  // 详情
  detail(data) {
    let url = '/guns-cloud-system/entUser/detail';
    return this.http.get(url, data);
  }
  // 禁用启用人员
  changeStatus(data) {
    let url = '/guns-cloud-system/entUser/changeStatus';
    return this.http.get(url, data);
  }
  // 删除
  delete(data) {
    let url = '/guns-cloud-system/entUser/delete';
    return this.http.post(url, data);
  }
  //获取公司列表
  getCompanySelect(data) {
    let url = '/guns-cloud-system/entUser/getCompanySelect';
    return this.http.get(url, data);
  }
  //获取部门列表
  getDeptSelect(data) {
    let url = '/guns-cloud-system/entUser/getDeptSelect';
    return this.http.get(url, data);
  }
  //获取职务列表
  getDutySelect(data) {
    let url = '/guns-cloud-system/entUser/getDutySelect';
    return this.http.get(url, data);
  }
  //人员配置角色时获取角色列表
  getRoles(data) {
    let url = '/guns-cloud-system/entUser/getRoles';
    return this.http.get(url, data);
  }
  //保存人员配置好的角色
  saveRoles(data) {
    let url = '/guns-cloud-system/entUser/saveRoles';
    return this.http.post(url, data);
  }
  // 重置密码
  resetPassword(data) {
    let url = '/guns-cloud-replenish/entUser/resetPassword';
    return this.http.get(url, data);
  }
}
