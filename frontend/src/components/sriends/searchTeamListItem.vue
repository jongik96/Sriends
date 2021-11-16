<template>
    <div v-if="state" class="border-2 border-yellow-500 rounded-xl shadow-md p-4 mt-5 mx-6 mb-4 w-auto md:w-96 lg:w-full h-48 grid grid-cols-6">
        <div class="grid col-start-1 col-span-2 md:col-start-1 md:col-span-2">
            <button @click="clickTeam">
                <img :src=pictureDownloadUrl @error="imgError" class="rounded-md h-20 w-20 md:ml-10 sm:ml-16" alt="">
            </button>
        </div>
        <div class=" grid col-start-3 col-span-4  md:col-start-3 md:col-span-4 ml-0 md:ml-5 lg:ml-0">
            <div class=" ">
                <p>팀명 : {{this.name}} </p>
                
            </div>
            <div class=" ">
                <p>종목 : {{this.sportCategory}} </p>
            </div>
            <div class="">
                <p>대표 : {{leaderName}} </p>
            </div>
            <div class="">
                <p>인원 : {{this.memberCount}} </p>
            </div>
            <div class="">
                <p>현재 {{recruitmentState}} </p>
            </div>
            <div class="">
                <p class="truncate w-96 invisible lg:visible">소개 : {{this.description}} </p>
            </div>
        </div>
    </div>
</template>

<script>
import img from '@/assets/sideImg.png'
import { getTeamInfo } from '@/api/team.js'
export default {
    props:{
        id:[String,Number]
    },
    data(){
        return{
            name:'',
            sportCategory:'',
            leaderName:'',
            memberCount:'',
            description:'',
            pictureDownloadUrl:'',
            recruitmentState:'',
            state:true
        }
    },
    mounted(){
        getTeamInfo(this.id)
        .then((res)=>{
            console.log(res)
            this.name = res.data.name
            if(res.data.pictureDownloadUrl){
            this.pictureDownloadUrl = res.data.pictureDownloadUrl
            }
            this.sportCategory = res.data.sportCategory
            this.leaderName = res.data.leader.name
            this.memberCount = res.data.memberCount
            this.description = res.data.description
            
            if(res.data.recruitmentState==true){
                this.recruitmentState='모집중!'
            }else{
                this.recruitmentState='모집완료'
            }

        }).catch((err)=>{
            console.log(err)
            this.state=false
        })
    },
    methods:{
        // joinBtn:function(){
        //     this.$store.commit('setTeamId',this.id)
        //     this.$router.push('/joinTeam')
        // },
        clickTeam: function(){
            this.$store.commit('setTeamId',this.id)
            this.$store.commit('setTeamName', this.name)
            this.$router.push('/team')
        },
        imgError:function(e){
            e.target.src = img
            console.log(img)
            console.log(e.target.src)
        }
    }
}
</script>

<style>

</style>