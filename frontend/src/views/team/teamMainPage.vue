<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <teamMain></teamMain>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import teamMain from '@/components/team/teamMain.vue'
export default {
    components: {
        topmenu,
        teamMenu,
        teamMain,
    }
}
</script>

<style>

</style>