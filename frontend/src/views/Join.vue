<script setup>
import {reactive} from "vue";
import {join} from "@/services/accountService";
import {useRouter} from "vue-router";

// 반응형 상태
const state = reactive({
  form: {
    name: "",
    loginId: "",
    loginPw: "",
  }
});

// 라우터 객체
const router = useRouter();

// 회원가입 데이터 전송
const submit = async () => {
  const res = await join(state.form);

  if (res.status === 200) {
    window.alert("Successfully Joined");
    await router.push("/");
  }
};
</script>

<template>
  <div class="join">
    <div class="container">
      <form class="py-5 d-flex flex-column gap-3" @submit.prevent="submit">
        <h1 class="h5 mb-3">Join</h1>
        <div class="form-floating">
          <input type="text" class="form-control" id="name"
                 placeholder="Name" v-model="state.form.name">
          <label for="loginId">Name</label>
        </div>
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
        <button type="submit" class="w-100 h6 btn py-3 btn-primary">Join</button>
      </form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.join > .container {
  max-width: 576px;
}
</style>