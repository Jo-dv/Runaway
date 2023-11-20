<<<<<<< HEAD
import { reactive } from 'vue'
=======
import { reactive,computed } from 'vue'
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import http from '@/common/axios.js'

//여기서 router쓰면 안된다.
//router 쓰려면 define안에 정의
export const useBoardStore = defineStore('boardStore', () => {
  // 로그인 여부, 사용자 이름, 프로필 이미지, 로그인 항목
  const boardStore = reactive({
    offset: 0,
    limit: 10,
    list: [],
<<<<<<< HEAD
    boardId: 0
=======
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
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
  })
  const router = useRouter()
  const setBoardList = async (list) => {
    boardStore.list = list
    sessionStorage.setItem('boardOffset', boardStore.offset) //refresh해도 유지 될 수 있도록 추가
    sessionStorage.setItem('boardLimit', boardStore.limit)
    sessionStorage.setItem('boardId', boardStore.boardId)
  }
  const boardList = async () => {
    let params = {
      limit: boardStore.limit,
<<<<<<< HEAD
      offset: boardStore.offset
=======
      offset: boardStore.offset,
      searchWord: boardStore.searchWord
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
    }
    try {
      let { data } = await http.get('/boards', { params }) //shortEnd Property {params : params}

      //만약 result가 login이면 로그인 해야함.
      if (data.result == 'login') {
        router.push('/login')
      } else {
<<<<<<< HEAD
        setBoardList(data)
=======
        console.log('list Data : ', data)
        setBoardList(data.list)
        setTotalListItemCount(data.count)
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
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
<<<<<<< HEAD
  return { boardStore, boardList, setBoard }
=======
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
      //10, 20...맨마지막
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
    if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      return (
        (boardStore.currentPageIndex / boardStore.pageLinkCount - 1) * boardStore.pageLinkCount +
        boardStore.pageLinkCount
      )
    } else {
      return (
        Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) *
          boardStore.pageLinkCount +
        boardStore.pageLinkCount
      )
    }
  })

  const prev = computed(() =>
    boardStore.currentPageIndex <= boardStore.pageLinkCount ? false : true
  )
  const next = computed(() =>
    Math.floor(pageCount / boardStore.pageLinkCount) * boardStore.pageLinkCount <
    boardStore.currentPageIndex
      ? false
      : true
  )

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
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
})
