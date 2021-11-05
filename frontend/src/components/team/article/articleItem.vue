<template>
    <div class="shadow-md p-4 mt-5 mx-6 mb-4 min-w-300">
        <router-link :to="{'name': 'articleDetail', params:{'boardId':this.boardId}}">
        <h2 class="text-2xl font-semibold"><a href="#">{{ this.title }}</a></h2>
        <p class="text-justify leading-tight text-gray-800">{{ this.content }}<p>
        <div class="mt-2">
        <span class="left">Posted On: {{ this.createDate }}</span>
        <span class="float-right">By: <a class="text-purple-500" href="#">{{ this.name }}</a></span>
        </div>
        </router-link>
    </div>
</template>

<script>
import store from '@/store/index.js'
import { getArticleInfo } from '@/api/article.js'
export default {
    props:{
        boardId:[String,Number]
    },
    data(){
        return{
            content:'',
            createDate:'',
            name:'',
            title:'',
        }
    },
    mounted(){
        const teamId = store.state.teamId
        this.$store.commit("setBoardId",this.boardId)
        getArticleInfo(teamId,this.boardId).
        then((res)=>{
            console.log(res)
            this.name = res.data.name
            this.title = res.data.title
            this.content = res.data.content
            this.createDate = res.data.createDate
            
        }).catch((err)=>{
            console.log(err)
        })
    }
}
</script>

<style>

</style>