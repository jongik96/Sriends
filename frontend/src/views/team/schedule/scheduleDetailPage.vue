<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <scheduleDetail></scheduleDetail>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import scheduleDetail from '@/components/team/schedule/scheduleDetail.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        scheduleDetail
    }
}
</script>

<style>

</style>