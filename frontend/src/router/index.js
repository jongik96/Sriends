import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

export default new VueRouter({
    mode: 'history',
    routes:[
        // 로그인 & 실행 후 첫페이지
        {
            path:'/',
            component: () => import('@/views/user/loginPage.vue')
        },
        // 회원가입
        {
            path:'/signup',
            component: () => import('@/views/user/signupPage.vue')
        },
        // 회원정보 수정
        {
            path:'/modify',
            component: () => import('@/views/user/modifyPage.vue')
        },
        // 로그인 후 메인화면 & 마이페이지
        {
            path: '/main',
            component: () => import('@/views/user/mainPage.vue')
        },
        // 비밀번호 찾기 페이지
        {
            path: '/findPassword',
            component: () => import('@/views/user/findPasswordPage.vue')
        },
        // 비밀번호 확인페이지
        {
            path: '/confirmPassword',
            component: () => import('@/views/user/confirmPasswordPage.vue')
        },
        // 비밀번호 수정페이지
        {
            path: '/modifyPassword',
            component: () => import('@/views/user/modifyPasswordPage.vue')
        },
        // 팀 생성 페이지
        {
            path: '/teamCreate',
            component: () => import('@/views/team/teamCreatePage.vue')
        },
        //팀 정보수정 페이지
        {
            path: '/teamModify',
            component: () => import('@/views/team/teamModifyPage.vue')
        },
        // 팀 메인페이지
        {
            path : '/team',
            component: () => import('@/views/team/teamMainPage.vue')
        },
        // 팀 가입신청
        {
            path: '/joinTeam',
            component: () => import('@/views/team/joinTeamPage.vue')
        },
        // 팀 공지사항
        {
            path: '/team/articleList',
            component: () => import('@/views/team/article/teamArticlePage.vue')
        },
        // 팀 공지사항 작성
        {
            path: '/team/createArticle',
            component: () => import('@/views/team/article/createArticlePage.vue')
        },
        // 팀 일정관리 페이지
        {
            path: '/team/calendar',
            component: () => import('@/views/team/schedule/teamCalendarPage.vue')
        },

        // 팀 일정 추가페이지
        {
            path:'/team/makeSchedule',
            component: () => import('@/views/team/schedule/makeSchedulePage.vue')
        },
        
        // 팀 멤버목록 페이지
        {
            path: '/team/memberList',
            component: () => import('@/views/team/member/teamMemberPage.vue')
        },

        // 가입 대기 중 회원 목록페이지
        {
            path: '/team/waitingMemberList',
            component: () => import('@/views/team/member/waitingMemberPage.vue')
        },
        
        // 팀찾기 목록
        {
            path: '/team/list',
            component: () => import('@/views/sriends/teamListPage.vue')
        },

        // 경기/스렌드 찾기 목록페이지
        {
            path: '/matchingList',
            component: () => import('@/views/matching/matchingListPage.vue')
        },

        // 매칭목록
        {
            path: '/createMatching',
            component: () => import('@/views/matching/createMatchingPage.vue')
        },

        // 팀게시글 상세페이지
        {
            path: '/team/articleDetail',
            component: () => import('@/views/team/article/articleDetailPage.vue')
        },

        // 매칭글 상세페이지
        {
            path: '/matchingDetail',
            component: () => import('@/views/matching/matchingDetailPage.vue')
        }
    ]
})