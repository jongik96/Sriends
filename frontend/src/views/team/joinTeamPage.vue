<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <joinTeam></joinTeam>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import joinTeam from '@/components/team/joinTeam.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        joinTeam,
    }
}
</script>

<style>

</style>