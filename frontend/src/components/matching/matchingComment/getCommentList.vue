<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <p v-if="replyCount>0"> 댓글 {{this.replyCount}} 개</p>
            <commentItem v-for="item in comments" :key="item.id"
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
                
            </commentItem>
        </div>
  </div>
</template>

<script>
import { getArticleCommentsList } from '@/api/comment.js'
import commentItem from '@/components/matching/matchingCommnet/commentItem.vue'
import store from '@/store/index.js'
export default {
    components:{
        commentItem
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