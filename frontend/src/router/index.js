import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

export default new VueRouter({
    mode: 'history',
    routes:[
        {
            path:'/',
            component: () => import('@/views/user/loginPage.vue')
        },
        {
            path:'/signup',
            component: () => import('@/views/user/signupPage.vue')
        },
        {
            path: '/main',
            component: () => import('@/views/mainPage.vue')
        },
        {
            path: '/myTeam',
            component: () => import('@/views/user/myPage.vue')
        },
        {
            path: '/findPassword',
            component: () => import('@/views/user/findPasswordPage.vue')
        },
    ]
})