<script setup>

import keycloak from '../keycloak';
import axios from 'axios'

import { reactive, ref } from 'vue';
import { onMounted } from 'vue';

async function fetchSocialActions() {
  const response = await axios.get('http://localhost:8081/socialactions', {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })
  keycloak.loadUserInfo().then(info => email.value = info.email)
  socialActions.data = response.data
  console.log(socialActions)
}

const socialActions = reactive({ data: null });
const email = ref(null);

onMounted(() => {
  fetchSocialActions()
})

</script>
<template>

  <div id="socialActionsListPage">
    <div id="header">
      <h1>Social Actions List</h1>
    </div>
    <ul id="socialActionsList">
      <ul class="socialAction" v-for="(action, index) in socialActions.data" :key="index">
        <li class="id">{{ action.id }}</li>
        <li class="name">{{ action.name }}</li>
        <li class="status">{{ action.status }}</li>
        <li class="description">{{ action.description }}</li>
        <li class="date">{{ action.startDate }}</li>
        <li class="date">{{ action.endDate }}</li>
        <li class="more-about">SAIBA MAIS</li>
      </ul>
    </ul>
  </div>
</template>