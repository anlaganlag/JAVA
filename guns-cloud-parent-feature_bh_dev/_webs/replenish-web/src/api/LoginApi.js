import HttpHandler from "../handler/HttpHandler";

import JSEncrypt from 'jsencrypt'
export default class LoginApi {

  constructor() {
    this.http = new HttpHandler();
  }

  //获取授权码
  getAuthCode(data){
    var encrypt = new JSEncrypt()
    let publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDt30AcO8CSAfzSa5L8ikVrfehH6aFw9KyL85NzOAduOfnPcbiAGLjLWEKOkOhkYrlSAfU5s+pa3OQTsgpfCkVVm56dEQh8sajIR4uyGbhv0/CdvPTZS5o3sP6Yi9TemWZ443+QNjajN6MSCTmTY86ZoR9jmTcJtV4kNTQWDov6qQIDAQAB'
    encrypt.setPublicKey(publicKey)	//	 publicKey为公钥
    data.password = encrypt.encrypt(data.password)
    let url = '/api/auth/accessCode'
    return this.http.post(url, data)
  }
  //获取个人信息
  getUserInfo(data){
    let url = '/guns-cloud-system/system/getUserInfo'
    return this.http.get(url, data)
  }
}
