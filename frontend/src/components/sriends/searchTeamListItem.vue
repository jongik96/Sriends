<template>
  <div class="border-2 border-yellow-500 rounded-xl shadow-md p-4 mt-5 mx-6 mb-4 w-60 md:w-96 lg:w-full h-48 grid grid-cols-6">
                <div class="grid col-start-1 col-span-2">
                    <button @click="clickTeam">
                        <img src='@/assets/logo.png' class="rounded-md h-20 w-20 md:ml-10 ml-16" alt="">
                    </button>
                    <p class="visible md:invisible ml-14 w-36">{{this.name}}</p>
                    <button class="bg-yellow-500 px-2 py-1 w-24
                    text-white font-semibold text-sm rounded block text-center md:ml-8 ml-12
                    sm:inline-block mr-5 h-10" 
                    @click="joinBtn"
                    >
                        가입 요청
                    </button>
                </div>
                <div class="invisible md:visible grid md:col-start-3 md:col-span-4 ml-0 md:ml-5 lg:ml-0">
                    <div class="flex ">
                        <p>팀명 : {{this.name}} </p>
                    </div>
                    <div class="flex ">
                        <p>종목 : {{this.sportCategory}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>대표 :  </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>인원 : {{this.memberCount}} </p>
                    </div>
                    <div class="">
                        <p class="truncate w-96 invisible lg:visible">소개 : {{this.description}} </p>
                    </div>
                </div>
            </div>
</template>

<script>
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
            pictureId:'',
        }
    },
    mounted(){
        getTeamInfo(this.id)
        .then((res)=>{
            console.log(res)
            this.name = res.data.name
            this.pictureId = res.data.pictureId
            this.sportCategory = res.data.sportCategory
            this.memberCount = res.data.memberCount
            this.description = res.data.description


        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        joinBtn:function(){

        },
        clickTeam: function(){
            this.$store.commit('setTeamId',this.id)
            this.$router.push('/team')
        }
    }
}
</script>

<style>

</style>