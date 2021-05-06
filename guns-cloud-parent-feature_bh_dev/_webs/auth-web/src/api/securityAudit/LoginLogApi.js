import HttpHandler from "@/handler/HttpHandler";

export default class LoginLogApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 日志详情
  detail(params) {
    let url = "/auth-manage/authLoginLog/detail";
    return this.http.get(url, params);
  }
  // 查看日志列表（分页）
  pageList(params) {
    let url = "/auth-manage/authLoginLog/pageList";
    return this.http.get(url, params);
  }
}
