import HttpHandler from "@/handler/HttpHandler";

export default class MenuManagementApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 应用下拉列表
  getAppSelect(params) {
    let url = "/guns-cloud-system/app/getAppSelect";
    return this.http.get(url, params);
  }
  //获取菜单列表
  list(params) {
    let url = "/guns-cloud-system/menu/list";
    return this.http.post(url, params);
  }
  //新增菜单
  add(params) {
    let url = "/guns-cloud-system/menu/add";
    return this.http.post(url, params);
  }
  //删除菜单
  delete(params) {
    let url = "/guns-cloud-system/menu/delete";
    return this.http.post(url, params);
  }
  //更改状态
  changeStatus(params) {
    let url = "/guns-cloud-system/menu/changeStatus";
    return this.http.get(url, params);
  }
  //获取菜单详情
  getMenuDetail(params) {
    let url = "/guns-cloud-system/menu/getMenuDetail";
    return this.http.get(url, params);
  }
  //编辑菜单
  update(params) {
    let url = "/guns-cloud-system/menu/update";
    return this.http.post(url, params);
  }
  //获取父级菜单列表
  getSelectMenuTreeList(params) {
    let url = "/guns-cloud-system/menu/getSelectMenuTreeList";
    return this.http.get(url, params);
  }
  //获取资源下拉列表
  getResourceSelectList(params) {
    let url = "/guns-cloud-system/resource/getResourceSelectList";
    return this.http.get(url, params);
  }
}
