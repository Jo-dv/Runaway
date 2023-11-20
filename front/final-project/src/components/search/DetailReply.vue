<script setup>
import { useAttractionReplyStore } from '../../stores/attractionReply'
const { attractionReplyStore, attractionReplyList } = useAttractionReplyStore()
attractionReplyStore.contentId = 125406

attractionReplyList()
</script>

<template></template>

<style scoped></style>
