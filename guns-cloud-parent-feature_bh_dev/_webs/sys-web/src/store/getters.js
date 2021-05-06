const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.common.token,
  userInfo: state => state.user.userInfo,
  homeTag : state => state.tags.homeTag,
  selectedTags : state => state.tags.selectedTags,
  currentTag : state => state.tags.currentTag,
  menus: state => state.common.menus,
  collapseFlag: state => state.common.collapseFlag,
}
export default getters
