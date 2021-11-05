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

// ---------------------------------------팀 공지사항 관련-----------------------

// 팀 공지사항 리스트 조회 api
function getArticleList(teamId){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/board/${teamId}`,
    //     headers: config,
    // })

    return instance.get(`teams/board/${teamId}`)
}

// 팀 공지사항 삭제 api
function deleteArticle(teamId,boardId){
    // return axios({
    //     method:'delete',
    //     url: `${SERVER_URL}/teams/board/${teamId}/${boardId}`,
    //     headers: config,
    // })

    return instance.delete(`teams/board/${teamId}/${boardId}`)
}

// 팀 공지사항 상세정보 api
function getArticleInfo(teamId,boardId){
    // return axios({
    //     method:'get',
    //     url: `${SERVER_URL}/teams/board/${teamId}/${boardId}`,
    //     headers: config,
    // })

    return instance.get(`teams/board/${teamId}/${boardId}`)
}

// 팀 공지사항 작성 api
function createArticleInfo(teamId,form){
    // return axios({
    //     method:'post',
    //     url: `${SERVER_URL}/teams/board/${teamId}`,
    //     headers: config,
    //     data: form
    // })

    return instance.post(`teams/board/${teamId}`,form)
}

// 팀 공지사항 수정 api
function modifyArticleInfo(teamId,boardId,form){
    // return axios({
    //     method:'put',
    //     url: `${SERVER_URL}/teams/board/${teamId}/${boardId}`,
    //     headers: config,
    //     data: form
    // })

    return instance.put(`teams/board/${teamId}/${boardId}`,form)
}

export {

    // 팀 공지사항 api
    getArticleList,
    deleteArticle,
    getArticleInfo,
    modifyArticleInfo,
    createArticleInfo


}