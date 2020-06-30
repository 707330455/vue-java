import request from '@/utils/request'

export function getAllUser(data) {
    return request({
      url: 'test',
      method: 'post',
      data
    })
}
export function upUser(data) {
  return request({
    url: 'up',
    method: 'post',
    data
  })
}
export function delUser(id) {
  return request({
    url: 'del/'+id,
    method: 'get'
  })
}
export function addUser(data) {
  return request({
    url: 'add',
    method: 'post',
    data
  })
}
export function getDetail() {
  return request({
    url: 'getDetail',
    method: 'get',
  })
}