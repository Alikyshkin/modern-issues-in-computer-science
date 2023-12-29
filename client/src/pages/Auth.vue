<template>
  <div class="max-w-6xl mx-auto p-4">
    <h1>Авторизация</h1>

    <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" @submit.prevent="login">
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="email">
          Электронная почта
        </label>
        <input v-model="user.email" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="email" type="email" placeholder="email@example.com" required>
      </div>
      <div class="mb-6">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="password">
          Пароль
        </label>
        <input v-model="user.password" class="form-input shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="********" required>
      </div>
<!--      <div class="mb-4">-->
<!--        <label class="block text-gray-700 text-sm font-bold mb-2">-->
<!--          Выберите роль-->
<!--        </label>-->
<!--        <div>-->
<!--          <label class="mr-4">-->
<!--            <input type="radio" v-model="user.role" value="STUDENT">-->
<!--            Студент-->
<!--          </label>-->
<!--          <label>-->
<!--            <input type="radio" v-model="user.role" value="TEACHER">-->
<!--            Учитель-->
<!--          </label>-->
<!--        </div>-->
<!--      </div>-->
      <div class="flex items-center justify-between">
        <button
            :class="{'opacity-50 cursor-not-allowed': !isFormFilled, 'hover:bg-blue-700': isFormFilled}"
            :disabled="!isFormFilled"
            class="bg-blue-500 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit">
          Войти
        </button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Auth',
  data() {
    return {
      user: {
        email: '',
        password: '',
        // role: '',
      },
    };
  },
  computed: {
    isFormFilled() {
      return this.user.email && this.user.password;
    },
  },
  methods: {
    login() {
      console.log(this.user)
      fetch('http://localhost:8080/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
          .then(response => {
            if (!response.ok) {
              throw new Error('Ошибка авторизации');
            }
            return response.json();
          })
          .then(data => {
            console.log('Успешная авторизация:', data);
            this.saveCurrentUser(data);
          })
          // .catch(error => {
          //   console.error('Ошибка при попытке входа:', error);
          // });
    },
    saveCurrentUser(data) {
      // Предполагаем, что data содержит нужные поля: token, name, isuNumber, email, role
      const currentUser = {
        password: data.password,
        userName: data.userName,
        isuNumber: data.isuNumber,
        email: data.email,
        role: data.role
      };
      localStorage.setItem('currentUser', JSON.stringify(currentUser));
      console.log(localStorage.getItem('currentUser'));
      this.$router.push('/tests-list');

    },
  },
};
</script>


<style scoped>

</style>
