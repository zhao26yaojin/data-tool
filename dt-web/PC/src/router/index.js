import { createRouter, createWebHashHistory } from 'vue-router'

import MainView from '@/views/MainView.vue'
import Team from '@/views/fb/Team.vue'
// import Standing from '@/views/fb/Standing.vue'

const routes = [
    {
        path: '/',
        component: MainView,
        children: [
            {
                path: '/fb/team',
                component: Team
            },
            {
                path: '/fb/standing',
                // component: Standing
                component: () => import('@/views/fb/Standing.vue')
            }
        ]
    }

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router