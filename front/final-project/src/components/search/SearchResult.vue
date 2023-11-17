<script setup>
import { useDetailStore } from '@/stores/detailStore'
defineProps(['resultList']);

const store = useDetailStore();
const {searchDetail} = store
</script>

<template>
  <div class="row">
    <div class="col-lg-4 col-md-4 col-12 mt-3 mb-4 mb-lg-0" v-for="(item, index) in resultList" :key="index" @click="searchDetail(item.contentId)">
      <div class="custom-block bg-white shadow-lg">
        <div class="">
          <div>
            <img :src="item.firstImage ? item.firstImage : '/src/assets/images/no_Image.jpg'" :alt="item.title" style="width: 100%; height: 13em" />
            <h5 class="mb-2" style="margin-top: 30px; margin-bottom: 10px">{{ item.title.length > 12 ? item.title.slice(0, 12) + '...' : item.title }}</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>