<script setup>

// import { reactive, ref } from 'vue';
import { onMounted } from 'vue';

import keycloak from '../keycloak';
// import axios from 'axios'

// async function fetchTasks() {
//   const response = await axios.get('http://localhost:8082/task', {
//     headers: {
//       accept: 'application/json',
//       authorization: `Bearer ${keycloak.token}`
//     }
//   })
//   keycloak.loadUserInfo().then(info => email.value = info.email)
//   tasks.value = response.data

// }

onMounted(() => {
  console.log(keycloak.token)
})



</script>
<template>

</template>