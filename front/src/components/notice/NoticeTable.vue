<script setup>
import { useRouter } from 'vue-router'
import NoticePagination from './NoticePagination.vue'
import { useNoticeStore } from '@/stores/noticeStore'
import { computed } from 'vue'
import { useAuthStore } from '@/stores/authStore'

//common
const router = useRouter()

//store
const { noticeStore, noticeList, setNoticeMovePage } = useNoticeStore()
const { authStore } = useAuthStore()
const detailPage = async (noticeId) => {
  noticeStore.noticeId = noticeId
  console.log('NoticeDetail detail Page: ' + noticeId)
  await router.push({
    name: 'noticeDetail'
  })
  // console.log(router.currentRoute.value)
}
const noticeInsert = async () => {
  router.push({
    name: 'noticeInsert'
  })
}
const getPosition = computed(() => {
  if (authStore.memberPosition == '관리자') {
    return true
  } else {
    return false
  }
})
noticeList()

//pagination
const movePage = (pageIndex) => {
  console.log('NoticeTable : movePage : pageIndex : ' + pageIndex)
  setNoticeMovePage(pageIndex) //noticeStore에 내장
  noticeList()
}
</script>
<template>
  <div class="row" style="margin-bottom: 45px">
    <div class="row">
      <div class="col-lg-6 col-md-6"></div>
      <div class="col-lg-6 col-md-6 text-lg-end text-md-end text-sm-start text-center">
        <button
          v-show="getPosition"
          type="button"
          class="btn custom-btn btn-primary shadow-sm"
          @click="noticeInsert()"
          style="
            color: white;
            font-weight: bold;
            border: none;
            background-image: linear-gradient(45deg, #8baaaa 0%, #ae8b9c 100%);
          "
        >
          글 작성
        </button>
      </div>
    </div>
  </div>
  <div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th class="col-2">번호</th>
          <th class="col-5">제목</th>
          <th class="col-2">작성자</th>
          <th class="col-2">작성일</th>
          <th class="col-1">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(notice, index) in noticeStore.list"
          :key="index"
          @click="detailPage(notice.noticeId)"
        >
          <td>{{ notice.noticeId }}</td>
          <td>{{ notice.noticeTitle }}</td>
          <td>{{ notice.memberName }}</td>
          <td>{{ notice.noticeRegdt }}</td>
          <td>{{ notice.noticeReadcount }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <NoticePagination v-on:call-parent="movePage"></NoticePagination>
</template>
