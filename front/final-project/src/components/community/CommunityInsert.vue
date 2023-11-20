<script setup>
import { ref, onMounted } from 'vue'
import http from '@/common/axios.js'

import CKEditor from '@ckeditor/ckeditor5-vue'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { useAuthStore } from '@/stores/authStore'

import router from '../../router'
const { authStore } = useAuthStore()

const ckeditor = CKEditor.component
const editor = ClassicEditor
const editorData = ref('')
const editorConfig = {
  fontSize: {
    options: [8, 10, 12, 'default', 14, 16, 18, 20]
  }
}

const title = ref('')

const initUI = () => {
  title.value = ''
  editorData.value = '' //editorData의 값을 init
}
const boardInsert = async () => {
  let BoardDto = {
    boardTitle: title.value,
    boardContent: editorData.value
  }
  try {
    let { data } = await http.post('/boards', BoardDto)
    console.log(data)
    if (data.result != 1) {
      doLogout()
    } else {
      // this.$alertify.success('글이 등록되었습니다.');
      alert('글이 등록되었습니다 ')
    }
  } catch (error) {
    console.log('InsertModalVue: error ')
    console.log(error)
  }
  router.push({
    name: 'communityTable'
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
            <button @click="boardInsert" class="btn btn-sm btn-primary btn-outline" type="button">
              등록
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
