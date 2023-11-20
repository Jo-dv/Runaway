<script setup>
import BookmarkSiteHeader from '../components/Bookmark/BookmarkSiteHeader.vue'
import BookmarkCard from '../components/Bookmark/BookmarkCard.vue'
</script>

<template>
  <BookmarkSiteHeader></BookmarkSiteHeader>
  <section class="section-padding section-bg">
    <div class="container">
      <div class="row">
        <BookmarkCard></BookmarkCard>
        <BookmarkCard></BookmarkCard>
        <BookmarkCard></BookmarkCard>
        <BookmarkCard></BookmarkCard>
      </div>
    </div>
  </section>
</template>
