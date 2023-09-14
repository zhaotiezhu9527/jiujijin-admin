import request from '@/utils/request'

// 查询救济金申请列表
export function listOpinion(query) {
  return request({
    url: '/business/opinion/list',
    method: 'get',
    params: query
  })
}

// 查询救济金申请详细
export function getOpinion(id) {
  return request({
    url: '/business/opinion/' + id,
    method: 'get'
  })
}

// 新增救济金申请
export function addOpinion(data) {
  return request({
    url: '/business/opinion',
    method: 'post',
    data: data
  })
}

// 修改救济金申请
export function updateOpinion(data) {
  return request({
    url: '/business/opinion',
    method: 'put',
    data: data
  })
}

// 删除救济金申请
export function delOpinion(id) {
  return request({
    url: '/business/opinion/' + id,
    method: 'delete'
  })
}
