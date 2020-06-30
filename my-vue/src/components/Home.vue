<template>
  <Navbar>
    <el-card>
      <el-form :model="form">
        <el-row style="margin-bottom: 10px">
          <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3" style="margin-top: 10px">
            <span >姓名</span>
          </el-col>
          <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
            <el-input v-model="form.name" clearable></el-input>
          </el-col>
          <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
            <el-button type="primary" @click="searchByName">search</el-button>
          </el-col>
          <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
            <el-button type="primary" @click="add">add</el-button>
          </el-col>
        </el-row>
        <el-row>

        </el-row>
      </el-form>


      <Paging :tableData = "tableData" :total="total" @handleCurrentChange ='changePage' :pageSize="pageSize">
        <el-table-column
          prop="name"
          label="姓名"
          width="110">
        </el-table-column>
        <el-table-column
          prop="age"
          label="年龄"
          width="90">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址">
        </el-table-column>
        <el-table-column
          prop="telephone"
          label="电话">
        </el-table-column>
        <el-table-column
          prop="sex"
          :formatter="sexFormat"
          width="90"
          label="性别">
        </el-table-column>
        <el-table-column
          prop="description"
          label="描述">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button @click="edit(scope)" type="primary">修改</el-button>
            <el-button @click="del(scope)" type="primary">删除</el-button>
          </template>
        </el-table-column>
      </Paging>


      <el-dialog
        :title="title"
        :close-on-click-modal="false"
        :visible.sync="dialogVisible"
        width="50%"
        >
        <el-form ref="form1" :model="form1" :rules="rules" label-width="110px">
          <el-row >
            <el-col :span="12" align="left">
          <el-form-item label="姓名:" prop="name">
            <el-input v-model="form1.name" autosize />
          </el-form-item>
            </el-col>
            <el-col :span="12" align="left">
          <el-form-item label="年龄:" >
            <el-input v-model="form1.age" autosize  @keyup.native="number" maxlength="4"/>
          </el-form-item>
          </el-col>
          </el-row>
          <el-row >
            <el-col :span="12" align="left">
              <el-form-item label="地址:" >
                <el-input v-model="form1.address"  autosize />
              </el-form-item>
            </el-col>
            <el-col :span="12" align="left">
              <el-form-item label="电话:" prop="telephone">
                <el-input v-model="form1.telephone" @keyup.native="telephoneNumber" autosize   maxlength="11"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row >

            <el-col :span="12" align="left">
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
              <el-col :span="12" align="left">
              <el-form-item v-if="title=='add'" label="密码:" prop="pwd">
                <el-input v-model="form1.pwd" type="password"  autosize />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" align="left">
              <el-form-item label="描述:">
                <el-input v-model="form1.description" type="textarea" :rows="4" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <span slot="footer">
                  <el-button style="margin-top: 10px" @click="dialogVisible = false;">取消</el-button>
                  <el-button
                    style="margin-top: 10px"
                    type="primary"
                    @click="ok"
                  >确认</el-button>
                </span>
      </el-dialog>
    </el-card>
  </Navbar>

</template>


<script>
import { getAllUser,addUser,upUser,delUser } from '@/api/users'
import Paging from "./common/Paging";
import route from "@/router/index"

  export default {
    components: {
      Paging
    },
    created(){
      const token = this.$store.getters.getToken
      this.changePage(1)
    },
    methods:{
      number(){
        this.form1.age=this.form1.age.replace(/[^\.\d]/g,'');
        this.form1.age=this.form1.age.replace('.','');
      },
      telephoneNumber(){
        this.form1.telephone=this.form1.telephone.replace(/[^\.\d]/g,'');
        this.form1.telephone=this.form1.telephone.replace('.','');
      },
      searchByName(){
        this.changePage(1)
      },
      sexFormat(row, column){
        if (row.sex === '1') {
          return '男'
        } else if (row.sex === '2') {
          return '女'
        }else{
          return ''
        }
      },
      edit(scope){
        this.title = 'edit'
        this.dialogVisible = true
        this.form1 ={
          name:scope.row.name,
          age:scope.row.age,
          address:scope.row.address,
          pwd:scope.row.pwd,
          description:scope.row.description,
          telephone:scope.row.telephone,
          sex:scope.row.sex,
          id:scope.row.id
        }
      },
      del(scope){
          this.$confirm('此操作将删除用户是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delUser(scope.row.id).then(res=>{
            this.$message({
            message: '删除成功',
            type: 'success',
            duration:1000
        })
          this.changePage(1)
        })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
      add(){
        this.title = 'add'
        this.reset()
        this.dialogVisible = true
      },
      changePage(e){
         this.getlist(e)
      },
      getlist(e){
        const data = {current:e,size:4,name:this.form.name}
        getAllUser(data).then(res =>{
          this.tableData  =res.data.records
          this.total = res.data.total
        })
      },
      reset(){
        if (this.$refs.form1 !== undefined) {
          this.$refs.form1.resetFields()
        }
        this.form1 ={
          name:'',
          age:'',
          address:'',
          pwd:'',
          description:'',
          telephone:'',
          sex:'',
         }
      },
      ok(){
        if(this.title ==='edit'){
          upUser(this.form1).then(res=>{
           this.$message({
            message: '修改成功',
            type: 'success',
            duration:1000
           })
            this.dialogVisible = false
            this.reset()
            this.changePage(1)
          })
        }else{
          addUser(this.form1).then(res =>{
            this.$message({
            message: '添加成功',
            type: 'success',
            duration:1000
          })
            this.dialogVisible = false
            this.reset()
            this.changePage(1)
          })
        }
      }
    },
    data() {
      return {
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
        options: [{
          sex: '1',
          label: '男'
        }, {
          sex: '2',
          label: '女'
        }],
        value: '',
        title:'',
        form1:{
          name:'',
          age:'',
          email:'',
          pwd:'',
          telephone:'',
          sex:'',
          description:''
        },
        dialogVisible: false,
        route:'',
        form: {
          name: ''
        },
        total:0,
        pageSize:5,
        tableData: []
      }
    }
  };
</script>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
