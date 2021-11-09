<template>
  <div>
      <section class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-4 mt-12 mb-12">
    <article>
        <h2 class="text-2xl font-extrabold text-gray-900">나의 스렌즈</h2>
        <section class="mt-6 grid md:grid-cols-2 lg:grid-cols-4 gap-x-6 gap-y-8">
            <!-- 새 스렌즈 만들기 -->
            <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                <div class="relative w-full h-80 md:h-64 lg:h-44">
                    <router-link to="/teamCreate">
                        <img src="@/assets/plus.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                    </router-link>
                </div>
                <div class="px-3 py-4 flex items-center justify-center">
                        <a class="bg-yellow-400 py-1 px-2 font-semibold text-black rounded-lg" href="#">                         
                            새 스렌즈 만들기
                        </a>
                </div>
            </article> 

            <cardItem v-for="item,index in teams" :key="index"
                :id="item.id"
            
            >
            </cardItem>
            <infinite-loading @infinite="infiniteHandler" spinner="sprial">
                <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
            </infinite-loading>

            
        </section>
    </article>
</section>
  </div>
</template>

<script>
// import {getProfileInfo} from '@/api/index.js'
import cardItem from '@/components/user/cardItem.vue'
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading';
import store from '@/store/index.js'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
export default {
    components:{
        InfiniteLoading,
        cardItem,
    },
    data(){
        return{
            page:0,
            teams : [
                {    
                id : '',
                pictureUrl: '',
                name : ''
                },
            ],
        }
    },
    methods:{
        infiniteHandler($state) {
            const token = store.state.accessToken
            axios({
                method: 'get',
                url: `${SERVER_URL}/users/team?page=` + (this.page),
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
                    if(res.data.content.length / 5 < 1) {
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
    },
    computed:{
        getToken(){
            const token = this.$store.state.accessToken
            const config = {
                Authorization: `Bearer ${token}`
            }
            return config
        },
    }
}
</script>

<style>

</style>