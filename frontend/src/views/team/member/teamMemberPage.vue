<template>
  <div>
    <topmenu></topmenu>
    <teamMenu></teamMenu>
    <teamMember></teamMember>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import teamMember from '@/components/team/member/teamMember.vue'
export default { 
    components: {
        topmenu,
        teamMenu,
        teamMember,
    }
}
</script>

<style>

</style>