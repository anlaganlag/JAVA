import HttpHandler from "@/handler/HttpHandler";
import HttpConstant from "@/const/HttpConstant"

export default class DictManageApi {

  constructor() {
    this.http = new HttpHandler();
  }

  // 获取代码类型列表
  getList(data) {
    let url = '/guns-cloud-system/dictType/getDictTypeList';
    return this.http.post(url, data);
  }

  // 新增代码类型
  addDictType(data) {
    let url = '/guns-cloud-system/dictType/addDictType';
    return this.http.post(url, data);
  }

  // 编辑代码类型
  updateDictType(data) {
    let url = '/guns-cloud-system/dictType/updateDictType';
    return this.http.post(url, data);
  }

  // 删除子弹类型
  deleteDictType(params){
    let url = '/guns-cloud-system/dictType/deleteDictType';
    return this.http.post(url, params, HttpConstant.FORM_DATA_REQ);
  }

  // 更改状态
  updateTypeStatus(data) {
    let url = '/guns-cloud-system/dictType/updateStatus';
    return this.http.post(url, data, HttpConstant.FORM_DATA_REQ);
  }

  // 删除代码类型
  deleteCodeType(data) {
    let url = '/guns-cloud-system/dict/deleteDictType';
    return this.http.post(url, data);
  }


  // 获取代码列表
  findDictPage(data) {
    let url = '/guns-cloud-system/dict/getDictPage';
    return this.http.post(url, data);
  }


  // 新增代码
  addCode(data) {
    let url = '/guns-cloud-system/dict/addDict';
    return this.http.post(url, data);
  }

  // 编辑代码
  updateCode(data) {
    let url = '/guns-cloud-system/dict/updateDict';
    return this.http.post(url, data);
  }

  // 获取上级代码
  getDictListByTypeCode(data) {
    let url = '/guns-cloud-system/dict/getDictListByTypeCode';
    return this.http.post(url, data, HttpConstant.FORM_DATA_REQ);
  }

  // 根据字典类型code和父id获取下级字典
  getListByTypeCodeAndPCode(data) {
    let url = '/guns-cloud-system/dict/getListByTypeCodeAndPCode';
    return this.http.post(url, data, HttpConstant.FORM_DATA_REQ);
  }

  // 更改代码状态
  updateDictStatus(data) {
    let url = '/guns-cloud-system/dict/updateDictStatus';
    return this.http.post(url, data, HttpConstant.FORM_DATA_REQ);
  }

  // 删除代码
  deleteCode(data) {
    let url = '/guns-cloud-system/dict/deleteDict';
    return this.http.post(url, data, HttpConstant.FORM_DATA_REQ);
  }

  // 基础数据类型校验
  checkCodeType(data) {
    let url = '/guns-cloud-system/dictType/checkCode';
    return this.http.post(url, data);

  }

// 基础数据校验
  checkCode(data) {

    let url = '/guns-cloud-system/dict/checkCode';
    return this.http.post(url, data,HttpConstant.FORM_DATA_REQ);

  }
}
