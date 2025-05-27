<script setup>
import {reactive} from "vue";
import {login} from "@/services/accountService";
import {useRouter} from "vue-router";
import {useAccountStore} from "@/stores/account";

// 반응형 상태
const state = reactive({
  form: {
    loginId: "",
    loginPw: "",
  }
});

// 라우터 객체
const router = useRouter();

// 계정 스토어
const accountStore = useAccountStore();

// 로그인 데이터 전송
const submit = async() => {
  if (state.form.loginId?.trim()) {
    window.alert("Email 입력은 필수입니다.");
    document.getElementById("loginId")?.focus();
    return
  } else if (state.form.loginPw?.trim()) {
    window.alert("Password 입력은 필수입니다.");
    document.getElementById("loginPw")?.focus();
    return;
  }
  const res = await login(state.form);

  switch(res.status) {
    case 200:
      accountStore.setAccessToken(res.data);
      await router.push("/");
      break;
    case 404:
      window.alert("Member Not Found");
      break;
  }
};

</script>

<template>
  <div class="login">
    <div class="container">
      <form class="py-5 d-flex flex-column gap-3" @submit.prevent="submit">
        <h1 class="h5 mb-3">Login</h1>
        <div class="form-floating">
          <input type="email" class="form-control" id="loginId"
                 placeholder="E-mail" v-model="state.form.loginId">
          <label for="loginId">E-mail</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="loginPw"
                 placeholder="Password" v-model="state.form.loginPw">
          <label for="loginPw">Password</label>
        </div>
        <button type="submit" class="w-100 h6 btn py-3 btn-primary">Login</button>
      </form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login > .container {
  max-width: 576px;
}
</style>