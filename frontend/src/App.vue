<script setup>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import {useAccountStore} from "@/stores/account";
import {watch} from "vue";
import {useRoute} from "vue-router";

const accountStore = useAccountStore();

const route = useRoute();

const checkAccount = async() => {
  // 추후
}

// 커스텀 생성 훅
(async function onCreated() {
  await checkAccount();
})();

// 라우트 경로 변경 시 로그인 여부 확인하는 로직
watch(() => route.path, () => {
  checkAccount();
});
</script>

<template>
  <Header />
  <main>
    <router-view></router-view>
  </main>
  <Footer />
</template>