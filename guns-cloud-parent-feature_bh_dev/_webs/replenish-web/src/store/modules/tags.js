import StorageHandler from "@/utils/StorageHandler.js";

let storageHandler = new StorageHandler();

const tagObj = {
  label: '',
  value: '',
  query: '',
  num: '',
  close: true,
}

const homeTag = {
  label: '首页',
  value: '/home',
  close: false
}

function setFistTag(list) {
  // if (list.length == 1) {
  //   list[0].close = false;
  // } else {
  //   list.some(a => {
  //     a.close = true
  //   })
  // }
  return list;

}

const tabs = {
  state: {
    selectedTags: storageHandler.getSessionStorage("selectedTags") || [homeTag],
    currentTag: storageHandler.getSessionStorage('currentTag') || tagObj,
    homeTag: homeTag
  },
  actions: {
    //添加标签页
    addTag({commit, state}, data) {
      commit('ADD_TAG', data);
    },

    //设置当前标签页
    setTag({commit, state}, data) {
      commit('SET_TAG', data);
    },

    //删除某标签页
    removeTag({commit, state}, data) {
      commit('DEL_TAG', data);
    },

    //清除其他标签页
    clearOtherTag({commit, state}) {
      commit('DEL_OTHER_TAG');
    },

    //清除标签页
    clearTag({commit, state}) {
      commit('DEL_ALL_TAG');
    }
  },
  mutations: {
    ADD_TAG: (state, action) => {
      state.currentTag = action;
      storageHandler.setSessionStorage("currentTag", state.currentTag);
      if (state.selectedTags.some(a => a.value === action.value)) return;
      state.selectedTags.push({
        label: action.label,
        value: action.value,
        query: action.query,
        close: true
      })
      state.selectedTags = setFistTag(state.selectedTags);
      storageHandler.setSessionStorage("selectedTags", state.selectedTags);
    },
    SET_TAG: (state, value) => {
      state.selectedTags.forEach((ele, num) => {
        if (ele.value === value) {
          state.currentTag = state.selectedTags[num];
          storageHandler.setSessionStorage("currentTag", state.currentTag);
        }
      });
    },
    DEL_TAG: (state, action) => {
      state.selectedTags.forEach((ele, num) => {
        if (ele.value === action.value) {
          state.selectedTags.splice(num, 1)
          state.selectedTags = setFistTag(state.selectedTags);
          storageHandler.removeSessionStorage("currentTag");
          storageHandler.setSessionStorage("selectedTags", state.selectedTags);
        }
      })
    },
    DEL_OTHER_TAG: (state, action) => {
      state.selectedTags.forEach((ele, num) => {
        state.selectedTags = [];
        state.selectedTags.push(state.homeTag);
        state.selectedTags.push(state.currentTag);
        storageHandler.setSessionStorage("selectedTags", state.selectedTags);
      })
    },
    DEL_ALL_TAG: (state, action) => {
      state.currentTag = tagObj;
      state.selectedTags = [];
      state.selectedTags.push(state.homeTag);
      storageHandler.removeSessionStorage("currentTag");
      storageHandler.removeSessionStorage("selectedTags");
    }
  }
}
export default tabs
