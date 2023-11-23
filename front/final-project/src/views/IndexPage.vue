<script setup>
import IndexHeroSection from '../components/index/IndexHeroSection.vue'
import SearchResult from '../components/search/SearchResult.vue'
import { ref, onMounted } from 'vue'
import { useAttractionStore } from '@/stores/attractionStore'
import http from '@/common/axios.js'
const { attractionStore, searchPopular, searchPopularDay, searchPopulaAge } = useAttractionStore()

const test = ref([])
const infoTotalCount = async () => {
    try {
      let { data } = await http.get('/')
      test.value = data
    } catch (error) {
      console.log(error)
    }
  }

onMounted(() => {
  infoTotalCount()
  searchPopular()
  searchPopularDay()
  searchPopulaAge()
})


</script>
<template>
  <index-hero-section></index-hero-section>

  <section class="explore-section section-padding" id="section_1" style="padding-top: 200px">
    <div class="container text-center">
      <div class="row">
          <div class="col-lg-3 col-md-4 col-12 mt-3 mb-4 mb-lg-0">
            <h5>등록된 여행지</h5>
            <h1>{{ test.attractionTotal }}</h1>
          </div>
          <div class="col-lg-3 col-md-4 col-12 mt-3 mb-4 mb-lg-0">
            <h5>전체 회원</h5>
            <h1>{{ test.memberTotal }}</h1>
          </div>
          <div class="col-lg-3 col-md-4 col-12 mt-3 mb-4 mb-lg-0">
            <h5>오늘 방문자</h5>
            <h1>{{ 156 }}</h1>
          </div>
          <div class="col-lg-3 col-md-4 col-12 mt-3 mb-4 mb-lg-0">
            <h5>누적 방문자</h5>
            <h1>{{ 22456 }}</h1>
          </div>
      </div>
    </div>
  </section>

  <section class="explore-section section-padding" id="section_1">
    <div class="container">
      <div class="col-12 text-center">
        <h2 class="mb-4">인기 여행지를 확인해 보세요!</h2>
      </div>

      <div class="row">
        <ul class="nav nav-tabs pe-0" id="myTab_1" role="tablist">
          <li class="nav-item" role="presentation">
            <button
              class="nav-link active"
              id="best-tab"
              data-bs-toggle="tab"
              data-bs-target="#best-tab-pane"
              type="button"
              role="tab"
              aria-controls="best-tab-pane"
              aria-selected="true"
            >
              인기 여행지 BEST 3
            </button>
          </li>

          <li class="nav-item" role="presentation">
            <button
              class="nav-link"
              id="day-tab"
              data-bs-toggle="tab"
              data-bs-target="#day-tab-pane"
              type="button"
              role="tab"
              aria-controls="day-tab-pane"
              aria-selected="false"
            >
              오늘의 인기 여행지
            </button>
          </li>

          <li class="nav-item" role="presentation">
            <button
              class="nav-link"
              id="age-tab"
              data-bs-toggle="tab"
              data-bs-target="#age-tab-pane"
              type="button"
              role="tab"
              aria-controls="age-tab-pane"
              aria-selected="false"
            >
              연령별 인기 여행지
            </button>
          </li>
        </ul>
      </div>
      <div class="row popular">
        <div class="col-12">
          <div class="tab-content" id="myTabContent_1">
            <div
              class="tab-pane fade show active"
              id="best-tab-pane"
              role="tabpanel"
              aria-labelledby="best-tab"
              tabindex="0"
            >
              <SearchResult
                :resultList="attractionStore.resultListBest"
                :indexFlag="attractionStore.indexFlag"
              ></SearchResult>
            </div>

            <div
              class="tab-pane fade"
              id="day-tab-pane"
              role="tabpanel"
              aria-labelledby="day-tab"
              tabindex="0"
            >
              <SearchResult
                :resultList="attractionStore.resultListDay"
                :indexFlag="attractionStore.indexFlag"
              ></SearchResult>
            </div>

            <div
              class="tab-pane fade"
              id="age-tab-pane"
              role="tabpanel"
              aria-labelledby="age-tab"
              tabindex="0"
            >
              <SearchResult
                :resultList="attractionStore.resultListAge"
                :ageFlag="attractionStore.ageFlag"
                :indexFlag="attractionStore.indexFlag"
              ></SearchResult>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
ul {
  list-style: none;
}

.popular {
  height: 360px;
}

#q-tab {
  margin-right: 0px;
}
</style>
