<script setup>
import { useAttractionReplyStore } from '../../stores/attractionReply'
import { useAuthStore } from '@/stores/authStore'
import { ref, computed } from 'vue'
import http from '@/common/axios.js'
const { attractionReplyStore, attractionReplyList } = useAttractionReplyStore()
const { authStore } = useAuthStore()

const memberReply = ref('')
const memberUpdateReply = ref('')
const showReply = ref(false)

const selectCommentId = ref(0)
const selectStatus = ref(false)
const getSameMember = async (memberId) => {
  if (authStore.memberId == memberId) {
    return true
  } else {
    return false
  }
}
const replyInsert = async () => {
  let replyInfo = {
    contentId: attractionReplyStore.contentId,
    memberId: authStore.memberId,
    replyContent: memberReply.value
  }
  try {
    console.log(memberReply.value)
    let { data } = await http.post('/trip-replys', replyInfo)
    if (data.result != 1) {
      doLogout() //router-login처리 필요
    } else {
      alert('댓글이 등록되었습니다 ')
      attractionReplyList()
    }
  } catch (error) {
    console.log('Reply Insert lVue: error ')
    console.log(error)
  }
}
const replyDelete = async (replyId) => {
  try {
    let { data } = await http.delete('/trip-replys/' + replyId)
    if (data.result != 1) {
      doLogout()
    } else {
      alert('댓글이 삭제되었습니다')
      attractionReplyList()
    }
  } catch (error) {
    console.log('Reply Delete Vue: error ')
    console.log(error)
  }
}
const replyUpdate = async (replyId) => {
  let replyInfo = {
    replyId: replyId,
    replyContent: memberUpdateReply.value
  }
  console.log(replyInfo)
  try {
    let { data } = await http.put('/trip-replys', replyInfo)
    if (data.result != 1) {
      doLogout()
    } else {
      alert('댓글이 수정되었습니다')
      selectStatus.value = false
      memberUpdateReply.value = ''
      showReply.value = false
      attractionReplyList()
    }
  } catch (error) {
    alert('댓글이 수정되지 못했습니다')
    console.log('Reply Update Vue: error ')
    console.log(error)
  }
}
const toggleUpdate = (replyId) => {
  if (replyId != selectCommentId.value) {
    selectStatus.value = true
    selectCommentId.value = replyId
  } else if (selectStatus.value == true) {
    selectStatus.value = false
  } else {
    selectStatus.value = true
    selectCommentId.value = replyId
  }
  showReply.value = selectStatus.value == true && selectCommentId.value == replyId
}
const moreReply = () => {
  if (attractionReplyStore.limit > attractionReplyStore.totalListItemCount) {
    alert('더 이상 댓글이 없습니다')
  } else {
    console.log(attractionReplyList.limit, attractionReplyStore.totalListItemCount)
    attractionReplyStore.limit += 5
    attractionReplyList()
  }
}
attractionReplyList()
</script>

<template>
  <div class="row">
    <h5 class="head">
      <i class="bi bi-chat-right-heart vibration" style="color: rgb(0,0,0,0.4)"></i>
      후기 작성
    </h5>
  </div>

  <div class="mb-3">
    <textarea
      class="form-control shadow"
      id="exampleFormControlTextarea1"
      rows="3"
      v-model="memberReply"
    ></textarea>
  </div>
  <div class="row">
    <div class="col-12 text-end">
      <button class="custom-btn btn-12" style="padding: 0px" @click="replyInsert">
        <span><i class="bi bi-check-circle"></i></span><span>작성</span>
      </button>
    </div>
  </div>
  <div class="col-11"></div>

  <hr />
  <div class="row" v-for="reply in attractionReplyStore.list" :key="reply.replyId">
    <div class="row rectangle shadow-sm">
      <!-- 상태에 따른 메세지 변환 replyIsDeleted = false-->
      <div class="row" v-show="!reply.replyIsDeleted">
        <div class="col-8">
          <p class="name">{{ reply.memberName }}</p>
        </div>
        <div class="col-4 text-end">
          <p class="date">{{ reply.replyRegdt }}</p>
        </div>
        <div class="row">
          <p class="reply">{{ reply.replyContent }}</p>
        </div>
      </div>
      <!-- <div class="col-12 text-center">
        <textarea
          class="form-control-sm shadow-sm"
          id="exampleFormControlTextarea1"
          rows="3"
          v-model="memberUpdateReply"
        ></textarea>
      </div> -->
      <!-- 상태에 따른 메세지 변환 replyIsDeleted = true-->
      <p class="reply-deleted d-flex align-items-center" v-if="reply.replyIsDeleted">
        삭제된 댓글입니다.
      </p>
    </div>

    <div class="row" v-show="getSameMember(reply.memberId) && !reply.replyIsDeleted">
      <div class="col-12 text-end" style="margin-top: 10px">
        <button
          type="button"
          class="btn btn-outline-danger shadow-sm"
          style="margin-right: 8px"
          @click="replyDelete(reply.replyId)"
        >
          삭제
        </button>
        <!-- <button type="button" class="btn btn-outline-primary shadow-sm" @click="replyUpdate">
          수정
        </button> -->
        <button
          class="btn btn-outline-primary shadow-sm"
          type="button"
          @click="toggleUpdate(reply.replyId)"
        >
          수정
        </button>

        <div class="row update-reply" v-if="showReply && selectCommentId == reply.replyId">
          <div class="col-12 d-flex align-items-center justify-content-center">
            <div class="col-11 text-center">
              <textarea
                class="form-control-sm shadow-sm"
                id="exampleFormControlTextarea1"
                rows="3"
                v-model="memberUpdateReply"
              ></textarea>
            </div>
            <div class="col-1 text-center">
              <button
                class="btn custom-btn2 smoothscroll me-4 shadow-sm"
                @click="replyUpdate(reply.replyId)"
                style="margin-left: 10px"
              >
                <span><i class="bi bi-check-circle"></i></span>
              </button>
              <!-- <button
        class="text-end"
        style="margin-top: 10px; border-radius: 40px"
        @click="replyUpdate(reply.replyId)"
      >
        <i class="bi bi-check-circle"></i>
      </button> -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-12 text-center" style="margin-top: 10px">
      <button class="custom-btn btn-3" @click="moreReply"><span>댓글 더보기</span></button>
    </div>
  </div>
  
</template>

<style scoped>
.update-reply {
  margin-top: 10px;
}
.chat-icon {
  color: linear-gradient(0deg, rgba(175, 155, 223) 0%, rgba(2, 126, 251, 1) 100%);
}
.reply-deleted {
  font-size: 14px;
  margin-left: 20px;
}
.reply {
  font-size: 14px;
  padding: 20px;
  padding-left: 40px;
}
.name {
  font-size: 15px;
  font-weight: 800;
  margin-left: 20px;
  margin-top: 15px;
  margin-bottom: 0px;
}
.date {
  font-size: 14px;
  margin-top: 15px;
  margin-bottom: 0px;
}
.rectangle {
  overflow-y: auto;
  white-space: pre-line;
  margin-top: 10px;
  margin-left: 3px;
  width: 100%;
  min-height: 100px;
  height: 100%;
  border-radius: 40px 80px / 80px 40px;
  background-color: rgb(238, 238, 238);
}
.form-control {
  border-radius: 40px 80px / 80px 40px;
  padding: 20px;
  padding-left: 40px;
}
.form-control-sm {
  width: 100%;
  height: 70%;
  border-radius: 40px 80px / 80px 40px;
  padding: 20px;
  padding-left: 40px;
}
.head {
  margin-left: 20px;
  letter-spacing: 1px;
  line-height: 1.5;
}
button {
  margin-bottom: 10px;
}
.custom-btn {
  width: 90px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 600;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow:
    inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1),
    4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
}

.btn-12 {
  position: relative;
  right: 20px;
  bottom: 20px;
  border: none;
  box-shadow: none;
  width: 90px;
  height: 40px;
  line-height: 42px;
  -webkit-perspective: 230px;
  perspective: 230px;
}
.btn-12 span {
  background: rgb(175, 155, 223);
  background: linear-gradient(0deg, rgba(175, 155, 223) 0%, rgba(2, 126, 251, 1) 100%);
  display: block;
  position: absolute;
  width: 90px;
  height: 40px;
  box-shadow:
    inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1),
    4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin: 0;
  text-align: center;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: all 0.3s;
  transition: all 0.3s;
}
.btn-12 span:nth-child(1) {
  box-shadow:
    -7px -7px 20px 0px #fff9,
    -4px -4px 5px 0px #fff9,
    7px 7px 20px 0px #0002,
    4px 4px 5px 0px #0001;
  -webkit-transform: rotateX(90deg);
  -moz-transform: rotateX(90deg);
  transform: rotateX(90deg);
  -webkit-transform-origin: 50% 50% -20px;
  -moz-transform-origin: 50% 50% -20px;
  transform-origin: 50% 50% -20px;
}
.btn-12 span:nth-child(2) {
  -webkit-transform: rotateX(0deg);
  -moz-transform: rotateX(0deg);
  transform: rotateX(0deg);
  -webkit-transform-origin: 50% 50% -20px;
  -moz-transform-origin: 50% 50% -20px;
  transform-origin: 50% 50% -20px;
}
.btn-12:hover span:nth-child(1) {
  box-shadow:
    inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1),
    4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  -webkit-transform: rotateX(0deg);
  -moz-transform: rotateX(0deg);
  transform: rotateX(0deg);
}
.btn-12:hover span:nth-child(2) {
  box-shadow:
    inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1),
    4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  color: transparent;
  -webkit-transform: rotateX(-90deg);
  -moz-transform: rotateX(-90deg);
  transform: rotateX(-90deg);
}
.btn-3 {
  background: rgb(151, 218, 218);
  width: 130px;
  height: 40px;
  line-height: 42px;
  padding: 0;
  border: none;
}
.btn-3 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}
.btn-3:before,
.btn-3:after {
  position: absolute;
  content: '';
  right: 0;
  top: 0;
  background: rgb(151, 218, 218, 1);
  transition: all 0.3s ease;
}
.btn-3:before {
  height: 0%;
  width: 2px;
}
.btn-3:after {
  width: 0%;
  height: 2px;
}
.btn-3:hover {
  background: transparent;
  box-shadow: none;
}
.btn-3:hover:before {
  height: 100%;
}
.btn-3:hover:after {
  width: 100%;
}
.btn-3 span:hover {
  color: rgb(151, 218, 218, 1);
}
.btn-3 span:before,
.btn-3 span:after {
  position: absolute;
  content: '';
  left: 0;
  bottom: 0;
  background: rgb(151, 218, 218, 1);
  transition: all 0.3s ease;
}
.btn-3 span:before {
  width: 2px;
  height: 0%;
}
.btn-3 span:after {
  width: 0%;
  height: 2px;
}
.btn-3 span:hover:before {
  height: 100%;
}
.btn-3 span:hover:after {
  width: 100%;
}
/* .vibration {
  animation: vibration 1s infinite;
  height: 150px;
}
@keyframes vibration {
  from {
    transform: rotate(1deg);
  }
  to {
    transform: rotate(-1deg);
  }
} */



</style>
