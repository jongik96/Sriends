<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <articleDetail v-bind:boardId="this.boardId"></articleDetail>
    <articleComment v-bind:boardId="this.boardId"></articleComment>
    <articleCommentList v-bind:boardId="this.boardId"></articleCommentList>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import articleDetail from '@/components/team/article/articleDetail.vue'
import articleComment from '@/components/team/article/articleComment.vue'
import articleCommentList from '@/components/team/article/articleCommentList.vue'
export default { 
  props:{
    boardId: [String, Number]
  },
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