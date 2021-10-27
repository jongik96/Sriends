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
            path:'/modify',
            component: () => import('@/views/user/modifyPage.vue')
        },
        {
            path: '/main',
            component: () => import('@/views/user/mainPage.vue')
        },
        {
            path: '/findPassword',
            component: () => import('@/views/user/findPasswordPage.vue')
        },
        {
            path: '/confirmPassword',
            component: () => import('@/views/user/confirmPasswordPage.vue')
        },
        {
            path: '/modifyPassword',
            component: () => import('@/views/user/modifyPasswordPage.vue')
        },
        {
            path: '/teamCreate',
            component: () => import('@/views/user/teamCreatePage.vue')
        },
        {
            path: '/teamNotice',
            component: () => import('@/views/user/teamNoticePage.vue')
        },
        {
            path: '/teamModify',
            component: () => import('@/views/user/teamModifyPage.vue')
        },
    ]
})