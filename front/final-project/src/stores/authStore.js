import { reactive } from 'vue'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('authStore', () => {
  // 로그인 여부, 사용자 이름, 프로필 이미지, 로그인 항목
  const authStore = reactive({
    isLogin: false, //전체에서 하나만 있어야 한다.
    memberId: 0,
    memberName: '',
    memberEmail: 'jisu@naver.com', // 임시 : ''여야함.
    memberPosition: '',
    memberGender: '',
    memberBirth: '',
    memberPhone: '',
    memberRegion: '',
    sidoName: ''
  }) //id,name,email,position,gender,birth,phone,region,sidoName

  // login 후 변경
  const setLogin = (payload) => {
    // console.log(payload)
    authStore.isLogin = payload.isLogin
    authStore.memberId = payload.memberId
    authStore.memberName = payload.memberName
    authStore.memberEmail = payload.memberEmail
    authStore.memberPosition = payload.memberPosition
    authStore.memberGender = payload.memberGender
    authStore.memberBirth = payload.memberBirth
    authStore.memberPhone = payload.memberPhone
    authStore.memberRegion = payload.memberRegion
    authStore.sidoName = payload.sidoName
    // console.log('여기는 setLogin')

    // console.log(authStore)
  }
  const logout = async () => {
    try {
      let { data } = await http.get('/logout')

      if (data.result == 'success') {
        setLogout()
      }
    } catch (error) {
      console.error(error)
    }
  }
  const setLogout = () => {
    sessionStorage.removeItem('isLogin')
    sessionStorage.removeItem('memberId')
    sessionStorage.removeItem('memberName')
    sessionStorage.removeItem('memberEmail')
    sessionStorage.removeItem('memberPosition')
    sessionStorage.removeItem('memberGender')
    sessionStorage.removeItem('memberBirth')
    sessionStorage.removeItem('memberPhone')
    sessionStorage.removeItem('memberRegion')
    sessionStorage.removeItem('sidoName')
    setLogin({
      isLogin: false,
      memberName: '',
      memberId:0,
      memberEmail: '',
      memberPosition: '',
      memberGender: '',
      memberBirth: '',
      memberPhone: '',
      memberRegion: 0
    })

  }
  return { authStore, setLogin ,setLogout}
})
