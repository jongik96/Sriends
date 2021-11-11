<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="flex justify-start">
                종목 : {{selectSportCategory}} 지역 : {{selectCity}}
            </div>
            <div class="flex justify-end">
                <router-link to="/teamList">
                <button class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            관심 리스트 보기
                </button>
                </router-link>
            </div>
            <div v-if="!state" class="text-2xl">조건에 해당하는 팀이 현재 없습니다</div>
            <searchTeamListItem v-for="item in teams" :key="item.id"
                :id="item.id"
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
                    <div class="flex ">
                        <p>종목 : {{item.sportsCategory}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>대표 : {{item.postedBy}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>인원 : {{item.memberCount}} </p>
                    </div>
                    <div class="">
                        <p class="truncate w-96 invisible lg:visible">소개 : {{item.description}} </p>
                    </div>
                </div> -->
            </searchTeamListItem>
        </div>
  </div>
</template>

<script>
import searchTeamListItem from '@/components/sriends/searchTeamListItem.vue'
import { getTeamList } from '@/api/team.js'
import store from '@/store/index.js'
export default {
    components:{
        searchTeamListItem
    },
    data() {
        return{
            selectCity: store.state.selectCity,
            selectSportCategory: store.state.selectSportCategory,
            teams:[
                {    
                    id : '',
                    pictureDownloadUri: '',
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
        const city = store.state.selectCity
        const sportCategory = store.state.selectSportCategory
        getTeamList(city, sportCategory)
        .then((res)=>{
            console.log(res)
            this.teams = res.data
        }).catch((err)=>{
            console.log(err)
            this.state=false
        })
    },
    
  methods:{
      joinBtn: function(){
          this.$router.push('/joinTeam')
      }
  }
}
</script>

<style>

</style>