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
            <articleItem v-for="item in post" :key="item.id"
                :boardId="item.id"
                :title="item.title"
                :content="item.content"
                :createDate="item.createDate"
                :name="item.name"
             >

            </articleItem>
        </div>
  </div>
</template>

<script>
import articleItem from '@/components/team/article/articleItem.vue'
import store from '@/store/index.js'
import { getArticleList } from '@/api/article.js'
export default {
    components:{
        articleItem
    },
    data() {
        return{
            post: [],

        }
    },
  created(){
      const teamId = store.state.teamId
      getArticleList(teamId)
      .then((res)=>{
          console.log(res.data)
          this.post = res.data
      }).catch((err)=>{
          console.log(err)
      })
  },
}
</script>

<style>

</style>
