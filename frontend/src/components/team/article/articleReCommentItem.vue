<template>
    <div class="bg-yellow-200 rounded-md border-2 border-yellow-500">
        <div v-if="!modifyState" class="grid grid-cols-6 h-10">
            <p class="col-start-1 col-span-3 text-justify leading-tight text-gray-800">{{ this.reComment.content }}</p>
            <span class="col-start-5 col-span-1">By: {{this.reComment.name}}</span>
            <span class="col-start-6 col-span-2">{{this.reComment.createDate}}</span>
        </div>
        <div v-if="modifyState">
            <textarea v-model="modifyReContent" id="comment" rows=2 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
            <button @click="modifyComment" :disabled="!modifyReContent" class="">수정하기</button>
            <button @click="modifyState=false" class="ml-5">취소</button>
        </div>
        <div v-if="!modifyState" class="">
            <!-- <span class="float-right">By: <a class="text-purple-500" href="#">{{ comments.postedBy }}</a></span> -->
            <div v-if="userId==reComment.writerId" class="">
                <button @click="modifyState=true" class="mr-3">수정</button>
                <button @click="deleteComment" class="mr-3">삭제</button>
            </div>
        </div>
    </div>      
            
</template>

<script>
import { getDate } from '@/utils/date.js'
import { getArticleComments } from '@/api/comment.js'
import { deleteArticleComments } from '@/api/comment.js'
// import { putArticleComments } from '@/api/comment.js'

import axios from 'axios'
const baseURL = process.env.VUE_APP_SERVER_URL


import Swal from 'sweetalert2'
import store from '@/store/index.js'
export default {
    props:{
        commentId : [String,Number],
        parentId: [String,Number],
        writerId: [String,Number]
    },
    data(){
        return{
            modifyReContent:'',
            modifyState:false,
            userId : store.state.userId,
            reComment:{
                createDate:'',
                content:'',
                modified:'',
                modifyDate:'',
                name:'',
                parentId:'',
                writerId:'',
            }
        }
    },
    mounted(){
        getArticleComments(this.commentId)
        .then((res)=>{
            console.log(res)
            this.reComment.createDate = getDate(res.data.createDate)
            this.reComment.content = res.data.content
            this.reComment.modified = res.data.modified
            this.reComment.modifyDate = res.data.modifyDate
            this.reComment.name = res.data.name
            this.reComment.parentId = res.data.parentId
            this.reComment.writerId = res.data.writerId
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
        // modifyComment:function(){
        //     putArticleComments(this.commentId,this.modifyReContent)
        //     .then((res)=>{
        //         console.log(res)
        //         // Swal.fire('댓글이수정되었습니다')
        //         this.$router.go()
        //     }).catch((err)=>{
        //         console.log(err)
        //     })
        // },
        modifyComment:function(){
            axios({
                method:'put',
                url:`${baseURL}/teams/board/comments/${this.commentId}`,
                headers:this.getToken,
                data:{
                    content:this.modifyReContent
                },
            })
            .then((res)=>{
                console.log(res)
                // Swal.fire('댓글이수정되었습니다')
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
        },
    },
    computed:{
        getToken(){
            const token = store.state.accessToken
            const config = {
                Authorization: `Bearer ${token}`
            }
            return config
        },
    }
}
</script>

<style>

</style>