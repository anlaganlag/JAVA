/**
 *
 * 系统工具类
 *
 *
 * */
export default class SystemUtil {

    static convertMenus(menuArr) {
        for (let i = 0; i < menuArr.length; i++) {
            let href = filterUrl(menuArr[i].code);
            if (menuArr[i].children.length != 0) {
                //把父节点的url设为空
                // menuArr[i].code = "";
                let child = menuArr[i].children;
                for (let j = 0; j < child.length; j++) {
                    //把子节点的url设为 parentUrl+childUrl
                    child[j].code = href.concat(filterUrl(child[j].code));
                    if (child[j].children.length != 0) {
                        this.convertMenus(child);
                    }
                }
            }
        }

        function filterUrl(url) {
            if(!url){
                return "";
            }
            if (url && !url.startsWith("/")) {
                url = "/".concat(url);
            }
            return url;
        }

        return menuArr;
    }

    static findMenuByPath(menu, menuArr) {
        let result = {};
        for (let i = 0; i < menuArr.length; i++) {
            if (menuArr[i].code == menu.code) {
                result = menuArr[i]
            }
            let child = menuArr[i].children;
            for (let j = 0; j < child.length; j++) {
                if (child[j].code == menu.code) {
                    result = child[j]
                }else{
                    this.findMenuByPath(menu, child);
                }
            }
        }

        return result;
    }
}
