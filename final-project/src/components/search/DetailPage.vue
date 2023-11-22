<script setup>
import http from '@/common/axios.js'
import SearchLoading from './SearchLoading.vue'
import KakaoMap from './KakaoMap.vue'
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import { useAttractionStore } from '@/stores/attractionStore'
import { useAuthStore } from '@/stores/authStore'
import DetailReply from './DetailReply.vue'
const { attractionStore, searchDetail } = useAttractionStore()
const { message, authStore } = useAuthStore()

const route = useRoute()
const router = useRouter()
const showMore = ref(true)
const isBookmarked = ref(false)

const bookMarkValidate = async (contentId) => {
  let isLogin = sessionStorage.getItem('isLogin')
  if (authStore.isLogin || isLogin == 'true ') {
    try {
      let { data } = await http.get(`/bookmarks/${contentId}`)
      if (data == 1) {
        await bookmarkDelete(contentId)
      } else await bookmarkRegister(contentId)
      isBookmarked.value = !isBookmarked.value
    } catch (error) {
      alert(message.error)
    }
  } else {
    alert(message.noLogin)
    router.push({
      name: 'login'
    })
  }
}

const bookmarkDelete = async (contentId) => {
  try {
    await http.delete(`/bookmarks/${contentId}`)
    alert(message.deleteSuccess)
    router.go(-1)
  } catch (error) {
    alert(message.deleteError)
    console.log(error)
  }
}

const bookmarkRegister = async (contentId) => {
  try {
    await http.post('/bookmarks/', contentId)
    alert(message.registerSuccess)
  } catch (error) {
    alert(message.registerError)
    console.log(error)
  }
}

const checkBookmarkStatus = async (contentId) => {
  // 처음 상세 페이지 접근 시 북마크 여부를 확인하는 메서드
  try {
    const { data } = await http.get(`/bookmarks/${contentId}`)
    isBookmarked.value = data == 1
  } catch (error) {
    console.log(error)
  }
}

const scrollToSection = () => {
  const section = document.getElementById('topics-detail')
  section.scrollIntoView({ behavior: 'smooth' })
}

const toggleText = () => {
  showMore.value = !showMore.value
}

onMounted(() => {
  const contentId = route.params.id
  searchDetail(contentId)
  checkBookmarkStatus(contentId)
})
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
              class="custom-icon bi-bookmark"
              @click="bookMarkValidate(attractionStore.resultDetail.contentId)"
              :class="{ 'toggle-yes': isBookmarked, 'toggle-no': !isBookmarked }"
            >
            </a>
          </div>
        </div>
      </div>
    </div>
  </header>
  <SearchLoading :connectionStatus="attractionStore.connectionStatus"></SearchLoading>
  <section
    v-if="attractionStore.connectionStatus"
    class="topics-detail-section"
    style="margin-bottom: 80px"
    id="topics-detail"
  >
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-12 m-auto">
          <h2 class="mb-4" style="height: auto; margin: 20px">&nbsp;Introduction to &nbsp;</h2>
          <hr />
          <div class="intro">
            <img
              :src="attractionStore.resultDetail.firstImage"
              :alt="attractionStore.resultDetail.title"
              class="intro-img"
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

.toggle-no {
  color: var(--white-color);
}

.toggle-no:hover {
  color: var(--secondary-color);
}

.toggle-yes {
  color: var(--secondary-color);
}

.toggle-yes:hover {
  color: var(--white-color);
}

.intro-img {
  border-radius: 10px;
}
</style>
