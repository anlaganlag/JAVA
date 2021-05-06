import HttpHandler from "@/handler/HttpHandler";

export default class ResourceMaintainIndexApi {
  constructor() {
    this.http = new HttpHandler();
  }

  // 网关路由列表
  gatewayRoutes(params) {
    let url = "/auth-manage/gateway/routes";
    return this.http.get(url, params);
  }

  // 新增路由
  addRoute(params) {
    let url = "/auth-manage/gateway/addRoute";
    return this.http.post(url, params);
  }

  // 根据id查询路由信息
  routeInfoById(params) {
    let url = "/auth-manage/gateway/routeInfoById";
    return this.http.get(url, params);
  }

  // 删除指定路由
  deleteRoute(params) {
    let url = "/auth-manage/gateway/deleteRoute";
    return this.http.get(url, params);
  }

  // 过滤器列表
  globalfilters(params) {
    let url = "/auth-manage/gateway/globalfilters";
    return this.http.get(url, params);
  }

  // 所有的过滤器工厂
  routefilters(params) {
    let url = "/auth-manage/gateway/routefilters";
    return this.http.get(url, params);
  }

  // 清空路由缓存
  refreshRoute(params) {
    let url = "/auth-manage/gateway/refreshRoute";
    return this.http.post(url, params);
  }

}
