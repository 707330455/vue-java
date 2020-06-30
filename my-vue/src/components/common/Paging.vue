<template>
    <div>
      <el-table
        :data="tableData"
        border
        style="width: 100%">
        <slot />
      </el-table>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
</template>

<script>
    export default {
      name: "Paging",
      props:['tableData','total','pageSize'],
      data(){
          return{
            currentPage: 1,
          }
      },
      methods:{
        handleCurrentChange(val) {
          this.$emit("handleCurrentChange",val)
        }
      }
    }
</script>

<style lang="css" scoped>

</style>
