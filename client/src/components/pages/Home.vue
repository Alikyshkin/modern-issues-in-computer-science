<template>
  <div class="flex flex-col items-center justify-center h-screen bg-gray-100">
    <img src="https://commons.wikimedia.org/wiki/File:ITMO_University_official_logo_horizontal.png" alt="Логотип ИТМО" class="mb-8" />
    <h1 class="text-2xl font-bold mb-4">Добро пожаловать на главную страницу!</h1>
    <button v-if="!user" @click="login" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Войдите</button>
    <button v-if="user" @click="goToTests" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Список тестов</button>
  </div>
</template>
<script>
export default {
  name: 'Home',
};
</script>
