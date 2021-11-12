<template>
    <div @click="clickMatch" class="grid mt-10 cursor-pointer">
        <div class="grid col-start-1 col-span-1">
                {{matchBoardCategory}}
        </div>
        <div class="grid col-start-2 col-span-1">
                {{sportCategory}}
        </div>
        <div class="grid col-start-3 col-span-1">
                {{recruitmentCount}}
        </div>
        <div class="grid col-start-4 col-span-1">
                {{team.name}}
        </div>
        <div class="grid col-start-5 col-span-2">
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