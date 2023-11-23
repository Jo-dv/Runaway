<script setup>
import { RouterLink, useRouter } from 'vue-router'
import { ref, reactive, computed } from 'vue'
import http from '@/common/axios.js'
import { useAuthStore } from '@/stores/authStore'
const router = useRouter()
const codeList = ref([]) //sidoCode
const { message } = useAuthStore()

//v-model 8개
const memberName = ref('')
const memberPwd = ref('')
const memberPwd2 = ref('')
const memberEmail = ref('')
const memberGender = ref('0')
const memberBirth = ref('')
const memberPhone = ref('')
const memberRegion = ref('null')

// focus : @focus & @blur ( region & gender는 체크 안함 -> 가입 신청때 함)
const isMemberNameFocus = ref(false)
const isMemberPwdFocus = ref(false)
const isMemberPwd2Focus = ref(false)
const isMemberEmailFocus = ref(false)
const isMemberBirthFocus = ref(false)
const isMemberPhoneFocus = ref(false)
const isMemberRegionFocus = ref(false)
const isMemberGenderFocus = ref(false)

// validation : @input
const isMemberNameValid = ref(false)
const isMemberPwdValid = ref(false)
const isMemberPwd2Valid = ref(false)
const isMemberEmailValid = ref(false)
const isMemberBirthValid = ref(false)
const isMemberPhoneValid = ref(false)
const isMemberRegionValid = ref(false)
const isMemberGenderValid = ref(false)

// focus + valid  <= computed
// valid
const isMemberNameFocusAndValid = computed(() => isMemberNameFocus.value && isMemberNameValid.value)
const isMemberEmailFocusAndValid = computed(
  () => isMemberEmailFocus.value && isMemberEmailValid.value
)
const isMemberPwdFocusAndValid = computed(() => isMemberPwdFocus.value && isMemberPwdValid.value)
const isMemberPwd2FocusAndValid = computed(() => isMemberPwd2Focus.value && isMemberPwd2Valid.value)
const isMemberBirthFocusAndValid = computed(
  () => isMemberBirthFocus.value && isMemberBirthValid.value
)
const isMemberPhoneFocusAndValid = computed(
  () => isMemberPhoneFocus.value && isMemberPhoneValid.value
)
const isMemberGenderFocusAndValid = computed(
  () => isMemberGenderFocus.value && isMemberGenderValid.value
)
const isMemberRegionFocusAndValid = computed(
  () => isMemberRegionFocus.value && isMemberRegionValid.value
)

//invalid
const isMemberNameFocusAndInValid = computed(
  () => isMemberNameFocus.value && !isMemberNameValid.value
)
const isMemberEmailFocusAndInValid = computed(
  () => isMemberEmailFocus.value && !isMemberEmailValid.value
)
const isMemberPwdFocusAndInValid = computed(() => isMemberPwdFocus.value && !isMemberPwdValid.value)
const isMemberPwd2FocusAndInValid = computed(
  () => isMemberPwd2Focus.value && !isMemberPwd2Valid.value
)
const isMemberBirthFocusAndInValid = computed(
  () => isMemberBirthFocus.value && !isMemberBirthValid.value
)
const isMemberPhoneFocusAndInValid = computed(
  () => isMemberPhoneFocus.value && !isMemberPhoneValid.value
)
const isMemberGenderFocusAndInValid = computed(
  () => isMemberGenderFocus.value && !isMemberGenderValid.value
)
const isMemberRegionFocusAndInValid = computed(
  () => isMemberRegionFocus.value && !isMemberRegionValid.value
)

//회원가입
const register = async (e) => {
  e.preventDefault()

  let memberDto = {
    memberName: memberName.value,
    memberEmail: memberEmail.value,
    memberBirth: memberBirth.value,
    memberPwd: memberPwd.value,
    memberGender: memberGender.value,
    memberPhone: memberPhone.value,
    memberRegion: memberRegion.value
  }
  if (
    isMemberNameValid.value &&
    isMemberPwdValid.value &&
    isMemberPwd2Valid.value &&
    isMemberEmailValid.value &&
    isMemberBirthValid.value &&
    isMemberPhoneValid.value &&
    isMemberRegionValid.value &&
    isMemberGenderValid.value
  ) {
    console.log(memberDto.memberRegion)
    try {
      let { data } = await http.post('/members', memberDto)
      if (data.result == 'success') {
        alert('회원가입에 성공하셨습니다')
      } else {
        alert('회원가입에 실패하셨습니다. \n 다시 시도해주십시오.')
      }
      router.push('/')
    } catch {
      console.log(error)
      console.log('register 오류 발생')
    }
  } else alert(message.noValid)
}

//sidoCode가져오기
const getCodeList = async () => {
  try {
    let { data } = await http.get('/trip')

    codeList.value = data
    console.log(codeList.value)
  } catch {
    console.log(error)
    console.log('getCodeList오류 발생')
  }
}

//codeList호출
getCodeList()

//validation
const validateMemberName = () => {
  let koreanReg = /^[가-힣]+$/ // 한글 확인
  isMemberNameValid.value =
    memberName.value.length > 1 && koreanReg.test(memberName.value) ? true : false
}
const validateMemberEmail = () => {
  let regexp = new RegExp(
    /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
  )
  isMemberEmailValid.value = regexp.test(memberEmail.value)
}
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
const validateMemberBirth = () => {
  let birth = new Date(memberBirth.value)
  let now = new Date()
  isMemberBirthValid.value = birth < now
}

const validateMemberPhone = () => {
  let regexp = new RegExp(/^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/)
  isMemberPhoneValid.value = regexp.test(memberPhone.value)
}
const validateMemberPwd2 = () => {
  isMemberPwd2Valid.value = memberPwd.value == memberPwd2.value
}
const validateMemberRegion = () => {
  isMemberRegionValid.value = memberRegion.value != 'null'
}
const validateMemberGender = () => {
  isMemberGenderValid.value = memberGender.value != '0'
}
</script>
<template>
  <div class="col-lg-5 col-sm-12">
    <!--memberName,memberEmail,memberPwd,memberGender,memberBirth,memberPhone,memberRegion-->
    <form action="#" method="post" class="custom-form contact-form" role="form">
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="text"
              name="memberName"
              v-model="memberName"
              :class="{
                'is-valid': isMemberNameFocusAndValid,
                'is-invalid': isMemberNameFocusAndInValid
              }"
              @focus="isMemberNameFocus = true"
              @blur="isMemberNameFocus = false"
              @input="validateMemberName"
              class="form-control"
              placeholder="이름"
              required="true"
            />
            <label for="floatingInput">이름</label>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="text"
              name="memberPhone"
              :class="{
                'is-valid': isMemberPhoneFocusAndValid,
                'is-invalid': isMemberPhoneFocusAndInValid
              }"
              @focus="isMemberPhoneFocus = true"
              @blur="isMemberPhoneFocus = false"
              @input="validateMemberPhone"
              v-model="memberPhone"
              class="form-control"
              placeholder="핸드폰 번호"
              required="true"
            />
            <label for="floatingInput">핸드폰 번호</label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="email"
              name="memberEmail"
              :class="{
                'is-valid': isMemberEmailFocusAndValid,
                'is-invalid': isMemberEmailFocusAndInValid
              }"
              @focus="isMemberEmailFocus = true"
              @blur="isMemberEmailFocus = false"
              @input="validateMemberEmail"
              v-model="memberEmail"
              pattern="[^ @]*@[^ @]*"
              class="form-control"
              placeholder="이메일"
              required="true"
            />
            <label for="floatingInput">이메일</label>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-12 col-12">
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
            <label for="floatingInput">비밀번호</label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
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
              placeholder="비밀번호 확인"
              required="true"
            />
            <label for="floatingInput">비밀번호 확인</label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="date"
              name="memberBirth"
              :class="{
                'is-valid': isMemberBirthFocusAndValid,
                'is-invalid': isMemberBirthFocusAndInValid
              }"
              @focus="isMemberBirthFocus = true"
              @blur="isMemberBirthFocus = false"
              @change="validateMemberBirth"
              v-model="memberBirth"
              class="form-control"
              required="true"
            />
            <label for="floatingInput">생일</label>
          </div>
        </div>
      </div>
      <div class="row">
        <!--v-model="memberRegion"-->
        <div class="col-lg-6 col-6">
          <select
            name="memberRegion"
            :class="{
              'is-valid': isMemberRegionFocusAndValid,
              'is-invalid': isMemberRegionFocusAndInValid
            }"
            @focus="isMemberRegionFocus = true"
            @blur="isMemberRegionFocus = false"
            @change="validateMemberRegion"
            v-model="memberRegion"
            class="form-control"
          >
            <option :value="null">= 거주지역을 선택해주세요 =</option>
            <option v-for="code in codeList" :key="code.sidoCode" :value="code.sidoCode">
              {{ code.sidoName }}
            </option>
          </select>
        </div>
        <div class="col-lg-6 col-6">
          <select
            name="memberGender"
            v-model="memberGender"
            class="form-control"
            :class="{
              'is-valid': isMemberGenderFocusAndValid,
              'is-invalid': isMemberGenderFocusAndInValid
            }"
            @focus="isMemberGenderFocus = true"
            @blur="isMemberGenderFocus = false"
            @change="validateMemberGender"
          >
            <option value="0">= 성별을 선택해주세요 =</option>
            <option value="남">남성</option>
            <option value="여">여성</option>
          </select>
        </div>
      </div>
      <div class="row"></div>
      <div class="row">
        <div class="col-lg-4 col-sm-12 ms-auto">
          <button
            type="submit"
            class="form-control"
            @click="register"
            style="background: linear-gradient(to right, burlywood, wheat); color: white"
          >
            Register
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
