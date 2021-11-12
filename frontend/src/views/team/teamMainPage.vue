<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <teamMain v-bind:teamId="this.teamId"></teamMain>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import teamMain from '@/components/team/teamMain.vue'
export default {
    props:{
      teamId: [String,Number]
    },
    components: {
        topmenu,
        teamMenu,
        teamMain,
    }
}
</script>

<style>

</style>