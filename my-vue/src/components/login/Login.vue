
<template>
  <body id="poster">
  <el-form class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.name" auto-complete="off" placeholder="用户名"></el-input>
    </el-form-item>

    <el-form-item>
      <el-input type="password" v-model="loginForm.pwd" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>

    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">登录</el-button>
    </el-form-item>
        <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="register">注册</el-button>
    </el-form-item>
    <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="resetPassword">忘记密码</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>


<script>
  import {login} from '../../api/login.js'
  export default {
    name: "Login",
    data() {
      return {
        loginForm: {
          name: 'aili',
          pwd: '123456'
        },
        responseResult: [],
        // token:''
      }
    },
    methods: {
      login() {
        const data = {'name':this.loginForm.name,'pwd':this.loginForm.pwd}
        login(data).then(res=>{
          if(res.code===1){
            this.$message.error('用户名或密码错误');
          }else{
            this.$message({
            message: '登陆成功',
            type: 'success',
            duration:1000
          });
          sessionStorage.setItem("token",res.data);
          this.$store.dispatch('changeToken',res.data)
          if(this.$store.getters.getToken!=''){
            this.$router.push({
              name: 'Home'
            })
          }
        }

        }).catch(err=>{
            this.$message.error('用户名或密码错误');
        })
      },
      register(){
        this.$router.push({
          name: 'Register'
        })
      },
      resetPassword(){
        this.$router.push({
          name: 'ResetPassword'
        })
      }
    }
  }
</script>

<style>
  #poster {
    background:url("../../assets/register.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
    padding: 0;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
