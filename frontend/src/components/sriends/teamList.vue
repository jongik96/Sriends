<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="md:flex justify-end ml-5 md:ml-0">
                <router-link to="/searchTeam">
                    <button class="bg-yellow-500 px-2 mb-2 md:mb-0 py-1 
                        text-white font-semibold text-sm rounded block text-center 
                        sm:inline-block mr-5">
                                다른 지역, 종목 찾아보기
                    </button>
                </router-link>
            </div>
            <div v-if="!state" class="text-2xl">회원정보수정에서 관심종목을 등록해주세요!</div>
            <teamListItem v-for="item in teams" :key="item.id"
                :id="item.id"
                :leaderId="item.leaderId"
             >
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
            state:true
        }
  },
  created(){
        getInterestTeam()
        .then((res)=>{
            console.log(res.data)
            this.teams = res.data
        }).catch((err)=>{
            console.log(err)
            this.state=false
            
        })
      
  },

}
</script>

<style>

</style>