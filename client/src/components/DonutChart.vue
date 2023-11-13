<template>
  <div class="flex justify-center items-center">
    <svg width="200" height="200" class="relative">
      <circle r="70" cx="100" cy="100" fill="transparent" stroke="#f87171" stroke-width="40" :stroke-dasharray="circumference" :stroke-dashoffset="wrongOffset"></circle>
      <circle r="70" cx="100" cy="100" fill="transparent" stroke="#34d399" stroke-width="40" :stroke-dasharray="circumference" :stroke-dashoffset="correctOffset"></circle>
    </svg>
    <div class="absolute">
      <span class="text-lg font-semibold">{{ correctPercentage }}% правильно</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      correctPercentage: 73.10, // Процент правильных ответов
      wrongPercentage: 22.90, // Процент неправильных ответов
    };
  },
  computed: {
    circumference() {
      return 2 * Math.PI * 70; // Длина окружности
    },
    correctOffset() {
      return this.circumference * (1 - this.correctPercentage / 100);
    },
    wrongOffset() {
      return this.circumference * (1 - this.wrongPercentage / 100);
    },
  },
};
</script>
