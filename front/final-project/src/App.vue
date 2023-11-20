<script setup>
import { RouterView } from 'vue-router'
import CommonNav from './components/common/CommonNav.vue'
import CommonFooter from './components/common/CommonFooter.vue'

import { useAuthStore } from '@/stores/authStore.js'
<<<<<<< HEAD
import { useBoardStore } from './stores/boardStore'
=======
import { useBoardStore } from '@/stores/boardStore.js'
import { useNoticeStore } from '@/stores/noticeStore.js'
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
//sessionStorage에 login된 상태를 확인하면 sessionStorage에 있는 값을 이용해서
//authStore를 갱신
const { setLogin } = useAuthStore()
const { setBoard } = useBoardStore()
<<<<<<< HEAD
=======
const { setNotice } = useNoticeStore()
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
let isLogin = sessionStorage.getItem('isLogin')
if (isLogin == 'true') {
  setLogin({
    isLogin: true,
    memberId: sessionStorage.getItem('memberId'),
    memberName: sessionStorage.getItem('memberName'),
    memberEmail: sessionStorage.getItem('memberEmail'),
    memberPosition: sessionStorage.getItem('memberPosition'),
    memberGender: sessionStorage.getItem('memberGender'),
    memberBirth: sessionStorage.getItem('memberBirth'),
    memberPhone: sessionStorage.getItem('memberPhone'),
    memberRegion: sessionStorage.getItem('memberRegion'),
    sidoName: sessionStorage.getItem('sidoName')
  })
}
let boardList = sessionStorage.getItem('boardList')
if (boardList != null) {
  setBoard({
<<<<<<< HEAD
    offset: sessionStorage.getItem('boardOffset'), //refresh해도 유지 될 수 있도록 추가
=======
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
    limit: sessionStorage.getItem('boardLimit'),
    boardId: sessionStorage.getItem('boardId')
  })
}
<<<<<<< HEAD
=======
let noticeList = sessionStorage.getItem('noticeList')
if (noticeList != null) {
  setNotice({
    limit: sessionStorage.getItem('noticeLimit'),
    noticeId: sessionStorage.getItem('noticeId')
  })
}
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
</script>

<template>
  <main>
    <common-nav></common-nav>
    <router-view></router-view>
    <common-footer></common-footer>
  </main>
</template>

<style scoped></style>
