<template>
    <div class="shadow-md p-4 mt-5 mx-6 mb-4 h-30 min-w-300">
        <!-- <router-link :to="{'name': 'articleDetail', params:{'boardId':this.boardId}}"> -->
        <div @click="clickArticle" class="grid grid-cols-6">
            <div class="col-start-1 col-span-5">
                <h2 class="text-2xl font-semibold">{{ this.title }}</h2>
                <p class="text-justify truncate leading-tight text-gray-800 mt-5 mr-4">{{ this.content }}</p>
            </div>
            <div class="col-start-6 col-span-1">
                <div class="">
                    <img :src="writer.pictureUrl" class="rounded-full object-contain w-12 h-12" @error="imgError">
                </div>
                <p class="text-xl">{{ this.writer.name }}</p>
                <p class="left">{{ this.createDate }}</p>
            </div>
            
            <div class="mt-2 col-start-1 col-span-6">
                
            
            </div>
        </div>
        <!-- </router-link> -->
    </div>
</template>

<script>
import img from '@/assets/profile.png'
import {getDate} from '@/utils/date.js'
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
            writer:{
                id:'',
                name:'',
                pictureUrl:'',
            },
            title:'',
        }
    },
    mounted(){
        const teamId = store.state.teamId
        getArticleInfo(teamId,this.boardId).
        then((res)=>{
            console.log(res)
            this.writer.name = res.data.writer.name
            if(res.data.writer.pictureUrl!=null){
            this.writer.pictureUrl = res.data.writer.pictureUrl
            }
            this.title = res.data.title
            this.content = res.data.content
            this.createDate = getDate(res.data.createDate)
            
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        clickArticle: function(){
            this.$store.commit('setBoardId',this.boardId)
            this.$router.push('/team/articleDetail')
        },
        imgError:function(e){
            e.target.src = img
        },
    }
}
</script>

<style>

</style>