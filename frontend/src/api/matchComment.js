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

/// =-------------------------팀 공지사항 댓글

// 댓글 불러오기
function getArticleComments(commentId){
    return instance.get(`match/board/comments/${commentId}`)
}

// 댓글목록 조회하기
function getArticleCommentsList(postId,parentId){
    return instance.get(`match/board/comments/${postId}/${parentId}`)
}

// 댓글 작성하기
function postArticleComments(postId,parentId,content){
    return instance.post(`match/board/comments/${postId}`,{
         'parentId': parentId,'content': content
    })
}

// 댓글 수정하기
function putArticleComments(commentId ,content){
    return instance.put(`match/board/comments/${commentId}`,content)
}

// 댓글 삭제하기
function deleteArticleComments(commentId){
    return instance.delete(`match/board/comments/${commentId}`)
}

export {
    getArticleComments,
    postArticleComments,
    putArticleComments,
    deleteArticleComments,
    getArticleCommentsList

}