<script setup>
import InformationSiteHeader from '../components/Information/InformationSiteHeader.vue'
import InformationList from '../components/Information/InformationList.vue'
import InformationUpdatForm from '../components/Information/InformationUpdateForm.vue'
import InformationUpdatePwdForm from '../components/Information/InformationUpdatePwdForm.vue'
import { ref } from 'vue'
const informStatus = ref(true)
const updateStatus = ref(false)
const updatePwdStatus = ref(false)
const updateView = () => {
  informStatus.value = false
  updateStatus.value = true
  updatePwdStatus.value = false
}
const updatePwdView = () => {
  informStatus.value = false
  updateStatus.value = false
  updatePwdStatus.value = true
}
const informView = () => {
  informStatus.value = true
  updateStatus.value = false
  updatePwdStatus.value = false
}
const initView = () => {
  informStatus.value = true
  updateStatus.value = false
  updatePwdStatus.value = false
}
initView()
</script>

<template>
  <InformationSiteHeader></InformationSiteHeader>
  <section class="section-bg" style="margin-top: 30px; margin-bottom: 80px">
    <div class="container">
      <div class="row">
        <div class="col-3"></div>

        <InformationList v-show="informStatus" @update-view="updateView"></InformationList>
        <InformationUpdatForm
          v-show="updateStatus"
          @inform-view="informView"
          @update-pwd-view="updatePwdView"
        ></InformationUpdatForm>
        <InformationUpdatePwdForm
          v-show="updatePwdStatus"
          @inform-view="informView"
          @update-view="updateView"
        ></InformationUpdatePwdForm>
        <div class="col-3"></div>
      </div>
    </div>
  </section>
</template>
