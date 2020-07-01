// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//axios
// import Axios from 'axios'
// Vue.prototype.$axios=Axios
// Axios.defaults.baseURL='http://localhost:8080/api/'

//设置全局的路由守卫
// router.beforeEach((to,from,next)=>{
//   if(sessionStorage.getItem('token')){
//     next()
//   }else{
//     next('/login')
//   }
// })

Vue.config.productionTip = false
Vue.use(ElementUI);
//axios
import Axios from 'axios'
Vue.prototype.$axios=Axios

//vuex
import store from './store'
/* eslint-disable no-new */
// moment 时间组件  {{？？|relativeTime}}
import Moment from 'moment'
Vue.filter('relativeTime',function (date) {
  return Moment(date).format('YYYY-MM-DD')
})
//注册全局的导航栏组件
import Navbar from './components/common/Navbar'
Vue.component('Navbar',Navbar);
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
