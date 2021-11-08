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

//----------------------------------- api 시작

// 프로필 조회 api
function getProfileInfo(){
    return instance.get('users/me')
}

// 다른사람 프로필 조회
function getTempProfileInfo(userId){
    return instance.get(`users/${userId}`)
}

// 로그인 api
function loginUser(loginData){
    return instance.post('auth/login',loginData)
}

// 이메일 중복검사 api
function duplicatedCheckEmail(parameter){
    return instance.get(`auth/sign-up/${parameter}`)
}

// 입력한 이메일로 인증코드 전송 api
function certificationEmail(email){
    return instance.get(`auth/${email}`)
}

// 회원가입 시 인증코드 확인 api
function certificationEmailCode(email,certCode){
    return instance.post(`auth/${email}`,{code:certCode})
}

// 비밀번호 변경 api
function modifyPassword(password){
    return instance.put('users/password',password)
}

// 비밀번호 확인 api
function confirmPassword(password){
    return instance.post('users/password',password)
}

// 회원삭제 api
function deleteUser(userid){
    return instance.delete(`users/${userid}`)
}

// 관심종목 선택 api
function selectSports(sports){
    return instance.put('users/interest',{'interests':sports})
}

function getInterest(){
    return instance.get('users/interest')
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
        getInterest,
        getTempProfileInfo
}