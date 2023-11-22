import { reactive, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import http from '@/common/axios.js'

//여기서 router쓰면 안된다.
//router 쓰려면 define안에 정의
export const useNoticeStore = defineStore('noticeStore', () => {
  // 로그인 여부, 사용자 이름, 프로필 이미지, 로그인 항목
  const noticeStore = reactive({
    offset: 0,
    limit: 10,
    list: [],
    noticeId: 0,
    searchWord: '',
    sameUser: false,

    // pagination
    listRowCount: 5, //한줄에 몇개 ?
    pageLinkCount: 5, //한페이지에 몇개 보여줄거야? 1. 2 3 4 5 >>next => 5
    currentPageIndex: 1, //현재 페이지 인덱스
    totalListItemCount: 0, //총건수

    //update
    noticeTitle: '',
    noticeContent: ''
  })
  const router = useRouter()
  const setNoticeList = async (list) => {
    noticeStore.list = list
    sessionStorage.setItem('noticeOffset', noticeStore.offset) //refresh해도 유지 될 수 있도록 추가
    sessionStorage.setItem('noticeLimit', noticeStore.limit)
    sessionStorage.setItem('noticeId', noticeStore.noticeId)
  }
  const noticeList = async () => {
    let params = {
      limit: noticeStore.limit,
      offset: noticeStore.offset,
      searchWord: noticeStore.searchWord
    }
    try {
      let { data } = await http.get('/notices', { params }) //shortEnd Property {params : params}

      //만약 result가 login이면 로그인 해야함.
      if (data.result == 'login') {
        router.push('/login')
      } else {
        setNoticeList(data.list)
        setTotalListItemCount(data.count)
      }
    } catch {
      console.log(error)
    }
  }
  const setNotice = (payload) => {
    // console.log(payload)
    noticeStore.offset = payload.offset
    noticeStore.limit = payload.limit
    noticeStore.noticeId = payload.noticeId
  }
  //update
  const updateNoticeList = (title, content) => {
    noticeStore.noticeTitle = title
    noticeStore.noticeContent = content

    sessionStorage.setItem('noticeTitle', title)
    sessionStorage.setItem('noticeContent', content)
  }
  const updateNoticeDelete = () => {
    noticeStore.noticeTitle = ''
    noticeStore.noticeContent = ''

    sessionStorage.removeItem('noticeTitle')
    sessionStorage.removeItem('noticeContent')
  }
  //pagination methods
  const setTotalListItemCount = (count) => (noticeStore.totalListItemCount = count)

  const setNoticeMovePage = (pageIndex) => {
    //param이 들어오면 그에 맞춰서 offset, current갱신
    noticeStore.offset = (pageIndex - 1) * noticeStore.listRowCount
    noticeStore.currentPageIndex = pageIndex
  }
  const pageCount = computed(() =>
    Math.ceil(noticeStore.totalListItemCount / noticeStore.listRowCount)
  )
  const startPageIndex = computed(() => {
    if (noticeStore.currentPageIndex % noticeStore.pageLinkCount == 0) {
      return (
        (noticeStore.currentPageIndex / noticeStore.pageLinkCount - 1) * noticeStore.pageLinkCount +
        1
      )
    } else {
      return (
        Math.floor(noticeStore.currentPageIndex / noticeStore.pageLinkCount) *
          noticeStore.pageLinkCount +
        1
      )
    }
  })

  const endPageIndex = computed(() => {
    let tempEndPageIndex = 0
    if (noticeStore.currentPageIndex % noticeStore.pageLinkCount == 0) {
      tempEndPageIndex =
        (noticeStore.currentPageIndex / noticeStore.pageLinkCount - 1) * noticeStore.pageLinkCount +
        noticeStore.pageLinkCount
    } else {
      tempEndPageIndex =
        Math.floor(noticeStore.currentPageIndex / noticeStore.pageLinkCount) *
          noticeStore.pageLinkCount +
        noticeStore.pageLinkCount
    }
    if (tempEndPageIndex > pageCount.value) tempEndPageIndex = pageCount.value
    return tempEndPageIndex
  })

  const prev = computed(() =>
    noticeStore.currentPageIndex <= noticeStore.pageLinkCount ? false : true
  )
  const next = computed(() => (endPageIndex.value == pageCount.value ? false : true))

  return {
    noticeStore,
    noticeList,
    setNotice,
    updateNoticeList,
    updateNoticeDelete,
    setNoticeMovePage,
    startPageIndex,
    endPageIndex,
    prev,
    next
  }
})
