<script setup>
import InformationSiteHeader from '../components/Information/InformationSiteHeader.vue'
import InformationList from '../components/Information/InformationList.vue'
import InformationUpdatForm from '../components/Information/InformationUpdateForm.vue'
import { ref } from 'vue'
const informStatus = ref(true)
const updateStatus = ref(false)

const updateView = () => {
  informStatus.value = false
  updateStatus.value = true
}
const informView = () => {
  informStatus.value = true
  updateStatus.value = false
}
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
        ></InformationUpdatForm>
        <div class="col-3"></div>
      </div>
    </div>
  </section>
</template>
