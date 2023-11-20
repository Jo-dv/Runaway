import { useRouter } from 'vue-router'
import { defineStore } from 'pinia'
import http from '@/common/axios.js'
import { reactive } from 'vue'

export const useBookmarkStore = defineStore('bookmarkStore', () => {
    const router = useRouter()
    const bookmarkStore = reactive({
        responseResult: false
    })
    const bookMarkValidate = async(contentId) => {  // 로그인 여부 확인할 것(23.11.20)
      try {
          let {data} = await http.get(`/bookmarks/${contentId}`)
          bookmarkStore.responseResult = data
          if(bookmarkStore.responseResult)
            await bookmarkDelete(contentId);
          else
            await bookmarkRegister(contentId);
        } catch(error) {
          alert('문제가 발생했습니다.')
          console.log(error)
        }
      }
      
    const bookmarkDelete = async(contentId) => {
        try {
          await http.delete(`/bookmarks/${contentId}`)
          alert("삭제되었습니다.")
          router.go(-1)
        } catch(error) {
          alert('삭제 과정에서 문제가 발생했습니다.')
          console.log(error)
        }
      }
      
    const bookmarkRegister = async(contentId) => {
        try {
          await http.post('/bookmarks/', contentId)
          alert("등록되었습니다.")
        } catch(error) {
          alert("등록 과정에서 문제가 발생했습니다.")
          console.log(error)
        }
      }

    return {bookMarkValidate}
})