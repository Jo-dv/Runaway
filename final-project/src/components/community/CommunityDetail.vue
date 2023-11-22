<script setup>
import { useBoardStore } from '@/stores/boardStore'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'
import { ref, reactive } from 'vue'
import http from '@/common/axios.js'

//common
const router = useRouter()
const { boardStore, updateBoardList } = useBoardStore()
const { message, authStore } = useAuthStore()

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
  // console.log(boardStore.boardId)
  let boardId = boardStore.boardId
  try {
    let { data } = await http.get('/boards/' + boardId) //shortEnd Property {params : params}
    // console.log(data.dto)
    if (data.result == 1) {
      board.value.boardId = data.dto.boardId
      board.value.boardTitle = data.dto.boardTitle
      board.value.boardContent = data.dto.boardContent
      board.value.memberId = data.dto.memberId
      board.value.memberName = data.dto.memberName
      board.value.boardReadcount = data.dto.boardReadcount
      board.value.boardRegdt = data.dto.boardRegdt
      isSameMember.value = data.dto.sameUser
    } else {
      alert(message.noLogin)
      router.push({
        name: 'login'
      })
    }
  } catch (error) {
    console.log(error)
  }
}
const detailPage = async () => {
  updateBoardList(board.value.boardTitle, board.value.boardContent)
  router.push({
    name: 'communityUpdate'
  })
}
const deletePage = async () => {
  var result = confirm(message.continueCheck)
  if (result) {
    try {
      let boardId = boardStore.boardId
      let { data } = await http.delete('/boards/' + boardId)
      if (data.result == 1) {
        alert(message.deleteSuccess)
        router.push({
          name: 'communityTable'
        })
      } else {
        alert(message.deleteError)
      }
    } catch (error) {
      console.error(error)
    }
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
            v-html="board.boardContent"
            style="
              font-size: 16px;
              margin-left: 20px;
              margin-right: 20px;
              margin-top: 30px;
              margin-bottom: 50px;
            "
          ></p>

          <div class="row">
            <div class="col-12 text-end">
              <button @click="deletePage" v-show="isSameMember" class="btn btn-danger btn-margin">
                삭제
              </button>
              <button @click="detailPage" v-show="isSameMember" class="btn btn-primary btn-margin">
                수정
              </button>
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
