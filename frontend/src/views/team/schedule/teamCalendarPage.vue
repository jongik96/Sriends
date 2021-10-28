<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <teamCalendar></teamCalendar>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import teamCalendar from '@/components/team/schedule/teamCalendar.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        teamCalendar,
    }
}
</script>

<style>

</style>