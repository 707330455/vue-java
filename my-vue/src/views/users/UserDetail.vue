<template>
  <Navbar>
        <el-form ref="form1" :model="form1" :rules="rules" label-width="110px">
          <el-row >
            <el-col :span="6" align="left">
          <el-form-item label="姓名:" prop="name">
            <el-input v-model="form1.name" autosize />
          </el-form-item>
            </el-col>
            <el-col :span="6" align="left">
          <el-form-item label="年龄:" >
            <el-input v-model="form1.age" autosize  @keyup.native="number" maxlength="4"/>
          </el-form-item>
          </el-col>
          </el-row>
          <el-row >
            <el-col :span="6" align="left">
              <el-form-item label="地址:" >
                <el-input v-model="form1.address"  autosize />
              </el-form-item>
            </el-col>
            <el-col :span="6" align="left">
              <el-form-item label="电话:" prop="telephone">
                <el-input v-model="form1.telephone" @keyup.native="telephoneNumber" autosize   maxlength="11"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row >

            <el-col :span="6" align="left">
              <el-form-item label="性别:" >
                <el-select v-model="form1.sex" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.sex"
                    :label="item.label"
                    :value="item.sex">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
              <el-col :span="6" align="left">
              <el-form-item label="密码:" prop="pwd">
                <el-input v-model="form1.pwd" type="password"  autosize />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" align="left">
              <el-form-item label="描述:">
                <el-input v-model="form1.description" type="textarea" :rows="4" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <span>
                  <el-button
                    style="float:left;margin-left:60px"
                    type="primary"
                    @click="ok"
                  >确认</el-button>
        </span>
  </Navbar>
</template>

<script>
import {getDetail,upUser} from'@/api/users'
export default {
  created(){
    this.getUserDetail()
  },
  data(){
      return{
        form1:{
          name:'',
          age:'',
          email:'',
          pwd:'',
          telephone:'',
          sex:'',
          description:''
        },
        options: [{
          sex: '1',
          label: '男'
        }, {
          sex: '2',
          label: '女'
        }],
        rules: {
          name: [
            { required: true, trigger: 'blur' }
          ],
          pwd: [
            { required: true, trigger: 'blur' }
          ],
          telephone:[
            { required: true, trigger: 'blur' }
          ]
        },
      }
  },
  methods:{
     getUserDetail(){
       getDetail().then(res=>{
         if(res.code===0){
          this.form1 = res.data
         }
       })
     },
     telephoneNumber(){
        this.form1.telephone=this.form1.telephone.replace(/[^\.\d]/g,'');
        this.form1.telephone=this.form1.telephone.replace('.','');
      },
     ok(){
       upUser(this.form1).then(res=>{
        this.$message({
          message: '修改成功',
          type: 'success',
          duration:1000
        });
           this.getUserDetail
       })
     }
  }
}
</script>

<style>

</style>