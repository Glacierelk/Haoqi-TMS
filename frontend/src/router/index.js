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
          component: () => import('../views/LoginView.vue'),
          meta: { requiresAuth: false }
        }
      ]
    },
    {
      path:'/staff',
      name: 'staff',
      component: () => import('../views/SiteStaffView.vue'),
      meta: { requiresAuth: false }
    },
    {
      path: '/manager',
        name: 'manager',
        component: () => import('../views/ManagerView.vue'),
      meta: { requiresAuth: false }
    },
    {
      path: '/executor',
        name: 'executor',
        component: () => import('../views/ExecutorView.vue'),
      meta: { requiresAuth: false }
    },
    {
        path: '/student',
        name: 'student',
        component: () => import('../views/StudentView.vue'),
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 检查用户是否已登录或者有相应的认证状态，可以在这里添加你的认证逻辑
    // 例如，你可以检查用户是否有 token 或者其他认证信息
    const isAuthenticated = checkAuth(); // 自定义的检查认证状态的函数

    if (!isAuthenticated) {
      // 如果用户未经过认证，可以重定向到其他页面，比如登录页面
      next('/');
    } else {
      // 用户经过认证，可以继续访问目标页面
      next();
    }
  } else {
    // 如果目标页面不需要验证，直接放行
    next();
  }
});

// 检查用户是否已认证的函数
function checkAuth() {
  // 在此处编写检查用户认证状态的逻辑，例如检查本地存储的 token 等
  // 返回 true 或 false 表示用户是否已认证
  // 示例：假设在 localStorage 中存储了 token
  const token = localStorage.getItem('token');
  return !!token; // 返回 token 是否存在的布尔值
}

export default router
