<script setup>
import SearchSiteHeader from '../components/search/SearchSiteHeader.vue'
import SearchResult from '../components/search/SearchResult.vue'
import SearchPagination from '../components/search/SearchPagination.vue'
import SearchLoading from '../components/search/SearchLoading.vue'
import { ref, onMounted, watchEffect } from 'vue'

let connectionStatus = ref(true)
let currentCity = ref(1)
const selectedRegion = ref('')
const cityList = ref([])
const regionList = ref([])
const resultList = ref([])

const getCity = async () => {
  try {
    let response = await fetch('http://localhost:8080/trip')
    let data = await response.json()
    cityList.value = data
  } catch (error) {
    console.log(error)
  }
}

const getRegion = async (sidoCode) => {
  regionList.value = []
  let urlParams = new URLSearchParams({ city: sidoCode }) // www-urlencoded
  let fetchOptions = {
    method: 'POST',
    body: urlParams
  }
  try {
    let response = await fetch('http://localhost:8080/trip/getRegion', fetchOptions)
    let data = await response.json()
    regionList.value = data
    selectedRegion.value = regionList.value[0].gugunName // 초기 도시 선택시 지역값 고정
  } catch (error) {
    console.log(error)
  }
}

const search = async (sidoCode, gugunCode) => {
  currentCity.value = sidoCode
  let urlParams = new URLSearchParams({ city: sidoCode, region: gugunCode }) // www-urlencoded
  let fetchOptions = {
    method: 'POST',
    body: urlParams
  }
  try {
    let response = await fetch('http://localhost:8080/trip/search', fetchOptions)
    let data = await response.json()
    resultList.value = data
    connectionStatus.value = false
  } catch (error) {
    connectionStatus.value = true
    console.log(error)
  }
}

onMounted(() => {
  getCity()
  watchEffect(() => {
    getRegion(currentCity.value)
  })
  search(currentCity.value, 0)
})
</script>

<template>
  <SearchSiteHeader></SearchSiteHeader>
  <div class="container pt-3">
    <SearchLoading :connectionStatus="connectionStatus"></SearchLoading>
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
