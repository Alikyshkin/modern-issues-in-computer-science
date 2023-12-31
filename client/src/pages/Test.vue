<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>{{ testName }}</h1>

    <div class="flex">
      <!-- Список вопросов -->
      <div class="w-1/4 p-4 border-r">
        <div v-for="(question, index) in questions" :key="question.id" class="p-2">
          <button
              :class="{'bg-green-300': question.answered, 'bg-white': !question.answered}"
              @click="currentQuestionIndex = index">
            Вопрос {{ index + 1 }}
          </button>
        </div>
      </div>

      <!-- Отображение вопроса -->
      <div class="w-3/4 p-4">
        <h2>Вопрос №{{ currentQuestion.id }}</h2>
        <h3>{{ currentQuestion.title }}</h3>
        <p>{{ currentQuestion.content }}</p>

        <!-- Отображение вариантов ответа -->
<!--        <div v-if="currentQuestion.type === 'short-answer'">-->
<!--          <input type="text" v-model="currentQuestion.userAnswer" class="border p-2" @input="updateAnswerStatus(currentQuestionIndex)">-->
<!--        </div>-->
<!--        <div v-else-if="currentQuestion.type === 'single-choice'">-->
          <div>
          <div v-for="(option, index) in currentQuestion.options" :key="index">
            <input type="radio" :value="option.text" v-model="currentQuestion.userAnswer" @change="updateAnswerStatus(currentQuestionIndex)">
            {{ option.text }}
          </div>
        </div>
<!--        <div v-else-if="currentQuestion.type === 'multiple-choice'">-->
<!--          <div v-for="(option, index) in currentQuestion.options" :key="index">-->
<!--            <input type="checkbox" :value="option.text" v-model="currentQuestion.userAnswers" @change="updateAnswerStatus(currentQuestionIndex)">-->
<!--            {{ option.text }}-->
<!--          </div>-->
<!--        </div>-->

        <!-- Кнопки навигации по вопросам -->
        <div class="max-w-6xl mx-auto pt-14">
          <button @click="goToPreviousQuestion" class="m-2 bg-blue-500 text-white p-2 rounded">Предыдущий вопрос</button>
          <button @click="goToNextQuestion" class="m-2 bg-blue-500 text-white p-2 rounded">Следующий вопрос</button>
        </div>
      </div>

      <div class="max-w-6xl mx-auto pt-14">
        <!-- Кнопка завершения теста -->
        <button @click="submitTest" class="fixed bottom-24 right-10 bg-red-400 text-white p-4 rounded">Завершить</button>

        <!-- Счетчик времени -->
        <div class="fixed bottom-24 right-44 bg-gray-200 p-4 rounded">
          Оставшееся время: {{ formattedTime }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      currentQuestionIndex: 0,
      testDuration: 0,
      remainingTime: 0,
      testName: '',
      questions: [],
      testId: null,
      testResult: null,
      user_id: null
    };
  },
  computed: {
    currentQuestion() {
      return this.questions[this.currentQuestionIndex] || {};
    },
    formattedTime() {
      const minutes = Math.floor(this.remainingTime / 60);
      const seconds = this.remainingTime % 60;
      return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    }
  },
  mounted() {
    this.testId = this.$route.params.test_id; // Получение ID теста из параметров маршрута
    this.loadTest();
    const userData = localStorage.getItem('currentUser');
    const user = JSON.parse(userData);
    this.user_id = user.isuNumber;
    console.log(this.user_id)
  },
  methods: {
    submitTest() {
      const currentUser = JSON.parse(localStorage.getItem('currentUser'));
      const isuNumber = currentUser ? currentUser.isuNumber : null;

      const testDuration = (this.testDuration * 60) - this.remainingTime; // Время прохождения теста в секундах

      const testSubmission = {
        isuNumber: this.user_id,
        testId: this.testId,
        testTitle: this.testName,
        userAnswerDtos: this.questions.map(q => ({
          id: q.id,
          title: q.title,
          userAnswers: [q.userAnswer] // Changed to an array
        }))
      };
console.log(JSON.stringify(testSubmission));
      axios.post('http://localhost:8080/tests/sendResults', testSubmission)
          .then(() => {
            this.goToResults(this.user_id, this.testId);
          })
          .catch(error => {
            console.error('Ошибка при отправке теста:', error);
          });

    },
    loadTest() {
      axios.get(`http://localhost:8080/tests/${this.testId}`)
          .then(response => {
            const data = response.data;
            this.testName = data.name;
            this.testDuration = data.duration;
            this.remainingTime = data.duration * 60; // Преобразование минут в секунды
            this.questions = data.questions.map(question => ({
              id: question.id,
              title: question.title,
              content: question.content,
              type: question.type,
              options: question.answers.map(answer => ({ text: answer })),
              userAnswer: null,
              userAnswers: [],
              answered: false
            }));
            this.startTimer();
          })
          .catch(error => {
            console.error('Ошибка при загрузке данных теста:', error);
          });
    },
    updateAnswerStatus(index) {
      const question = this.questions[index];
      if (question.type === 'short-answer') {
        question.answered = question.userAnswer.trim() !== '';
      } else if (question.type === 'single-choice') {
        question.answered = question.userAnswer !== null;
      } else if (question.type === 'multiple-choice') {
        question.answered = question.userAnswers.length > 0;
      }
    },
    goToNextQuestion() {
      if (this.currentQuestionIndex < this.questions.length - 1) {
        this.currentQuestionIndex++;
      }
    },
    goToPreviousQuestion() {
      if (this.currentQuestionIndex > 0) {
        this.currentQuestionIndex--;
      }
    },
    goToResults(user_id, test_id) {
      this.$router.push({ path: `/analytics/${test_id}` });
    },
    startTimer() {
      const timer = setInterval(() => {
        if (this.remainingTime > 0) {
          this.remainingTime--;
        } else {
          clearInterval(timer);
          // Обработка окончания времени теста
        }
      }, 1000);
    }
  }
};
</script>
