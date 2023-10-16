import { createRouter, createWebHistory } from 'vue-router';
import Home from '../components/Home.vue';
import Tests from '../components/Tests.vue';

const routes = [
    {
        path: '/',
        component: Home,
    },
    {
        path: '/tests',
        component: Tests,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
