<script setup>
import { useRouter } from 'vue-router'
import CommunityPagination from './CommunityPagination.vue'
const router = useRouter()
const searchDetail = async (contentId) => {
  router.push({
    name: 'communityDetail',
    params: {
      contentId: contentId
    }
  })
}
</script>
<template>
  <div class="row" style="margin-bottom: 45px">
    <div class="row">
      <div class="col-lg-6 col-md-6"></div>
      <div class="col-lg-6 col-md-6 text-lg-end text-md-end text-sm-start text-center">
        <button
          type="button"
          class="btn"
          style="
            background: linear-gradient(to right, burlywood, wheat);
            color: white;
            font-weight: bold;
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
        <th class="col-2">번호</th>
        <th class="col-5">제목</th>
        <th class="col-2">작성자</th>
        <th class="col-2">작성일</th>
        <th class="col-1">조회수</th>
      </thead>
      <tbody>
        <tr @click="searchDetail(1)">
          <td>1</td>
          <td>안녕하세요~ 반갑습니다</td>
          <td>송승준</td>
          <td>2023-10-13</td>
          <td>14</td>
        </tr>
        <tr>
          <td>2</td>
          <td>안녕하세요~ 반갑습니다</td>
          <td>송승준</td>
          <td>2023-10-13</td>
          <td>14</td>
        </tr>
        <tr>
          <td>3</td>
          <td>안녕하세요~ 반갑습니다</td>
          <td>송승준</td>
          <td>2023-10-13</td>
          <td>14</td>
        </tr>
        <tr>
          <td>4</td>
          <td>안녕하세요~ 반갑습니다</td>
          <td>송승준</td>
          <td>2023-10-13</td>
          <td>14</td>
        </tr>
        <tr>
          <td>5</td>
          <td>안녕하세요~ 반갑습니다</td>
          <td>송승준</td>
          <td>2023-10-13</td>
          <td>14</td>
        </tr>
      </tbody>
    </table>
  </div>

  <CommunityPagination></CommunityPagination>
</template>
