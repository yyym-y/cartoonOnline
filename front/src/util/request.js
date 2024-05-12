/*引入axios*/
import axios from 'axios'
const request = axios.create({
    baseURL: "http://10.252.114.70:8888/", // 基础路径,将统一的部分全部封装
    withCredentials: true // 表示请求可以携带cookie
})
//前端采用export.default，在写后端代码时用module.export
export default request