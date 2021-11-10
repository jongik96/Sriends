<template>
    <!-- 대댓글은 40자 이하로 설정할 것 -->
    <div class=" rounded-md border-2 border-yellow-500 mb-1">
        <div v-if="!modifyState" class="grid grid-cols-6 h-10">
            <p class="col-start-1 break-words col-span-5 text-justify leading-tight text-gray-800">{{ this.reComment.content }}</p>
            <div class="col-start-6 col-span-1 lg:ml-10 xl:ml-20">
                <p class=" text-yellow-600"><button @click="clickUser">{{this.writer.name}}</button></p>
                <img :src="writer.pictureUrl" class="h-6 w-6 rounded-xl" alt="">
                <p class="text-xs">{{this.reComment.createDate}}</p>
            </div>
        </div>
        <div v-if="modifyState">
            <textarea v-model="modifyReContent" id="comment" rows=2 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
            <button @click="modifyComment" :disabled="btnDisabled" class="">수정하기</button>
            <p v-if="modifyReContent.length>100"> 100자 이하로 작성 가능합니다</p>
            <button @click="modifyState=false" class="ml-5">취소</button>
        </div>
        <div v-if="!modifyState" class="">
            <!-- <span class="float-right">By: <a class="text-purple-500" href="#">{{ comments.postedBy }}</a></span> -->
            <div v-if="userId==writerId" class="">
                <button @click="modifyState=true" class="mr-3">수정</button>
                <button @click="deleteComment" class="mr-3">삭제</button>
            </div>
        </div>
    </div>      
            
</template>

<script>
import { getDate } from '@/utils/date.js'
import { getArticleComments } from '@/api/matchComment.js'
import { deleteArticleComments } from '@/api/matchComment.js'
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
                parentId:'',
                
            },
            writer:{
                    writerId:'',
                    name:'',
                    pictureUrl:'',
            }
        }
    },
    mounted(){
        getArticleComments(this.commentId)
        .then((res)=>{
            this.reComment.createDate = getDate(res.data.createDate)
            this.reComment.content = res.data.content
            this.reComment.modified = res.data.modified
            this.reComment.modifyDate = res.data.modifyDate
            this.writer.name = res.data.writer.name
            this.reComment.parentId = res.data.parentId
            this.writer.writerId = res.data.writer.writerId
            this.writer.pictureUrl = res.data.writer.pictureUrl
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
        clickUser: function(){
            this.$store.commit('setTempUserId', this.writer.writerId)
            this.$router.push('/user')
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
            if((this.modifyReContent.length>100) || (this.modifyReContent.length ==0) ){
                return true
            }else{
                return false
            }
        }
    }
}
</script>

<style>

</style>