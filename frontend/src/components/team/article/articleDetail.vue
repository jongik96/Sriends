<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div>
                <div class="border-b">
                    <h2 class="text-2xl font-semibold">{{this.title}}</h2>
                    <div class="float-right">
                        <router-link v-if="this.userId==this.writer.id" :to="{'name': 'modifyArticle', params:{'boardId':this.boardId}}">
                            <button class="mr-3">수정</button>
                        </router-link>
                        <button v-if="this.userId==this.writer.id" @click="clickDelete" class="mr-3">삭제</button>
                    </div>
                </div>
                <p class="text-justify break-words leading-tight text-gray-800 w-full h-48 mt-10">
                    {{this.content}}    
                <p>
                <div class="mt-2 float-right">
                    <button @click="clickUser">
                        <img :src="writer.pictureUrl" @error="imgError" class="h-10 w-10 rounded-xl" alt="">
                        <p class="">{{this.writer.name}}</p>
                    </button>
                    <p class="">{{this.createDate}}</p>
                    
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import img from '@/assets/profile.png'
import { getDate } from '@/utils/date.js'
import { deleteArticle } from '@/api/article.js'
import { getArticleInfo } from '@/api/article.js'
import store from '@/store/index.js'
import Swal from 'sweetalert2'
export default {
    props:{
        boardId: [String, Number]
    },
    data(){
        return{
            title: '',
            content: '',
            createDate:'',
            writer:{
                id:'',
                name:'',
                pictureUrl:'',
            },
            userId: store.state.userId
        }
    }
    ,
    created(){
        console.log(this.userId)
        const teamId = store.state.teamId
        // this.$store.commit("setBoardId",this.boardId)
        const boardId = store.state.boardId
        getArticleInfo(teamId,boardId).
        then((res)=>{
            console.log(res)
            this.writer.name = res.data.writer.name
            if(res.data.writer.pictureUrl!=null){
            this.writer.pictureUrl = res.data.writer.pictureUrl
            }
            this.title = res.data.title
            this.content = res.data.content
            this.createDate = getDate(res.data.createDate)
            this.writer.id = res.data.writer.id
            // this.$store.commit("setBoardId",this.boardId)
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        clickDelete: function(){
            Swal.fire({
             title: '게시글을 삭제하시겠습니까?',
              text: "삭제한 글은 복구할 수 없습니다.",
               icon: 'warning',
                showCancelButton: true,
                 confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                   confirmButtonText: '네',
                    cancelButtonText: '아니오'
             })
            .then((result) => {
                if(result.isConfirmed){
                    const teamId = store.state.teamId
                    const boardId = store.state.boardId
                    deleteArticle(teamId, boardId)
                    .then((res)=>{
                        console.log(res)
                        Swal.fire('삭제되었습니다.')
                        this.$router.push('/team/articleList')
                    }).catch((err)=>{
                        console.log(err)
                    })
                }

               
            })
        },
        imgError:function(e){
            e.target.src = img
        },

        clickUser: function(){
            this.$store.commit('setTempUserId', this.writer.id)
            this.$router.push('/user')
        }
        
    }
}
</script>

<style>

</style>