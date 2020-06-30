
<template>
  <body id="poster">
  <el-form class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">注册</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.name" auto-complete="off" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="loginForm.pwd" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.telephone" auto-complete="off" @keyup.native="telephoneNumber" autosize   maxlength="11" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">注册</el-button>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="tologin">返回登录页</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>


<script>
  import {register} from '../../api/register.js'
  export default {
    name: "Register",
    data() {
      return {
        loginForm: {
          name: '',
          pwd: '',
          telephone:''
        },
        responseResult: [],
        // token:''
      }
    },
    methods: {
       telephoneNumber(){
        this.loginForm.telephone=this.loginForm.telephone.replace(/[^\.\d]/g,'');
        this.loginForm.telephone=this.loginForm.telephone.replace('.','');
      },
      tologin(){
        this.$router.push({
          name:'Login'
        })
      },
      login() {
        if(this.loginForm.name&&this.loginForm.pwd&&this.loginForm.telephone){
        const data = {'name':this.loginForm.name,'pwd':this.loginForm.pwd,'telephone':this.loginForm.telephone}
        register(data).then(res=>{
          if(res.code === 0){
            sessionStorage.setItem("token",res.data);
            this.$store.dispatch('changeToken',res.data)
            if(this.$store.getters.getToken!=''){
              this.$router.push({
                name: 'Home'
              })
            }
          }

        }).catch(err=>{
            this.$message.error('注册错误');
        })
        }else{
          this.$message.error('信息不全无法注册');
        }

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
