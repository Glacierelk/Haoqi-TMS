<script setup>

import { RouterView } from 'vue-router'
import axios from "axios";
//本地部署
axios.defaults.baseURL = "http://localhost:8888";
//服务器部署
// axios.defaults.baseURL = '/store';

</script>

<template>
  <RouterView />
</template>

<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

</style>
