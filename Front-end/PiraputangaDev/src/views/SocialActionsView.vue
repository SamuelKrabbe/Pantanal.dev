<script setup>

import keycloak from '../keycloak';
import axios from 'axios'

import { reactive, ref } from 'vue';
import { onMounted } from 'vue';

import SocialAction from '../components/SocialAction.vue'

import { useSocialActionStore } from '../stores/socialaction'

const socialActions = useSocialActionStore()
const email = ref({});

async function fetchSocialActions() {
  const response = await axios.get('http://localhost:8081/socialactions', {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })
  keycloak.loadUserInfo().then(info => email.value = info.email)
  socialActions.set(response.data)
}

onMounted(() => {
  fetchSocialActions()
})

</script>
<template>

  <div id="socialActionsListPage">
    <div id="header">
      <h1>Social Actions List</h1>
    </div>
    <section id="socialActionsList">
      <SocialAction @social-action-deleted="fetchSocialActions" v-for="(socialActionItem, index) in socialActions.get" :key="index" :social-action="socialActionItem"/>
    </section>
  </div>
</template>