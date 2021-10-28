<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <waitingMember></waitingMember>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import waitingMember from '@/components/team/member/waitingMember.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        waitingMember,
    }
}
</script>

<style>

</style>