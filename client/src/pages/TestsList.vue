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
      <div class="flex-1 break-words  mr-3">{{ test.title }}</div>
      <div class="flex-1 break-words mr-3">{{ test.subject }}</div>
      <div class="flex-1 break-words mr-3">{{ test.questionCount }} вопросов</div>
      <div class="flex-1 break-words mr-3">{{ test.duration }} минут</div>
      <div :class="test.passed ? 'bg-green-500' : 'bg-red-500'" class="h-4 w-4 rounded-full"></div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeTab: 'Все тесты',
      tabs: ['Все тесты', 'Не пройденные', 'Завершенные'],
      allTests: [
        { id: 1, title: 'Принципы нормализации и оптимизации баз данных ', duration: 60, questionCount: 20, subject: 'Проектирование баз данных', passed: true },
        { id: 2, title: 'Стратегии управления аудиторией и обратной связью', duration: 45, questionCount: 15, subject: 'Техники публичных выступлений', passed: false },
        { id: 3, title: 'Продвинутые алгоритмы машинного обучения', duration: 30, questionCount: 10, subject: 'Машинное обучение', passed: true },
        { id: 4, title: 'Современные подходы к проектированию NoSQL баз данных ', duration: 60, questionCount: 20, subject: 'Проектирование баз данных', passed: true },
        { id: 5, title: 'Статистические методы в прикладной математике', duration: 45, questionCount: 15, subject: 'Прикладная математика', passed: true },
        { id: 6, title: 'Нейронные сети и глубокое обучение', duration: 30, questionCount: 10, subject: 'Машинное обучение', passed: true },
        { id: 7, title: 'Расширенные методы проектирования баз данных ', duration: 60, questionCount: 20, subject: 'Проектирование баз данных', passed: true },
        { id: 8, title: 'Автоматизация задач в Windows Server с помощью PowerShell', duration: 45, questionCount: 15, subject: 'Администрирование в ОС Windows Server', passed: false },
        { id: 9, title: 'Основы эффективной речи и презентации', duration: 30, questionCount: 10, subject: 'Техники публичных выступлений', passed: true },
        { id: 10, title: 'Основы SQL и проектирования схем баз данных ', duration: 60, questionCount: 20, subject: 'Проектирование баз данных', passed: true },
        { id: 11, title: 'Адаптивный и респонсивный веб-дизайн', duration: 45, questionCount: 15, subject: 'Web-программирование', passed: false },
      ],
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
    goToTest(testId) {
      this.$router.push({ name: 'Test', params: { test_id: testId } });
    }
  },
  mounted() {
    this.notPassedTests = this.allTests.filter(test => !test.passed);
    this.completedTests = this.allTests.filter(test => test.passed);
  },
};
</script>
