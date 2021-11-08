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

/////////////////// 매칭 관련 api

// 매칭글 상세보기 api
function getMatchDetail(postId){
   return instance.get(`match/board/posts/info/${postId}`)
}

// 매칭글 작성전 자기 팀 리스트 조회 api
function getMyTeam(){
    return instance.get('/match/board/posts/team')
}

// 매칭글 생성 api
function postMatching(form){
    return instance.post(`match/board/posts`,form)
}

export {
    getMatchDetail,
    postMatching,
    getMyTeam

}