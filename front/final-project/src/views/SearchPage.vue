<script setup>
import SearchSiteHeader from '../components/search/SearchSiteHeader.vue'
import SearchResult from '../components/search/SearchResult.vue'
import SearchPagination from '../components/search/SearchPagination.vue'
import SearchLoading from '../components/search/SearchLoading.vue'
import { onMounted, watchEffect } from 'vue'
import { useAttractionStore } from '@/stores/attractionStore'
const { attractionStore, getCity, getRegion, search } = useAttractionStore()

onMounted(() => {
  getCity()
  watchEffect(() => {
    // 도시가 변경됐을 때만 지역 변경
    getRegion(attractionStore.currentCity)
  })
  search(attractionStore.currentCity, 0)
})
</script>

<template>
  <SearchSiteHeader></SearchSiteHeader>
  <SearchLoading :connectionStatus="attractionStore.connectionStatus"></SearchLoading>
  <div v-if="attractionStore.connectionStatus" class="container pt-3">
    <div class="row row-cols-auto ps-3">
      <div
        class="btnCity ps-0 pe-0"
        v-for="(city, index) in attractionStore.cityList"
        v-bind:key="index"
        @click.prevent="search(city.sidoCode, 0)"
      >
        <input
          type="radio"
          class="btn-check"
          v-model="attractionStore.currentCity"
          :value="city.sidoCode"
          :id="'city' + index"
          autocomplete="off"
        />
        <label class="btn btn-light fs-5 fw-bold" :for="'city' + index">{{ city.sidoName }}</label>
      </div>
    </div>

    <div>
      <hr />
      <ul class="row row-cols-auto ps-3 mb-0">
        <li
          class="btnRegion ps-0 pe-0"
          v-for="(region, index) in attractionStore.regionList"
          v-bind:key="index"
          @click.prevent="search(region.sidoCode, region.gugunCode)"
        >
          <input
            type="radio"
            class="btn-check"
            v-model="attractionStore.currentRegion"
            :value="region.gugunCode"
            :id="'region' + index"
            autocomplete="off"
          />
          <label class="btn btn-light" :for="'region' + index">{{ '#' + region.gugunName }}</label>
        </li>
      </ul>
      <hr />
      <SearchResult :resultList="attractionStore.resultList"></SearchResult>
      <SearchPagination></SearchPagination>
    </div>
  </div>
</template>

<style scoped>
.btnCity {
  text-align: center;
  width: 5.8%;
}

.btnCity label {
  width: 100%;
}

ul {
  list-style: none;
}

.btnRegion {
  text-align: center;
}

@media screen and (max-width: 767px) {
  .btnCity {
    width: 100%; /* Set width to 100% for small screens */
    margin-bottom: 10px; /* Add margin to separate the buttons */
  }
}
</style>
