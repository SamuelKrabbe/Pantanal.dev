<script setup>

import { reactive, ref } from 'vue';
import { onMounted } from 'vue';

import { RouterLink, RouterView } from 'vue-router'

import keycloak from '../keycloak';
import axios from 'axios'

const socialaction = reactive({
  name: null,
  status: false,
  description: null,
  startDate: null,
  endDate: null
})

const styles = reactive({
  displayMenu: "none",
  containerDisplay: "block"
})

async function createSocialAction() {
  //const socialAction = { name: 'teste', status: false, description: "teste desc", startDate: "2023-01-01", endDate: "2023-02-01" }
  console.log(socialaction)
  const response = await axios.post('http://localhost:8081/socialactions', socialaction, {
    headers: {
      accept: 'application/json',
      authorization: `Bearer ${keycloak.token}`
    }
  })
  console.log(response.data)
}

function openCreateSocialActionMenu() {
  styles.displayMenu = "block"
}

function closeCreateSocialActionMenu() {
  styles.displayMenu = "none"
}

onMounted(() => {
  console.log(keycloak.token)
})

</script>
<template>

  <div id="container">
    <div id="navbar">
      <a id="logo" href="#"><img src="../assets/logo.jpg" alt="fish logo" /></a>

      <a id="register" href="#" @click="openCreateSocialActionMenu"><span><b>CADASTRAR</b> AÇÃO</span></a>

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

      <a id="share" href="#"><span><b>DIVULGAR</b> AÇÃO</span></a>

      <a href="#socialActionsListPage"><span><b>VER</b> AÇÕES</span></a>

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
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
        Enim laudantium minus, iure magni autem fugit! Dignissimos
        ab natus eos, laborum assumenda, nemo commodi, beatae
        explicabo excepturi voluptatum culpa recusandae eligendi!
      </p>
      <h1>VISÃO</h1>
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
        Enim laudantium minus, iure magni autem fugit! Dignissimos
        ab natus eos, laborum assumenda, nemo commodi, beatae
        explicabo excepturi voluptatum culpa recusandae eligendi!
      </p>
      <h1>MISSÃO</h1>
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
        Enim laudantium minus, iure magni autem fugit! Dignissimos
        ab natus eos, laborum assumenda, nemo commodi, beatae
        explicabo excepturi voluptatum culpa recusandae eligendi!
      </p>
      <h1>VALORES</h1>
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
    <div id="footer">
      <ul id="linksFooter">
        <li><a href="#">Termos de uso e proteção de dados</a></li>
        <li><a href="#">Atendimento</a></li>
        <li><a href="#">Canal de denúncias</a></li>
      </ul>
      <ul id="socialMedia">
        <li>
          <a href="#"></a>
        </li>
        <li>
          <a href="#"></a>
        </li>
        <li>
          <a href="#"></a>
        </li>
        <li>
          <a href="#"></a>
        </li>
        <li>
          <a href="#"></a>
        </li>
        <li>
          <a href="#"></a>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
@import '../assets/style.css';
</style>