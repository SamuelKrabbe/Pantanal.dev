import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import Keycloak from "./keycloak";

const app = createApp(App)
    .use(router);

Keycloak.init({ onLoad: "login-required" })
    .then(auth => {
        if (!auth) {
            window.location.reload();
        } else {
            app.mount("#app");
        }
        //Token Refresh
        setInterval(() => {
            Keycloak.updateToken(70).catch(() => {
                console.error("Failed to refresh token");
            });
        }, 6000);
    })
    .catch((e) => {
        console.log(e)
        console.error("Authentication Failed");
    });