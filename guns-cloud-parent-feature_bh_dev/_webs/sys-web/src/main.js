import Vue from 'vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/index.scss' // global css
import "./assets/css/common.scss"
import "./assets/css/element-ui.scss"
import "./styles/theme/base.scss"  //更换主题css
import App from './App'
import store from './store'
import router from './router'
import DictConstant from './const/DictConstant'
import HttpConstant from './const/HttpConstant'
import mixin from './mixin'
import './components/component'
import './assets/iconfont/iconfont.css'  //引入iconfont

import '@/permission' // permission control

// set ElementUI lang to EN
Vue.use(ElementUI)

Vue.prototype.DictConstant = DictConstant
Vue.prototype.HttpConstant = HttpConstant
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
