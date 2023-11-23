<script setup>
import IndexHeroSection from '../components/index/IndexHeroSection.vue'
import SearchResult from '../components/search/SearchResult.vue'
import { ref, onMounted } from 'vue'
import { useAttractionStore } from '@/stores/attractionStore'
import { useAuthStore } from '@/stores/authStore'
import http from '@/common/axios.js'

const { authStore } = useAuthStore()
const {
  attractionStore,
  searchPopular,
  searchPopularDay,
  searchPopulaAge,
  searchRandom,
  searchRegion
} = useAttractionStore()

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
  searchRandom()
  if (authStore.isLogin) {
    searchRegion()
  }
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

  <section class="explore-section section-padding" id="section_2">
    <div class="container">
      <div class="col-12 text-center">
        <h2 class="mb-4 pyeongChange">인기 여행지를 확인해 보세요 !</h2>
      </div>

      <div class="row">
        <ul class="nav nav-tabs pe-0" id="myTab2" role="tablist">
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
          <div class="tab-content" id="myTabContent">
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
  <section class="explore-section section-padding" id="section_2">
    <div class="container">
      <div class="col-12 text-center">
        <h4 class="mb-4 pyeongChange">이번 휴가는</h4>
        <h1 class="mb-4 pyeongChange text-sh">{{ attractionStore.randSido }}</h1>
        <h4 class="mb-4 pyeongChange">으로 어떠세요 ?</h4>
      </div>

      <div class="row popular">
        <div class="col-12">
          <div class="tab-content" id="myTabContent_2">
            <div
              class="tab-pane fade show active"
              id="q-tab-pane"
              role="tabpanel"
              aria-labelledby="q-tab"
              tabindex="0"
            >
              <SearchResult
                :resultList="attractionStore.resultListRand"
                :indexFlag="attractionStore.indexFlag"
              ></SearchResult>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <section class="explore-section section-padding" id="section_3" v-if="authStore.isLogin">
    <div class="container">
      <div class="row text-center">
        <h4 class="mb-4 pyeongChange">{{ authStore.memberName }}님의 거주지역</h4>
        <h1 class="mb-4 pyeongChange text-sh2">{{ authStore.sidoName }}</h1>
        <h4 class="mb-4 pyeongChange">의 추천 관광지 입니다 !</h4>
      </div>

      <hr />
      <div class="row popular">
        <div class="col-12">
          <div class="tab-content" id="myTabContent_3">
            <div
              class="tab-pane fade show active"
              id="q-tab-pane"
              role="tabpanel"
              aria-labelledby="q-tab"
              tabindex="0"
            >
              <SearchResult
                :resultList="attractionStore.resultListRegion"
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

@font-face {
  font-family: 'PyeongChangPeace-Bold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-02@1.0/PyeongChangPeace-Bold.woff2')
    format('woff2');
  font-weight: 700;
  font-style: normal;
}
@font-face {
  font-family: 'LINESeedKR-Bd';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2')
    format('woff2');
  font-weight: 700;
  font-style: normal;
}
.pyeongChange {
  font-family: 'PyeongChangPeace-Bold';
}
.line {
  font-family: 'LINESeedKR-Bd';
}
.text-sh {
  text-shadow:
    -2px 0 black,
    0 2px black,
    2px 0 black,
    0 -2px black;

  color: white;
  background: linear-gradient(to top, #80d0c756 50%, transparent 50%);
}
.text-sh2 {
  text-shadow:
    -2px 0 black,
    0 2px black,
    2px 0 black,
    0 -2px black;

  color: white;
  background: linear-gradient(to top, rgba(236, 219, 140, 0.337) 50%, transparent 50%);
}
</style>
