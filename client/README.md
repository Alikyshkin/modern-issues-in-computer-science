# Vue 3 + Vite

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VS Code](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Тестирование без бека
Чтобы сделать пользователя авторизованным нужно ввести в консоль (в гугле инструменты разработчика) и обновить страницу


    const currentUser = {
    token: "1234567890abcdef",
    name: "Иван Иванов",
    isuNumber: "123456",
    email: "ivanov@example.com",
    role: "student" // или "admin", "teacher" в зависимости от того, какую роль вы хотите протестировать
    };
    localStorage.setItem('currentUser', JSON.stringify(currentUser));


Чтобы удалить

    localStorage.removeItem('currentUser');


