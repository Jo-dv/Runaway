import { useRouter } from 'vue-router'
import { defineStore } from 'pinia'
import { ref } from 'vue';
import http from '@/common/axios.js'
import { useAuthStore } from '@/stores/authStore'

const check = ref(false);

export const useBookmarkStore = defineStore('bookmarkStore', () => {
  const { message, authStore } = useAuthStore()
    const router = useRouter()

    const bookMarkValidate = async(contentId) => { 
        let isLogin = sessionStorage.getItem('isLogin')
        if (authStore.isLogin || isLogin == 'true ') {
          try {
            let {data} = await http.get(`/bookmarks/${contentId}`)
            if(data == 1)
              await bookmarkDelete(contentId);
            else
              await bookmarkRegister(contentId);
            check.value = !check.value
          } catch(error) {
            alert(message.error)
          }
        }
        else {
          alert(message.noLogin)
          router.push({
            name: 'login'
          })
        }
      }
      
    const bookmarkDelete = async(contentId) => {
        try {
          await http.delete(`/bookmarks/${contentId}`)
          alert(message.deleteSuccess)
          // router.go(-1)
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

    return {bookMarkValidate, check}
})