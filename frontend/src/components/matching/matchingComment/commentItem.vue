<template>
    <div class="shadow-md p-4 mt-5 mx-6 mb-4 min-w-300 border-2 border-yellow-400 rounded-lg">
        <div v-if="!modifyState" class="grid grid-cols-6 h-10">
            <p class="col-start-1 col-span-5 break-words text-justify leading-tight text-gray-800">{{ this.content }}</p>
            <div class="col-start-6 col-span-1 ml-3 lg:ml-10 xl:ml-20">
                <p class=" text-yellow-600">
                    <button @click="clickUser">{{ this.writerName }}</button>
                    <img :src="writerImg" @error="imgError" class="h-10 w-10 rounded-xl" alt="">
                </p>
                <p class="text-xs">{{getTime}}</p>
            </div>
        </div>
        <div v-if="modifyState">
            <textarea v-model="modifyContent" id="comment" rows=2 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
            <button @click="modifyComment" :disabled="!modifyContent" class="">수정하기</button>
            <button @click="deleteComment" class="ml-3">삭제</button>
            <p v-if="modifyContent.length>100"> 100자 이하로 작성 가능합니다</p>
            <button @click="modifyState=false" class="ml-5">취소</button>
        </div>
        <div v-if="!modifyState" class="flex">
            <!-- <span class="float-right">By: <a class="text-purple-500" href="#">{{ comments.postedBy }}</a></span> -->
            <div v-if=" this.myUserId==this.writerId && !reCommentState" class="">
                <button @click="modifyState=true" class="mr-3">수정</button>
                
            </div>
            <div class="" v-if="!reCommentState">
                <button @click="reCommentState=true"  class="mr-3">답글 작성하기</button>
            </div>
            <div v-if="reCommentState">
                <textarea v-model="reCommentContent" id="comment" rows=2 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
                <button @click="postReComment">작성</button>      
                <button @click="reCommentState=false" class="ml-5">취소</button>
            </div>
        </div>

        <div v-if="replyCount>0" class="w-6/7 ml-5">
            <!-- <p>답글  개 </p> -->
            <div v-if="!hideComment">
                <button @click="hideComment=true">{{replyCount}}개의 답글 확인하기</button>
            </div>
            <div v-if="hideComment">
                <button @click="hideComment=false">답글 숨기기</button>
            <reCommentItem v-for="item in reply" :key="item.id" 
             :commentId="item.id"
             :parentId="item.parentId"
             :writerId="item.writer.id"
             :writerName="item.writer.name"
             :writerImg="item.writer.pictureUrl"
             :content="item.content"
             :createdAt="item.createdAt"
            >
                <!-- <div class="grid grid-cols-6 h-10">
                    <p class="col-start-1 col-span-3 text-justify leading-tight text-gray-800">{{ item.content }}</p>
                    <span class="col-start-5 col-span-1">By: {{ item.name }}</span>
                    <span class="col-start-6 col-span-2">{{ item.createDate }}</span>
                </div> -->
            </reCommentItem>
            </div>
        </div>

        
        
    </div>
</template>

<script>
import img from '@/assets/profile.png'
import { getDate } from '@/utils/date.js'
// import { getArticleComments } from '@/api/matchComment.js'
import { deleteArticleComments } from '@/api/matchComment.js'
import { getArticleCommentsList } from '@/api/matchComment.js'
// import { putArticleComments } from '@/api/comment.js'
import axios from 'axios'
const baseURL = process.env.VUE_APP_SERVER_URL

import { postArticleComments } from '@/api/matchComment.js'
import reCommentItem from '@/components/matching/matchingComment/reCommentItem.vue'
import store from '@/store/index.js'
import Swal from 'sweetalert2'
export default {
    components:{
        reCommentItem
    },
    props:{
        commentId : [String, Number],
        content:[String],
        createAt:[String,Date],
        writerId:[String,Number],
        writerName:[String],
        writerImg:[String,File],
        replyCount:[String, Number]
    },
    data(){
        return{
            myUserId: store.state.userId ,
            hideComment:false,
            reCommentContent:'',
            reCommentState:false,
            modifyContent:'',
            modifyState: false,
            comments:{
                content:'',
                createDate:'',
                
                userId: store.state.userId
            },
            writer:{
                    writerId:'',
                    name:'',
                    pictureUrl:''
            },
            reply:[],
            rereplyCount:0,
        }
    },
    mounted(){
        // getArticleComments(this.commentId)
        // .then((res)=>{
        //     console.log(res)
        //     this.comments.content = res.data.content
        //     this.writer.name = res.data.writer.name
        //     this.comments.createDate = getDate(res.data.createDate)
        //     this.writer.writerId = res.data.writer.id
        //     this.writer.pictureUrl = res.data.writer.pictureUrl
        //     this.replyCount = res.data.replyCount
        //     if(res.data.replyCount>0){
        //         const boardId = store.state.boardId
        //         getArticleCommentsList(boardId,this.commentId)
        //         .then((res)=>{
        //             console.log(res)
        //             this.reply = res.data.commentsList
        //             this.rereplyCount = res.data.replyCount
        //         }).catch((err=>{
        //             console.log(err)
        //         }))
        //     }
        // }).catch((err)=>{
        //     console.log(err)
        // })
        const postId = store.state.postId
        getArticleCommentsList(postId, this.commentId)
        .then((res)=>{
            console.log(res.data)
            this.reply = res.data
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        deleteComment:function(){
            Swal.fire({
             title: '댓글을 삭제하시겠습니까?',
              text: "삭제한 댓글은 복구할 수 없습니다.",
               icon: 'warning',
                showCancelButton: true,
                 confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                   confirmButtonText: '네',
                    cancelButtonText: '아니오'
             })
            .then((result) => {
                if(result.isConfirmed){
                deleteArticleComments(this.commentId)
                .then((res)=>{
                    console.log(res.data)
                    Swal.fire('댓글이 삭제되었습니다.')
                    this.$router.go();
                }).catch((err)=>{
                    console.log(err)
                })

            }})
        },
        modifyComment:function(){
            axios({
                method:'put',
                url:`${baseURL}/match/board/comments/${this.commentId}`,
                headers:this.getToken,
                data:{
                    content:this.modifyContent
                },
            })
            .then((res)=>{
                console.log(res)
                console.log(this.commentId)
                Swal.fire('댓글이수정되었습니다')
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
        },
        postReComment:function(){
            this.$store.commit('setMatchCommentId',this.commentId)
            const postId = store.state.postId
            postArticleComments(postId,this.commentId,this.reCommentContent)
            .then((res)=>{
                console.log(res.data)
                Swal.fire('댓글이 작성되었습니다.')
                this.$router.go()
                
            }).catch((err)=>{
                console.log(err)
            })
        },
        clickUser: function(){
            this.$store.commit('setTempUserId', this.writer.writerId)
            this.$router.push('/user')
        },
        imgError:function(e){
            e.target.src = img
        }

    },
    computed:{
        getToken(){
            const token = store.state.accessToken
            const config = {
                Authorization: `Bearer ${token}`
            }
            return config
        },
        btnDisabled(){
            if((this.modifyContent.length>100) || (this.modifyContent.length ==0) ){
                return true
            }else{
                return false
            }
        },
        getTime(){
            const time = getDate(this.createAt)
            return time
        }
    }
}
</script>

<style>

</style>