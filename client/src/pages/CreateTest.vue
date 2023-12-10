<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Создание теста</h1>
    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
      <input
          class="form-input mb-3 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          type="text" placeholder="Название теста" v-model="test.name">
      <input
          class="form-input mb-3 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          type="number" placeholder="Продолжительность" v-model="test.duration">
      <input
          class="form-input mb-3 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          type="text" placeholder="Название предмета" v-model="test.subject">
      <textarea
          class="form-textarea shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          placeholder="Описание теста" v-model="test.description"></textarea>
    </div>
    <!-- Вопросы -->
    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
      <div v-for="(question, qIndex) in test.questions" :key="qIndex" class="mb-4">
        <input
            class="form-input mb-3 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            type="text" placeholder="Название вопроса" v-model="question.title">
        <textarea
            class="form-textarea mb-3 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            placeholder="Содержание вопроса" v-model="question.content"></textarea>
        <!-- Выбор типа вопроса -->
        <select v-model="question.type"
                class="form-select block w-full mt-1 mb-3 shadow border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
          <option value="short-answer">Короткий ответ</option>
          <option value="single-choice">Одиночный выбор</option>
          <option value="multiple-choice">Множественный выбор</option>
        </select>
        <!-- Для коротких ответов -->
        <div v-if="question.type === 'short-answer'">
          <input
              class="form-input mb-3 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              type="text" placeholder="Правильный ответ" v-model="question.answer">
        </div>
        <!-- Варианты ответа -->
        <div v-if="question.type === 'single-choice' || question.type === 'multiple-choice'">
          <div v-for="(option, oIndex) in question.options" :key="oIndex" class="flex items-center mb-2">
            <input
                class="form-input w-full block shadow border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                type="text" v-model="option.text">
            <input
                class="ml-2 form-checkbox h-5 w-5 text-gray-600"
                type="checkbox"
                v-model="option.isCorrect">
            <button @click="removeOption(qIndex, oIndex)"
                    class="ml-2 bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
              Удалить
            </button>
          </div>
          <button @click="addOption(qIndex)"
                  class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mt-4 w-full">
            Добавить ответ
          </button>
        </div>

        <button @click="removeQuestion(qIndex)"
                class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mt-4 w-full">
          Удалить вопрос
        </button>
      </div>
    </div>
    <button @click="addQuestion" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full">
      Добавить вопрос
    </button>
    <button @click="submitTest" class="bg-black hover:bg-gray-800 text-white font-bold py-2 px-4 rounded w-full mt-4">
      Создать тест
    </button>
  </div>
</template>

<script>
export default {
  name: 'CreateTest',
  data() {
    return {
      test: {
        name: '',
        duration: '',
        subject: '',
        description: '',
        questions: [],
      }
    };
  },
  methods: {
    addQuestion() {
      this.test.questions.push({
        title: '',
        content: '',
        type: 'short-answer',
        answer: '', // Для коротких ответов
        options: [], // Для вопросов с выбором
      });
    },
    removeQuestion(qIndex) {
      this.test.questions.splice(qIndex, 1);
    },
    addOption(qIndex) {
      this.test.questions[qIndex].options.push({
        text: '',
        isCorrect: false
      });
    },
    removeOption(qIndex, oIndex) {
      this.test.questions[qIndex].options.splice(oIndex, 1);
    },
    submitTest() {
      fetch('https://example.com/api/tests', { // Замените URL на фактический URL вашего API
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          // Добавьте другие заголовки, если необходимо, например, для аутентификации
        },
        body: JSON.stringify(this.prepareTestData()),
      })
          .then(response => {
            if (!response.ok) {
              throw new Error('Ошибка при отправке теста');
            }
            return response.json();
          })
          .then(data => {
            console.log('Тест успешно отправлен:', data);
            // Обработка успешной отправки, например, перенаправление на другую страницу
          })
          .catch(error => {
            console.error('Ошибка при отправке теста:', error);
          });
    },
    prepareTestData() {
      const currentUser = JSON.parse(localStorage.getItem('currentUser'));
      const isuNumber = currentUser ? currentUser.isuNumber : null;

      return {
        ...this.test,
        isuNumber: isuNumber, // Добавляем isuNumber к данным теста
        questions: this.test.questions.map(question => {
          if (question.type === 'single-choice' || question.type === 'multiple-choice') {
            return {
              ...question,
              options: question.options.map(option => ({
                text: option.text,
                isCorrect: option.isCorrect,
              })),
            };
          }
          return question;
        })
      };
    }
  }
};
</script>

<style>
/* Глобальные стили для вашего приложения */
</style>
