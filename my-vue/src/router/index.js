import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from "../components/login/Login";
import Register from "../views/users/Register";
import UserDetail from "../views/users/UserDetail";
import ResetPassword from "../views/users/ResetPassword";
import Test from "../components/login/Test";


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      hidden: false,
      redirect:'/login'
    },
    {
      path: '/home',
      name: 'Home',
      hidden: true,
      component: Home,
        title:"首页"
    },
    {
      path: '/test',
      name: 'Test',
      hidden: true,
      component: Test,
        title:"测试"
    },
    {
      path: '/login',
      name: 'Login',
      hidden: false,
      component: Login,
        title:"登录"
    },
    {
      path: '/register',
      name: 'Register',
      hidden: false,
      component: Register,
        title:"注册"
    },
    {
      path: '/resetPassword',
      name: 'ResetPassword',
      hidden: false,
      component: ResetPassword,
        title:"重置密码"
    },
    {
      path: '/userDetail',
      name: 'UserDetail',
      hidden: false,
      component: UserDetail,
        title:"个人信息修改"
    }
  ]
})
