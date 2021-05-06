import HttpHandler from "@/handler/HttpHandler";

export default class AppRegisterApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 获取列表
  list(params) {
    let url = "/guns-cloud-system/app/list";
    return this.http.get(url, params);
  }
  // 新增
  add(params) {
    let url = "/guns-cloud-system/app/add";
    return this.http.post(url, params);
  }
  // 编辑
  update(params) {
    let url = "/guns-cloud-system/app/update";
    return this.http.post(url, params);
  }
  // 详情
  detail(params) {
    let url = "/guns-cloud-system/app/detail";
    return this.http.get(url, params);
  }
  // 删除
  delete(params) {
    let url = "/guns-cloud-system/app/delete";
    return this.http.get(url, params);
  }
  // 禁用或启用
  changeStatus(params) {
    let url = "/guns-cloud-system/app/changeStatus";
    return this.http.get(url, params);
  }
  // 获取应用下拉列表
  getAppSelect(params) {
    let url = "/guns-cloud-system/app/getAppSelect";
    return this.http.get(url, params);
  }
}
