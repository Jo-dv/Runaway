<script setup>
import http from '@/common/axios.js'
import SearchSiteHeader from '../components/search/SearchSiteHeader.vue'
import SearchResult from '../components/search/SearchResult.vue'
import SearchPagination from '../components/search/SearchPagination.vue'
import SearchLoading from '../components/search/SearchLoading.vue'
import { ref, onMounted, watchEffect } from 'vue'

let connectionStatus = ref(false)
let currentCity = ref(1)
let currentRegion = ref(0)
const cityList = ref([])
const regionList = ref([])
const resultList = ref([])

const getCity = async () => {
  try {
    let { data } = await http.get('/trip')
    cityList.value = data
  } catch(error) {
    console.log(error)
  }
}

const getRegion = async (sidoCode) => {
  regionList.value = []  // 지역마다 서로 다른 지역을 가지므로 호출될 때마다 초기화
  try {
    let { data } = await http.get('/trip/getRegion/' + sidoCode)
    regionList.value = data
  } catch(error) {
    console.log(error)
  }
}

const search = async (sidoCode, gugunCode) => {
  if(currentCity.value != sidoCode)  // 도시가 변경되면 지역을 0으로 변경
    currentRegion.value = 0
  currentCity.value = sidoCode
  try {
    let { data } = await http.get(`/trip/search/?city=${sidoCode}&region=${gugunCode}`)
    resultList.value = data
    connectionStatus.value = true
  } catch(error) {
    connectionStatus.value = false
    console.log(error)
  }
}

onMounted(() => {
  getCity()
  watchEffect(() => {  // 도시가 변경됐을 때만 지역 변경
    getRegion(currentCity.value)
  })
  search(currentCity.value, 0)
})
</script>

<template>
  <SearchSiteHeader></SearchSiteHeader>
  <SearchLoading :connectionStatus="connectionStatus"></SearchLoading>
  <div v-if="connectionStatus" class="container pt-3">
    <div class="row">
      <div class="btn-group" role="group">
        <div class="btnCity" v-for="(city, index) in cityList" v-bind:key="index" @click="search(city.sidoCode, 0)">
          <input type="radio" class="btn-check" v-model="currentCity" :value="city.sidoCode" :id="'city' + index" autocomplete="off">
          <label class="btn btn-light fs-5 fw-bold" :for="'city' + index">{{city.sidoName}}</label>
       </div>
      </div>
    </div>
    <div>
    <hr/>
      <ul class="row btn-group mb-0" role="group">
        <li class="col-1 p-0" v-for="(region, index) in regionList" v-bind:key="index" @click="search(region.sidoCode, region.gugunCode)">
          <input type="radio" class="btn-check" v-model="currentRegion" :value="region.gugunCode" :id="'region' + index" autocomplete="off">
          <label class="btn btn-light" :for="'region' + index">{{ region.gugunName }}</label>
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

ul.row.btn-group {
  padding-left: 16px;
  padding-right: 16px;
}

.btn-group {
  width: 100%;
} 
.btnCity {
  width: 55.09%
}

.btn-group label {  /*상위 div 크기에 맞춤*/ 
  width: 100%
}
</style>
