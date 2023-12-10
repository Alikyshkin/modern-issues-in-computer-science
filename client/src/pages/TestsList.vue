<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Список тестов</h1>
    <div class="p-6">
      <div class="flex border-b">
        <button
            v-for="tab in tabs"
            :key="tab"
            :class="['inline-block p-4', activeTab === tab ? 'text-blue-500 border-b-2 border-blue-500' : 'text-gray-500']"
            @click="activeTab = tab"
        >
          {{ tab }}
        </button>
      </div>
      <!-- Заголовки колонок -->
      <div class="flex border-b p-4 font-bold">
        <div class="flex-1">Название</div>
        <div class="flex-1">Предмет</div>
        <div class="flex-1">Количество вопросов</div>
        <div class="flex-1">Продолжительность</div>
      </div>
      <!-- Список тестов -->
      <div v-for="test in filteredTests" :key="test.id" class="flex border-b p-4 cursor-pointer" @click="goToTest(test.id)">
        <div class="flex-1 break-words mr-3">{{ test.name }}</div>
        <div class="flex-1 break-words mr-3">{{ test.subject }}</div>
        <div class="flex-1 break-words mr-3">{{ test.questionCount }} вопросов</div>
        <div class="flex-1 break-words mr-3">{{ test.duration }} минут</div>
        <div :class="test.passed ? 'bg-green-500' : 'bg-red-500'" class="h-4 w-4 rounded-full"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      activeTab: 'Все тесты',
      tabs: ['Все тесты', 'Не пройденные', 'Завершенные'],
      allTests: [],
      notPassedTests: [],
      completedTests: [],
    };
  },
  computed: {
    filteredTests() {
      switch (this.activeTab) {
        case 'Все тесты':
          return this.allTests;
        case 'Не пройденные':
          return this.notPassedTests;
        case 'Завершенные':
          return this.completedTests;
        default:
          return [];
      }
    },
  },
  methods: {
    goToTest(test) {
      if (test.passed) {
        // Если тест пройден, перенаправляем на страницу аналитики
        this.$router.push({ name: 'Analytics', params: { id: test.id } });
      } else {
        // Если тест не пройден, перенаправляем на страницу прохождения теста
        this.$router.push({ name: 'Test', params: { test_id: test.id } });
      }
    },
    fetchTests() {
      axios.get('https://example.com/api/tests')
          .then(response => {
            this.allTests = response.data.map(test => ({
              id: test.id,
              isuNumber: test.isuNumber,
              title: test.name,
              subject: test.subject,
              questionCount: test.questions.length,
              duration: test.duration,
              passed: test.passed
            }));
            this.updateTestLists();
          })
          .catch(error => {
            console.error('Ошибка при получении тестов:', error);
          });
    },
    updateTestLists() {
      this.notPassedTests = this.allTests.filter(test => !test.passed);
      this.completedTests = this.allTests.filter(test => test.passed);
    }
  },
  mounted() {
    this.fetchTests();
  },
};
</script>

<style>
/* Глобальные стили для вашего приложения */
</style>
