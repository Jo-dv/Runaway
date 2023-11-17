<script setup>
import CommunitySiteHeader from '../components/community/CommunitySiteHeader.vue'
import { RouterView } from 'vue-router'
</script>

<template>
  <CommunitySiteHeader></CommunitySiteHeader>
  <!--번호 제목 작성자(memberId) 작성일 조회수-->
  <section class="section-bg" style="margin-top: 30px; margin-bottom: 80px">
    <div class="container">
      <div class="row">
        <div class="col-3"></div>
        <router-view></router-view>
        <div class="col-3"></div>
      </div>
    </div>
  </section>
</template>
