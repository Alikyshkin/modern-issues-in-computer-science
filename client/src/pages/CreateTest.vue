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
        <textarea
            class="form-textarea shadow appearance-none border rounded w-full py-2 px-3 mb-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            placeholder="Текст вопроса" v-model="question.text"></textarea>

        <!-- Выбор типа вопроса -->
        <select v-model="question.type"
                class="form-select block w-full mt-1 shadow border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
          <option value="text">Короткий ответ</option>
          <option value="single">Одиночный выбор</option>
          <option value="multiple">Множественный выбор</option>
        </select>

        <!-- Варианты ответа -->
        <div v-if="question.type === 'single' || question.type === 'multiple'">
          <div v-for="(answer, aIndex) in question.answers" :key="aIndex" class="flex">
            <input class="form-input w-full block mt-1 shadow border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" type="text" v-model="answer.text">
            <button @click="removeAnswer(qIndex, aIndex)"
                    class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
              Удалить
            </button>
          </div>
          <button @click="addAnswer(qIndex)"
                  class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mt-4 w-full">
            Добавить ответ
          </button>
        </div>

        <button @click="removeQuestion(index)"
                class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mt-4 w-full">
          <i class="fas fa-minus"></i>
        </button>
      </div>
    </div>
    <button @click="addQuestion" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full">
      Добавить вопрос <i class="fas fa-plus"></i>
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
        text: '',
        type: 'text', // Установка начального значения типа вопроса
        answers: [],
      });
    },
    removeQuestion(index) {
      this.test.questions.splice(index, 1);
    },
    addAnswer(questionIndex) {
      this.test.questions[questionIndex].answers.push({text: ''});
    },

    removeAnswer(questionIndex, answerIndex) {
      this.test.questions[questionIndex].answers.splice(answerIndex, 1);
    },
    submitTest() {
      fetch('https://example.com/api/tests', { // Замените URL на фактический URL вашего API
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          // Добавьте другие заголовки, если необходимо, например, для аутентификации
        },
        body: JSON.stringify(this.test),
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
    }
  }
};
</script>

<style>
/* Глобальные стили для вашего приложения */
</style>
