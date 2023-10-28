<script setup>

import { reactive, ref } from 'vue';
import { onMounted } from 'vue';

import { RouterLink, RouterView } from 'vue-router'

import keycloak from '../keycloak';
import axios from 'axios'

import { useSocialActionStore } from '../stores/socialaction'


const socialActions = useSocialActionStore()

const socialaction = reactive({
  name: null,
  status: false,
  description: null,
  startDate: null,
  endDate: null
})

const email = reactive({
  subject: null,
  body: null
})

const styles = reactive({
  displayMenu: "none",
  containerDisplay: "block",
  emailMenu: "none"
})


async function createSocialAction() {
  const response = await axios.post('http://localhost:8081/socialactions', socialaction, {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })
  socialActions.add(response.data)
  closeCreateSocialActionMenu()
}

async function sendEmails(){
  const response = await axios.post('http://localhost:8081/sendEmails', email, {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })

  console.log(response.data)
  closeEmailMenu()
}

function openCreateSocialActionMenu() {
  styles.displayMenu = "block"
}

function closeCreateSocialActionMenu() {
  styles.displayMenu = "none"
}

function openEmailMenu(){
  styles.emailMenu = "block"
}

function closeEmailMenu(){
  styles.emailMenu = "none"
}

</script>
<template>

  <div id="container">
    <div id="navbar">
      <a id="logo" href="#"><img src="../assets/logo.jpg" alt="fish logo" /></a>

      <a v-if="keycloak.hasRealmRole('admin')" id="register" href="#" @click="openCreateSocialActionMenu"><span><b>CADASTRAR</b> AÇÃO</span></a>

      <div id="myModal" class="modal" :style="{ display: styles.displayMenu }">
        <div class="modal-content">
          <span class="close" @click="closeCreateSocialActionMenu">X</span>
          <h2 style="margin: 15px auto">
            Cadastro de Ação Social
          </h2> 

          <div id="form">
            <label for="socialActionName">Nome da Ação Social:</label>
            <input v-model="socialaction.name" type="text" id="socialActionName" name="socialActionName" placeholder="Nome da ação social" required />
            <br /><br />
            <label for="description">Descrição:</label>
            <textarea v-model="socialaction.description" id="description" name="description" placeholder="Descrição da ação social" required></textarea>
            <br /><br />
            <label for="startDate">Data de início:</label>
            <input v-model="socialaction.startDate" type="date" id="startDate" name="startDate" placeholder="Data de início" required />
            <br /><br />
            <label for="endDate">Data de término:</label>
            <input v-model="socialaction.endDate" type="date" id="endDate" name="endDate" placeholder="Data de término" required />
            <br /><br />
            <input @click="createSocialAction" type="submit" value="Cadastrar" />
          </div>
        </div>
      </div>

      <a v-if="keycloak.hasRealmRole('admin')" @click="openEmailMenu" id="share" href="#"><span><b>DIVULGAR</b> AÇÃO</span></a>

      <div v-if="keycloak.hasRealmRole('admin')" id="emailModal" class="modal" :style="{ display: styles.emailMenu }">
        <div class="modal-content">
          <span class="close" @click="closeEmailMenu">X</span>
          <h2 style="margin: 15px auto">
            Envio de Email
          </h2> 

          <div id="form">
            <input v-model="email.subject" type="text" id="emailSubject" name="emailSubject" placeholder="Assunto" required />
            <br /><br />
            <textarea v-model="email.body" id="body" name="body" placeholder="Corpo do email" required></textarea>
            <br /><br />
            <input @click="sendEmails" type="submit" value="Divulgar" />
          </div>
        </div>
      </div>

      <a href="#socialActionsListPage"><span><b>VER</b> AÇÕES</span></a>

      <router-link to="/user">USER</router-link>

      <div class="search-box" id="searchBox">
        <input type="text" placeholder="Pesquisar..." />
        <button>Buscar</button>
      </div>

    </div>

    <img id="bannerImg" src="../assets/contentImg.jpg" alt="..." />

    <router-view></router-view>

    <div id="content">
      <h1>QUEM SOMOS?</h1>
      <p>
        Nós somos a equipe Piraputanga.dev, composta por quatro membros: Luiz Gustavo S. S. Junqueira, Alexandre Diniz de Souza, Pedro Henrique M. de Labio e Samuel de Oliveira Krabbe.
Este produto é o resultado de nossa participação no programa Pantanal.dev, uma iniciativa de capacitação realizada pelas empresas da B3 em parceria com a Universidade Federal de Mato Grosso do Sul (UFMS).
Contamos também com a colaboração do nosso mentor Marlon Ergon dos Santos, da PDTec, que desempenhou um papel fundamental em nossa jornada.
      </p>
      <h1>VALORES DA B3 SOCIAL</h1>
      <ul>
        <li>
          <span>Compromisso Social: Estamos comprometidos com a
            responsabilidade social, agindo de forma ética,
            transparente e em conformidade com as normas e leis
            vigentes.</span>
        </li>
        <li>
          <span>Inovação: Buscamos constantemente soluções
            inovadoras para enfrentar os desafios sociais e
            atender às necessidades emergentes da nossa
            comunidade.</span>
        </li>
        <li>
          <span>Empatia: Valorizamos a compreensão e a consideração
            pelas necessidades e experiências dos nossos
            clientes, colaboradores e das comunidades que
            servimos.</span>
        </li>
        <li>
          <span>Colaboração: Acreditamos na força da colaboração e
            do trabalho em equipe para alcançar resultados
            significativos e duradouros.</span>
        </li>
      </ul>
    </div>

  </div>
</template>

<style scoped>
@import '../assets/style.css';
</style>