import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../views/Layout.vue'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/drone/list',
    children: [
      {
        path: 'drone/list',
        name: 'DroneList',
        component: () => import('../views/DroneList.vue'),
        meta: { title: '无人机列表' }
      },
      {
        path: 'drone/detail/:id',
        name: 'DroneDetail',
        component: () => import('../views/DroneDetail.vue'),
        meta: { title: '无人机详情' }
      },
      {
        path: 'drone/add',
        name: 'DroneAdd',
        component: () => import('../views/DroneForm.vue'),
        meta: { title: '添加无人机' }
      },
      {
        path: 'drone/edit/:id',
        name: 'DroneEdit',
        component: () => import('../views/DroneForm.vue'),
        meta: { title: '编辑无人机' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  document.title = to.meta.title ? `${to.meta.title} - 无人机管理系统` : '无人机管理系统'
  const token = localStorage.getItem('token')
  if (to.path !== '/login' && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router
