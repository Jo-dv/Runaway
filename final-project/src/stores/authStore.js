import { reactive } from 'vue'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('authStore', () => {
  const message = reactive({
    noLogin: '로그인 후 이용 가능합니다.',
    loginFail: '이메일 혹은 비밀번호를 확인하세요.',
    logOut: '로그아웃 되었습니다.',
    error: '문제가 발생했습니다.',
    deleteSuccess: '삭제되었습니다.',
    deleteError: '삭제 과정에서 문제가 발생했습니다.',
    registerSuccess: '등록되었습니다.',
    registerError: '등록 과정에서 문제가 발생했습니다.',
    updateSuccess: '수정되었습니다.',
    updateError: '수정 과정에서 문제가 발생했습니다.',
    continueCheck: '계속 진행하시겠습니까?',
    requireAdmin: '관리자 권한이 필요합니다.',
    requireTitle: '제목을 입력하세요.',
    requireContent: '내용을 입력하세요.',
    noValid: '유효하지 않은 입력입니다.',
    updatePwdSuccess: '비밀번호 변경에 성공했습니다',
    updatePwdFail: '비밀번호 변경에 성공했습니다'
  })

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
      memberId: 0,
      memberEmail: '',
      memberPosition: '',
      memberGender: '',
      memberBirth: '',
      memberPhone: '',
      memberRegion: 0
    })
  }
  return { message, authStore, setLogin, setLogout, logout }
})
