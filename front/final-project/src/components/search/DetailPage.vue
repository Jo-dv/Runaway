<script setup>
import KakaoMap from './KakaoMap.vue'
import { useRoute } from 'vue-router'
import { ref } from 'vue'

const route = useRoute()
const result = ref([])
const showMore = ref(true)

const searchDetail = async (content_id) => {
  let urlParams = new URLSearchParams({ contentId: content_id }) // www-urlencoded
  let fetchOptions = {
    method: 'POST',
    body: urlParams
  }
  try {
    let response = await fetch('http://localhost:8080/trip/searchDetail', fetchOptions)
    let data = await response.json()
    result.value = data
  } catch (error) {
    console.log(error)
  }
}
const scrollToSection = () => {
  const section = document.getElementById('topics-detail')
  section.scrollIntoView({ behavior: 'smooth' })
}
searchDetail(route.params.id)

const toggleText = () => {
  showMore.value = !showMore.value
}
</script>

<template>
  <main>
    <header class="site-header d-flex flex-column justify-content-center align-items-center">
      <div class="container">
        <div class="row justify-content-center align-items-center">
          <div class="col-lg-8 col-12 mb-5">
            <h1 class="text-white">
              {{ result.title }}
            </h1>

            <div class="d-flex align-items-center mt-5">
              <button
                class="btn custom-btn2 custom-border-btn2 smoothscroll me-4" @click="scrollToSection">
                Read More
              </button>
              <a href="" class="custom-icon bi-bookmark smoothscroll"></a>
            </div>
          </div>
        </div>
      </div>
    </header>

    <section class="topics-detail-section section-padding" id="topics-detail">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-12 m-auto">
            <h2 class="mb-4">Introduction to</h2>
            <hr />
            <div class="intro">
              <img :src="result.firstImage" :alt="result.title" />
            </div>
            <hr />
            <div>
              <p>
                {{
                  result.overview
                    ? result.overview.length > 120 && showMore
                      ? result.overview.slice(0, 120) + '...'
                      : result.overview
                    : ''
                }}
              </p>
              <!-- 작동에는 문제 없는데, 오류 및 경고가 거슬려서 처리함: 받아온 문자열이 있는지 확인하고 문자열 길이에 따라 문자열 축약 -->
              <button
                class="btn custom-btn2"
                v-if="result.overview && result.overview.length > 120"
                @click="toggleText"
              >
                {{ showMore ? '더 보기' : '간략히 보기' }}
              </button>
            </div>
            <hr />
            <div>
              <ul>
                <li>주소: {{ result.addr1 }}</li>
                <li>문의 및 안내: {{ result.tel ? result.tel : '추가 예정' }}</li>
              </ul>
            </div>
            <KakaoMap :latitude="result.latitude" :longitude="result.longitude"></KakaoMap>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<style scoped>
.intro img {
  /* 사진 크기 페이지 비율에 맞춤*/
  width: 100%;
}
</style>
