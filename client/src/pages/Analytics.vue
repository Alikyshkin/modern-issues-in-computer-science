<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Результаты теста: {{ testInfo.testTitle }}</h1>

    <!-- Отображение для администратора/учителя -->
    <div v-if="isAdminOrTeacher">
      <p>ID теста: {{ testInfo.testId }}</p>
      <p>Средний результат тестов: {{ testInfo.averageTestsResult }}%</p>
      <p>Прошедших тест: {{ testInfo.usersPassed }}</p>

      <div v-for="user in testInfo.users" :key="user.id">
        <h3>{{ user.name }}</h3>
        <p>Email: {{ user.email }}</p>
        <p>Количество правильных/неправильных ответов: {{ user.correctAnswers }}/{{ user.wrongAnswers }}</p>
        <p>Время прохождения: {{ formatTime(user.timeTaken) }}</p>
        <p>Средний результат: {{ user.averageResult }}%</p>
        <!-- Можно добавить дополнительные детали по каждому пользователю -->
      </div>
    </div>

    <!-- Отображение для студента -->
    <div>
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
          <div v-for="(question, index) in testResult.questions" :key="question.id" class="flex border-b p-4">
            <div class="flex-1 break-words">{{ index + 1 }}</div>
            <div class="flex-1 break-words">{{ question.text }}</div>
            <div class="flex-1 break-words">{{ question.passed ? 'Правильно' : 'Неправильно' }}</div>
          </div>
        </div>
        <div class="w-1/2 pl-4 pr-4 pt-32">
          <DonutChart :testResult="testResult" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import DonutChart from '../components/DonutChart.vue';

export default {
  components: {
    DonutChart
  },
  data() {
    return {
      isAdminOrTeacher: false,
      testResult: null, // Для студентов
      testInfo: null // Для администратора/учителя
    };
  },
  mounted() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.isAdminOrTeacher = currentUser && (currentUser.role === 'admin' || currentUser.role === 'teacher');
    const testId = this.$route.params.id;

    if (this.isAdminOrTeacher) {
      this.loadAdminTeacherAnalytics(testId);
    } else {
      this.loadTestResults(testId);
    }
  },
  methods: {
    loadAdminTeacherAnalytics(testId) {
      axios.get(`https://example.com/api/admin-teacher-analytics/${testId}`)
          .then(response => {
            this.testInfo = response.data;
          })
          .catch(error => {
            console.error('Ошибка при загрузке аналитики:', error);
          });
    },
    loadTestResults(testId) {
      axios.get(`http://localhost:8080/users/results/${testId}`)
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
