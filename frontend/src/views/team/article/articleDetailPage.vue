<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <articleDetail></articleDetail>
    <articleComment></articleComment>
    <articleCommentList></articleCommentList>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import articleDetail from '@/components/team/article/articleDetail.vue'
import articleComment from '@/components/team/article/articleComment.vue'
import articleCommentList from '@/components/team/article/articleCommentList.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        articleDetail,
        articleComment,
        articleCommentList
    }
}
</script>

<style>

</style>