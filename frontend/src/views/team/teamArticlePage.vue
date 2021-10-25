<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <teamArticle></teamArticle>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import teamArticle from '@/components/team/teamArticle.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        teamArticle,
    }
}
</script>

<style>

</style>