<script setup>

import keycloak from '../keycloak';
import axios from 'axios'
import SocialAction from '../components/SocialAction.vue'
import { useSocialContractsStore } from '../stores/socialcontracts';
import { useSocialActionStore } from '../stores/socialaction'

import { reactive } from 'vue';

import { onMounted } from 'vue';

const socialContracts = useSocialContractsStore()
const socialActions = useSocialActionStore()

const volunteeredSocialActions = reactive([])

async function fetchSocialContracts() {
  const response = await axios.get('http://localhost:8081/socialcontracts/usersocialcontracts', {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })
  keycloak.loadUserInfo().then(info => console.log(info.email))
  socialContracts.set(response.data)

  socialContracts.get.forEach(socialContract => {
    volunteeredSocialActions.push()
  });
}

</script>

<template>

<div id="socialContractsListPage">
    <div id="header">
      <h1>Social Contracts List</h1>
    </div>
    <section id="socialContractsList">
      <SocialAction @social-action-deleted="fetchSocialActions" v-for="(socialActionItem, index) in socialActions.get" :key="index" :social-action="socialActionItem"/>
    </section>
  </div>

</template>