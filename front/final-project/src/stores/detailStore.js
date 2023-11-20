import { useRouter } from 'vue-router'
import { defineStore } from 'pinia'

export const useDetailStore = defineStore('detailStore', () => {
  // state
  const router = useRouter()
  const searchDetail = async (content_id) => {
    router.push({
      name: 'detail',
      params: { id: content_id }
    })
  }

  return { searchDetail }
})
