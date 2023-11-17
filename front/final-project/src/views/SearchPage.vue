<script setup>
import http from '@/common/axios.js'
import SearchSiteHeader from '../components/search/SearchSiteHeader.vue'
import SearchResult from '../components/search/SearchResult.vue'
import SearchPagination from '../components/search/SearchPagination.vue'
import SearchLoading from '../components/search/SearchLoading.vue'
import { ref, watchEffect } from 'vue'

let connectionStatus = ref(true)
let currentCity = ref(1)
const selectedRegion = ref('')
const cityList = ref([])
const regionList = ref([])
const resultList = ref([])

const getCity = async () => {
  await http.get('/trip')
  .then(response => {cityList.value = response.data})
  .catch(error => {console.log(error)})
}

const getRegion = async (sidoCode) => {
  regionList.value = []  // 지역마다 서로 다른 지역을 가지므로 호출될 때마다 초기화
  await http.post('/trip/getRegion', sidoCode)
  .then(response => {
    regionList.value = response.data
    selectedRegion.value = regionList.value[0].gugunName // 초기 도시 선택시 지역값 고정
  })
  .catch(error => {console.log(error)})
}

const search = async (sidoCode, gugunCode) => {
  currentCity.value = sidoCode
  const params = {
    'city': sidoCode,
    'region': gugunCode
  }
  await http.post('/trip/search', params)
  .then(response => {
    resultList.value = response.data
    connectionStatus.value = false
  })
  .catch(error => {
    connectionStatus.value = true
    console.log(error)
  })
}

getCity()
watchEffect(() => {  // 도시가 변경됐을 때를 감지해서 표시 지역을 변경하기 위함
  getRegion(currentCity.value)
})
search(currentCity.value, 0)
</script>

<template>
  <SearchSiteHeader></SearchSiteHeader>
  <div class="container pt-3">
    <div class="row">
        <button
          style="width: 5.8823%;"
          class="btn btn-light fs-5 fw-bold ps-0 pe-0"
          v-for="(city, index) in cityList"
          v-bind:key="index"
          @click="search(city.sidoCode, 0)"
        >
          {{ city.sidoName }}
        </button>
        <SearchLoading :connectionStatus="connectionStatus"></SearchLoading>
    </div>
    <div v-if="!connectionStatus">
      <hr/>
        <ul class="row ps-0">
          <li class="btnRegion col-1 fs-6 pe-0 mb-16" 
            v-for="(region, index) in regionList" 
            v-bind:key="index"
            @click="search(region.sidoCode, region.gugunCode)"
          >
            {{ region.gugunName }}
          </li>
        </ul>
      <hr/>
      <SearchResult :resultList="resultList"></SearchResult>
      <SearchPagination></SearchPagination>
    </div>
  </div>
</template>

<style scoped>
ul {
  list-style: none;
}

li {
  width: 130px;
  text-align: center;
}

.btnRegion {
  cursor: pointer;
}

</style>
