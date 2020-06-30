<template>
    <div>
      <el-container style="height: 1000px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-active="$route.path"  router unique-opened >
            <template v-for="(item,index) in $router.options.routes">
              <el-submenu  :index="index+''" v-show="item.hidden" :key="index">
                <template slot="title">
                  <router-link :to="item.name?item.name:''" style="text-decoration: none;color:black"><div style="width:100%">{{item.title}}</div></router-link>
               </template>
              <el-menu-item v-for="(child,index1) in item.children" :key="index1"  :index="'/home/'+child.path"  >
                 {{child.title}}
            </el-menu-item>
          </el-submenu>
        </template>
      </el-menu>
        </el-aside>
        <el-container>
          <el-header style="text-align: right; font-size: 12px">
            <el-dropdown>
              <i class="el-icon-setting" style="margin-right: 15px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="edit">修改</el-dropdown-item>
                <el-dropdown-item @click.native="quit">退出</el-dropdown-item>
                <el-dropdown-item @click.native="cancellation">注销</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <span style="font-size:25px">{{name}}</span>
          </el-header>
          <el-main style="overflow:hidden">
            <slot />
          </el-main>
        </el-container>
      </el-container>

    </div>
</template>

<script>
  import route from "@/router/index"
  import { getName ,cancellationUser} from "@/api/navbar"
  export default {
      name: "Navbar",
      props:['title'],
      created(){
        this.getusername()
      },
      data(){

        return {
          name:''
        }
      },
      computed:{

      },
      methods:{
        getusername(){
          getName().then(res =>{
            this.name = res.data
          })
        },
        goBack(){
          this.$router.go(-1)
        },
        edit(){
          this.$router.push({
            name:'UserDetail'
          })
        },
        cancellation(){
          this.$confirm('此操作将注销用户是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            cancellationUser().then(res=>{
              if(res.code === 0){
                this.$message({
                message: '注销成功',
                type: 'success',
                duration:1000
                })
                sessionStorage.setItem("token",'null');
                this.$store.dispatch('changeToken','null')
                this.$router.push({
                  name:'Login'
                })
              }
        })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });          
        });
        },
        quit(){
          sessionStorage.setItem("token",'null');
          this.$store.dispatch('changeToken','null')
          this.$router.push({
            name:'Login'
          })
        }
      }
    }
</script>

<style lang="css" scoped>

</style>
