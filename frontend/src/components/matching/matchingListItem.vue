<template>
    <div @click="clickMatch" class="grid grid-cols-6 mt-10 cursor-pointer text-center">
        <div class="grid col-start-1 col-span-1 text-center text-gray-600">
                {{matchBoardCategory}}
        </div>
        <div class="grid col-start-2 col-span-1 text-center">
                {{sportCategory}}
        </div>
        <div class="grid col-start-3 col-span-1 text-center">
                {{recruitmentCount}}
        </div>
        <div class="grid col-start-4 col-span-2 ml-5 text-center">
                {{team.name}}
        </div>
        <div class="grid col-start-6 col-span-1 text-center">
                {{createAt}}        
        </div>
    </div>
</template>

<script>
import { getMatchDetail } from '@/api/matching.js'
import { getDate } from '@/utils/date.js'
export default {
    props:{
        postId:[String,Number]
    },
    data(){
        return{
            city:'',
            content:'',
            createAt:'',
            matchBoardCategory:'',
            place:'',
            playingTime:'',
            recruited:'',
            id:'',
            recruitmentCount:'',
            sportCategory:'',
            team:{
                id:'',
                name:'',
                pictureUrl:'',
            }
        }
    },
    mounted(){
        getMatchDetail(this.postId)
        .then((res)=>{
            console.log(res)
            this.city = res.data.city
            this.content = res.data.content
            this.createAt = getDate(res.data.createAt)
            this.matchBoardCategory = res.data.matchBoardCategory
            if(res.data.matchBoardCategory=='용병'){
                this.matchBoardCategory = '게스트'
            }
            this.place = res.data.place
            this.playingTime = res.data.playingTime
            this.recruited = res.data.recruited
            this.recruitmentCount = res.data.recruitmentCount
            this.id = res.data.id
            this.sportCategory = res.data.sportCategory
            this.team.id = res.data.team.id
            this.team.name = res.data.team.name
            this.team.pictureUrl = res.data.team.pictureUrl
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        clickMatch: function(){
            this.$store.commit('setMatchingId',this.postId)
            this.$router.push('/matchingDetail')
        }
    }
}
</script>

<style>

</style>