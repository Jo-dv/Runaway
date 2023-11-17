import { useRouter } from 'vue-router'
import { defineStore } from 'pinia'

export const useDetailStore = defineStore('detailStore', () => {
  // state
  const router = useRouter()
  const searchDetail = async (contentId) => {
    router.push({
      name: 'detail',
      params: { id: contentId }
    })
  }

  return { searchDetail }
})
