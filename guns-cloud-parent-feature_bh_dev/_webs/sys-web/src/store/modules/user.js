import StorageHandler from '@/utils/StorageHandler'
import SystemConfig from '@/config/SystemConfig'

let storage = new StorageHandler()
const state = {
  userInfo: storage.getStorage("userInfo"),
}
const mutations = {
  SET_USER_INFO:(state, data) => {
    storage.setStorage("userInfo", data, SystemConfig.storate.sessionStorageKey)
    state.userInfo = data
  },
  CLEAR_USER_INFO:(state, data) => {
    state.userInfo = {}
    storage.removeStorage("userInfo", SystemConfig.storate.sessionStorageKey)
  },
}

const actions = {
  //保存userInfo
  setUserInfo:({commit}, data)=>{
    commit('SET_USER_INFO', data)
  },
  //清除userInfo
  clearUserInfo:({commit}, data)=>{
    commit('CLEAR_USER_INFO', data)
  },
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

