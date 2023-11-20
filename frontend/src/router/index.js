import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../App.vue')
    },
    {
      path:'/user',
      name: 'user',
      component: () => import('../App.vue'),
      children: [
        {
          path: 'login',
          name: 'login',
          component: () => import('../views/LoginView.vue')
        }
      ]
    },
    {
      path:'/staff',
      name: 'staff',
      component: () => import('../views/SiteStaffView.vue'),
    }
  ]
})

export default router
