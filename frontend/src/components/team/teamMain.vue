<template>
  <div class="grid grid-cols-6">
      <div class="col-start-2 col-span-4">
        <header class="flex flex-wrap items-center p-4 md:py-8">

        <div class="md:w-4/12 md:ml-16">
            <!-- profile image -->
            <img class="w-30 h-30 md:w-50 md:h-50 object-cover rounded-xl
                        border-2 border-yellow-500 p-1" src="@/assets/sideImg.png" alt="profile">
        </div>
        <!-- profile meta -->
        <div class="w-6/12 md:w-6/12 = md:ml-5 ml-4 2xl:ml-20">
            <div class="md:flex md:flex-wrap md:items-center mb-4">
            <h2 class="text-3xl font-semibold inline-block md:mr-2 mb-2 sm:mb-0">
                {{this.name}}
            </h2>

            <!-- badge -->
            <span class="inline-block fas fa-certificate fa-lg text-blue-500 
                                relative mr-6 text-xl transform -translate-y-2" aria-hidden="true">
                <i class="fas fa-check text-white text-xs absolute inset-x-0
                                ml-1 mt-px"></i>
            </span>

            <!-- follow button -->
            <a href="#" class="bg-yellow-500 px-2 py-1 
                            text-white font-semibold text-sm rounded block text-center 
                            sm:inline-block">정보수정</a>
            </div>

            <!-- post, following, followers list for medium screens -->
            <ul class=" md:inline-block  mb-4">
            <li>
                대표자
                <span class="font-semibold">박범진</span>
                종목
                <span class="font-semibold">{{this.sportCategory}}</span>
            </li>
            <li>
                총 인원
                <span class="font-semibold">{{this.memberCount}}</span>
                회비 유무
                <span class="font-semibold">{{this.membershipFee}}</span>
            </li>
            <li>
                지역
                <span class="font-semibold">{{this.city}}</span>
            </li>
            </ul>

            <!-- user meta form medium screens -->
            <div class="hidden md:block">
            <!-- <h1 class="font-semibold">우리팀의 짱 박범진.</h1>
            <span>Travel, Nature and Music</span> -->
            <p>{{this.description}}</p>
            </div>

        </div>

        <!-- user meta form small screens -->
        <div class="md:hidden text-sm my-2">
            <p>{{this.description}}</p>
        </div>

        </header>
        <div class="grid justify-center">
            <div class="mt-7">
                <router-link to='/joinTeam'>
                    <button class="bg-yellow-500 px-2 py-1 
                                    text-white font-semibold text-sm rounded block text-center 
                                    sm:inline-block">
                                    가입 신청하기
                    </button>
                </router-link>
            </div>
        </div>
    </div>
  </div>
</template>

<script> 
import { getTeamInfo } from '@/api/team.js'
import store from '@/store/index.js'
export default {
    props:{
        teamId: [Number,String]
    },
    data(){
        return{
            name : '',
            createDate : '',
            leaderId : '',
            pictureId : '',
            memberCount : '',
            maxCount : '',
            description : '',
            recruitmentState : '',
            membershipFee : '',
            city : '',
            sportCategory : ''
        }
    },
    created(){
        // this.$store.commit('setTeamId',this.teamId)
        const teamId = store.state.teamId
        getTeamInfo(teamId)
        .then((res)=>{
            console.log(res)
            this.name = res.data.name
            this.createDate = res.data.createDate
            this.leaderId = res.data.leaderId
            this.pictureId = res.data.pictureId
            this.memberCount = res.data.memberCount
            this.maxCount = res.data.maxCount
            this.description = res.data.description
            this.recruitmentState = res.data.recruitmentState
            this.membershipFee = res.data.membershipFee
            this.city = res.data.city
            this.sportCategory = res.data.sportCategory

        }).catch((err)=>{
            console.log(err)
            console.log(this.teamId)
        })
    }

}
</script>

<style>

</style>