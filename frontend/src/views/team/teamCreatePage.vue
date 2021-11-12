<template>
    <div>
        <topMenu></topMenu>
        <!-- <teamMenu></teamMenu> -->
        <teamcreate></teamcreate>
    </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
// import teamMenu from '@/components/team/teamMenu.vue'
import teamcreate from '@/components/team/teamcreate.vue'
export default {
    components: {
        teamcreate,
        // teamMenu,
        topMenu
        
    }
}
</script>

<style>

</style>