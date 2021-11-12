<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4  px-4 py-2 bg-white mt-10">
            <p v-if="replyCount>0"> 댓글 {{this.replyCount}} 개</p>
            <articleCommentItem v-for="item in comments" :key="item.id"
                :commentId="item.id"
                :createAt="item.createdAt"
                :content="item.content"
                :modified="item.modified"
                :writerId="item.writer.id"
                :writerName="item.writer.name"
                :writerImg="item.writer.pictureUrl"
                :replyCount="item.replyCount"
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
import { getArticleCommentsList } from '@/api/matchComment.js'
import articleCommentItem from '@/components/matching/matchingComment/commentItem.vue'
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
    const boardId = store.state.postId
    getArticleCommentsList(boardId, this.parentId)
    .then((res)=>{
        console.log(res)
        this.comments = res.data
        this.replyCount = res.data.replyCount
        if(res.data.id.writer.pictureUrl!=null){
        this.comments.writer.pictureUrl = res.data.writer.pictureUrl
        }
    }).catch((err)=>{
        console.log(err)
    })
  }
}
</script>

<style>

</style>