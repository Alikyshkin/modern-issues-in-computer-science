<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Название теста</h1>
    <h5> Поздравляем! Тест завершен</h5>

    <div class="flex">
    <div class="w-1/2 pl-4 pr-4 pt-32">
    <div class="flex border-b p-4 font-bold">
      <div class="flex-1">№</div>
      <div class="flex-1">Вопрос</div>
      <div class="flex-1">Вердикт</div>
    </div>
    <!-- Список тестов -->
    <div v-for="q in questions" :key="q.id" class="flex border-b p-4 cursor-pointer">
      <div class="flex-1 break-words  mr-3">{{ q.id }}</div>
      <div class="flex-1 break-words  mr-3">{{ q.title }}</div>
      <div class="flex-1 break-words mr-3">{{ q.answer }}</div>
    </div>
  </div>
    <div class="w-1/2 pl-4 pr-4 pt-32">
    <DonutChart/>
    </div>
  </div>
  </div>
</template>

<script>
import DonutChart from '../components/DonutChart.vue'

export default {
  name: 'Analytics',
  components: {
    DonutChart
  },
  data() {
    return {
      questions: [
        {
          id: 1,
          type: "short-answer",
          title: "Что такое нормализация баз данных?",
          content: "Опишите основную идею нормализации баз данных.",
          answer: "100%",
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
          answer: "78.00%",
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
          answer: "50%",
          answered: false
        },
        // Добавьте больше вопросов по аналогии
      ]
    }
  },
};
</script>
