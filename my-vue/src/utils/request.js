import axios from 'axios'
import store from '@/store'
import router from '@/router';
// create an axios instance
const service = axios.create({
  baseURL: 'http://localhost:8080/api/', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
})

// request interceptor
service.interceptors.request.use(
  config => {
    // 在header中加参数
    config.headers['lang'] = 'en'
    config.headers['token'] = store.getters.getToken
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
      return res

  },error => {
    router.push({
      name: 'Login'
    })
}
)

export default service
