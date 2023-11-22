<script setup>
import { useAuthStore } from '@/stores/authStore'
import { ref, computed, onMounted } from 'vue'
import http from '@/common/axios.js'
const emit = defineEmits(['informView', 'updateView'])
const { message, authStore } = useAuthStore()
//v-model
const memberPwd = ref('')
const memberPwd2 = ref('')
const memberPwd3 = ref('')
//focus
const isMemberPwdFocus = ref(false)
const isMemberPwd2Focus = ref(false)
const isMemberPwd3Focus = ref(false)
//validation
const isMemberPwdValid = ref(false)
const isMemberPwd2Valid = ref(false)
const isMemberPwd3Valid = ref(false)

// focus + valid  <= computed
const isMemberPwdFocusAndValid = computed(() => isMemberPwdFocus.value && isMemberPwdValid.value)
const isMemberPwd2FocusAndValid = computed(() => isMemberPwd2Focus.value && isMemberPwd2Valid.value)
const isMemberPwd3FocusAndValid = computed(() => isMemberPwd3Focus.value && isMemberPwd3Valid.value)
//invalid
const isMemberPwdFocusAndInValid = computed(() => isMemberPwdFocus.value && !isMemberPwdValid.value)
const isMemberPwd2FocusAndInValid = computed(
  () => isMemberPwd2Focus.value && !isMemberPwd2Valid.value
)
const isMemberPwd3FocusAndInValid = computed(
  () => isMemberPwd3Focus.value && !isMemberPwd3Valid.value
)

const validateMemberPwd = () => {
  let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/) // + for at least one
  let patternNumAtListOne = new RegExp(/[0-9]+/) // + for at least one
  let patternEngAtListOne = new RegExp(/[a-zA-Z]+/) // + for at least one
  isMemberPwdValid.value =
    patternEngAtListOne.test(memberPwd.value) &&
    patternSpeAtListOne.test(memberPwd.value) &&
    patternNumAtListOne.test(memberPwd.value) &&
    memberPwd.value.length >= 8
      ? true
      : false
}
const validateMemberPwd2 = () => {
  let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/) // + for at least one
  let patternNumAtListOne = new RegExp(/[0-9]+/) // + for at least one
  let patternEngAtListOne = new RegExp(/[a-zA-Z]+/) // + for at least one
  isMemberPwd2Valid.value =
    memberPwd.value != memberPwd2.value &&
    patternEngAtListOne.test(memberPwd2.value) &&
    patternSpeAtListOne.test(memberPwd2.value) &&
    patternNumAtListOne.test(memberPwd2.value) &&
    memberPwd2.value.length >= 8
      ? true
      : false
}
const validateMemberPwd3 = () => {
  isMemberPwd3Valid.value = memberPwd2.value == memberPwd3.value
}

const goInfo = (e) => {
  e.preventDefault()
  emit('updateView')
}
const changePwd = async (e) => {
  e.preventDefault()
  if (isMemberPwdValid.value && isMemberPwd2Valid.value && isMemberPwd3Valid.value) {
    try {
      let updatePwd = {
        memberId: authStore.memberId,
        memberNewPwd: memberPwd2.value,
        memberPwd: memberPwd.value
      }
      let { data } = await http.put('/members/pwd', updatePwd)
      if (data.result == 'success') {
        alert(message.updatePwdSuccess)
        emit('informView')
      } else {
        alert(message.updatePwdFail)
      }
    } catch {
      console.error(error)
    }
  } else {
    alert(message.noValid)
  }
}
</script>
<template>
  <div class="col-lg-6 col-sm-12">
    <h5 class="head-text">비밀번호 변경</h5>
    <!--memberName,memberEmail,memberPwd,memberGender,memberBirth,memberPhone,memberRegion-->
    <form action="#" method="post" class="custom-form contact-form" role="form">
      <div class="row">
        <div class="col-12">
          <div class="form-floating">
            <input
              type="password"
              name="memberPwd"
              :class="{
                'is-valid': isMemberPwdFocusAndValid,
                'is-invalid': isMemberPwdFocusAndInValid
              }"
              @focus="isMemberPwdFocus = true"
              @blur="isMemberPwdFocus = false"
              @input="validateMemberPwd"
              v-model="memberPwd"
              class="form-control"
              placeholder="비밀번호"
              required="true"
            />
            <label for="floatingInput" style="color: rgb(0, 0, 0, 0.3)">현재 비밀번호</label>
          </div>
        </div>
        <div class="col-12">
          <div class="form-floating">
            <input
              type="password"
              name="memberPwd2"
              :class="{
                'is-valid': isMemberPwd2FocusAndValid,
                'is-invalid': isMemberPwd2FocusAndInValid
              }"
              @focus="isMemberPwd2Focus = true"
              @blur="isMemberPwd2Focus = false"
              @input="validateMemberPwd2"
              v-model="memberPwd2"
              class="form-control"
              placeholder="비밀번호"
              required="true"
            />
            <label for="floatingInput" style="color: rgb(0, 0, 0, 0.3)">새로운 비밀번호</label>
          </div>
        </div>
        <div class="col-12">
          <div class="form-floating">
            <input
              type="password"
              name="memberPwd"
              :class="{
                'is-valid': isMemberPwd3FocusAndValid,
                'is-invalid': isMemberPwd3FocusAndInValid
              }"
              @focus="isMemberPwd3Focus = true"
              @blur="isMemberPwd3Focus = false"
              @input="validateMemberPwd3"
              v-model="memberPwd3"
              class="form-control"
              placeholder="비밀번호"
              required="true"
            />
            <label for="floatingInput" style="color: rgb(0, 0, 0, 0.3)">새로운 비밀번호 확인</label>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-6 ms-auto">
          <button class="btn custom-btn2 smoothscroll shadow-sm" @click="goInfo">뒤로 가기</button>
        </div>
        <div class="col-6 ms-auto text-end">
          <button class="btn custom-btn2 smoothscroll shadow-sm" @click="changePwd">
            비밀번호 변경
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
<style scoped>
.head-text {
  background: linear-gradient(to top, #80d0c756 50%, transparent 50%);
  margin-bottom: 30px;
}
</style>
