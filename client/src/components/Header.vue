<template>
  <header class="bg-blue-500 py-4">
    <nav class="container mx-auto flex justify-between items-center">
      <router-link to="/" class="text-white text-xl">Главная</router-link>
      <br>
      <router-link to="/tests" class="text-white text-xl">Тесты</router-link>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'Header',
};
</script>

<style scoped>
/* Стили для компонента Header могут быть добавлены здесь */
</style>
