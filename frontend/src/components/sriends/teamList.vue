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
            <div class="text-base sm:text-xl">{{this.myCity}} 의 <span class="mr-3" v-for="item in mySportCategory" :key="item.id">{{item.interest}} </span>에 해당하는 스렌즈 리스트입니다~</div>
            
            <!-- <div v-if="!state" class="text-xl sm:text-2xl mt-3 md:mt-10">해당 스렌즈가 없습니다.</div> -->
            <div v-if="teams.length==0">{{this.myCity}}의 <span v-for="item in mySportCategory" :key="item.id">{{item.interest}}</span>에 해당하는 팀이 없습니다.</div>
            <teamListItem v-for="item in teams" :key="item.id"
                :id="item.id"
                :leaderId="item.leaderId"
             >
            </teamListItem>
            <infinite-loading @infinite="infiniteHandler" spinner="sprial">
                <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
                <div slot="no-results" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">가입된 팀이 없습니다 :)</div>
            </infinite-loading>
        </div>
  </div>
</template>

<script>
import teamListItem from '@/components/sriends/teamListItem.vue'
// import { getInterestTeam } from '@/api/team.js'
import { getInterest } from '@/api/auth.js'
import store from '@/store/index.js'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
// import Swal from 'sweetalert2'
export default {
    components:{
        teamListItem,
        InfiniteLoading
    },
    data() {
        return{
            myCity: store.state.myCity,
            mySportCategory:[],
            teams:[],
            page:0,
            state:true
        }
  },
  created(){
        // getInterestTeam()
        // .then((res)=>{
        //     console.log(res.data)
        //     this.teams = res.data
        // }).catch((err)=>{
        //     console.log(err)
        //     this.state=false
            
        // }),
        getInterest()
        .then((res)=>{
        console.log(res)  
        this.mySportCategory = res.data    
        }).catch((err)=>{
        console.log(err)
        })
      
  },
  methods:{
      infiniteHandler($state) {
            const token = store.state.accessToken
            axios({
                method: 'get',
                url: `${SERVER_URL}/teams/list?page=` + (this.page),
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            }).then(res => {
                console.log(res.data)
                setTimeout(() => {
                    if(res.data.content.length) {
                    this.teams = this.teams.concat(res.data.content)
                    $state.loaded()
                    this.page += 1
                    // 끝인지 판별
                    if(res.data.content.length / 10 < 1) {
                        $state.complete()
                    }
                    } else {
                    // 끝 지정(No more data)
                    $state.complete()
                    }
                }, 1000)
            }).catch(err => {
                console.error(err);
                this.state=false
            })
        },
  }

}
</script>

<style>

</style>