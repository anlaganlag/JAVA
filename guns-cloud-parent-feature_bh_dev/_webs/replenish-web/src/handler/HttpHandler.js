import qs from 'qs'
import axios from "../interceptor/HttpInterceptor"
import HttpConstant from "../const/HttpConstant"

export default class HttpHandler {
  constructor() {
  }
  /**
   *
   * 自定义get请求
   *
   */
  get(url, params, config) {
    if (!params) {
      params = {};
    }
    params.assign('eeee',getToken())
    console.log(getToken())
    return axios.get(url,{params: params},config);
  }
  /**
   *
   * 自定义post请求
   *
   */
  post(url, params, type, config) {
    if (!params) {
      params = {};
    }
    if(!config){
      config = {};
    }
    if (type == HttpConstant.FORM_DATA_REQ) {
      let configTmp = Object.assign({}, config);
      configTmp["Content-Type"] = HttpConstant.FORM_DATA_REQ;
      params = qs.stringify(params);
    }
    return axios.post(url, params, config);
  }
  /**
   *
   * 自定义文件上传方法
   *
   */
  upload(url, params, config) {
    if (!config) {
      config = axios.defaults.headers;
    }
    let configTmp = Object.assign({}, config);
    configTmp["timeout"] = 1000 * 60 * 5;
    configTmp["Content-Type"] = this.HttpConstant.MUL_FORM_REQ;
    configTmp["Authorization"] = getToken();

    let formData = new FormData(); //创建form对象

    for (let field in params) {
      if (params[field]) {
        formData.append(field, params[field]);
      }
    }
    return axios.post(url, formData, configTmp);
  }

  /**
   *
   * 自定义文件下载方法
   *
   */
  download(url, params, config) {
    var paramUrl = "?";
    for (let field in params) {
      if (params[field]) {
        paramUrl = paramUrl + field + "=" + params[field] + "&";
      }
    }
    paramUrl = paramUrl.substring(0, paramUrl.length - 1);
    console.log(url + paramUrl);
    window.location.href = url + paramUrl;
  }

  /**
   *
   * 自定义文件预览方法
   *
   */
  preview(url, params, config) {
    let paramUrl = "?";
    for (let field in params) {
      if (params[field]) {
        paramUrl = paramUrl + field + "=" + params[field] + "&";
      }
    }
    paramUrl = paramUrl.substring(0, paramUrl.length - 1);
    window.open(url + paramUrl);
  }
}

