<script setup>
import NoticeSiteHeader from '../components/notice/NoticeSiteHeader.vue';
import NoticePagination from '../components/notice/NoticePagination.vue';
import NoticeTable from '../components/notice/NoticeTable.vue';
</script>

<template>
  <NoticeSiteHeader></NoticeSiteHeader>
  <section class="section-bg" style="margin-top: 30px; margin-bottom: 80px">
    <div class="container">
      <div class="row">
        <div class="col-3"></div>
        <NoticeTable></NoticeTable>
        <div class="col-3"></div>
      </div>
      <NoticePagination></NoticePagination>
    </div>
  </section>
</template>
