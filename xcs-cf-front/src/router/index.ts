import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'


const routes: Array<RouteRecordRaw> = [
    {
        name: 'admin',
        path: '/admin',
        component: () => import('@/views/admin/adminIndex.vue'),
        children: [{
            name: 'add',
            path: 'add',
            component: () => import('@/views/admin/add.vue')
        }, {
            name: 'admin-student',
            path: 'student',
            component: () => import('@/views/admin/student.vue')
        }, {
            name: 'admin-contest',
            path: 'contest',
            component: () => import('@/views/admin/contest.vue')
        }]
    }, {
        name: 'user',
        path: '/user',
        component: () => import('@/views/user/userIndex.vue'),
        children: [{
            name: 'user-student',
            path: 'student',
            component: () => import('@/views/user/student.vue')
        }, {
            name: 'user-contest',
            path: 'contest',
            component: () => import('@/views/user/contest.vue')
        }]
    }, {
        name: 'login',
        path:'/login-and-register',
        component:()=>import('@/views/loginAndRegister.vue')
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
