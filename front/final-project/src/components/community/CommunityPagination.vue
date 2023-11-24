<script setup>
import { useBoardStore } from '@/stores/boardStore'
import { storeToRefs } from 'pinia'
const { boardStore, startPageIndex, endPageIndex, prev, next } = storeToRefs(useBoardStore())
const emit = defineEmits(['call-parent'])
const paginationChanged = (pageIndex) => {
  // console.log('paginationVue : paginationChanged : pageIndex : ' + pageIndex)
  emit('call-parent', pageIndex)
}
</script>
<template>
  <div class="col-lg-12 col-12">
    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center mb-0">
        <li v-if="prev" class="page-item">
          <a
            class="page-link"
            aria-label="Previous"
            @click="paginationChanged(startPageIndex - 1)"
          >
            <span aria-hidden="true">Prev</span>
          </a>
        </li>

        <li
          v-for="index in endPageIndex - startPageIndex + 1"
          :key="index"
          :class="{
            active: startPageIndex + index - 1 == boardStore.currentPageIndex
          }"
          class="page-item"
          aria-current="page"
        >
          <a class="page-link" @click="paginationChanged(startPageIndex + index - 1)">{{
            startPageIndex + index - 1
          }}</a>
        </li>

        <li v-if="next" class="page-item">
          <a
            class="page-link"
            aria-label="Next"
            @click="paginationChanged(endPageIndex + 1)"
          >
            <span aria-hidden="true">Next</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>
<style scoped></style>
