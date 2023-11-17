<script setup>
import {useAuthStore} from '@/stores/authStore.js'
const emit = defineEmits(['updateView'])

const { authStore } = useAuthStore()

const changeView = (e) => {
  e.preventDefault() 
  emit('updateView')
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
        <div class="col-lg-4 col-sm-12 ms-auto">
          <button
            type="submit"
            class="form-control"
            @click="changeView"
            style="background: linear-gradient(to right, rgb(216, 208, 136) #f2c94c); color: white"
          >
            Update
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
