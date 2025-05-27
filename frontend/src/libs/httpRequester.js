import axios from 'axios';
import {useAccountStore} from "@/stores/account";

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
            const config = err.config;

            if (config.retried) {
                window.alert("Authentication Failed");
                window.location.replace("/");
                return
            }

            const res = await axios.get("/v1/api/account/token");

            const accessToken = res.data;

            const accountStore = useAccountStore();

            accountStore.setAccessToken(accessToken);

            config.headers.authorization = `Bearer ${accountStore.accessToken}`;

            config.retried = true;

            return instance(config);

        case 500:
            window.alert("System Error");
            break;
    }

    return Promise.reject(err);
});

const generateConfig = () => {
    const accountStore = useAccountStore();

    if (accountStore.accessToken) {
        return {
            headers: {authorization: `Bearer ${accountStore.accessToken}`}
        };
    }

    return {};
}

export default {
    get(url, params) {
        const config = generateConfig();
        config.params = params;
        return instance.get(url, config);
    },
    post(url, params) {
        return instance.post(url, params, generateConfig());
    },
    put(url, params) {
        return instance.put(url, params, generateConfig());
    },
    delete(url) {
        return instance.delete(url, generateConfig());
    }
};