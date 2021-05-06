import HttpHandler from "@/handler/HttpHandler";

export default class DbManageApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 获取数据源列表
  page(params) {
    let url = "/guns-cloud-system/databaseInfo/page";
    return this.http.get(url, params);
  }
  // 新增
  add(params) {
    let url = "/guns-cloud-system/databaseInfo/add";
    return this.http.post(url, params);
  }
  // 编辑
  edit(params) {
    let url = "/guns-cloud-system/databaseInfo/edit";
    return this.http.post(url, params);
  }
  // 删除
  delete(params) {
    let url = "/guns-cloud-system/databaseInfo/delete";
    return this.http.post(url, params);
  }
  // 获取详情
  detail(params) {
    let url = "/guns-cloud-system/databaseInfo/detail";
    return this.http.post(url, params);
  }
}
