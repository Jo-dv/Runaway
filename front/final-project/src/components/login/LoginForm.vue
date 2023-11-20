<script setup>
import { RouterLink, useRouter } from 'vue-router'
import { reactive } from 'vue'
import http from '@/common/axios.js'

import { useAuthStore } from '@/stores/authStore'

const { authStore, setLogin } = useAuthStore()

const router = useRouter()
const member = reactive({
  memberEmail: 'fisu@naver.com', //임시
  memberPwd: 'jisuuuuu'
})

const login = async (e) => {
  e.preventDefault()

  let memberDto = {
    memberEmail: member.memberEmail,
    memberPwd: member.memberPwd
  }
  try {
    let { data } = await http.post('/login', memberDto)
    if (data.result == 'success') {
      sessionStorage.setItem('isLogin', 'true')
      // sessionStorage.setItem('memberId', data.memberId)
      sessionStorage.setItem('memberName', data.memberName)
      sessionStorage.setItem('memberEmail', data.memberEmail)
      sessionStorage.setItem('memberPosition', data.memberPosition)
      sessionStorage.setItem('memberGender', data.memberGender)
      sessionStorage.setItem('memberBirth', data.memberBirth)
      sessionStorage.setItem('memberPhone', data.memberPhone)
      sessionStorage.setItem('memberRegion', data.memberRegion)
      sessionStorage.setItem('sidoName', data.sidoName)
      console.log(data)
      setLogin({
        isLogin: true,
        // memberId : data.memberId,
        memberName: data.memberName,
        memberEmail: data.memberEmail,
        memberPosition: data.memberPosition,
        memberGender: data.memberGender,
        memberBirth: data.memberBirth,
        memberPhone: data.memberPhone,
        memberRegion: data.memberRegion,
        sidoName: data.sidoName
      })
      router.push('/')
    } else if (data.result == 'fail') {
      alert('이메일 또는 비밀번호를 확인하세요.')
    }
  } catch {
    console.log(error)
  }
}
</script>
<template>
  <div class="col-lg-5 col-sm-12">
    <form action="#" method="post" class="custom-form contact-form" role="form">
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="email"
              name="email"
              v-model="member.memberEmail"
              pattern="[^ @]*@[^ @]*"
              class="form-control"
              placeholder="Email address"
              required="true"
            />

            <label for="floatingInput">Email address</label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="password"
              name="subject"
              v-model="member.memberPwd"
              class="form-control"
              placeholder="Password"
              required="true"
            />

            <label for="floatingInput">Password</label>
          </div>
        </div>
      </div>
      <div class="row" style="margin-bottom: 20px">
        <div class="col-lg-5 col-sm-12 ms-auto">
          <router-link style="color: azure; font-weight: 500; letter-spacing: 1px" to="/register"
            >>> 계정이 없으신가요?</router-link
          >
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 col-sm-12 ms-auto">
          <button
            type="submit"
            class="form-control"
            @click="login"
            style="background: linear-gradient(to right, #ed6ea0, #ec8c69)"
          >
            Submit
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
