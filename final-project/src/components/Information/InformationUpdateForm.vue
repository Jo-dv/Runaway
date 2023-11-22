<script setup>
import { useAuthStore } from '@/stores/authStore'
import { ref, computed, onMounted } from 'vue'
import http from '@/common/axios.js'

const { message, authStore } = useAuthStore()

const emit = defineEmits(['informView', 'updatePwdView'])
//sidoCode
const codeList = ref([]) //sidoCode

//v-model
const updateName = ref(`${authStore.memberName}`)
const updateBirth = ref(`${authStore.memberBirth}`)
const updatePhone = ref(`${authStore.memberPhone}`)
const updateRegion = ref(`${authStore.memberRegion}`)
const updateGender = ref(`${authStore.memberGender}`)
// focus : @focus & @blur ( region & gender는 체크 안함 -> 가입 신청때 함)
const isMemberNameFocus = ref(false)
const isMemberBirthFocus = ref(false)
const isMemberPhoneFocus = ref(false)
const isMemberRegionFocus = ref(false)
const isMemberGenderFocus = ref(false)

// validation : @input
const isMemberNameValid = ref(false)
const isMemberBirthValid = ref(false)
const isMemberPhoneValid = ref(false)
const isMemberRegionValid = ref(false)
const isMemberGenderValid = ref(false)

// focus + valid  <= computed
// valid
const isMemberNameFocusAndValid = computed(() => isMemberNameFocus.value && isMemberNameValid.value)
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

//validation Logic
const validateMemberName = () => {
  let koreanReg = /^[가-힣]+$/ // 한글 확인
  isMemberNameValid.value =
    updateName.value.length > 1 && koreanReg.test(updateName.value) ? true : false
}
const validateMemberBirth = () => {
  let birth = new Date(updateBirth.value)
  let now = new Date()
  isMemberBirthValid.value = birth < now
}
const validateMemberPhone = () => {
  let regexp = new RegExp(/^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/)
  isMemberPhoneValid.value = regexp.test(updatePhone.value)
}
const validateMemberRegion = () => {
  isMemberRegionValid.value = updateRegion.value != 'null'
}
const validateMemberGender = () => {
  isMemberGenderValid.value = updateGender.value != '0'
}

//update로직
const changeView = async (e) => {
  e.preventDefault()
  let updateDto = {
    //name,email,gender,birth, phone,region,id
    memberName: updateName.value,
    memberEmail: authStore.memberEmail,
    memberBirth: updateBirth.value,
    memberRegion: updateRegion.value,
    memberGender: updateGender.value,
    memberPhone: updatePhone.value
  }
  if (
    isMemberNameValid.value &&
    isMemberBirthValid.value &&
    isMemberPhoneValid.value &&
    isMemberRegionValid.value &&
    isMemberGenderValid.value
  ) {
    try {
      let { data } = await http.put('/members', updateDto)
      if (data.result == 'success') {
        alert(message.updateSuccess)
      } else {
        alert(message.updateError + '\n 다시 시도해주십시오.')
      }
    } catch {
      console.error(error)
    }
    authStore.memberName = updateName.value
    authStore.memberBirth = updateBirth.value
    authStore.memberRegion = updateRegion.value
    authStore.memberGender = updateGender.value
    authStore.memberPhone = updatePhone.value
    const updateSidoName = codeList.value
      .filter((item) => item.sidoCode == updateRegion.value)
      .map((item) => item.sidoName)
    authStore.sidoName = updateSidoName
    emit('informView')
  } else alert(message.noValid)
}
const changePwd = (e) => {
  e.preventDefault()
  emit('updatePwdView')
}
//codeList 가져오기
const getCodeList = async () => {
  try {
    let { data } = await http.get('/trip')
    codeList.value = data
  } catch {
    console.log(error)
    console.log('getCodeList오류 발생')
  }
}

//codeList호출
getCodeList()
onMounted(() => {
  validateMemberName()
  validateMemberBirth()
  validateMemberPhone()
  validateMemberRegion()
  validateMemberGender()
})
</script>
<template>
  <div class="col-lg-6 col-sm-12">
    <!--memberName,memberEmail,memberPwd,memberGender,memberBirth,memberPhone,memberRegion-->
    <form action="#" method="post" class="custom-form contact-form" role="form">
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="text"
              name="updateName"
              v-model="updateName"
              class="form-control"
              :class="{
                'is-valid': isMemberNameFocusAndValid,
                'is-invalid': isMemberNameFocusAndInValid
              }"
              @focus="isMemberNameFocus = true"
              @blur="isMemberNameFocus = false"
              @input="validateMemberName"
              placeholder="Name"
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
              type="email"
              name="updateEmail"
              v-model="authStore.memberEmail"
              pattern="[^ @]*@[^ @]*"
              class="form-control"
              placeholder="Email address"
              required="true"
              disabled
            />
            <label for="floatingInput">이메일</label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="tel"
              name="updatePhone"
              v-model="updatePhone"
              :class="{
                'is-valid': isMemberPhoneFocusAndValid,
                'is-invalid': isMemberPhoneFocusAndInValid
              }"
              @focus="isMemberPhoneFocus = true"
              @blur="isMemberPhoneFocus = false"
              @input="validateMemberPhone"
              class="form-control"
              placeholder="연락처"
              required="true"
            />
            <label for="floatingInput">연락처</label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
          <div class="form-floating">
            <input
              type="date"
              name="updateBirth"
              v-model="updateBirth"
              :class="{
                'is-valid': isMemberBirthFocusAndValid,
                'is-invalid': isMemberBirthFocusAndInValid
              }"
              @focus="isMemberBirthFocus = true"
              @blur="isMemberBirthFocus = false"
              @change="validateMemberBirth"
              class="form-control"
              required="true"
            />
            <label for="floatingInput">생년월일</label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
          <select
            name="updateGender"
            class="form-control"
            v-model="updateGender"
            :class="{
              'is-valid': isMemberGenderFocusAndValid,
              'is-invalid': isMemberGenderFocusAndInValid
            }"
            @focus="isMemberGenderFocus = true"
            @blur="isMemberGenderFocus = false"
            @change="validateMemberGender"
          >
            <option value="0">=== 성별을 선택해주세요 ===</option>
            <option value="남">남성</option>
            <option value="여">여성</option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 col-12">
          <select
            name="updateRegion"
            v-model="updateRegion"
            :class="{
              'is-valid': isMemberRegionFocusAndValid,
              'is-invalid': isMemberRegionFocusAndInValid
            }"
            @focus="isMemberRegionFocus = true"
            @blur="isMemberRegionFocus = false"
            @change="validateMemberRegion"
            class="form-control"
          >
            <option :value="null">=== 거주지역을 선택해주세요 ===</option>
            <option v-for="code in codeList" :key="code.sidoCode" :value="code.sidoCode">
              {{ code.sidoName }}
            </option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-6 ms-auto">
          <button class="btn custom-btn2 smoothscroll shadow-sm" @click="changePwd">
            비밀번호 변경
          </button>
        </div>
        <div class="col-6 ms-auto text-end">
          <button class="btn custom-btn2 smoothscroll shadow-sm" @click="changeView">
            정보 수정 완료
          </button>
        </div>
      </div>
    </form>
  </div>
</template>
