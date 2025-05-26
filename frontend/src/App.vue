<script setup>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import {useAccountStore} from "@/stores/account";
import {watch} from "vue";
import {useRoute} from "vue-router";
import {check} from "@/services/accountService";

const accountStore = useAccountStore();

const route = useRoute();

const checkAccount = async () => {
  const res = await check();

  if (res.status === 200) {
    accountStore.setChecked(true);
    accountStore.setLoggedIn(res.data === true);
  } else {
    accountStore.setChecked(false);
  }
};

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
<!-- 로그인 여부 확인 후 출력 -->
  <template v-if="accountStore.checked">
    <Header/>
    <main>
      <router-view></router-view>
    </main>
    <Footer/>
  </template>
</template>