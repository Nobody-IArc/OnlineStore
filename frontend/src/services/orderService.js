import httpRequester from '@/libs/httpRequester';

// 주문 삽입
export const addOrder = (args) => {
    return httpRequester.post("/v1/api/orders", args).catch(err => err.response);
};

// 주문 목록 조회
export const getOrders = (args) => {
    return httpRequester.get("/v1/api/orders", args).catch(err => err.response);
};

// 주문 상세 조회
export const getOrder = (id) => {
    return httpRequester.get(`/v1/api/orders/${id}`).catch(err => err.response);
};