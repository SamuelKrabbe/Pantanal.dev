<script setup>

import axios from 'axios'

const props = defineProps(['socialAction'])

</script>

<template>

    <section class="socialAction">
        <p id="name"> {{ socialAction.name }} </p>
        <p id="description"> {{ socialAction.description }}</p>
        <p id="dates"> Período: {{ socialAction.startDate.substring(0,10) }} à {{ socialAction.endDate.substring(0,10) }}</p>
    </section>

</template>