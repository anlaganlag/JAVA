import HttpHandler from "@/handler/HttpHandler";

export default class ButtonMaintainApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 分页查询列表
  queryListPage(params) {
    let url = "/guns-cloud-system/sysButton/queryListPage";
    return this.http.get(url, params);
  }
  // 新增
  add(params) {
    let url = "/guns-cloud-system/sysButton/add";
    return this.http.post(url, params);
  }
  // 修改
  update(params) {
    let url = "/guns-cloud-system/sysButton/update";
    return this.http.post(url, params);
  }
  // 查询详情
  getDetail(params) {
    let url = "/guns-cloud-system/sysButton/getDetail";
    return this.http.get(url, params);
  }
  // 按钮启用禁用
  changeStatus(params) {
    let url = "/guns-cloud-system/sysButton/changeStatus";
    return this.http.get(url, params);
  }
  // 新增时获取资源
  getResources(params) {
    let url = "/guns-cloud-system/sysButton/getResources";
    return this.http.get(url, params);
  }
  // 获取用户权限的按钮
  buttonPermission(params) {
    let url = "/guns-cloud-system/sysButton/buttonPermission";
    return this.http.get(url, params);
  }
  // 删除
  delete(params) {
    let url = "/guns-cloud-system/sysButton/delete";
    return this.http.post(url, params);
  }

  //获取应用按钮状态
  getAppButtonStatus(params) {
    let url = "/guns-cloud-system/permission/getAppButtonStatus";
    return this.http.get(url, params);
  }

}
