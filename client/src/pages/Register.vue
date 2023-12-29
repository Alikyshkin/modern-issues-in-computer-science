<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Регистрация</h1>

    <div class="flex flex-col space-y-4 lg:flex-row lg:space-y-0 lg:space-x-4 mb-4">
      <button
          class="flex-1 py-2 px-4 text-white bg-blue-500 hover:bg-blue-700"
          :class="{ 'bg-blue-700': activeTab === 'student' }"
          @click="changeTab('student')"
      >
        Студент
      </button>
      <button
          class="flex-1 py-2 px-4 text-white bg-blue-500 hover:bg-blue-700"
          :class="{ 'bg-blue-700': activeTab === 'teacher' }"
          @click="changeTab('teacher')"
      >
        Преподаватель
      </button>
    </div>

    <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" @submit.prevent="submitForm">
      <h2 class="text-lg font-bold mb-4">{{ formTitle }}</h2>
      <div class="mb-4 relative">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="name">Имя</label>
        <input v-model="user.userName" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="name" type="text" placeholder="Имя" required>
        <i v-if="user.userName" :class="{'fas fa-check text-green-500': validateName, 'fas fa-times text-red-500': !validateName}" class="absolute right-3 top-3"></i>
        <p v-if="!validateName && user.userName" class="text-red-500 text-xs italic">Минимум 3 символа.</p>
      </div>
<!--      <div class="mb-4 relative">-->
<!--        <label class="block text-gray-700 text-sm font-bold mb-2" for="isu-number">Номер ИСУ</label>-->
<!--        <input v-model="user.isuNumber" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="isu-number" type="number" placeholder="Номер ИСУ" required>-->
<!--        <i v-if="user.isuNumber" :class="{'fas fa-check text-green-500': validateIsuNumber, 'fas fa-times text-red-500': !validateIsuNumber}" class="absolute right-3 top-3"></i>-->
<!--        <p v-if="!validateIsuNumber && user.isuNumber" class="text-red-500 text-xs italic">Должен быть числом и содержать минимум 3 символа.</p>-->
<!--      </div>-->
      <div class="mb-4 relative">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="email">Электронная почта</label>
        <input v-model="user.email" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="email" type="email" placeholder="email@example.com" required>
        <i v-if="user.email" :class="{'fas fa-check text-green-500': validateEmail, 'fas fa-times text-red-500': !validateEmail}" class="absolute right-3 top-3"></i>
        <p v-if="!validateEmail && user.email" class="text-red-500 text-xs italic">Неверный формат электронной почты.</p>
      </div>
      <div class="mb-6 relative">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Пароль</label>
        <input v-model="user.password" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="********" required>
        <i v-if="user.password" :class="{'fas fa-check text-green-500': validatePassword, 'fas fa-times text-red-500': !validatePassword}" class="absolute right-3 top-3"></i>
        <p v-if="!validatePassword && user.password" class="text-red-500 text-xs italic">Минимум 3 символа.</p>
      </div>
      <div class="flex items-center justify-between">
        <button
            class="bg-blue-500 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
            @click="submitForm">
          <!-- Добавленный атрибут @click -->

          Регистрация
        </button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    return {
      activeTab: 'student',
      user: {
        userName: '',
        // isuNumber: '',
        email: '',
        password: '',
        role: '', // Добавляем поле для указания роли пользователя
      },
    };
  },
  computed: {
    formTitle() {
      return `Зарегистрироваться как ${this.activeTab === 'student' ? 'студент' : 'преподаватель'}`;
    },
    validateName() {
      return this.user.userName.length >= 3;
    },
    validateIsuNumber() {
      return !isNaN(this.user.isuNumber);
    },
    validateEmail() {
      return /\S+@\S+\.\S+/.test(this.user.email);
    },
    validatePassword() {
      return this.user.password.length >= 3;
    },
    isFormValid() {
      return (
          this.validateName &&
          this.validateIsuNumber &&
          this.validateEmail &&
          this.validatePassword &&
          this.user.role !== ''
      );
    },
  },
  methods: {
    changeTab(tab) {
      this.activeTab = tab;
      if (tab === 'student') {
        this.user.role = 'STUDENT';
      } else if (tab === 'teacher') {
        this.user.role = 'TEACHER';
      }
      console.log(this.user.role);
    },
    submitForm() {
      fetch('http://localhost:8080/users/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
          .then(response => response.json())
          .then(data => {
            console.log(data);
          })
          .catch(error => {
            console.error('Ошибка при отправке формы:', error);
          });
      this.$router.push('/auth');
    }
  },
};
</script>

<style scoped>

</style>
