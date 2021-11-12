<template>
  <div>
    <topMenu></topMenu>
    <matchingDetail></matchingDetail>
    <postComment v-bind:postId="this.postId"></postComment>
    <commentList v-bind:postId="this.postId"></commentList>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import matchingDetail from '@/components/matching/matchingDetail.vue'
import postComment from '@/components/matching/matchingComment/postComment.vue'
import commentList from '@/components/matching/matchingComment/getCommentList.vue'
export default {
    props:{
      postId: [String,Number]
    },
    components:{
        topMenu,
        matchingDetail,
        postComment,
        commentList
    }

}
</script>

<style>

</style>