import Vue from 'vue'

import Vuex from 'vuex'
Vue.use(Vuex)
export default new Vuex.Store({
  state:{
    token:sessionStorage.getItem('token')?sessionStorage.getItem('token'):null
  },
  getters:{
    getToken(state){
      return state.token
    }
  },
  mutations:{
    changeToken(state,num){
      state.token=num
    }
  },
  actions:{
    changeToken({commit},num){
      commit('changeToken',num)
    }
  }
  //五
  // modules:{
  //   a:moduleA
  // },
  // state:{
  //   count:1,
  //   lists:[
  //     {id:1,msg:"aa"},
  //     {id:2,msg:"bb"},
  //     {id:3,msg:"cc"}
  //   ]
  // },
  // getters:{
  //   lists(state){
  //      return state.lists
  //    },
  //   item:(state)=>(i)=>{
  //     return state.lists[i]
  //   }
  // },
  // //操作同步数据
  // mutations:{
  //   addNum(state,num){
  //     state.count +=count
  //   },
  //   addCountByasync(state,payload){
  //     console.log(payload)
  //     // setTimeout(()=>{
  //       state.count += payload.num
  //     // },1000)
  //   },
  //   addCountByasync2(state,num){
  //     // setTimeout(()=>{
  //     state.count += num
  //     // },1000)
  //   }
  // },
  // //异步操作
  // actions:{
  //   addCountByasync({commint},payload){
  //     setTimeout(()=>{
  //       commint(addCountByasync,payload.num)//这里是调用mutations的方法数据对应上
  //     },1000)
  //   }
  // }
})
