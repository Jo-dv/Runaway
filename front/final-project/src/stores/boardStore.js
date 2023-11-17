import { reactive } from 'vue'
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
    boardId: 0
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
      offset: boardStore.offset
    }
    try {
      let { data } = await http.get('/boards', { params }) //shortEnd Property {params : params}

      //만약 result가 login이면 로그인 해야함.
      if (data.result == 'login') {
        router.push('/login')
      } else {
        setBoardList(data)
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
  return { boardStore, boardList, setBoard }
})
