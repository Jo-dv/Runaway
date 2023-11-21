<script setup>
import { ref } from 'vue'
import http from '@/common/axios.js'

import CKEditor from '@ckeditor/ckeditor5-vue'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { useAuthStore } from '@/stores/authStore'
import { useNoticeStore } from '../../stores/noticeStore'
import router from '../../router'
const { authStore } = useAuthStore()
const { noticeStore, updateNoticeDelete } = useNoticeStore()

const ckeditor = CKEditor.component
const editor = ClassicEditor
const editorData = ref(noticeStore.noticeContent)
const editorConfig = {}

const title = ref(noticeStore.noticeTitle)

const noticeUpdate = async () => {
  let NoticeDto = {
    noticeId: noticeStore.noticeId,
    noticeTitle: title.value,
    noticeContent: editorData.value
  }
  try {
    let { data } = await http.put('/notices', NoticeDto)
    console.log(data)
    if (data.result != 1) {
      doLogout()
    } else {
      alert('글이 수정되었습니다 ')
      updateNoticeDelete()
    }
  } catch (error) {
    console.log('InsertModalVue: error ')
    console.log(error)
  }
  router.push({
    name: 'noticeTable'
  })
}
const doLogout = () => {
  authStore.setLogout()
  router.push('/login')
}
</script>
<template>
  <section class="topics-detail-section" id="topics-detail" style="margin-top: 30px">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-12 m-auto" style="margin-left: 5px">
          <div class="mb-3">
            <input v-model="title" type="text" class="form-control" placeholder="제목" />
          </div>
          <div class="mb-3">
            <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
          </div>
          <div class="text-end">
            <!-- 추가: text-end 클래스를 사용하여 오른쪽 정렬 -->
            <button @click="noticeUpdate" class="btn btn-sm btn-primary btn-outline" type="button">
              수정
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<style scoped>
.btn {
  font-size: 15px;
}
.container:deep(.ck-editor__editable) {
  min-height: 600px;
}

.container:deep(p) {
  font-size: 16px !important;
}
</style>
