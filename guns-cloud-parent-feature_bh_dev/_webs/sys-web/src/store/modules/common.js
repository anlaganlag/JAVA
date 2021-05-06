import StorageHandler from "../../utils/StorageHandler";
let storageHandler = new StorageHandler()
const common = {
  state: {
    token: "" || storageHandler.getSessionStorage('token'),
    menus: [],
    tags: [],
    currentTag: {},
    collapseFlag: false
  },
  actions: {
    // 缓存是否显示轮播图
    setToken({ commit, state, dispatch }, data) {
      commit("SET_TOKEN", data);
    },
    // 缓存当前选中的栏目
    clearToken({ commit, state, dispatch }, data) {
      commit("CLEAR_TOKEN", data);
    },
    // 缓存当前菜单
    setMenus({ commit, state, dispatch }, data) {
      commit("SET_MENUS", data);
    },
    // 缓存左侧导航栏收缩状态
    setCollapse({ commit, state, dispatch }, data) {
      commit("SET_COLLAPSE", data);
    }


  },

  mutations: {
    SET_TOKEN: (state, data) => {
      state.token = data;
    },

    CLEAR_TOKEN: (state, data) => {
      state.token = "";
    },

    SET_MENUS: (state, data) => {
      state.menus = data;
    },

    SET_COLLAPSE: (state, data) => {
      state.collapseFlag = data;
    }
  }
};
export default common;
