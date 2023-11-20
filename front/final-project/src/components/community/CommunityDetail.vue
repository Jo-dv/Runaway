<script setup>
import CommonDetail from '../common/CommonDetail.vue'
import { useRoute } from 'vue-router'
import { ref } from 'vue'
const route = useRoute()
const contentId = ref('')
const checkContentId = () => {
  // from.value = route.query.from
  // content.value = route.query.content
  contentId.value = route.query.contentId
  return contentId.value
}
console.log(checkContentId())
</script>

<template>
  <CommonDetail></CommonDetail>
</template>

<style scoped></style>
