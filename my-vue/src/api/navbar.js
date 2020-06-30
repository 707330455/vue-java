import request from '@/utils/request'

export  function getName() {
    return request({
      url: 'getName',
      method: 'get',
    })
}
export  function cancellationUser() {
  return request({
    url: 'cancellationUser',
    method: 'get',
  })
}
