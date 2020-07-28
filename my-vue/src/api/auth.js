let delay =(function() { // 防抖
    let timer = 0
    return function(callback, ms) {
      clearTimeout(timer)
      timer = setTimeout(callback, ms)
    }
  })()
//delary(()=>{},500)
export default delay