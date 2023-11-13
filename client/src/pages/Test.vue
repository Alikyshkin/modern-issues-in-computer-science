<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Название теста</h1>

    <div class="flex">
    <!-- Список вопросов -->
    <div class="w-1/4 p-4 border-r">
      <div v-for="(question, index) in questions" :key="question.id" class="p-2">
        <button :class="{'bg-green-300': question.answered, 'bg-white': !question.answered}" @click="currentQuestionIndex = index">Вопрос {{ index + 1 }}</button>
      </div>
    </div>

    <!-- Отображение вопроса -->
    <div class="w-3/4 p-4">
      <h1>Вопрос №{{ currentQuestion.id }}</h1>

      <h2>{{ currentQuestion.title }}</h2>
      <p>{{ currentQuestion.content }}</p>

      <!-- Отображение вариантов ответа в зависимости от типа вопроса -->
      <div v-if="currentQuestion.type === 'short-answer'">
        <input type="text" v-model="currentQuestion.answer" class="border p-2" @input="updateAnswerStatus(currentQuestionIndex)">
      </div>
      <div v-else-if="currentQuestion.type === 'single-choice'">
        <div v-for="(option, index) in currentQuestion.options" :key="index">
          <input type="radio" :value="option" v-model="currentQuestion.answer" @change="updateAnswerStatus(currentQuestionIndex)"> {{ option }}
        </div>
      </div>
      <div v-else-if="currentQuestion.type === 'multiple-choice'">
        <div v-for="(option, index) in currentQuestion.options" :key="index">
          <input type="checkbox" :value="option" v-model="currentQuestion.answers" @change="updateAnswerStatus(currentQuestionIndex)"> {{ option }}
        </div>
      </div>

      <!-- Кнопки навигации по вопросам -->
      <div class="max-w-6xl mx-auto pt-14">

      <button @click="goToPreviousQuestion" class="m-2 bg-blue-500 text-white p-2 rounded">Предыдущий вопрос</button>
      <button @click="goToNextQuestion" class="m-2 bg-blue-500 text-white p-2 rounded">Следующий вопрос</button>
      </div>
      </div>
      <div class="max-w-6xl mx-auto pt-14">

        <!-- Кнопка завершения теста -->
        <button class="fixed bottom-24 right-10 bg-red-400 text-white p-4 rounded">Завершить</button>

        <!-- Счетчик времени -->
        <div class="fixed bottom-24 right-44 bg-gray-200 p-4 rounded">
          Оставшееся время: {{ formattedTime }}
        </div>
      </div>
  </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      currentQuestionIndex: 0,
      testDuration: 3600, // 60 минут в секундах
      remainingTime: 3600, // Исходное время
      questions: [
        {
          id: 1,
          type: "short-answer",
          title: "Что такое нормализация баз данных?",
          content: "Опишите основную идею нормализации баз данных.",
          answer: "",
          answered: false
        },
        {
          id: 2,
          type: "single-choice",
          title: "Какой принцип важен при публичных выступлениях?",
          content: "Выберите один из вариантов:",
          options: [
            "Ясность сообщения",
            "Громкость голоса",
            "Длина речи",
            "Использование слайдов"
          ],
          answer: null,
          answered: false
        },
        {
          id: 3,
          type: "multiple-choice",
          title: "Какие особенности есть у Windows Server?",
          content: "Выберите все подходящие варианты:",
          options: [
            "Поддержка Active Directory",
            "Встроенный веб-сервер",
            "Поддержка Linux-приложений",
            "Функционал виртуализации"
          ],
          answers: [],
          answered: false
        },
        // Добавьте больше вопросов по аналогии
      ]
    };
  },
  computed: {
    currentQuestion() {
      return this.questions[this.currentQuestionIndex];
    },
    formattedTime() {
      const minutes = Math.floor(this.remainingTime / 60);
      const seconds = this.remainingTime % 60;
      return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    }
  },
  mounted() {
    this.startTimer();
  },
  methods: {
    updateAnswerStatus(index) {
      const question = this.questions[index];
      if (question.type === 'short-answer') {
        question.answered = question.answer.trim() !== '';
      } else if (question.type === 'single-choice') {
        question.answered = question.answer !== null;
      } else if (question.type === 'multiple-choice') {
        question.answered = question.answers.length > 0;
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
