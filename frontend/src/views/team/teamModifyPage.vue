<template>
    <div>
        <topMenu></topMenu>
        <teamMenu></teamMenu>
        <teammodify></teammodify>
    </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import teamMenu from '@/components/team/teamMenu.vue'
import teammodify from '@/components/team/teammodify.vue'
export default {
    components: {
        teammodify,
        teamMenu,
        topMenu
        
    }
}
</script>

<style>

</style>