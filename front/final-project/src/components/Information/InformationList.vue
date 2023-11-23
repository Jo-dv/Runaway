<script setup>
import { useAuthStore } from '@/stores/authStore.js'
import { useRouter } from 'vue-router'
const emit = defineEmits(['updateView'])
import http from '@/common/axios.js'
const { authStore, setLogout } = useAuthStore()
const router = useRouter()
const changeView = (e) => {
  e.preventDefault()
  emit('updateView')
}
const memberWithdraw = async (e) => {
  e.preventDefault()
  ///members/delete/{memberId}
  if (confirm('Run away를 탈퇴하시겠습니까?')) {
    try {
      let tmpMemberId = authStore.memberId
      console.log(tmpMemberId)
      // logout() //탈퇴하기 전에 로그아웃부터 실행시키게 한다.--> 백엔드에서 처리
      let { data } = await http.delete('/members/delete/' + tmpMemberId)
      if (data.result == 'success') {
        setLogout()
        alert('회원 탈퇴가 성공적으로 완료되었습니다.')
        router.push('/')
      } else {
        alert('회원 탈퇴에 실패했습니다.')
      }
    } catch {
      console.error(error)
    }
  } else {
  }
}
</script>
<template>
  <div class="col-lg-6 col-sm-12">
    <!--memberName,memberEmail,memberPwd,memberGender,memberBirth,memberPhone,memberRegion-->
    <form action="#" method="post" class="custom-form contact-form" role="form">
      <div class="row">
        <label for="floatingInput" class="information-title">이름</label>
        <div class="col-lg-12 col-12">
          <input
            type="text"
            name="memberName"
            v-model="authStore.memberName"
            class="form-control"
            required="true"
            disabled
          />
        </div>
      </div>
      <div class="row">
        <label for="floatingInput" class="information-title">이메일</label>
        <div class="col-lg-12 col-12">
          <input
            type="email"
            name="memberEmail"
            v-model="authStore.memberEmail"
            pattern="[^ @]*@[^ @]*"
            class="form-control"
            placeholder="Email address"
            required="true"
            disabled
          />
        </div>
      </div>
      <div class="row">
        <label for="floatingInput" class="information-title">연락처</label>
        <div class="col-lg-12 col-12">
          <input
            type="tel"
            name="memberPhone"
            v-model="authStore.memberPhone"
            class="form-control"
            required="true"
            disabled
          />
        </div>
      </div>
      <div class="row">
        <label for="floatingInput" class="information-title">생년월일</label>
        <div class="col-lg-12 col-12">
          <input
            type="date"
            name="memberBirth"
            v-model="authStore.memberBirth"
            class="form-control"
            required="true"
            disabled
          />
        </div>
      </div>
      <div class="row">
        <label for="floatingInput" class="information-title">성별</label>
        <div class="col-lg-12 col-12">
          <input
            type="text"
            name="memberGender"
            v-model="authStore.memberGender"
            class="form-control"
            required="true"
            disabled
          />
        </div>
      </div>

      <div class="row">
        <label for="floatingInput" class="information-title">거주지역</label>
        <div class="col-lg-12 col-12">
          <input
            type="text"
            name="sidoName"
            v-model="authStore.sidoName"
            class="form-control"
            required="true"
            disabled
          />
        </div>
      </div>
      <div class="row">
        <div class="col-6 ms-auto">
          <button class="btn custom-btn2 smoothscroll shadow-sm" @click="memberWithdraw">
            회원 탈퇴
          </button>
        </div>
        <div class="col-6 ms-auto text-end">
          <button class="btn custom-btn2 smoothscroll shadow-sm" @click="changeView">
            정보 수정
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
