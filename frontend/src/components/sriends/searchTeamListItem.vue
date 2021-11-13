<template>
  <div v-if="state" class="border-2 border-yellow-500 rounded-xl shadow-md p-4 mt-5 mx-6 mb-4 w-48 md:w-96 lg:w-full h-48 grid grid-cols-6">
                <div class="grid col-start-1 col-span-6 md:col-start-1 md:col-span-2">
                    <button @click="clickTeam">
                        <img :src=pictureDownloadUrl @error="imgError" class="object-contain rounded-md h-36 w-36 " alt="">
                    </button>
                    <p class="visible md:invisible ml-14 w-36">{{this.name}}</p>
                    <!-- <button class="bg-yellow-500 px-2 py-1 w-24
                    text-white font-semibold text-sm rounded block text-center md:ml-8 ml-12
                    sm:inline-block mr-5 h-10" 
                    @click="joinBtn"
                    >
                        가입 요청
                    </button> -->
                </div>
                <div class="invisible md:visible grid md:col-start-3 md:col-span-4 ml-0 md:ml-10 lg:ml-0">
                    <div class="flex ">
                        <p>팀명 : {{this.name}} </p>
                        
                    </div>
                    <div class="flex ">
                        <p>종목 : {{this.sportCategory}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>대표 : {{leaderName}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>인원 : {{this.memberCount}} </p>
                    </div>
                    <div class="invisible md:visible">
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