<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div v-if="this.Myauthority=='대표' || this.Myauthority=='매니저'" class="grid place-content-end">
                <router-link to="/team/waitingMemberList">
                <p class="bg-yellow-400 px-2 py-1 text-black font-semibold text-sm rounded">대기중인 요청</p>
                </router-link>
            </div>
            <memberItem v-for="item in member" :key="item.id"
                :id="item.user.id"
                :authority="item.authority"
            >
                <!-- <div class="grid grid-cols-7">
                    <div class="col-start-1 col-span-1">
                        <img src='@/assets/profiledefault.jpg' class="h-10 w-10 rounded-md md:h-20 md:w-20" alt="">
                    </div>
                    <div class="ml-1 md:ml-0 col-start-2 col-span-2 grid sm:place-items-center">
                        {{item.name}}
                        <br>
                        {{item.level}}
                    </div>
                    <div class="invisible sm:visible sm:col-start-4 sm:col-span-2 grid sm:place-items-center">
                        {{item.phone}}
                    </div>

                    <div class="col-start-6 col-span-1 flex justify-center">
                        <button><font-awesome-icon icon="comments"/></button>
                    </div>
                    <div class="col-start-7 col-span-1 flex justify-center">
                        <button><font-awesome-icon icon="ban"/></button>
                    </div>
                </div> -->
            </memberItem>
        </div>
  </div>
</template>

<script>
import { getTeamMemberList } from '@/api/team.js'
import { getPermitState } from '@/api/team.js'
import memberItem from '@/components/team/member/teamMemberItem.vue'
import store from '@/store/index.js'
export default {
    components:{
        memberItem
    },
    data(){
        return{
            member:[],
            Myauthority:''
        }
    },
    created: function(){
        const teamId = store.state.teamId
        getTeamMemberList(teamId)
        .then((res)=>{
            console.log(res.data)
            this.member = res.data
        }).catch((err)=>{
            console.log(err)
        }),
        getPermitState(teamId)
        .then((res)=>{
            console.log(res)
            this.Myauthority = res.data.authority
        }).catch((err)=>{
            console.log(err)
        })
    }
}
</script>

<style>

</style>