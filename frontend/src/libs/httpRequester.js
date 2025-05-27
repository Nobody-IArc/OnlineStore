import axios from 'axios';

const instance = axios.create();

// 인터셉터
instance.interceptors.response.use((res) => {
    return res;
}, async (err) => {
    switch (err.response.status) {
        case 400:
            window.alert("Wrong Request");
            break;
        case 401:
            window.alert("None Authenticated Request");
            window.location.replace("/");
            break;
        case 500:
            window.alert("System Error");
            break;
    }

    return Promise.reject(err);
});

export default {
    get(url, params) {
        return instance.get(url, { params });
    },
    post(url, params) {
        return instance.post(url, { params });
    },
    put(url, params) {
        return instance.put(url, { params });
    },
    delete(url) {
        return instance.delete(url);
    }
};