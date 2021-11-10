import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

export default new VueRouter({
    mode: 'history',
    routes:[
        // 로그인 & 실행 후 첫페이지
        {
            path:'/',
            name: 'login',
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
        // 다른사람 프로필 들어갔을 때
        {
            path: '/user',
            component: () => import('@/views/user/memberProfile.vue')
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
            component: () => import('@/views/team/teamMainPage.vue'),
            name : 'team',
            props: true
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
        
        // 팀 일정 상세페이지
        {
            path: '/team/calendar/detail',
            component: () => import('@/views/team/schedule/scheduleDetailPage.vue')
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
        
        // 회원가입 후 관심종목 선택
        {
            path: '/selectCategory',
            component: () => import('@/views/sriends/selectCategoryPage.vue')
        },

        // 관심목록 팀찾기 목록
        {
            path: '/teamList',
            component: () => import('@/views/sriends/teamListPage.vue'),
            name: 'teamList',
            props:true
        },

        // 검색할 지역/종목 선택 페이지
        {
            path: '/searchTeam',
            component: () => import('@/views/sriends/searchTeamPage.vue')
        },

        // 지역/종목에 해당하는 팀 리스트페이지
        {
            path: '/searchTeamList',
            name: 'searchTeamList',
            component: () => import('@/views/sriends/searchTeamListPage.vue'),
            props: true
        },

        // 경기/스렌드 찾기 목록페이지
        {
            path: '/matchingList',
            component: () => import('@/views/matching/matchingListPage.vue')
        },

        // 매칭생성
        {
            path: '/createMatching',
            component: () => import('@/views/matching/createMatchingPage.vue')
        },

        // 매칭수정
        {
            path: '/modifyMatching',
            component: () => import('@/views/matching/modifyMatchingPage.vue')
        },

        // 팀게시글 상세페이지
        {
            path: '/team/articleDetail',
            component: () => import('@/views/team/article/articleDetailPage.vue'),
            name: 'articleDetail',
            props: true
        },

        // 팀게시글 수정페이지
        {
            path: '/team/modifyArticle',
            component: () => import('@/views/team/article/modifyArticlePage.vue'),
            name: 'modifyArticle',
            props: true
        },

        // 매칭글 상세페이지
        {
            path: '/matchingDetail',
            component: () => import('@/views/matching/matchingDetailPage.vue')
        }
    ]
})