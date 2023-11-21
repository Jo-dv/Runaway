<script setup>
import SearchLoading from './SearchLoading.vue'
import KakaoMap from './KakaoMap.vue'
import { useRoute } from 'vue-router'
import { ref } from 'vue'
import { useAttractionStore } from '@/stores/attractionStore'
import { useBookmarkStore } from '@/stores/bookmarkStore'
import DetailReply from './DetailReply.vue'
const { attractionStore, searchDetail } = useAttractionStore()
const { bookMarkValidate } = useBookmarkStore()

const route = useRoute()
const showMore = ref(true)

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
  <header class="site-header d-flex flex-column justify-content-center align-items-center">
    <div class="container">
      <div
        v-if="attractionStore.connectionStatus"
        class="row justify-content-center align-items-center"
      >
        <div class="col-lg-8 col-12 mb-5">
          <h1 class="text-white">
            {{ attractionStore.resultDetail.title }}
          </h1>

          <div class="d-flex align-items-center mt-5">
            <button
              class="btn custom-btn2 custom-border-btn2 smoothscroll me-4"
              @click="scrollToSection"
            >
              Read More
            </button>
            <a
              class="custom-icon bi-bookmark smoothscroll"
              @click="bookMarkValidate(attractionStore.resultDetail.contentId)"
            ></a>
          </div>
        </div>
      </div>
    </div>
  </header>
  <SearchLoading :connectionStatus="attractionStore.connectionStatus"></SearchLoading>
  <section
    v-if="attractionStore.connectionStatus"
    class="topics-detail-section" 
    style=" margin-bottom: 80px;"
    id="topics-detail" 
  >
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-12 m-auto">
          <h2 class="mb-4" style="height: auto; margin: 20px;" >
            <!-- <span><i class="bi bi-airplane" style="color: rgb(151, 218, 218, 1)"></i> </span> -->
            &nbsp;Introduction to &nbsp;
          </h2>
          <hr />
          <div class="intro">
            <img
              :src="attractionStore.resultDetail.firstImage"
              :alt="attractionStore.resultDetail.title"
            />
          </div>
          <hr />
          <div>
            <p>
              {{
                attractionStore.resultDetail.overview
                  ? attractionStore.resultDetail.overview.length > 120 && showMore
                    ? attractionStore.resultDetail.overview.slice(0, 120) + '...'
                    : attractionStore.resultDetail.overview
                  : ''
              }}
            </p>
            <!-- 작동에는 문제 없는데, 오류 및 경고가 거슬려서 처리함: 받아온 문자열이 있는지 확인하고 문자열 길이에 따라 문자열 축약 -->
            <button
              class="btn custom-btn2"
              v-if="
                attractionStore.resultDetail.overview &&
                attractionStore.resultDetail.overview.length > 120
              "
              @click="toggleText"
            >
              {{ showMore ? '더 보기' : '간략히 보기' }}
            </button>
          </div>
          <hr />
          <div>
            <ul>
              <li>주소: {{ attractionStore.resultDetail.addr1 }}</li>
              <li>
                문의 및 안내:
                {{
                  attractionStore.resultDetail.tel ? attractionStore.resultDetail.tel : '추가 예정'
                }}
              </li>
            </ul>
          </div>
          <KakaoMap
            :latitude="attractionStore.resultDetail.latitude"
            :longitude="attractionStore.resultDetail.longitude"
          ></KakaoMap>
          <div style="margin-top: 40px"></div>
          <DetailReply></DetailReply>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.intro img {
  /* 사진 크기 페이지 비율에 맞춤*/
  width: 100%;
}
h2 {
  height: 100px;
}
h2 span {
  position: relative;
  top: 0px;
  display: inline-block;
  animation: bounce 1s ease infinite alternate;
  font-family: 'Titan One', cursive;
  font-size: 80px;
  color: #FFF;
  text-shadow: 0 1px 0 #CCC,
               0 2px 0 #CCC,
               0 3px 0 #CCC,
               0 4px 0 #CCC,
               0 5px 0 #CCC,
               0 6px 0 transparent,
               0 7px 0 transparent,
               0 5px 5px rgba(0, 0, 0, .4);
}

/* animation*/
@keyframes bounce {
  100% {
    top:0px;
    text-shadow:
      0 1px 0 #ccc,
      0 2px 0 #ccc,
      0 3px 0 #ccc,
      0 4px 0 #ccc,
      0 5px 0 #ccc,
      0 6px 0 #ccc,
      0 7px 0 #ccc,
      0 40px 25px rgba(0, 0, 0, 0.2);
  }
}
</style>
