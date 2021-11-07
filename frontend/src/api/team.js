import axios from 'axios';
import { setInterceptors } from './common/interceptors'
function createInstance(){
    const instance = axios.create({
        baseURL: process.env.VUE_APP_SERVER_URL,
    })
    // interceptor 설정
    return setInterceptors(instance);
}

const instance = createInstance();

// ---------------------------------------------------팀정보관련


// 관심종목 팀 조회 api
function getInterestTeam(){
    return instance.get('teams/list')
}

// 지역 종목 선택한 팀 리스트 조회 api
function getTeamList(city, sportCategory){
    return instance.get(`teams/list/${city}/${sportCategory}`)
}

// 팀 정보 받아오기 api
function getTeamInfo(teamId){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/${teamId}`,
    //     headers: config,
    // })

    return instance.get(`teams/${teamId}`)
}


// 팀 정보 수정하기 api
function modifyTeamInfo(teamId,form){
    // return axios({
    //     method:'put',
    //     url: `${SERVER_URL}/teams/${teamId}`,
    //     headers: config,
    //     data: form,
    // })
    return instance.put(`teams/${teamId}`,form)
}

// 팀 가입신청 api
function joinTeam(teamId, userId, description){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/${teamId}/${userId}`,
    //     headers: config,
    //     data : {description : description}
    // })


    return instance.post(`teams/${teamId}/${userId}`,{'description':description})
}

// 팀 가입승인 api
function permitTeam(teamId,userId){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/permit/${teamId}/${userId}`,
    //     headers: config,
    // })



    return instance.get(`teams/permit/${teamId}/${userId}`)
}

// 가입신청 리스트 조회 api
function lookupApplyList(teamId){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/apply-list/${teamId}`,
    //     headers: config,
    // })


    return instance.get(`teams/apply-list/${teamId}`)
}

// 본인 권한 조회
function getPermitState(teamId){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/${teamId}/me`,
    //     headers: config,
    // })


    return instance.get(`teams/${teamId}/me`)
}

// 팀 관리자 권한 부여
function givePermit(teamId,userId){
    // return axios({
    //     method:'post',
    //     url: `${SERVER_URL}/teams/manager/${teamId}/${userId}`,
    //     headers: config,
    // })

    return instance.post(`teams/manager/${teamId}/${userId}`)

}

// 팀 멤버 목록 조회 api
function getTeamMemberList(teamId){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/member-list/${teamId}`,
    //     headers: config,
    // })

    return instance.get(`teams/member-list/${teamId}`)
}

// 팀 탈퇴하기 api
function outTeam(teamId){
    // return axios({
    //     method:'delete',
    //     url: `${SERVER_URL}/teams/${teamId}/me`,
    //     headers: config,
    // })


    return instance.delete(`teams/${teamId}/me`)
}

// 멤버강퇴하기 api
function kickMember(teamId,userId){
    // return axios({
    //     method:'delete',
    //     url: `${SERVER_URL}/teams/kick-out/${teamId}/${userId}`,
    //     headers: config,
    // })


    return instance.delete(`teams/kick-out/${teamId}/${userId}`)
}

export{
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
    modifyTeamInfo,
}