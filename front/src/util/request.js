/*引入axios*/
import axios from 'axios'
const request = axios.create({
    baseURL: "http://10.252.116.34:8888",
    withCredentials: true // 表示请求可以携带cookie
})
//前端采用export.default，在写后端代码时用module.export
export default request