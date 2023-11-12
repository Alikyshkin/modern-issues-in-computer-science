import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/pages/Home.vue'
import Analytics from '../components/pages/Analytics.vue'
import Auth from '../components/pages/Auth.vue'
import CreateTest from '../components/pages/CreateTest.vue'
import Register from '../components/pages/Register.vue'
import TestsList from '../components/pages/TestsList.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/analytics',
        name: 'Analytics',
        component: Analytics
    },
    {
        path: '/tests-list',
        name: 'TestsList',
        component: TestsList
    },
    {
        path: '/auth',
        name: 'Auth',
        component: Auth
    },
    {
        path: '/create-test',
        name: 'CreateTest',
        component: CreateTest
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/tests-list',
        name: 'TestsList',
        component: TestsList
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
