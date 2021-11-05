<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="flex justify-start">
                종목 : {{sportCategory}} 지역 : {{city}}
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
            <div v-for="item in teams" :key="item.id" class="border-2 border-yellow-500 rounded-xl shadow-md p-4 mt-5 mx-6 mb-4 w-60 md:w-96 lg:w-full h-48 grid grid-cols-6">
                <div class="grid col-start-1 col-span-2">
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
                        <p>대표 : {{item.postedBy}} </p>
                    </div>
                    <div class="invisible md:visible">
                        <p>인원 : {{item.memberCount}} </p>
                    </div>
                    <div class="">
                        <p class="truncate w-96 invisible lg:visible">소개 : {{item.description}} </p>
                    </div>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import { getTeamList } from '@/api/index.js'
export default {
    props:{
        city:String,
        sportCategory:String
    },
    created(){
        getTeamList(this.selectCity, this.selectSportCategory)
        .then((res)=>{
            console.log(res)
            this.teams = res.data
        }).catch((err)=>{
            console.log(err)
        })
    },
    data() {
        return{
            selectCity: this.city,
            selectSportCategory: this.sportCategory,
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
            items: [
            { id: 1,
                title: "동호회1",
                description: "동호회소개 어쩌구저쩌구 ㅁㄴㅇㄻㄴ아럼ㄴ;이러",
                datePosted: "10/09/2020",
                postedBy: "user525"
            },
            { id: 2,
                title: "Random post",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user120"
            },
            { id: 3,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dumasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdmy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 4,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 5,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 6,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 7,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            }
            ]
        }
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