import HttpHandler from "@/handler/HttpHandler";

export default class RoleManageApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 获取角色列表（分页）
  page(params) {
    let url = "/guns-cloud-system/role/page";
    return this.http.post(url, params);
  }
  // 新增
  add(params) {
    let url = "/guns-cloud-system/role/add";
    return this.http.post(url, params);
  }
  // 删除
  delete(params) {
    let url = "/guns-cloud-system/role/delete";
    return this.http.post(url, params);
  }
  // 获取角色详情
  detail(params) {
    let url = "/guns-cloud-system/role/detail";
    return this.http.post(url, params);
  }
  // 更换角色
  update(params) {
    let url = "/guns-cloud-system/role/update";
    return this.http.post(url, params);
  }
  // 校验角色名称
  name(params) {
    let url = "/guns-cloud-system/role/validate/name";
    return this.http.post(url, params);
  }
  // 校验角色编码
  code(params) {
    let url = "/guns-cloud-system/role/validate/code";
    return this.http.post(url, params);
  }
  // 设置角色状态
  status(params) {
    let url = "/guns-cloud-system/role/status";
    return this.http.post(url, params);
  }
  // 查询角色分配的权限
  permissions(params) {
    let url = "/guns-cloud-system/role/permissions";
    return this.http.get(url, params);
  }
  // 查询角色分配的所有权限
  findRoleAllPermissions(params) {
    let url = "/guns-cloud-system/role/findRoleAllPermissions";
    return this.http.post(url, params);
  }
  // 为角色分配权限
  permission(params) {
    let url = "/guns-cloud-system/role/assign/permission";
    return this.http.post(url, params);
  }
}
