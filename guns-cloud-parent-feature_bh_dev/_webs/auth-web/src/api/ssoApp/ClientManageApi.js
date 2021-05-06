import HttpHandler from "@/handler/HttpHandler";

export default class ClientManageApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 获取单点客户端分页列表
  pageList(params) {
    let url = "/auth-manage/authClient/pageList";
    return this.http.get(url, params);
  }
  // 获取应用下拉列表
  getAppSelectList(params) {
    let url = "/auth-manage/authClient/getAppSelectList";
    return this.http.get(url, params);
  }
  // 客户端新增
  add(params) {
    let url = "/auth-manage/authClient/add";
    return this.http.post(url, params);
  }
  // 客户端编辑
  edit(params) {
    let url = "/auth-manage/authClient/edit";
    return this.http.post(url, params);
  }
  // 客户端删除
  delete(params) {
    let url = "/auth-manage/authClient/delete";
    return this.http.post(url, params);
  }
  // 客户端详情
  detail(params) {
    let url = "/auth-manage/authClient/detail";
    return this.http.get(url, params);
  }
}
