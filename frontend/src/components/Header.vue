<script setup>
// 로그아웃
const logoutAccount = async () => {
  window.alert("Non-Ready");
};
</script>

<template>
  <header>
    <div class="navbar navbar-dark bg-dark text-white shadow-sm">
      <div class="container">
        <router-link to ="/" class="navbar-brand">
          <strong>Online Store</strong>
        </router-link>
        <div class="menus d-flex gap-3">
          <template v-if="true">
            <router-link to="/login">Login</router-link>
            <router-link to="/join">Join</router-link>
          </template>
          <template v-else>
            <a @click="logoutAccount()">Logout</a>
            <router-link to="/orders">Orders</router-link>
            <router-link to="/cart">Cart</router-link>
          </template>
        </div>
      </div>
    </div>
  </header>
</template>

<style lang="scss">
header {
  .menus {
    a {
      cursor: pointer;
      color: #fff;
      text-decoration: none;
    }
  }
}
</style>