<template>
    <div class="shadow-md p-4 mt-5 mx-6 mb-4 min-w-300">
        <div v-if="!modifyState" class="grid grid-cols-6 h-10">
            <p class="col-start-1 col-span-3 text-justify leading-tight text-gray-800">{{ this.comments.content }}</p>
            <span class="col-start-5 col-span-1">By: {{ this.comments.name }}</span>
            <span class="col-start-6 col-span-2">{{ this.comments.createDate }}</span>
        </div>
        <div v-if="modifyState">
            
            <textarea v-model="modifyContent" id="comment" rows=2 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
            <button @click="modifyComment" class="">수정하기</button>
            <button @click="modifyState=false" class="ml-5">취소</button>
        </div>
        <div v-if="!modifyState" class="">
            <!-- <span class="float-right">By: <a class="text-purple-500" href="#">{{ comments.postedBy }}</a></span> -->
            <div v-if=" this.comments.userId==this.comments.writerId" class="">
                <button @click="modifyState=true" class="mr-3">수정</button>
                <button @click="deleteComment" class="mr-3">삭제</button>
            </div>
            <div v-if="!reCommentState">
                <button @click="reCommentState=true"  class="mr-3">답글 작성하기</button>
            </div>
            <div v-if="reCommentState">
                <textarea v-model="reCommentContent" id="comment" rows=2 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
                <button @click="postReComment">작성</button>      
                <button @click="reCommentState=false" class="ml-5">취소</button>
            </div>
        </div>
        <div v-if="rereplyCount>0" class="w-4/5 ml-5">
            <p>답글 {{rereplyCount}} 개 </p>
            <div v-if="!hideComment">
                <button @click="hideComment=true">답글 확인하기</button>
            </div>
            <div v-if="hideComment">
                <button @click="hideComment=false">답글 숨기기</button>
            <reCommentItem v-for="item in reply" :key="item.id" 
             :commentId=item.id
             :parentId=item.parentId
             :writerId=item.writerId
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
import { getDate } from '@/utils/date.js'
import { getArticleComments } from '@/api/comment.js'
import { deleteArticleComments } from '@/api/comment.js'
import { getArticleCommentsList } from '@/api/comment.js'
import { putArticleComments } from '@/api/comment.js'
import { postArticleComments } from '@/api/comment.js'
import reCommentItem from '@/components/team/article/articleReCommentItem.vue'
import store from '@/store/index.js'
import Swal from 'sweetalert2'
export default {
    components:{
        reCommentItem
    },
    props:{
        commentId : [String, Number],
    },
    data(){
        return{
            hideComment:false,
            reCommentContent:'',
            reCommentState:false,
            modifyContent:'',
            modifyState: false,
            comments:{
                content:'',
                name:'',
                createDate:'',
                writerId: '',
                userId: store.state.userId
            },
            replyCount:0,
            reply:[],
            rereplyCount:0,
        }
    },
    mounted(){
        getArticleComments(this.commentId)
        .then((res)=>{
            console.log(res)
            this.comments.content = res.data.content
            this.comments.name = res.data.name
            this.comments.createDate = getDate(res.data.createDate)
            this.comments.writerId = res.data.writerId
            this.replyCount = res.data.replyCount
            if(res.data.replyCount>0){
                const boardId = store.state.boardId
                getArticleCommentsList(boardId,this.commentId)
                .then((res)=>{
                    console.log(res)
                    this.reply = res.data.commentsList
                    this.rereplyCount = res.data.replyCount
                }).catch((err=>{
                    console.log(err)
                }))
            }
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        deleteComment:function(){
            deleteArticleComments(this.commentId)
            .then((res)=>{
                console.log(res.data)
                Swal.fire('댓글이 삭제되었습니다.')
                this.$router.go();
            }).catch((err)=>{
                console.log(err)
            })
        },
        modifyComment:function(){
            putArticleComments(this.commentId,this.modifyContent)
            .then((res)=>{
                console.log(res)
                // Swal.fire('댓글이수정되었습니다')
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
        },
        postReComment:function(){
            this.$store.commit('setCommentId',this.commentId)
            const boardId = store.state.boardId
            postArticleComments(boardId,this.reCommentContent,this.commentId)
            .then((res)=>{
                console.log(res.data)
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style>

</style>