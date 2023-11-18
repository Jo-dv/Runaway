import { createRouter, createWebHistory } from 'vue-router'
import IndexPage from '@/views/IndexPage.vue'
import LoginPage from '@/views/LoginPage.vue'
import RegisterPage from '@/views/RegisterPage.vue'
import BookmarkPage from '@/views/BookmarkPage.vue'
import InformationPage from '@/views/InformationPage.vue'
import CommunityPage from '@/views/CommunityPage.vue'
import NoticePage from '@/views/NoticePage.vue'
import SearchPage from '@/views/SearchPage.vue'
import DetailPage from '@/components/search/DetailPage.vue'
import CommunityDetail from '@/components/community/CommunityDetail.vue'
import CommunityTable from '@/components/community/CommunityTable.vue'
import CommunityInsert from '@/components/community/CommunityInsert.vue'
import CommunityUpdate from '@/components/community/CommunityUpdate.vue'

//store
import { useAuthStore } from '@/stores/authStore'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'index',
      component: IndexPage
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterPage
    },
    {
      path: '/bookmark',
      name: 'bookmark',
      component: BookmarkPage
    },
    {
      path: '/inform',
      name: 'inform',
      component: InformationPage
    },
    {
      path: '/community',
      name: 'community',
      component: CommunityPage,
      children: [
        // UserHome will be rendered inside User's <router-view>
        // when /users/:username is matched
        { path: '', name: 'communityTable', component: CommunityTable },

        // UserProfile will be rendered inside User's <router-view>
        // when /users/:username/profile is matched
        {
          path: '/detail',
          name: 'communityDetail',
          component: CommunityDetail,
          beforeEnter: (to, from, next) => {
            const { authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              next()
            } else {
              alert('글을 확인하려면 로그인이 필요합니다.')
              next('/login')
            }
          }
        },
        {
          path: '/insert',
          name: 'communityInsert',
          component: CommunityInsert,
          beforeEnter: (to, from, next) => {
            const { authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              next()
            } else {
              alert('글을 작성하려면 로그인이 필요합니다.')
              next('/login')
            }
          }
        },
        {
          path: '/update',
          name: 'communityUpdate',
          component: CommunityUpdate,
          beforeEnter: (to, from, next) => {
            const { authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              next()
            } else {
              alert('글을 수정하려면 로그인이 필요합니다.')
              next('/login')
            }
          }
        }
      ]
    },
    {
      path: '/admin/notices',
      name: 'notice',
      component: NoticePage
    },
    {
      path: '/trip/search',
      name: 'search',
      component: SearchPage
    },
    {
      path: '/trip/detail/:id',
      name: 'detail',
      component: DetailPage
    }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ],
  scrollBehavior(to, from, savedPosition) {
    // 라우터를 통한 컴포넌트 이동시 스크롤 고정 함수
    // if (to.name === 'detail') {
    //   // DetailPage로 이동하는 경우에만 스크롤 맨 위로 이동
    //   return { top: 0, behavior: 'smooth' }
    // }
    // return savedPosition // 다른 페이지로 이동하는 경우에는 이전의 저장된 위치로 이동
    return { top: 0, behavior: 'smooth' }
  }
})

export default router
