<script setup>
import { RouterView } from 'vue-router'
import CommonNav from './components/common/CommonNav.vue'
import CommonFooter from './components/common/CommonFooter.vue'

import { useAuthStore } from '@/stores/authStore.js'
import { useBoardStore } from '@/stores/boardStore.js'
import { useNoticeStore } from '@/stores/noticeStore.js'
//sessionStorage에 login된 상태를 확인하면 sessionStorage에 있는 값을 이용해서
//authStore를 갱신
const { setLogin } = useAuthStore()
const { setBoard } = useBoardStore()
const { setNotice } = useNoticeStore()
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
    limit: sessionStorage.getItem('boardLimit'),
    boardId: sessionStorage.getItem('boardId')
  })
}
let noticeList = sessionStorage.getItem('noticeList')
if (noticeList != null) {
  setNotice({
    limit: sessionStorage.getItem('noticeLimit'),
    noticeId: sessionStorage.getItem('noticeId')
  })
}
</script>

<template>
  <main>
    <common-nav></common-nav>
    <router-view></router-view>
    <common-footer></common-footer>
  </main>
</template>

<style scoped></style>
