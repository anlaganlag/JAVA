import HttpHandler from "@/handler/HttpHandler";
export default class UserApi {

  constructor() {
    this.http = new HttpHandler();
  }
  // 获取当前登录人信息
  getCurrentUser(data){
    let url = '/guns-cloud-system/entUser/getCurrentUser'
    return this.http.get(url, data)
  }
  // 保存当前登录人的信息
  saveCurrentUser(data){
    let url = '/guns-cloud-system/entUser/saveCurrentUser'
    return this.http.post(url, data)
  }
  // 修改密码
  updatePassword(data){
    let url = '/guns-cloud-system/entUser/updatePassword'
    return this.http.post(url, data)
  }
  //获取应用列表
  appList(data){
    let url = '/guns-cloud-system/app/list'
    return this.http.get(url, data)
  }
}
