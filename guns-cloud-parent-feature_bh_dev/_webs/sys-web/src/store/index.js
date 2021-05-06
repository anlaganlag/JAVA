import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import user from './modules/user'
import tags from './modules/tags'
import common from './modules/common'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user,
    tags,
    common
  },
  getters
})

export default store
