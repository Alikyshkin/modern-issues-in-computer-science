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
      <p v-if="testResult">ID теста: {{ this.test_id }}</p>
<!--      <p v-if="testResult">ISU номер: {{ this.isuNumber }}</p>-->
      <p v-if="testResult">Количество правильных ответов: {{ this.correctAnswers }} из {{ testResult.totalQuestions }}</p>
      <p v-if="testResult">Время прохождения: {{ this.timeTaken }}</p>

      <div v-if="testResult" class="flex">
<!--        <div class="w-1/2 pl-4 pr-4 pt-32">-->
<!--          <div class="flex border-b p-4 font-bold">-->
<!--            <div class="flex-1">№</div>-->
<!--            <div class="flex-1">Вопрос</div>-->
<!--            <div class="flex-1">Вердикт</div>-->
<!--          </div>-->
<!--          <div v-for="(question, index) in testResult.questions" :key="question.id" class="flex border-b p-4">-->
<!--            <div class="flex-1 break-words">{{ index + 1 }}</div>-->
<!--            <div class="flex-1 break-words">{{ question.text }}</div>-->
<!--            <div class="flex-1 break-words">{{ question.passed ? 'Правильно' : 'Неправильно' }}</div>-->
<!--          </div>-->
<!--        </div>-->
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
      testInfo: null, // Для администратора/учителя
      test_id: null,
      testName : null,
      testDuration: null,
      correctAnswers: null,
      wrongAnswers : null,
      totalQuestions : null,
      role: null,
    };
  },
  mounted() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const userData = localStorage.getItem('currentUser');
    const user = JSON.parse(userData);
    this.role = user.role;
    console.log('role:', this.role)

    this.isAdminOrTeacher = currentUser && (currentUser.role === 'TEACHER');

    console.log('role:', this.isAdminOrTeacher)
    this.test_id = this.$route.params.id;
    if (this.isAdminOrTeacher) {
      this.loadAdminTeacherAnalytics(this.test_id);
    } else {
      this.loadTestResults(this.test_id);
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
            console.log(response.data);
            this.testName = data.testTitle;
            this.testDuration = data.timeTaken;
            this.correctAnswers = data.correctAnswers;
            this.wrongAnswers = data.wrongAnswers;
            this.totalQuestions = data.totalQuestions;
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
