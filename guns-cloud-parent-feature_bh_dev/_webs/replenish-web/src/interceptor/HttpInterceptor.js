import axios from "axios";
import StorageHandler from "../utils/StorageHandler";
import SystemConfig from "../config/SystemConfig";
import { Message } from "element-ui";

//默认请求响应超时时间
axios.defaults.timeout = 1000 * 60;
//跨域请求，允许保存cookie
axios.defaults.withCredentials = true;

//HTTPrequest拦截
axios.interceptors.request.use(
  config => {
    // 设置请求格式
    //config.headers["Content-Type"] = "application/json";

    // 设置token
    let storageHandler = new StorageHandler();
    config.headers["Authorization"] = storageHandler.getSessionStorage('token');
    // config.headers["Authorization"] = '1'

    // 是否需要签名
    if (SystemConfig.sign) {
      let signHanler = new SignHanler();
      signHanler.handler(config);
    }
    return config;
  },
  error => {
    console.log("err" + error); // for debug
    return Promise.reject(error);
  }
);
//HTTPresponse拦截
axios.interceptors.response.use(
  data => {
    return data.data;
  },
  error => {
    console.log(error);
    if(error.response.status === 401){
      Message({showClose: true, message: error.response.data.message, type: 'error'});
      let storageHandler = new StorageHandler();
      storageHandler.clearStorage()
      window.location.href = `${process.env.VUE_APP_SSO_HOST}/sso/login?redirectUrl=${encodeURIComponent(window.location.href)}&clientId=${process.env.VUE_APP_SSO_APP_ID}`
      return Promise.reject(error.response.data)
    }else{
      Message({
        showClose: true,
        message: error.response.data.message,
        type: "error"
      });
      return Promise.reject(error.response.data.message);
    }
  }
);

export default axios;
