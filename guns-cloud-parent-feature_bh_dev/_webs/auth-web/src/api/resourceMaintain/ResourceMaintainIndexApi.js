import HttpHandler from "@/handler/HttpHandler";

export default class ResourceMaintainIndexApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 新增资源缓存
  addResCache(params) {
    let url = "/auth-manage/resCacheManager/addResCache";
    return this.http.post(url, params);
  }
  // 修改资源缓存
  editResCache(params) {
    let url = "/auth-manage/resCacheManager/editResCache";
    return this.http.post(url, params);
  }
  // 获取应用下拉列表
  getAppSelect(params) {
    let url = "/auth-manage/resCacheManager/getAppSelect";
    return this.http.get(url, params);
  }
  // 获取缓存资源的详情
  getDetail(params) {
    let url = "/auth-manage/resCacheManager/getDetail";
    return this.http.get(url, params);
  }
  // 获取http请求下拉列表
  getHttpRequestSelect(params) {
    let url = "/auth-manage/resCacheManager/getHttpRequestSelect";
    return this.http.get(url, params);
  }
  // 获取资源缓存分页
  getResCacheList(params) {
    let url = "/auth-manage/resCacheManager/getResCacheList";
    return this.http.get(url, params);
  }
  // 获取资源所属模块下拉列表
  getResModuleSelect(params) {
    let url = "/auth-manage/resCacheManager/getResModuleSelect";
    return this.http.get(url, params);
  }
  // 删除资源缓存
  removeResCache(params) {
    let url = "/auth-manage/resCacheManager/removeResCache";
    return this.http.get(url, params);
  }
}
