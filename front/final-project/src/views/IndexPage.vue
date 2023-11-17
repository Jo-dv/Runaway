<script setup>
import http from '@/common/axios.js'
import IndexHeroSection from '../components/index/IndexHeroSection.vue'
import SearchResult from '../components/search/SearchResult.vue'
import { ref, onMounted } from 'vue'

const resultListBest = ref([])
const resultListDay = ref([])
const resultListAge = ref([])

const searchPopular = async () => {
  await http.get('/trip/searchPopular')
  .then(response => {resultListBest.value = response.data})
  .catch(error => {console.log(error)})
}

const searchPopularDay = async () => {
  await http.get('/trip/searchPopularDay')
  .then(response => {resultListDay.value = response.data})
  .catch(error => {console.log(error)})
}

const searchPopulaAge = async () => {
  await http.get('/trip/searchPopularAge')
  .then(response => {resultListAge.value = response.data})
  .catch(error => {console.log(error)})
}

onMounted(() => {
  searchPopular()
  searchPopularDay()
  searchPopulaAge()
})

</script>
<template>
  <index-hero-section></index-hero-section>

  <section class="explore-section section-padding" id="section_2">
    <div class="container">
      <div class="col-12 text-center">
        <h2 class="mb-4">인기 여행지를 확인해 보세요!</h2>
      </div>

      <div class="row" >
        <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="best-tab" data-bs-toggle="tab" data-bs-target="#best-tab-pane" type="button" role="tab" aria-controls="best-tab-pane" aria-selected="true">
              인기 여행지 BEST 5
            </button>
          </li>

          <li class="nav-item" role="presentation">
            <button class="nav-link" id="day-tab" data-bs-toggle="tab" data-bs-target="#day-tab-pane" type="button" role="tab" aria-controls="day-tab-pane" aria-selected="false">
              오늘의 인기 여행지
            </button>
          </li>

          <li class="nav-item" role="presentation">
            <button class="nav-link" id="age-tab" data-bs-toggle="tab" data-bs-target="#age-tab-pane" type="button" role="tab" aria-controls="age-tab-pane" aria-selected="false">
              연령별 인기 여행지
            </button>
          </li>
        </ul>
      </div>

      <div class="row popular">
        <div class="col-12">
          <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="best-tab-pane" role="tabpanel" aria-labelledby="best-tab" tabindex="0">
              <div class="row">
                <SearchResult :resultList="resultListBest"></SearchResult>
              </div>
            </div>

            <div class="tab-pane fade" id="day-tab-pane" role="tabpanel" aria-labelledby="day-tab" tabindex="0">
              <div class="row">
                <SearchResult :resultList="resultListDay"></SearchResult>
              </div>
            </div>

            <div class="tab-pane fade" id="age-tab-pane" role="tabpanel" aria-labelledby="age-tab" tabindex="0">
              <div class="row">
                <SearchResult :resultList="resultListAge" :ageFlag="true"></SearchResult>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

</template>

<style scoped>
.popular {
  height: 360px;
}
</style>