import request from '@/utils/request'

export function register(data) {
    return request({
      url: 'register',
      method: 'post',
      data
    })
}
export function resetPassword(data) {
  return request({
    url: 'resetPassword',
    method: 'post',
    data
  })
}
