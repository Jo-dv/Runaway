<script setup>
import { useRouter } from 'vue-router'
import CommunityPagination from '@/components/community/CommunityPagination.vue';
import { useBoardStore } from '@/stores/boardStore'
//common
const router = useRouter()
const { boardStore, boardList,setBoardMovePage } = useBoardStore()

const detailPage = async (boardId) => {
  boardStore.boardId = boardId
  await router.push({
    name: 'communityDetail'
  })
  console.log(router.currentRoute.value)
}
const boardInsert = async () => {
  router.push({
    name: 'communityInsert'
  })
}
boardList()

//pagination
const movePage= (pageIndex) => {
      console.log("CommunityTable : movePage : pageIndex : " + pageIndex);
      setBoardMovePage(pageIndex); //boardStore에 내장
      boardList();
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
          @click="boardInsert()"
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
          v-for="(board, index) in boardStore.list"
          :key="index"
          @click="detailPage(board.boardId)"
        >
          <td>{{ board.boardId }}</td>
          <td>{{ board.boardTitle }}</td>
          <td>{{ board.memberName }}</td>
          <td>{{ board.boardRegdt }}</td>
          <td>{{ board.boardReadcount }}</td>
        </tr>
      </tbody>
    </table>
  </div>
<CommunityPagination v-on:call-parent="movePage"></CommunityPagination>
</template>
