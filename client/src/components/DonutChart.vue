<template>
  <div class="flex justify-center items-center">
    <svg width="200" height="200" class="relative">
      <!-- Only one circle for the average test result -->
      <circle r="70" cx="100" cy="100" fill="transparent" stroke="#34d399" stroke-width="40" :stroke-dasharray="circumference" :stroke-dashoffset="averageResultOffset"></circle>
    </svg>
    <div class="absolute">
      <span class="text-lg font-semibold">{{ averageTestResult }}% средний результат</span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    averageTestResult: {
      type: Number,
      required: true
    }
  },
  computed: {
    correctPercentage() {
      // Вычисление процента правильных ответов
      return (this.testResult.correctAnswers / this.testResult.totalQuestions) * 100;
    },
    wrongPercentage() {
      // Вычисление процента неправильных ответов
      return (this.testResult.wrongAnswers / this.testResult.totalQuestions) * 100;
    },
    circumference() {
      return 2 * Math.PI * 70; // Длина окружности
    },
    correctOffset() {
      return this.circumference * (this.averageTestResult / 100);
    },
    wrongOffset() {
      return this.circumference * (1 - this.averageTestResult / 100);
    },
  },
};
</script>
