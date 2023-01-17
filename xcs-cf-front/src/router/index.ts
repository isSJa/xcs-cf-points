import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'


const routes: Array<RouteRecordRaw> = [
  {
    name:'add',
    path:'/add',
    component:()=>import('@/views/add.vue')
  },
  {
    name:'student',
    path:'/student',
    component:()=>import('@/views/student.vue')
  },
  {
    name:'contest',
    path:'/contest',
    component:()=>import('@/views/contest.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
