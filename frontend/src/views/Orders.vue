<script setup>
import {reactive} from "vue";
import {getOrders} from "@/services/orderService";

// 반응형 상태
const state = reactive({
  args: {
    page: 0,
    size: 5
  },
  page: {
    index: 0,
    totalPages: 0,
    totalElements: 0,
  },
  orders: [],
});

// 목록 번호 추출
const getListNum = (idx) => {
  // 전체 - 인덱스 - 페이지당 데이터 * 페이지 인덱스
  return state.page.totalElements - idx - state.args.size * state.page.index;
};

// 주문 목록 조회
const load = async(pageIdx) => {
  if (pageIdx !== undefined) {
    state.args.page = pageIdx;
  }

  // HTTP args 전달
  const res = await getOrders(state.args);

  if (res.status === 200) {
    state.orders = res.data.content;
    state.page.index = res.data.number;
    state.page.totalPages = res.data.totalPages;
    state.orders.totalElements = res.data.totalElements;
  }
};

// 커스텀 생성 훅
(async function onCreated() {
  await load();
})();

</script>

<template>
  <div class="orders">
    <div class="container">
      <table class="table table-bordered my-4">
        <thead>
        <tr>
          <th class="text-center">Number</th>
          <th>주문자</th>
          <th>결제 수단</th>
          <th>결제 금액</th>
          <th>결제 일자</th>
          <th>...더보기</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(o, idx) in state.orders">
          <td class="text-center">{{ state.orders.length - idx }}</td>
          <td>{{ o.name }}</td>
          <td>{{ o.payment === 'card' ? 'Card' : 'Bank' }}</td>
          <td>{{ o.amount.toLocaleString()}}원</td>
          <td>{{ o.createdAt.toLocaleString() }}</td>
          <td>
            <router-link :to="`/orders/${o.id}`">...더보기</router-link>
          </td>
        </tr>
        </tbody>
      </table>
      <div class="pagination d-flex justify-content-center pt-2">
        <div class="btn-group" role="group">
          <button class="btn py-2 px-3"
                  :class="[state.page.index === idx ? 'btn-primary' : 'btn-outline-secondary']"
                  v-for="(i, idx) in state.page.totalPages" @click="load(idx)">
            {{ i }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>