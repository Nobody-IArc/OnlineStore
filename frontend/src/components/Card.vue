<script setup>
import {addItem} from "@/services/cartService";
import {useRouter} from "vue-router";
import {computed} from "vue";
import {useAccountStore} from "@/stores/account";

// 계정 스토어
const accountStore = useAccountStore();

// 상품 담기
const put = async () => {
  if (!accountStore.loggedIn) {
    if (window.confirm("로그인이 필요한 서비스입니다. 로그인 페이지로 이동하시겠습니까?")) {
      await router.push("/login");
    }
  }

  return;
}

// 프로퍼티 객체
const props = defineProps({
  item: {
    id: Number,
    imgPath: String,
    name: String,
    price: Number,
    discountPer: Number,
  }
});

// 상품 할인가
const computedItemDiscountPrice = computed(() => {
  return (props.item.price - (props.item.price * props.item.discountPer / 100)).toLocaleString() + '원';
});

// 라우터 객체 생성
const router = useRouter();
</script>

<template>
  <div class="card shadow-sm">
    <span class="img" :style="{backgroundImage: `url(${props.item.imgPath})`}"
          :aria-label="`상품 사진(${props.item.name})`"></span>
    <div class="card-body">
      <p class="card-text">
        <span class="me-2">{{ props.item.name }}</span>
        <span class="discount badge bg-danger">{{ props.item.discountPer }}%</span>
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary btn-sm" @click="put()">Add to Cart</button>
        <small class="price text-muted">{{ props.item.price.toLocaleString() }}원</small>
        <small class="real text-danger">{{ computedItemDiscountPrice }}</small>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.card {
  .img {
    display: inline-block;
    width: 100%;
    height: 250px;
    background-size: cover;
    background-position: center;
  }

  .card-body .price {
    text-decoration: line-through;
  }
}
</style>