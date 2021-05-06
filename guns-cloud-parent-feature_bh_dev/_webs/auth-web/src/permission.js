import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
// import { getToken } from '@/utils/auth' // get token from cookie
import StorageHandler from '@/utils/StorageHandler'
import SystemUtil from '@/utils/SystemUtil'
import SystemConfig from "./config/SystemConfig"
import getPageTitle from '@/utils/get-page-title'
import LoginApi from '@/api/LoginApi'


let storageHandler = new StorageHandler()

NProgress.configure({ showSpinner: false }) // NProgress Configuration

router.beforeEach(async(to, from, next) => {
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)
  let token = storageHandler.getStorage("token") || to.query.token
  let redirectUrl = to.query.redirectUrl
  //先判断本地是否存在token
  if (!token) {
    //在判断进入的页是否是登录页和sso页面
    if (to.path != "/login") {
      //如果地址栏中存在errCode则返回登陆页
      if(to.query.errCode){
        next("/login")
      }else {
        let ssoHost = process.env.VUE_APP_SSO_HOST
        let appSsoId = process.env.VUE_APP_SSO_APP_ID
        window.location.href = `${ssoHost}/sso/login?redirectUrl=${encodeURIComponent(window.location.href)}&clientId=${appSsoId}`
      }
    } else {
      next()
    }
  } else {
    if(redirectUrl){
      let url = ''
      if(redirectUrl.indexOf('?') === -1){
        url = `${redirectUrl}?token=${token}`
      }else{
        url = `${redirectUrl}&token=${token}`
      }
      window.location.href = url
      next({...to})
    }else {
      if(to.query.token){
        storageHandler.setStorage("token", to.query.token, SystemConfig.storate.sessionStorageKey)
        reloadCache().then(res => {
          next()
        })
      }else{
        reloadCache().then(res => {
          next()
        })
      }
    }
  }
})

router.afterEach((to, from, next) => {
  let menus = store.state.common.menus;
  let item = {code: to.path};
  let currMenu = SystemUtil.findMenuByPath(item, menus);
  if(!_.isEmpty(currMenu)){
    let currTag = {
      id: currMenu.id,
      label: currMenu.name,
      value: currMenu.code,
      close: true
    };
    // store.commit("tags/ADD_TAG", currTag);
    store.commit("ADD_TAG", currTag);
  }
  //判断本地缓存是否有值
  if(storageHandler.getSessionStorage('--mainThemeColor')){
    let mainColor = storageHandler.getSessionStorage('--mainThemeColor')
    let secondColor = storageHandler.getSessionStorage('--secondThemeColor')
    document.documentElement.style.setProperty('--mainThemeColor', mainColor)
    document.documentElement.style.setProperty('--secondThemeColor', secondColor)
  }else {
    document.documentElement.style.setProperty('--mainThemeColor', SystemConfig.defaultColor.mainColor)
    document.documentElement.style.setProperty('--secondThemeColor', SystemConfig.defaultColor.secondColor)
  }
  // finish progress bar
  NProgress.done()
})

//重新加载系统公共资源
function reloadCache(){
  return new Promise( async (resolve, reject) => {
    if(storageHandler.getStorage("userInfo")){
      resolve('success')
    }else{
      //获取用户信息
      let http = new LoginApi()
      let userInfo = await http.getUserInfo({})
      store.dispatch("user/setUserInfo", userInfo.data)
      resolve('success')
    }
    // let menuHttp = new MenuApi()
    // menuHttp.getLeftMenuList({appId: '1'}).then(res => {
    //   resolve('success')
    // })
  })
}
