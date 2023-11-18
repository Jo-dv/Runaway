<script setup>
import { useDetailStore } from '@/stores/detailStore'
defineProps(['resultList', 'ageFlag', 'indexFlag']);

const store = useDetailStore();
const {searchDetail} = store
</script>

<template>
  <div class="row">
    <div class="col-lg-4 col-md-4 col-12 mt-3 mb-4 mb-lg-0" 
    v-for="(item, index) in indexFlag ? 3 : resultList " :key="index" @click="searchDetail(resultList[index].contentId)">
      <div class="custom-block bg-white shadow-lg">
        <div class="">
          <div>
            <span v-if="ageFlag" style="text-align: center;"><h3>{{ (index+2) * 10 + "대" }}</h3></span>
            <div v-if="!resultList[index]"  class="card" aria-hidden="true">
              <img src="/src/assets/images/no_Image.jpg" class="card-img-top" alt="...">
              <div class="card-body">
                <p class="card-text placeholder-glow">
                  <span class="placeholder col-4"></span>
                  <span class="placeholder col-6"></span>
                  <span class="placeholder col-8"></span>
                </p>
              </div>
            </div>
            <div v-else>
              <img :src="resultList[index].firstImage ? resultList[index].firstImage : '/src/assets/images/no_Image.jpg'" :alt="resultList[index].title"/>
              <h5 class="mb-2" style="margin-top: 30px; margin-bottom: 10px">
                {{ resultList[index].title.length > 12 ? resultList[index].title.slice(0, 12) + '...' : resultList[index].title }}
              </h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
  img {
    width: 100%; 
    height: 13em
  }
</style>