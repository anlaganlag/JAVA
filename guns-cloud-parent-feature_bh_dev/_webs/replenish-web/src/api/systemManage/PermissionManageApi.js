import HttpHandler from "@/handler/HttpHandler";

export default class PermissionManageApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 新增
  add(params) {
    let url = "/guns-cloud-system/permission/add";
    return this.http.post(url, params);
  }
  // 解除权限绑定资源
  cancelPermissionBindResource(params) {
    let url = "/guns-cloud-system/permission/cancelPermissionBindResource";
    return this.http.post(url, params);
  }
  // 获取所有权限列表
  getPermissionList(params) {
    let url = "/guns-cloud-system/permission/getPermissionList";
    return this.http.get(url, params);
  }
  // 分级加载权限
  getPermissionList4Level(params) {
    let url = "/guns-cloud-system/permission/getPermissionList4Level";
    return this.http.get(url, params);
  }
  // 分页获取权限列表
  getPermissionPageList(params) {
    let url = "/guns-cloud-system/permission/getPermissionPageList";
    return this.http.get(url, params);
  }
  // 获取权限绑定的资源
  getPermissionResourceList(params) {
    let url = "/guns-cloud-system/permission/getPermissionResourceList";
    return this.http.get(url, params);
  }
  // 获取权限资源的关系
  getPermissionResourceRelation(params) {
    let url = "/guns-cloud-system/permission/getPermissionResourceRelation";
    return this.http.get(url, params);
  }
  // 获取权限树
  getPermissionTree(params) {
    let url = "/guns-cloud-system/permission/getPermissionTree";
    return this.http.get(url, params);
  }
  // 获取权限资源列表
  getResourceList4Permission(params) {
    let url = "/guns-cloud-system/permission/getResourceList4Permission";
    return this.http.get(url, params);
  }
  // 权限绑定资源
  permissionBindResource(params) {
    let url = "/guns-cloud-system/permission/permissionBindResource";
    return this.http.post(url, params);
  }
  // 更改权限状态
  setPermissionStatus(params) {
    let url = "/guns-cloud-system/permission/setPermissionStatus";
    return this.http.post(url, params);
  }

  //删除权限
  removePermission(params){
    let url = "/guns-cloud-system/permission/removePermission";
    return this.http.get(url, params);
  }
}
