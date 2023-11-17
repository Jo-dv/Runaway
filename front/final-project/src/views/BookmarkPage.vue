<script setup>
import http from '@/common/axios.js'
import BookmarkSiteHeader from '../components/Bookmark/BookmarkSiteHeader.vue'
import SearchLoading from '../components/search/SearchLoading.vue'
import SearchResult from '../components/search/SearchResult.vue'
import { ref } from 'vue'

let connectionStatus = ref(true)
let emptyData = ref(true)
const resultList = ref([])

const bookmarkList = async () => {
  await http.get('/bookmarks')
  .then(response => {
    resultList.value = response.data
    if(resultList.value.length > 0)
      emptyData.value = false
    connectionStatus.value = false
  })
  .catch(error => {
    connectionStatus.value = true
    console.log(error)
  })
}

bookmarkList()

</script>

<template>
  <BookmarkSiteHeader></BookmarkSiteHeader>
  <section class="section-padding section-bg">
    <div class="container">
      <div class="row">
        <SearchLoading :connectionStatus="connectionStatus"></SearchLoading>
        <div v-show="emptyData && !connectionStatus" class="alert alert-secondary" role="alert">
          <strong>북마크된 관광지 정보가 없습니다.</strong>
        </div>
        <SearchResult :resultList="resultList"></SearchResult>
      </div>
    </div>
  </section>
</template>
