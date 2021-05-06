import HttpHandler from "@/handler/HttpHandler";

export default class BizLogApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 查看日志列表（分页）
  pageList(params) {
    let url = "/guns-cloud-system/sysOpLog/page";
    return this.http.get(url, params);
  }

  // 清空操作日志
  deleteAll(params) {
    let url = "/guns-cloud-system/sysOpLog/delete";
    return this.http.post(url, params);
  }

}
