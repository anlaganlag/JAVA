import HttpHandler from "@/handler/HttpHandler";
export default class LoginApi {

  constructor() {
    this.http = new HttpHandler();
  }

  //获取左侧树菜单
  getLeftMenuList(data){
    let url = '/guns-cloud-system/menu/getLeftMenuList'
    return this.http.get(url, data)
  }
}
