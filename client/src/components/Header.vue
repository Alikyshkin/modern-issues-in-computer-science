<template>
  <header class="bg-gray-100 shadow-md">
    <nav class="flex items-center justify-between py-4 px-6">
      <!-- Left side - Menu -->
      <div class="flex items-center space-x-4">
        <a href="/tests" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Список тестов</a>
        <a href="/create-test" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Создать тест</a>
        <a href="/analytics" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Аналитика</a>
      </div>

      <!-- Right side - Authentication -->
      <div class="flex items-center space-x-4">
        <a href="/auth" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Войти</a>
        <a href="/register" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Регистрация</a>
        <!-- Show this button only if the user is logged in -->
        <a href="/logout" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Выйти</a>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'Header',
  // You can add your Vue.js logic here if needed
};
</script>

<style scoped>
/* Additional styles can be added here if necessary */
</style>
