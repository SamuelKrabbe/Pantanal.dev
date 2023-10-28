<script setup>

import keycloak from '../keycloak';
import axios from 'axios'

const emit = defineEmits(['socialActionDeleted'])
const props = defineProps(['socialAction'])

async function deleteSocialAction(socialAction){
    const response = await axios.delete(`http://localhost:8081/socialactions/${socialAction.id}`, {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })

  if(response.status === 200){
    emit('socialActionDeleted')
  }
}

async function volunteerToSocialAction(socialAction){
    let date = new Date()
    const offset = date.getTimezoneOffset()
    date = new Date(date.getTime() - (offset*60*1000))
    date = date.toISOString().split('T')[0]
    let socialContract = {socialActionId: socialAction.id, contractDate: date}
    const response = await axios.post(`http://localhost:8081/socialcontracts`,  socialContract, {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })


}

</script>

<template>

    <section class="socialAction">
        <p id="name"> {{ socialAction.name }} </p>
        <p id="description"> {{ socialAction.description }}</p>
        <p id="dates"> Datas: {{ socialAction.startDate }} à {{ socialAction.endDate }}</p>
        <button id="removeActionButton" v-if="keycloak.hasRealmRole('admin')" @click="deleteSocialAction(socialAction)"> Remover ação</button>
        <button id="volunteerButton" v-if="keycloak.hasRealmRole('user')" @click="volunteerToSocialAction(socialAction)"> Voluntariar-se</button>
    </section>

</template>