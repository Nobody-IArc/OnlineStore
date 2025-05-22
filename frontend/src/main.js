import {createApp} from "vue";
import {createPinia} from "pinia";
import App from "./App.vue";
import router from "./router";

const app = createApp(App);

app.use(createPinia());
app.use(router);

// 초기 작업 완료 후로 마운트 시점 설정
router.isReady().then(() => {
    app.mount("#app");
});