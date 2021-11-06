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

// ---------------------------------------------------팀 일정관리 api

// 팀 월별 일정 목록 가져오기
function getListMonth(teamId,year,month){
    return instance.get(`teams/calendar/${teamId}/${year}/${month}`)
}

// 일정 정보 가져오기
function getSchedule(calendarId){
    return instance.get(`teams/calendar/${calendarId}`)
}

// 일정 정보 삭제하기
function deleteSchedule(calendarId){
    return instance.delete(`teams/calendar/${calendarId}`)
}

// 일정 추가하기
function postSchedule(teamId,wrtierId,name,content,schedule){
    return instance.post(`teams/calendar/${teamId}`,{
        "teamId": teamId,
        "writerId": wrtierId,
        "name": name,
        "content": content,
        "schedule": schedule
    })
}

// 일정 수정하기
function putSchedule(calendarId,teamId,wrtierId,name,content,schedule){
    return instance.put(`teams/calendar/${calendarId}`,{
        "teamId": teamId,
        "writerId": wrtierId,
        "name": name,
        "content": content,
        "schedule": schedule
    })
}

export {
    getListMonth,
    getSchedule,
    deleteSchedule,
    postSchedule,
    putSchedule
}