<template>
  <header class="bg-gray-100 shadow-md">
    <nav class="max-w-6xl mx-auto flex flex-col lg:flex-row items-start lg:items-center justify-between py-4 px-6">
      <div class="flex flex-col lg:flex-row w-full lg:w-auto">
        <!-- Кнопка бургера для мобильных устройств -->
        <button @click="toggleMenu" class="text-gray-500 focus:outline-none lg:hidden">
          <i class="fas fa-bars"></i>
        </button>

        <!-- Меню -->
        <div :class="{'hidden': !isMenuOpen, 'flex': isMenuOpen}"
             class="flex-col lg:flex lg:flex-row lg:items-center space-y-4 lg:space-y-0 lg:space-x-4">
          <a href="/" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Главная</a>

          <!-- Отображаем для авторизованных пользователей с ролями admin и teacher -->
          <div v-if="isAuthenticated && (userRole === 'admin' || userRole === 'TEACHER')">
            <a href="/tests-list" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Список
              тестов</a>
            <a href="/create-test" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Создать
              тест</a>
            <a href="/analytics" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Аналитика</a>
          </div>

          <!-- Отображаем для авторизованных пользователей с ролью student -->
          <div v-else-if="userRole === 'STUDENT'">
            <a href="/tests-list" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Все тесты</a>
          </div>
        </div>
      </div>

      <!-- Ссылки управления учетной записью -->
      <div :class="{'hidden': !isMenuOpen, 'flex': isMenuOpen}"
           class="flex-col lg:flex lg:flex-row lg:items-center space-y-4 lg:space-y-0 lg:space-x-4">
        <div v-if="isAuthenticated">
          <a href="/profile"
             class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Профиль</a>
          <a @click="logout"
             class="cursor-pointer px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Выйти</a>
        </div>
        <div v-else>
          <a href="/auth" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Войти</a>
          <a href="/register" class="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:bg-gray-200">Регистрация</a>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      currentUser: null, // Здесь мы храним информацию о пользователе
      isMenuOpen: false, // состояние меню для мобильных устройств
    };
  },
  computed: {
    isAuthenticated() {
      return !!this.currentUser;
      console.log(this.currentUser.role)
    },
    currentUser() {
      return JSON.parse(localStorage.getItem('currentUser'));
    },
    userRole() {
      return this.currentUser ? this.currentUser.role : null;
    }
  },
  watch: {
    // Watcher for changes in localStorage
    '$route': function() {
      this.currentUser = JSON.parse(localStorage.getItem('currentUser') || 'null');
    }
  },
  created() {
    try {
      const userData = localStorage.getItem('currentUser');
      if (userData) {
        this.currentUser = JSON.parse(userData);
      }
    } catch (error) {
      console.error('Failed to parse user data:', error);
      // Обработка ошибки, например, удаление неверных данных из localStorage
      localStorage.removeItem('currentUser');
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('currentUser'); // Удаляем данные пользователя из localStorage
      this.user = null; // Обнуляем данные пользователя в компоненте
      // перенаправляем пользователя на страницу входа
      this.$router.push('/auth');
    },
    toggleMenu() {
      this.isMenuOpen = !this.isMenuOpen;
    },
  }
};
</script>
