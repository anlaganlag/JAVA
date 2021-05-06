import HttpHandler from "@/handler/HttpHandler";

export default class GodKeyPasswordApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 启用禁用万能密码
  checkedStatus(params) {
    let url = "/auth-manage/godKeyManager/checkedStatus";
    return this.http.get(url, params);
  }
  // 获取万能密码的值和状态
  getGodKey(params) {
    let url = "/auth-manage/godKeyManager/getGodKey";
    return this.http.get(url, params);
  }
}
