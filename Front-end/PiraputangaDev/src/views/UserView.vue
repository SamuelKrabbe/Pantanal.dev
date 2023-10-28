<script setup>

import keycloak from '../keycloak';
import axios from 'axios'
import UserSocialAction from '../components/UserSocialAction.vue'
import { useSocialContractsStore } from '../stores/socialcontracts'
import { useSocialActionStore } from '../stores/socialaction';

import { onMounted } from 'vue';

const socialContracts = useSocialContractsStore()
const socialActions = useSocialActionStore()

async function fetchSocialContracts() {
  const response = await axios.get('http://localhost:8081/socialcontracts/usersocialcontracts', {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })
  socialContracts.set(response.data)
  console.log(response.data)
  socialContracts.setVolunteered(socialActions.get);
}

onMounted(() => {
  fetchSocialContracts()
})

</script>

<template>
  <div id="socialContractsListPage">
    <div id="container">
      <div id="navbar">
        <div id="header">
          <h1>Lista dos Contratos Sociais</h1>
          <router-link to="/">Voltar</router-link>
        </div>
      </div>
    </div>

    <section id="socialContractsList">
      <UserSocialAction @social-action-deleted="fetchSocialActions"
        v-for="(socialActionItem, index) in socialContracts.getVolunteered" :key="index" :social-action="socialActionItem" />
    </section>
  </div>

</template>