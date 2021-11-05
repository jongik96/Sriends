<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div>
                <div class="border-b">
                    <h2 class="text-2xl font-semibold">{{this.title}}</h2>
                    <div class="float-right">
                        <router-link :to="{'name': 'modifyArticle', params:{'boardId':this.boardId}}">
                            <button class="mr-3">수정</button>
                        </router-link>
                        <button @click="clickDelete" class="mr-3">삭제</button>
                    </div>
                </div>
                <p class="text-justify leading-tight text-gray-800 h-48 mt-10">
                    {{this.content}}    
                <p>
                <div class="mt-2">
                    <span class="left">Posted On: {{this.createDate}}</span>
                    <span class="float-right flex">By: <p class="text-purple-500">{{this.name}}</p></span>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
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
            name: '',
            title: '',
            content: '',
            createDate:'',
        }
    }
    ,
    created(){
        const teamId = store.state.teamId
        // this.$store.commit("setBoardId",this.boardId)
        const boardId = store.state.boardId
        getArticleInfo(teamId,boardId).
        then((res)=>{
            console.log(res)
            this.name = res.data.name
            this.title = res.data.title
            this.content = res.data.content
            this.createDate = res.data.createDate
            
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        clickDelete: function(){
            const teamId = store.state.teamId
            deleteArticle(teamId, this.boardId)
            .then((res)=>{
                console.log(res)
                Swal.fire('삭제되었습니다.')
                this.$router.push('/team/articleList')
            }).catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style>

</style>