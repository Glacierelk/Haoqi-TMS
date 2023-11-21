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
        },
        {
          path: 'InstructorSearchInformation',
          name: 'InstructorSearchInformation',
          component: () => import('../views/InstructorSearchInformation.vue')
        },
        {
          path: 'ExecutorChangeInstructor',
          name: 'ExecutorChangeInstructor',
          component: () => import('../views/ExecutorChangeInstructorold.vue')
        },
        {
          path: 'ExecutorChangeInstructor2',
          name: 'ExecutorChangeInstructor2',
          component: () => import('../views/ExecutorChangeInstructor.vue')
        }
      ]
    }
  ]
})

export default router
