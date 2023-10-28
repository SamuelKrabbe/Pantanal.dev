import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SocialActionsView from '../views/SocialActionsView.vue'
import UserView from '../views/UserView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      children: [
        {
          path: '/',
          name: 'social actions list',
          component: SocialActionsView
        }
      ]
    },
    {        
      path: '/user',
      name: 'user',
      component: UserView
    }
  ]
})

export default router
