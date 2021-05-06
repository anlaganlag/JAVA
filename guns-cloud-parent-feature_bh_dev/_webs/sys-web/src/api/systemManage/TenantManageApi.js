import HttpHandler from "@/handler/HttpHandler";

export default class TenantManageApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 获取数据源列表
  page(params) {
    let url = "/guns-cloud-system/tenantInfo/page";
    return this.http.get(url, params);
  }

  // 新增
  add(params) {
    let url = "/guns-cloud-system/tenantInfo/add";
    return this.http.post(url, params);
  }

  // 编辑
  edit(params) {
    let url = "/guns-cloud-system/tenantInfo/edit";
    return this.http.post(url, params);
  }

  // 删除
  delete(params) {
    let url = "/guns-cloud-system/tenantInfo/delete";
    return this.http.post(url, params);
  }

  // 详情
  detail(id) {
    let url = "/guns-cloud-system/tenantInfo/detail?id=" + id;
    return this.http.get(url);
  }

  // 下拉列表
  listTenants(params) {
    let url = "/guns-cloud-system/tenantInfo/listTenants";
    return this.http.get(url, params);
  }
}
