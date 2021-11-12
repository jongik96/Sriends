<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="flex justify-end">
                <router-link to="/createMatching">
                <button class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-md md:text-xl rounded block text-center 
                    sm:inline-block mr-5">
                            경기 매칭구하기
                </button>
                </router-link>
            </div>
            <!-- <div v-for="item in items" :key="item.id" class="shadow-md p-4 mt-5 mx-6 mb-4 min-w-300 h-36 md:h-24">
                <h2 class="text-2xl font-semibold"><a href="#">{{ item.title }}</a></h2>
                <p class="truncate md:w-96 w-48 text-justify leading-tight text-gray-800">{{ item.description }}<p>
                <div class="mt-2">
                <span class="left">Posted On: {{ item.datePosted }}</span>
                <span class="float-right">By: <a class="text-purple-500" href="#">{{ item.postedBy }}</a></span>
                </div>
            </div> -->
            <div class="grid mt-10">
                <div class="grid col-start-1 col-span-1">
                    글 분류
                </div>
                <div class="grid col-start-2 col-span-1">
                    종목
                </div>
                <div class="grid col-start-3 col-span-1">
                    모집인원
                </div>
                <div class="grid col-start-4 col-span-1">
                    작성팀
                </div>
                <div class="grid col-start-5 col-span-2">
                    작성일시
                </div>
            </div>
            <matchingListItem v-for="item,index in teams" :key="index"
                :postId=item.postId
            >
                <!-- <div class="grid col-start-1 col-span-1">
                    
                </div>
                <div class="grid col-start-2 col-span-1">
                   
                </div>
                <div class="grid col-start-3 col-span-1">
                    
                </div>
                <div class="grid col-start-4 col-span-1">
                    
                </div>
                <div class="grid col-start-5 col-span-2">
                    
                </div> -->
            </matchingListItem>
            <infinite-loading @infinite="infiniteHandler" spinner="sprial">
                <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
            </infinite-loading>
      </div>
  </div>
</template>

<script>
import matchingListItem from '@/components/matching/matchingListItem.vue'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
import store from '@/store/index.js'
export default {
    components:{
        InfiniteLoading,
        matchingListItem
    },
    data() {
        return{
            page:0,
            cities: store.state.selectCity,
            sportCategories: store.state.selectSportCategory,
            matchBoardCategories: store.state.category,
            teams:[]
        }
  },
  methods:{
      infiniteHandler($state) {
            const token = store.state.accessToken
            axios({
                method: 'get',
                url: `${SERVER_URL}/match/board/posts/all/${this.cities}/${this.sportCategories}/${this.matchBoardCategories}?page=` + (this.page),
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
            })
        },
  }
}
</script>

<style>

</style>