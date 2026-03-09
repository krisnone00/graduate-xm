import axios from "axios";
import { ElMessage } from "element-plus";

const request = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 3000,  //后台超时响应时间
});

//request 拦截器
//可以自请求发送前对请求做一些处理
request.interceptors.request.use((config => {
  config.headers['Content-Type'] = 'application/json;charset=utf-8';
  return config;
}), (error) => {
  return Promise.reject(error);
});

//response 拦截器
//可以在接口响应后统一处理结果
request.interceptors.response.use(
    (response) => {
        let res = response.data;
        if ( typeof res === 'string' ) {
            res = res ? JSON.parse(res) : res;
        }
        return res;
    },
    (error) => {
        // ✅ 新增：如果是 401 (未授权/Token过期)，强制跳回赛博登录页
        if (error.response.status === 401) {
            localStorage.removeItem('bs-xm-user') // 清除旧数据
            location.href = '/cyberlogin'         // <--- 这里改成 /cyberlogin
        }
        // ... 原有代码 ...
        else if (error.response.status === 404) {
            ElMessage.error("未找到请求接口");
        } else if (error.response.status === 500) {
            ElMessage.error('系统异常，请查看后端控制台报错');
        } else {
            console.error(error.message)
        }
        return Promise.reject(error);
    }
)

export default request
