import HttpHandler from "@/handler/HttpHandler";

export default class HardwareInfo {
  constructor() {
    this.http = new HttpHandler();
  }

  // 所有硬件信息
  getTotalInfo(params) {
    let url = "/guns-cloud-system/sysMachine/query";
    return this.http.get(url, params);
  }

}
