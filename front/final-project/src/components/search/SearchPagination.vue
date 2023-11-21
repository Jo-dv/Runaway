<script setup>
  import {useAttractionStore} from '@/stores/attractionStore'
  import { storeToRefs } from 'pinia'
  const { attractionStore, startPageIndex, endPageIndex, prev, next } = storeToRefs(useAttractionStore());
  const emit = defineEmits(['call-parent'])
  const paginationChanged = (pageIndex) => {
    console.log("paginationVue : paginationChanged : pageIndex : " + pageIndex );
    emit('call-parent', pageIndex);
  }
</script>

<template>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center mb-4">
          <li v-if="prev" class="page-item">
            <a class="page-link" href="#" aria-label="Previous" @click="paginationChanged(startPageIndex - 1)">
              <span aria-hidden="true">Prev</span>
            </a>
          </li>        
          
          <li v-for="index in ( endPageIndex-startPageIndex + 1 )" :key="index"
              v-bind:class="{active: (startPageIndex + index - 1 == attractionStore.currentPageIndex)}" class="page-item">
            <a @click="paginationChanged(startPageIndex + index - 1)" 
              class="page-link">{{ startPageIndex + index - 1 }}</a> <!-- href 는 그대로, 커서 모양 유지-->
          </li>

          <li v-if="next" class="page-item">
            <a class="page-link" href="#" aria-label="Next" @click="paginationChanged(endPageIndex + 1)">
              <span aria-hidden="true">Next</span>
            </a>
          </li>
        </ul>
      </nav>
</template>