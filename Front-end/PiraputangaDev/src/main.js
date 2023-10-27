// import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia' 

import Keycloak from "./keycloak";

import "./assets/style.css"

const pinia = createPinia();

const app = createApp(App)
    .use(pinia)
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