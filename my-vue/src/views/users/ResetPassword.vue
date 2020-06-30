
<template>
  <body id="poster">
  <el-form class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">忘记密码</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.name" auto-complete="off" placeholder="用户名"></el-input>
    </el-form-item>

    <el-form-item>
      <el-input type="text" v-model="loginForm.telephone" auto-complete="off" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
        <span>重置密码为123456</span>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="reset">重置</el-button>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="tologin">返回登录页</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>


<script>
  import {resetPassword} from '../../api/register.js'
  export default {
    name: "ResetPassword",
    data() {
      return {
        loginForm: {
          name: '',
          telephone:''
        },
        responseResult: [],
        // token:''
      }
    },
    methods: {
      tologin(){
        this.$router.push({
          name:'Login'
        })
      },
      reset() {
        const data = {'name':this.loginForm.name,'telephone':this.loginForm.telephone}
        resetPassword(data).then(res=>{
          if(res.code === 0){
            this.$message({
            message: '重置密码成功',
            type: 'success',
            duration:1000
           })
            this.$router.push({
              name:'Login'
            })
          }else{
            this.$message.error('用户不存在');
          }
        }).catch(err=>{
            this.$message.error(err);
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
