<script setup>
import NoticeSiteHeader from '../components/notice/NoticeSiteHeader.vue'
import { RouterView } from 'vue-router'
</script>

<template>
  <NoticeSiteHeader></NoticeSiteHeader>
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
