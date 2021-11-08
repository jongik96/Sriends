<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <p v-if="replyCount>0"> 댓글 {{this.replyCount}} 개</p>
            <articleCommentItem v-for="item in comments" :key="item.id" class="shadow-md p-4 mt-5 mx-6 mb-4 min-w-300"
                :commentId="item.id"
            >
                <!-- <p class="text-justify leading-tight text-gray-800">{{ item.content }}<p>
                <div class="mt-3">
                    <span class="left">Posted On: {{ item.datePosted }}</span>
                    <span class="float-right">By: <a class="text-purple-500" href="#">{{ item.postedBy }}</a></span>
                    <div class="float-right">
                        <button class="mr-3">수정</button>
                        <button class="mr-3">삭제</button>
                    </div>
                </div> -->
                
            </articleCommentItem>
        </div>
  </div>
</template>

<script>
import { getArticleCommentsList } from '@/api/comment.js'
import articleCommentItem from '@/components/team/article/articleCommentItem.vue'
import store from '@/store/index.js'
export default {
    components:{
        articleCommentItem
    },
    data() {
        return{
            parentId:0,
            comments:[],
            replyCount:0,
        }
  },
  created(){
    const boardId = store.state.boardId
    getArticleCommentsList(boardId, this.parentId)
    .then((res)=>{
        console.log(res)
        this.comments = res.data.commentsList
        this.replyCount = res.data.replyCount
    }).catch((err)=>{
        console.log(err)
    })
  }
}
</script>

<style>

</style>