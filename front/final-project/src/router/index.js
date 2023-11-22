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

//notice
import NoticeDetail from '@/components/notice/NoticeDetail.vue'
import NoticeTable from '@/components/notice/NoticeTable.vue'
import NoticeInsert from '@/components/notice/NoticeInsert.vue'
import NoticeUpdate from '@/components/notice/NoticeUpdate.vue'

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
      component: BookmarkPage,
      beforeEnter: (to, from, next) => {
        const { message, authStore } = useAuthStore()
        let isLogin = sessionStorage.getItem('isLogin')
        if (authStore.isLogin || isLogin == 'true ') {
          next()
        } else {
          alert(message.noLogin)
          next('/login')
        }
      }
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
          path: '/community/detail',
          name: 'communityDetail',
          component: CommunityDetail,
          beforeEnter: (to, from, next) => {
            const { message, authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              next()
            } else {
              alert(message.noLogin)
              next('/login')
            }
          }
        },
        {
          path: '/community/insert',
          name: 'communityInsert',
          component: CommunityInsert,
          beforeEnter: (to, from, next) => {
            const { message, authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              next()
            } else {
              alert(message.noLogin)
              next('/login')
            }
          }
        },
        {
          path: '/community/update',
          name: 'communityUpdate',
          component: CommunityUpdate,
          beforeEnter: (to, from, next) => {
            const { message, authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              next()
            } else {
              alert(message.noLogin)
              next('/login')
            }
          }
        }
      ]
    }, //notice
    {
      path: '/notice',
      name: 'notice',
      component: NoticePage,
      children: [
        { path: '', name: 'noticeTable', component: NoticeTable },
        {
          path: '/notice/detail',
          name: 'noticeDetail',
          component: NoticeDetail,
          beforeEnter: (to, from, next) => {
            const { message, authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              console.log('Notice Detail Page 이동 : index.js')
              next()
            } else {
              alert(message.noLogin)
              next('/login')
            }
          }
        },
        {
          path: '/notice/insert',
          name: 'noticeInsert',
          component: NoticeInsert,
          beforeEnter: (to, from, next) => {
            const { message, authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              if (authStore.memberPosition == '관리자') {
                next()
              } else {
                alert(message.requireAdmin)
                next('/notice')
              }
            } else {
              alert(message.noLogin)
              next('/login')
            }
          }
        },
        {
          path: '/notice/update',
          name: 'noticeUpdate',
          component: NoticeUpdate,
          beforeEnter: (to, from, next) => {
            const { message, authStore } = useAuthStore()

            let isLogin = sessionStorage.getItem('isLogin')
            if (authStore.isLogin || isLogin == 'true ') {
              if (authStore.memberPosition == '관리자') {
                next()
              } else {
                alert(message.requireAdmin)
                next('/notice')
              }
            } else {
              alert(message.noLogin)
              next('/login')
            }
          }
        }
      ]
    },
    {
      path: '/trip/search',
      name: 'search',
      component: SearchPage
    },
    {
      path: '/trip/search/detail/:id',
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
    if (to.name === 'detail') {
      // DetailPage로 이동하는 경우에만 스크롤 맨 위로 이동
      return { top: 0, behavior: 'smooth' }
    }
    return savedPosition // 다른 페이지로 이동하는 경우에는 이전의 저장된 위치로 이동
  }
})

export default router
