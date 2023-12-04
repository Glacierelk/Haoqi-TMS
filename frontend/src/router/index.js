import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/BrowseTrainingNotificationsView.vue')
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
      path: '/instructor',
      name: 'instructor',
      //component: () => import('../views/ManagerView.vue'),
      children: [
        {
          path: 'InstructorSearchInformation',
          name: 'InstructorSearchInformation',
          component: () => import('../views/Instructor/InstructorSearchInformation.vue')
        }
      ]
    },
    {
      path: '/executor',
      name: 'executor',
      component: () => import('../views/ExecutorView.vue')
    }
      ]
    })

export default router
