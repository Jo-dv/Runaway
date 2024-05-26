<script setup>
import { RouterLink, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'
import http from '@/common/axios.js'
const { authStore, setLogout } = useAuthStore()
const router = useRouter()
const logout = async () => {
  try {
    let { data } = await http.get('/logout')

    console.log(data)
    if (data.result == 'success') {
      setLogout()
      alert('로그아웃 되었습니다.')
      router.push('/')
    }
  } catch (error) {
    console.log(error)
  }
}
</script>
<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container">
      <router-link to="/">
        <a class="navbar-brand">
          <!--홈-->
          <span>Run away</span>
        </a>
      </router-link>

      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-lg-5 me-lg-auto">
          <li class="nav-item">
            <router-link class="nav-link click-scroll" to="/">Home</router-link>
          </li>

          <li class="nav-item">
            <router-link class="nav-link click-scroll" to="/trip/search">Search</router-link>
          </li>

          <li class="nav-item" v-show="authStore.isLogin">
            <router-link class="nav-link click-scroll" to="/bookmark">Bookmark</router-link>
          </li>

          <li class="nav-item">
            <router-link class="nav-link click-scroll" to="/community">Community</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link click-scroll" to="/notice">Notice</router-link>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#section_5"
              id="navbarLightDropdownMenuLink"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              >User</a
            >

            <ul
              class="dropdown-menu dropdown-menu-light"
              aria-labelledby="navbarLightDropdownMenuLink"
            >
              <li v-show="authStore.isLogin">
                <router-link class="dropdown-item active" to="/inform">Information</router-link>
              </li>
              <li v-show="!authStore.isLogin">
                <router-link class="dropdown-item active" to="/login">Login</router-link>
              </li>

              <li v-show="authStore.isLogin">
                <a class="dropdown-item active" href="#" @click="logout">Logout</a>
              </li>
            </ul>
          </li>
        </ul>

        <div class="d-none d-lg-block">
          <router-link
            v-if="authStore.isLogin"
            class="navbar-icon bi-person"
            to="/inform"
          ></router-link>
          <router-link
            v-if="!authStore.isLogin"
            class="navbar-icon bi-person"
            to="/login"
          ></router-link>
        </div>
      </div>
    </div>
  </nav>
</template>
