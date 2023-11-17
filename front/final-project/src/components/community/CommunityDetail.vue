<script setup>
import { useBoardStore } from '@/stores/boardStore'
import { useAuthStore } from '@/stores/authStore'
import { useRoute } from 'vue-router'
import { ref, reactive } from 'vue'
import http from '@/common/axios.js'

//common
const route = useRoute()
const { boardStore } = useBoardStore()
const { authStore } = useAuthStore()

const board = ref({
  boardId: '',
  boardTitle: '',
  boardContent: '',
  memberId: '',
  memberName: '',
  boardReadcount: '',
  boardRegdt: ''
})
const isSameMember = ref(false)
const boardInform = async () => {
  console.log(boardStore.boardId)
  let boardId = boardStore.boardId
  try {
    let { data } = await http.get('/boards/' + boardId) //shortEnd Property {params : params}
    console.log(data)
    board.value.boardId = data.boardId
    board.value.boardTitle = data.boardTitle
    board.value.boardContent = data.boardContent
    board.value.memberId = data.memberId
    board.value.memberName = data.memberName
    board.value.boardReadcount = data.boardReadcount
    board.value.boardRegdt = data.boardRegdt
  } catch {
    console.log(error)
  }
  if (board.value.memberId == authStore.memberId) {
    isSameMember.value = true
  }
}
boardInform()
</script>

<template>
  <section class="topics-detail-section" id="topics-detail" style="margin-top: 30px">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-12 m-auto" style="margin-left: 5px">
          <div class="row" style="height: 30px">
            <h5 class="mb-4 text-truncate">
              {{ board.boardTitle }}
            </h5>
          </div>
          <div class="row" style="height: 30px; display: flex">
            <div class="col-10">
              <p class="community-detail-p">
                작성 일자 : {{ board.boardRegdt }} &nbsp;&nbsp;&nbsp; 작성자 :
                {{ board.memberName }}
              </p>
            </div>
            <div class="col-2">
              <p class="community-detail-p">조회수 : {{ board.boardReadcount }}</p>
            </div>
          </div>

          <hr />

          <p
            style="
              font-size: 16px;
              margin-left: 20px;
              margin-right: 20px;
              margin-top: 30px;
              margin-bottom: 50px;
            "
          >
            {{ board.boardContent }}
          </p>

          <div class="row">
            <div class="col-12 text-end">
              <button v-show="isSameMember" class="btn btn-danger btn-margin">삭제</button>
              <button v-show="isSameMember" class="btn btn-primary btn-margin">수정</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.btn-margin {
  margin-right: 15px;
}
</style>
