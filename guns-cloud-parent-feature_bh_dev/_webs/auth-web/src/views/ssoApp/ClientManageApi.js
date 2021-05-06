import HttpHandler from "@/handler/HttpHandler";

export default class ClientManageApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 获取单点客户端分页列表
  pageList(params) {
    let url = "/authorize-manager/authClient/pageList";
    return this.http.get(url, params);
  }
  // 获取应用下拉列表
  getAppSelectList(params) {
    let url = "/authorize-manager/authClient/getAppSelectList";
    return this.http.get(url, params);
  }
  // 客户端新增
  add(params) {
    let url = "/authorize-manager/authClient/add";
    return this.http.post(url, params);
  }
  // 客户端编辑
  edit(params) {
    let url = "/authorize-manager/authClient/edit";
    return this.http.post(url, params);
  }
  // 客户端删除
  delete(params) {
    let url = "/authorize-manager/authClient/delete";
    return this.http.post(url, params);
  }
  // 客户端详情
  detail(params) {
    let url = "/authorize-manager/authClient/detail";
    return this.http.get(url, params);
  }
}
