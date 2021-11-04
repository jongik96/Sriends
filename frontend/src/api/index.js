import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL

const token = localStorage.getItem('token')
const config = {
    Authorization: `Bearer ${token}`
}

// ------------------------------------------------------회원관련 api

function getProfileInfo(){
    return axios({
        method:'get',
        url: `${SERVER_URL}/users/me`,
        headers: config
    })
}

// 로그인 api
function loginUser(loginData){
    return axios({
        method:'post',
        url: `${SERVER_URL}/auth/login`,
        data: loginData
    })
}

// 이메일 중복검사 api
function duplicatedCheckEmail(parameter){
    return axios({
        method:'get',
        url: `${SERVER_URL}/auth/sign-up/${parameter}`,
    })
}

// 입력한 이메일로 인증코드 전송 api
function certificationEmail(email){
    return axios({
        method:'get',
        url: `${SERVER_URL}/auth/sign-up/${email}`,
    })
}

// 회원가입 시 인증코드 확인 api
function certificationEmailCode(certCode, email){
    return axios({
        method:'post',
        url: `${SERVER_URL}/auth//${email}`,
        data: {
            code : certCode
        }
    })
}

// 비밀번호 변경 api
function modifyPassword(password){
    return axios({
        method:'put',
        url: `${SERVER_URL}/users/password`,
        headers: config,
        data: password
    })
}

// 비밀번호 확인 api
function confirmPassword(password){
    return axios({
        method:'post',
        url: `${SERVER_URL}/users/password`,
        headers: config,
        data: password
    })
}

// 회원삭제 api
function deleteUser(userid){
    return axios({
        method:'delete',
        url: `${SERVER_URL}/users/${userid}`,
        headers: config
    })
}

// 관심종목 선택 api
function selectSports(sports){
    return axios({
        method:'post',
        url: `${SERVER_URL}/users/interest`,
        headers: config,
        data : {
            'sportCategory' : sports
        }
    })
}

// ---------------------------------------------------팀정보관련


// 관심종목 팀 조회 api
function getInterestTeam(){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/list`,
        headers: config,
    })
}

// 팀 정보 받아오기 api
function getTeamInfo(){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/list`,
        headers: config,
    })
}

// 지역 종목 선택한 팀 리스트 조회 api
function getTeamList(city, sportCategory){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/list/${city}/${sportCategory}`,
        headers: config,
    })
}

// 팀 가입신청 api
function joinTeam(teamId,userId, description){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/${teamId}/${userId}`,
        headers: config,
        data : {description : description}
    })
}

// 팀 가입승인 api
function permitTeam(teamId,userId){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/permit/${teamId}/${userId}`,
        headers: config,
    })
}

// 가입신청 리스트 조회 api
function lookupApplyList(teamId){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/apply-list/${teamId}`,
        headers: config,
    })
}

// 본인 권한 조회
function getPermitState(teamId){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/${teamId}/me`,
        headers: config,
    })
}

// 팀 관리자 권한 부여
function givePermit(teamId,userId){
    return axios({
        method:'post',
        url: `${SERVER_URL}/teams/manager/${teamId}/${userId}`,
        headers: config,
    })
}

// 팀 멤버 목록 조회 api
function getTeamMemberList(teamId){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/member-list/${teamId}`,
        headers: config,
    })
}

// 팀 탈퇴하기 api
function outTeam(teamId){
    return axios({
        method:'delete',
        url: `${SERVER_URL}/teams/${teamId}/me`,
        headers: config,
    })
}

// 멤버강퇴하기 api
function kickMember(teamId,userId){
    return axios({
        method:'delete',
        url: `${SERVER_URL}/teams/kick-out/${teamId}/${userId}`,
        headers: config,
    })
}

// ---------------------------------------팀 공지사항 관련-----------------------

// 팀 공지사항 리스트 조회 api
function getArticleList(teamId){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/list/${teamId}`,
        headers: config,
    })
}

// 팀 공지사항 삭제 api
function deleteArticle(teamId,boardId){
    return axios({
        method:'delete',
        url: `${SERVER_URL}/teams/list/${teamId}/${boardId}`,
        headers: config,
    })
}

// 팀 공지사항 상세정보 api
function getArticleInfo(teamId,boardId){
    return axios({
        method:'get',
        url: `${SERVER_URL}/teams/list/${teamId}/${boardId}`,
        headers: config,
    })
}

// 팀 공지사항 수정 api
function modifyArticleInfo(teamId,boardId){
    return axios({
        method:'put',
        url: `${SERVER_URL}/teams/list/${teamId}/${boardId}`,
        headers: config,
    })
}

export {
    // 회원관련 api
    getProfileInfo, 
    loginUser, 
    duplicatedCheckEmail,
    certificationEmail,
    certificationEmailCode,
    modifyPassword,
    confirmPassword,
    deleteUser,
    selectSports,

    //팀정보관련 api
    getInterestTeam,
    getTeamInfo,
    getTeamList,
    joinTeam,
    permitTeam,
    lookupApplyList,
    getPermitState,
    givePermit,
    getTeamMemberList,
    outTeam,
    kickMember,


    // 팀 공지사항 api
    getArticleList,
    deleteArticle,
    getArticleInfo,
    modifyArticleInfo,


}