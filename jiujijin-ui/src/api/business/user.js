import request from '@/utils/request'

// 查询用户列表列表
export function listUser(query) {
  return request({
    url: '/business/user/list',
    method: 'get',
    params: query
  })
}

// 查询用户列表详细
export function getUser(id) {
  return request({
    url: '/business/user/' + id,
    method: 'get'
  })
}

// 新增用户列表
export function addUser(data) {
  return request({
    url: '/business/user',
    method: 'post',
    data: data
  })
}

// 修改用户列表
export function updateUser(data) {
  return request({
    url: '/business/user',
    method: 'put',
    data: data
  })
}

// 删除用户列表
export function delUser(id) {
  return request({
    url: '/business/user/' + id,
    method: 'delete'
  })
}
