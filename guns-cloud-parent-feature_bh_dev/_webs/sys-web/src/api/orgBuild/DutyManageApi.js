import HttpHandler from "@/handler/HttpHandler";

export default class DutyManageApi {

  constructor() {
    this.http = new HttpHandler();
  }

  // 获取公司列表
  queryListPage(data) {
    let url = '/guns-cloud-system/entDuty/queryListPage';
    return this.http.get(url, data);
  }
  // 新增
  add(data) {
    let url = '/guns-cloud-system/entDuty/add';
    return this.http.post(url, data);
  }
  // 修改
  update(data) {
    let url = '/guns-cloud-system/entDuty/update';
    return this.http.post(url, data);
  }
  // 详情
  detail(data) {
    let url = '/guns-cloud-system/entDuty/detail';
    return this.http.get(url, data);
  }
  // 禁用启用部门
  changeStatus(data) {
    let url = '/guns-cloud-system/entDuty/changeStatus';
    return this.http.get(url, data);
  }
  // 删除
  delete(data) {
    let url = '/guns-cloud-system/entDuty/delete';
    return this.http.post(url, data);
  }
}
