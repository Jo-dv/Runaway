import { reactive, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import http from '@/common/axios.js'

//여기서 router쓰면 안된다.
//router 쓰려면 define안에 정의
export const useAttractionReplyStore = defineStore('attractionReplyStore', () => {
  // 로그인 여부, 사용자 이름, 프로필 이미지, 로그인 항목
  const attractionReplyStore = reactive({
    limit: 5,
    list: [],
    replyId: 0,

    replyContent: '',
    contentId: 0,
    totalListItemCount: 0
  })
  const router = useRouter()
  const setAttractionReplyList = async (list) => {
    attractionReplyStore.list = list
  }
  const attractionReplyList = async () => {
    let params = {
      limit: attractionReplyStore.limit,
      contentId: attractionReplyStore.contentId
    }
    try {
      let { data } = await http.get('/trip/replys', { params }) //shortEnd Property {params : params}
      // console.log(data.list)
      //만약 result가 login이면 로그인 해야함.
      if (data.result == 'login') {
        router.push('/login')
      } else {
        // console.log('reply list Data : ', data)
        setAttractionReplyList(data.list)
        setTotalListItemCount(data.count)
      }
    } catch {
      console.log(error)
    }
  }
  const setAttractionReply = (payload) => {
    // console.log(payload)
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
  const setTotalListItemCount = (count) => (attractionReplyStore.totalListItemCount = count)

  return {
    attractionReplyStore,
    setAttractionReplyList,
    updateBoardList,
    updateBoardDelete,
    attractionReplyList,
    setAttractionReply
  }
})
