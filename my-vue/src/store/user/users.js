const users = {
    state: {
        count:111
    },
    mutations: {
        changeCount(state,num){
            state.count=num
          }
    },
    actions: {
        changeCount({commit},num){
            commit('changeCount',num)
          }
    },
    getters:{
        getCountn(state){
          return state.count
        }
      },
  }
  
  export default users