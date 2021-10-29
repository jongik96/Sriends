<template>
  <div>
    <topMenu></topMenu>
    <matchingDetail></matchingDetail>
    <articleComment></articleComment>
    <articleCommentList></articleCommentList>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import matchingDetail from '@/components/matching/matchingDetail.vue'
import articleComment from '@/components/team/article/articleComment.vue'
import articleCommentList from '@/components/team/article/articleCommentList.vue'
export default {
    components:{
        topMenu,
        matchingDetail,
        articleComment,
        articleCommentList
    }

}
</script>

<style>

</style>