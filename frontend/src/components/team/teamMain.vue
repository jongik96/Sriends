<template>
  <div class="grid grid-cols-6">
      <div class="col-start-2 col-span-4">
        <header class="flex flex-wrap items-center p-4 md:py-8">

        <div class="md:w-4/12 md:ml-16">
            <!-- profile image -->
            <img class="w-30 h-30 md:w-50 md:h-50 object-cover rounded-xl
                        border-2 border-yellow-500 p-1" :src="pictureDownloadUrl" @error="imgError">
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
            <router-link v-if="(authority=='대표') || (authority='매니저')" to="/teamModify" class="bg-yellow-500 px-2 py-1 
                            text-white font-semibold text-sm rounded block text-center 
                            sm:inline-block"
            >정보수정
            </router-link>
        </div>

            <!-- post, following, followers list for medium screens -->
            <ul class=" md:inline-block  mb-4">
            <li>
                대표자
                <span class="font-semibold"><button @click="clickUser">{{this.leader.name}}</button></span>
                종목
                <span class="font-semibold">{{this.sportCategory}}</span>
            </li>
            <li>
                총 인원
                <span class="font-semibold">{{this.memberCount}}</span>
                회비
                <span class="font-semibold">{{this.membershipFee}}</span>
            </li>
            <li>
                지역
                <span class="font-semibold">{{this.city}}</span>
            </li>
            <li>
                모집여부
                <span v-if="!recruitmentState" class="font-semibold">모집 중</span>
                <span v-if="recruitmentState" class="font-semibold">모집 완료</span>
            </li>
            </ul>

            <!-- user meta form medium screens -->
            <div class="hidden md:block">
            <!-- <h1 class="font-semibold">우리팀의 짱 박범진.</h1>
            <span>Travel, Nature and Music</span> -->
            소개
            <p>{{this.description}}</p>
            </div>

        </div>

        <!-- user meta form small screens -->
        <div class="md:hidden text-sm my-2">
            <p>{{this.description}}</p>
        </div>

        </header>
        <div v-if="this.authority" class="grid justify-end text-xl">회원님은 {{this.authority}}입니다!</div>
        <div v-if="this.authority" class="grid justify-end"><button @click="outofTeam">탈퇴하기</button></div>
        <div v-if="!this.authority" class="grid justify-center">
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
import img from '@/assets/sideImg.png'
import { getPermitState } from '@/api/team.js'
import { getTeamInfo } from '@/api/team.js'
import { outTeam } from '@/api/team.js'
import store from '@/store/index.js'
import Swal from 'sweetalert2'
export default {
    props:{
        teamId: [Number,String]
    },
    data(){
        return{
            name : '',
            createDate : '',
            leader:{
                id:'',
                name:''
            },
            pictureDownloadUrl : '',
            memberCount : '',
            maxCount : '',
            description : '',
            recruitmentState : '',
            membershipFee : '',
            city : '',
            sportCategory : '',
            authority:'',
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
            this.leader.id = res.data.leader.id
            this.leader.name = res.data.leader.name
            this.pictureDownloadUrl = res.data.pictureDownloadUrl
            this.memberCount = res.data.memberCount
            this.maxCount = res.data.maxCount
            this.description = res.data.description
            this.recruitmentState = res.data.recruitmentState
            if(res.data.membershipFee==true){
                this.membershipFee='有'
            }else{
                this.membershipFee='無'
            }
            // this.membershipFee = res.data.membershipFee
            this.city = res.data.city
            this.sportCategory = res.data.sportCategory

            getPermitState(teamId).
            then((res)=>{
                console.log(res)
                this.authority = res.data.authority
                this.$store.commit('setAuth',res.data.authority)
            }).catch(()=>{

            })

        }).catch((err)=>{
            console.log(err)
            console.log(this.teamId)
        })
    },
    methods:{
        clickUser: function(){
            this.$store.commit('setTempUserId', this.leader.id)
            this.$router.push('/user',)
        },
        outofTeam: function(){
            Swal.fire({
            title: '정말 탈퇴하시겠어요?',
            text: "대표 권한을 다른 회원에게 넘기지 않고 탈퇴하면 팀 정보까지 삭제됩니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네',
            cancelButtonText: '아니오'
          })
          .then((result) => {
            if(result.isConfirmed){            
                const teamId = store.state.teamId
                outTeam(teamId)
                .then((res)=>{
                    console.log(res)
                    this.$router.push('main')
                }).catch((err)=>{
                    console.log(err)
                })
            }})
        },
        imgError:function(e){
            e.target.src = img
        }
    }

}
</script>

<style>

</style>