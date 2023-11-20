<script setup>
import { useNoticeStore } from '@/stores/noticeStore'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'
import { ref } from 'vue'
import http from '@/common/axios.js'
//common
const router = useRouter()
const { noticeStore, updateNoticeList } = useNoticeStore()
const { authStore } = useAuthStore()

const notice = ref({
  noticeId: '',
  noticeTitle: '',
  noticeContent: '',
  memberId: '',
  memberName: '',
  noticeReadcount: '',
  noticeRegdt: ''
})
const isSameMember = ref(false)
const noticeInform = async () => {
  console.log(noticeStore.noticeId)
  let noticeId = noticeStore.noticeId
  try {
    let { data } = await http.get('/notices/' + noticeId) //shortEnd Property {params : params}
    console.log(data.dto)
    if (data.result == 1) {
      notice.value.noticeId = data.dto.noticeId
      notice.value.noticeTitle = data.dto.noticeTitle
      notice.value.noticeContent = data.dto.noticeContent
      notice.value.memberId = data.dto.memberId
      notice.value.memberName = data.dto.memberName
      notice.value.noticeReadcount = data.dto.noticeReadcount
      notice.value.noticeRegdt = data.dto.noticeRegdt
    }else{
      alert('로그인이 필요합니다')
      router.push({
    name: 'login'
  })
    }
  } catch {
    console.log(error)
  }
  if (notice.value.memberId == authStore.memberId) {
    isSameMember.value = true
  }
}
const detailPage = async () => {
  updateNoticeList(notice.value.noticeTitle, notice.value.noticeContent)
  router.push({
    name: 'noticeUpdate'
  })
}
const deletePage = async () => {
  var result = confirm('계속 진행하시겠습니까?')
  if (result) {
    try {
      let noticeId = noticeStore.noticeId
      let { data } = await http.delete('/notices/' + noticeId)
      if (data.result == 1) {
        alert('성공적으로 삭제되었습니다. ')
        router.push({
          name: 'noticeTable'
        })
      } else {
        alert('삭제에 실패했습니다. ')
      }
    } catch {
      console.error(error)
    }
  }
}
noticeInform()
</script>

<template>
  <section class="topics-detail-section" id="topics-detail" style="margin-top: 30px">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-12 m-auto" style="margin-left: 5px">
          <div class="row" style="height: 30px">
            <h5 class="mb-4 text-truncate">
              {{ notice.noticeTitle }}
            </h5>
          </div>
          <div class="row" style="height: 30px; display: flex">
            <div class="col-10">
              <p class="community-detail-p">
                작성 일자 : {{ notice.noticeRegdt }} &nbsp;&nbsp;&nbsp; 작성자 :
                {{ notice.memberName }}
              </p>
            </div>
            <div class="col-2">
              <p class="community-detail-p">조회수 : {{ notice.noticeReadcount }}</p>
            </div>
          </div>

          <hr />

          <p
            v-html="notice.noticeContent"
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
