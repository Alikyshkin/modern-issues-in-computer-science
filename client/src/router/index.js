import { createRouter, createWebHistory } from 'vue-router'
import Home from '../pages/Home.vue'
import Analytics from '../pages/Analytics.vue'
import Auth from '../pages/Auth.vue'
import CreateTest from '../pages/CreateTest.vue'
import Register from '../pages/Register.vue'
import TestsList from '../pages/TestsList.vue'
import Profile from "../pages/Profile.vue";
import Test from "../pages/Test.vue";

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
        path: '/test/:test_id',
        name: 'Test',
        component: Test
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
