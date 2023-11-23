<template>
  <section class="hero-section d-flex justify-content-center align-items-center" id="section_1">
    <div class="container" style="height: 700px">
      <div class="row" style="height: 200px"></div>
      <div class="row">
        <div class="col-lg-8 col-12 mx-auto">
          <h1 class="text-white text-center">Discover. Enjoy Trip !</h1>

          <h6 class="text-center">전국 관광지의 정보를 한번에 찾아보세요.</h6>

          <form method="get" class="custom-form mt-4 pt-2 mb-lg-0 mb-5" role="search">
            <div class="input-group input-group-lg">
              <span class="input-group-text bi-search" id="basic-addon1"> </span>
              <p class="form-control p2">{{ typedText }}</p>
              
              <router-link to="/trip/search">
                <button type="submit" class="form-control" style="width: 200px;">Search</button>
              </router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const textToType = " 여행을 떠나보세요 !";
const typedText = ref("");
let currentIndex = 0;

const typeNextCharacter = () => {
  if (currentIndex >= textToType.length) {
    // 모든 텍스트가 나타났으면 초기화
    typedText.value = "";
    currentIndex = 0;
  }

  typedText.value += textToType[currentIndex];
  currentIndex++;
  setTimeout(typeNextCharacter, 150);
};

onMounted(() => {
  typeNextCharacter();
});
</script>

<style scoped>
.typing-animation {
  display: inline-block;
  overflow: hidden;
}

.typing-animation h1 {
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  animation: typingAnimation 0.5s steps(1, end) forwards infinite;
}

@keyframes typingAnimation {
  from {
    width: 0;
  }
  to {
    width: 100%;
  }
}

.form-control.p2{
  border: 0px;
  margin-bottom: 0px;
}
</style>
