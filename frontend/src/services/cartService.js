import axios from "axios";

// 카트 상품 목록 조회
export const getItems = () => {
    return axios.get("/v1/api/cart/items").catch(err => err.response);
};

// 카트 상품 추가
export const addItem = (itemId) => {
    return axios.post("/v1/api/carts", {itemId}).catch(err => err.response);
};

// 카트 상품 삭제
export const removeItem = (itemId) => {
    return axios.delete(`/v1/api/cart/items/${itemId}`).catch(err => err.response);
};