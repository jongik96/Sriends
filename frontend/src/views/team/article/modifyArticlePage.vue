<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <modifyArticle></modifyArticle>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import modifyArticle from '@/components/team/article/modifyArticle.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        modifyArticle,
    }
}
</script>

<style>

</style>