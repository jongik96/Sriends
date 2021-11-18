<template>
  <div class="grid grid-cols-6">
            <!-- nav -->
    <nav class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-5">
    <div class="flex justify-between flex-wrap items-center  md:justify-around">
        <!-- logo -->
        <router-link to="/team">
            <p class="text-xl md:text-2xl text-yellow-700">Home</p>
        </router-link>
        <div v-if="authority!=''" class="space-x-4">
            <router-link to="/team/articleList">
                <button class="inline-block bg-yellow-400 px-2 py-1 text-black font-semibold text-md rounded">
                    <p>공지사항</p>
                </button>
            </router-link>
            <router-link to="/team/calendar">
                <button class="inline-block bg-yellow-400 px-2 py-1 text-black font-semibold text-md rounded">
                    <p>일정관리</p>
                </button>
            </router-link>
            <router-link to="/team/memberList">
                <button class="inline-block bg-yellow-400 px-2 py-1 text-black font-semibold text-md rounded">
                    <p>회원관리</p>
                </button>
            </router-link>
        </div>
    </div>
    </nav>
  </div>
</template>

<script>
import { getPermitState } from '@/api/team.js'
import store from '@/store/index.js'
export default {
    data(){
        return{
            teamId : store.state.teamId,
            authority:'',
            teamName: store.state.teamName
        }
    },
    created(){
        
        getPermitState(this.teamId).
            then((res)=>{
                console.log(res)
                this.authority = res.data.authority
            }).catch((err)=>{
                console.log(err)
                console.log('미가입 상태')
            })
    }
}
</script>

<style>

</style>