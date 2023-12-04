import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
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
          component: () => import('../views/Instructor/InstructorSearchInformation.vue')
        },
        {
          path: 'ExecutorChangeInstructor2',
          name: 'ExecutorChangeInstructor2',
          component: () => import('../views/Executor/ExecutorChangeInstructor.vue')
        }
      ]
    },
    {
      path:'/staff',
      name: 'staff',
      component: () => import('../views/SiteStaffView.vue'),
    },
    {
      path: '/manager',
        name: 'manager',
        component: () => import('../views/ManagerView.vue'),
    },
    {
      path: '/executor',
        name: 'executor',
        component: () => import('../views/ExecutorView.vue'),
    },
    {
        path: '/student',
        name: 'student',
        component: () => import('../views/StudentView.vue'),
    }
  ]
})

export default router
