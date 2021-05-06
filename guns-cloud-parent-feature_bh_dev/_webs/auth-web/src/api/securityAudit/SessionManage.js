import HttpHandler from "@/handler/HttpHandler";

export default class SessionManage {
  constructor() {
    this.http = new HttpHandler();
  }

  // 强制用户下线
  forcedOffline(params) {
    let url = "/auth-manage/session/forcedOffline";
    return this.http.get(url, params);
  }
  // 获取在线用户信息
  pageList(params) {
    let url = "/auth-manage/session/pageList";
    return this.http.get(url, params);
  }
}
