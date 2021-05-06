import HttpHandler from "@/handler/HttpHandler";

export default class ResourceManageApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 获取资源列表
  list(params) {
    let url = "/guns-cloud-system/resource/list";
    return this.http.get(url, params);
  }
}
