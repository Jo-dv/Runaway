<script setup>
import LoginForm from '@/components/login/LoginForm.vue'
</script>

<template>
  <header class="hero-section d-flex justify-content-center align-items-center">
    <div class="container" style="height: 700px">
      <div class="row align-items-center">
        <div class="col-lg-5 col-12">
          <h2 class="text-white align-items-center">Login</h2>
        </div>
        <section
          class="section-padding section-bg"
          style="background-color: rgba(255, 255, 255, 0.2)"
        >
          <div class="container">
            <div class="row">
              <div class="col-4"></div>
              <!--loginform-->
              <LoginForm></LoginForm>
            </div>
          </div>
        </section>
      </div>
    </div>
  </header>
</template>
