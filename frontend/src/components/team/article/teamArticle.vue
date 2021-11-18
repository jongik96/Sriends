<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6  md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="flex justify-end">
                <router-link to="/team/createArticle">
                <button class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            글 작성하기
                </button>
                </router-link>
            </div>
            <div v-if="!state">등록된 공지사항이 없습니다</div>
            <articleItem v-for="item in post" :key="item.id"
                :boardId="item.id"
                :title="item.title"
                :content="item.content"
                :createDate="item.createDate"
                :name="item.name"
             >

            </articleItem>
            <infinite-loading @infinite="infiniteHandler" spinner="sprial">
                <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
                <div slot="no-results" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">가입된 팀이 없습니다 :)</div>
            </infinite-loading>
        </div>
  </div>
</template>

<script>
import articleItem from '@/components/team/article/articleItem.vue'
import store from '@/store/index.js'
// import { getArticleList } from '@/api/article.js'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
export default {
    components:{
        articleItem,
        InfiniteLoading
    },
    data() {
        return{
            post: [],
            page:0,
            state:false

        }
    },
  created(){
    //   const teamId = store.state.teamId
    //   getArticleList(teamId)
    //   .then((res)=>{
    //       console.log(res.data)
    //       this.post = res.data
    //   }).catch((err)=>{
    //       console.log(err)
    //   })
  },
  methods:{
      infiniteHandler($state) {
          const teamId = store.state.teamId
            const token = store.state.accessToken
            axios({
                method: 'get',
                url: `${SERVER_URL}/teams/board/${teamId}?page=` + (this.page),
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            }).then(res => {
                console.log(res.data)
                this.state=true
                setTimeout(() => {
                    if(res.data.content.length) {
                    this.post = this.post.concat(res.data.content)
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
