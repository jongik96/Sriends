<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="md:flex md:justify-end ml-5 md:ml-0">
                <router-link to="/searchTeam">
                <button class="bg-yellow-500 px-2 mb-2 md:mb-0 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            다른 지역, 종목 찾아보기
                </button>
                </router-link>
                <router-link to="/team/createArticle">
                <button class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            모집글 작성하기
                </button>
                </router-link>
            </div>
            <teamListItem v-for="item in teams" :key="item.id"
                :id="item.id"
                :leaderId="item.leaderId"
             >
                <!-- <div class="grid col-start-1 col-span-2">
                    <img src='@/assets/logo.png' class="rounded-md h-20 w-20 md:ml-10 ml-16" alt="">
                    <p class="visible md:invisible ml-14 w-36">{{item.name}}</p>
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
                        <p>팀명 : {{item.name}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>대표 : {{item.leaderName}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>종목 : {{item.sportsCategory}} </p>
                    </div>
                    <div class="">
                        <p class="truncate w-96 invisible lg:visible">소개 : {{item.description}} </p>
                    </div>
                </div> -->
            </teamListItem>
        </div>
  </div>
</template>

<script>
import teamListItem from '@/components/sriends/teamListItem.vue'
import { getInterestTeam } from '@/api/team.js'
// import Swal from 'sweetalert2'
export default {
    components:{
        teamListItem
    },
    data() {
        return{
            teams:[
                {    
                    id : '',
                    pictureDownloadUri: '',
                    leaderName:'',
                    leaderId:'',
                    name : '',
                    description : '',
                    membershipFee : '',
                    userCount : '',
                    sportsCategory : '',
                }
            ],
        }
  },
  created(){
        getInterestTeam()
        .then((res)=>{
            console.log(res.data)
            this.teams = res.data
        }).catch((err)=>{
            console.log(err)
            
            
        })
      
  },

}
</script>

<style>

</style>