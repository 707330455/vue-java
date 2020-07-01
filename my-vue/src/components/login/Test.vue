<template>
  <Navbar>我是测试页  {{count}}  <el-button @click="addCount">count+1</el-button>
  <el-button @click="cutCount">count-1</el-button></Navbar>

</template>

<script>
export default {
  created(){
    this.count = this.$store.state.user.count
  },
  data(){
    return {
      count:''
    }
  },
  methods:{
    addCount(){
      var num =this.$store.state.user.count + 1
      this.$store.dispatch('changeCount',num)
      this.count =this.$store.state.user.count
    },
    cutCount(){
      var num =this.$store.state.user.count - 1
      this.$store.dispatch('changeCount',num)
      this.count =this.$store.state.user.count
    }
  }
}
</script>

<style>

</style>