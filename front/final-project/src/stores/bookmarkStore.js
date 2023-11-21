import { useRouter } from 'vue-router'
import { defineStore } from 'pinia'
import http from '@/common/axios.js'
import { reactive } from 'vue'
import { useAuthStore } from '@/stores/authStore'

export const useBookmarkStore = defineStore('bookmarkStore', () => {
  const { message } = useAuthStore()
    const router = useRouter()
    const bookmarkStore = reactive({
        responseResult: 1
    })
    const bookMarkValidate = async(contentId) => {  // 로그인 여부 확인할 것(23.11.20)
      try {
          let {data} = await http.get(`/bookmarks/${contentId}`)
          bookmarkStore.responseResult = data
          if(bookmarkStore.responseResult  == -1)
            alert(message.noLogin)
          else if(bookmarkStore.responseResult  == 0)
            await bookmarkDelete(contentId);
          else
            await bookmarkRegister(contentId);
        } catch(error) {
          alert(message.error)
          console.log(error)
        }
      }
      
    const bookmarkDelete = async(contentId) => {
        try {
          await http.delete(`/bookmarks/${contentId}`)
          alert(message.deleteSuccess)
          router.go(-1)
        } catch(error) {
          alert(message.deleteError)
          console.log(error)
        }
      }
      
    const bookmarkRegister = async(contentId) => {
        try {
          await http.post('/bookmarks/', contentId)
          alert(message.registerSuccess)
        } catch(error) {
          alert(message.registerError)
          console.log(error)
        }
      }

    return {bookMarkValidate}
})