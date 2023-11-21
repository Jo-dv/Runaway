<script setup>
import { RouterView } from 'vue-router'
import CommonNav from './components/common/CommonNav.vue'
import CommonFooter from './components/common/CommonFooter.vue'

import { useAuthStore } from '@/stores/authStore.js'
//sessionStorage에 login된 상태를 확인하면 sessionStorage에 있는 값을 이요해서
//authStore를 갱신
const { setLogin } = useAuthStore()
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
</script>

<template>
  <main>
    <common-nav></common-nav>
    <router-view></router-view>
    <common-footer></common-footer>
  </main>
</template>

<style scoped>
#app {
  min-height: 100%;
  height: auto;
  position: relative;
  padding-bottom: 40px;
}
</style>
