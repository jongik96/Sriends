<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <makeSchedule></makeSchedule>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import makeSchedule from '@/components/team/schedule/makeSchedule.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        makeSchedule
    }
}
</script>

<style>

</style>