<script setup>
import RegisterForm from '@/components/register/RegisterForm.vue'
</script>
<template>
  <header class="hero-section2 d-flex justify-content-center align-items-center">
    <div class="container" style="height: 700px">
      <div class="row align-items-center">
        <div class="col-lg-5 col-12">
          <h2 class="text-white align-items-center">Register</h2>
        </div>
        <section
          class="section-padding section-bg"
          style="background-color: rgba(255, 255, 255, 0.2)"
        >
          <div class="container">
            <div class="row">
              <div class="col-4"></div>
              <!--register form-->
              <RegisterForm></RegisterForm>
            </div>
          </div>
        </section>
      </div>
    </div>
  </header>
</template>
