import { reactive, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/authStore.js'
import http from '@/common/axios.js'

//여기서 router쓰면 안된다.
//router 쓰려면 define안에 정의
export const useBoardStore = defineStore('boardStore', () => {
  // 로그인 여부, 사용자 이름, 프로필 이미지, 로그인 항목
  const boardStore = reactive({
    offset: 0,
    limit: 10,
    list: [],
    boardId: 0,
    searchWord: '',
    sameUser: false,

    // pagination
    listRowCount: 5, //한줄에 몇개 ?
    pageLinkCount: 5, //한페이지에 몇개 보여줄거야? 1. 2 3 4 5 >>next => 5
    currentPageIndex: 1, //현재 페이지 인덱스
    totalListItemCount: 0, //총건수

    //update
    boardTitle: '',
    boardContent: ''
  })
  const router = useRouter()
  const { authStore, setLogout } = useAuthStore()
  const setBoardList = async (list) => {
    boardStore.list = list
    sessionStorage.setItem('boardOffset', boardStore.offset) //refresh해도 유지 될 수 있도록 추가
    sessionStorage.setItem('boardLimit', boardStore.limit)
    sessionStorage.setItem('boardId', boardStore.boardId)
  }
  const boardList = async () => {
    let params = {
      limit: boardStore.limit,
      offset: boardStore.offset,
      searchWord: boardStore.searchWord
    }
    try {
      let { data } = await http.get('/boards', { params }) //shortEnd Property {params : params}
      console.log(data)
      //만약 result가 login이면 로그인 해야함.
      if (data.result == 'login') {
        if (authStore.isLogin) {
          setLogout()
        }
        router.push('/login')
      } else {
        setBoardList(data.list)
        setTotalListItemCount(data.count)
      }
    } catch {
      console.log(error)
    }
  }
  const setBoard = (payload) => {
    // console.log(payload)
    boardStore.offset = payload.offset
    boardStore.limit = payload.limit
    boardStore.boardId = payload.boardId
  }
  //update
  const updateBoardList = (title, content) => {
    boardStore.boardTitle = title
    boardStore.boardContent = content

    sessionStorage.setItem('boardTitle', title)
    sessionStorage.setItem('boardContent', content)
  }
  const updateBoardDelete = () => {
    boardStore.boardTitle = ''
    boardStore.boardContent = ''

    sessionStorage.removeItem('boardTitle')
    sessionStorage.removeItem('boardContent')
  }
  //pagination methods
  const setTotalListItemCount = (count) => (boardStore.totalListItemCount = count)

  const setBoardMovePage = (pageIndex) => {
    //param이 들어오면 그에 맞춰서 offset, current갱신
    boardStore.offset = (pageIndex - 1) * boardStore.listRowCount
    boardStore.currentPageIndex = pageIndex
  }
  const pageCount = computed(() =>
    Math.ceil(boardStore.totalListItemCount / boardStore.listRowCount)
  )
  const startPageIndex = computed(() => {
    if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
      return (
        (boardStore.currentPageIndex / boardStore.pageLinkCount - 1) * boardStore.pageLinkCount + 1
      )
    } else {
      return (
        Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) *
          boardStore.pageLinkCount +
        1
      )
    }
  })

  const endPageIndex = computed(() => {
    let tempEndPageIndex = 0
    if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      tempEndPageIndex =
        (boardStore.currentPageIndex / boardStore.pageLinkCount - 1) * boardStore.pageLinkCount +
        boardStore.pageLinkCount
    } else {
      tempEndPageIndex =
        Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) *
          boardStore.pageLinkCount +
        boardStore.pageLinkCount
    }
    if (tempEndPageIndex > pageCount.value) tempEndPageIndex = pageCount.value
    return tempEndPageIndex
  })

  const prev = computed(() =>
    boardStore.currentPageIndex <= boardStore.pageLinkCount ? false : true
  )
  const next = computed(() => (endPageIndex.value == pageCount.value ? false : true))

  return {
    boardStore,
    boardList,
    setBoard,
    updateBoardList,
    updateBoardDelete,
    setBoardMovePage,
    startPageIndex,
    endPageIndex,
    prev,
    next
  }
})
