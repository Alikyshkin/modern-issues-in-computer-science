<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Результаты теста: {{ testResult?.testName }}</h1>
    <p v-if="testResult">ID теста: {{ testResult.testId }}</p>
    <p v-if="testResult">ISU номер: {{ testResult.isuNumber }}</p>
    <p v-if="testResult">Количество правильных ответов: {{ testResult.correctAnswers }} из {{ testResult.totalQuestions }}</p>
    <p v-if="testResult">Время прохождения: {{ formatTime(testResult.timeTaken) }}</p>

    <div v-if="testResult" class="flex">
      <div class="w-1/2 pl-4 pr-4 pt-32">
        <div class="flex border-b p-4 font-bold">
          <div class="flex-1">№</div>
          <div class="flex-1">Вопрос</div>
          <div class="flex-1">Вердикт</div>
        </div>
        <!-- Список вопросов и результатов -->
        <div v-for="(question, index) in testResult.questions" :key="question.id" class="flex border-b p-4">
          <div class="flex-1 break-words">{{ index + 1 }}</div>
          <div class="flex-1 break-words">{{ question.text }}</div>
          <div class="flex-1 break-words">{{ question.passed ? 'Правильно' : 'Неправильно' }}</div>
        </div>
      </div>
      <div class="w-1/2 pl-4 pr-4 pt-32">
        <!-- Компонент диаграммы, если нужен -->
        <DonutChart :testResult="testResult" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import DonutChart from '../components/DonutChart.vue'

export default {
  components: {
    DonutChart
  },
  data() {
    return {
      testResult: null // Данные результатов теста
    };
  },
  mounted() {
    this.loadTestResults();
  },
  methods: {
    loadTestResults() {
      const testId = this.$route.params.id;
      axios.get(`https://example.com/api/test-results/${testId}`)
          .then(response => {
            this.testResult = response.data;
          })
          .catch(error => {
            console.error('Ошибка при загрузке результатов теста:', error);
          });
    },
    formatTime(seconds) {
      const minutes = Math.floor(seconds / 60);
      const remainingSeconds = seconds % 60;
      return `${minutes} минут ${remainingSeconds} секунд`;
    }
  }
};
</script>

<style>
/* Стилизация страницы Analytics */
</style>
