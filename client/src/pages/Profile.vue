<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Профиль пользователя</h1>
    <p>Имя: {{ userProfile.name }}</p>
    <p>ISU номер: {{ userProfile.isuNumber }}</p>
    <p>Email: {{ userProfile.email }}</p>
    <p>Тип пользователя: {{ userProfile.type }}</p>
    <p>Пройдено тестов: {{ userProfile.testsPassed }}</p>
    <p>Средний результат тестов: {{ userProfile.averageTestsResult }}</p>

    <div v-for="test in userProfile.tests" :key="test.testId">
      <h2>{{ test.testName }}</h2>
      <AverageResultChart :averageTestResult="userProfile.averageTestsResult" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import AverageResultChart from '../components/AverageResultChart.vue';

export default {
  components: {
    AverageResultChart,
  },
  data() {
    return {
      userProfile: {
        name: '',
        isuNumber: '',
        email: '',
        type: '',
        testsPassed: 0,
        averageTestsResult: 0,
        tests: []
      }
    };
  },
  mounted() {
    this.loadUserProfile();
  },
  methods: {
    loadUserProfile() {
      const currentUser = JSON.parse(localStorage.getItem('currentUser'));
      if (currentUser) {
        this.userProfile.name = currentUser.name;
        this.userProfile.isuNumber = currentUser.isuNumber;
        this.userProfile.email = currentUser.email;
        // Запрос на сервер для получения дополнительных данных
        axios.get(`https://example.com/api/user-profile/${currentUser.isuNumber}`)
            .then(response => {
              const data = response.data;
              this.userProfile.type = data.type;
              this.userProfile.testsPassed = data.testsPassed;
              this.userProfile.averageTestsResult = data.averageTestsResult;
              this.userProfile.tests = data.tests;
            })
            .catch(error => {
              console.error('Ошибка при получении данных профиля:', error);
            });
      }
    }
  }
};
</script>

<style>
/* Стилизация страницы профиля */
</style>
