<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <createArticle></createArticle>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import createArticle from '@/components/team/article/createArticle.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        createArticle,
    }
}
</script>

<style>

</style>