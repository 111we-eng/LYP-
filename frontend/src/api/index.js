import request from '../utils/request'

export const droneApi = {
  // 获取无人机列表
  list(keyword) {
    return request.get('/drone/list', { params: { keyword } })
  },
  // 获取无人机详情
  detail(id) {
    return request.get(`/drone/detail/${id}`)
  },
  // AI生成无人机
  generate() {
    return request.post('/drone/generate')
  },
  // 保存（新增/编辑）
  save(drone) {
    return request.post('/drone/save', drone)
  },
  // 删除
  delete(id) {
    return request.delete(`/drone/delete/${id}`)
  }
}

export const authApi = {
  // 登录
  login(username, password) {
    return request.post('/auth/login', { username, password })
  }
}
