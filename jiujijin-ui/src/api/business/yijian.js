import request from '@/utils/request'

// 查询意见箱列表
export function listYijian(query) {
  return request({
    url: '/business/yijian/list',
    method: 'get',
    params: query
  })
}

// 查询意见箱详细
export function getYijian(id) {
  return request({
    url: '/business/yijian/' + id,
    method: 'get'
  })
}

// 新增意见箱
export function addYijian(data) {
  return request({
    url: '/business/yijian',
    method: 'post',
    data: data
  })
}

// 修改意见箱
export function updateYijian(data) {
  return request({
    url: '/business/yijian',
    method: 'put',
    data: data
  })
}

// 删除意见箱
export function delYijian(id) {
  return request({
    url: '/business/yijian/' + id,
    method: 'delete'
  })
}
