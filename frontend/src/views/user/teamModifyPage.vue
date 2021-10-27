<template>
    <div>
        <topMenu></topMenu>
        <teammodify></teammodify>
    </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import teammodify from '@/components/user/teammodify.vue'
export default {
    components: {
        teammodify,
        topMenu
        
    }
}
</script>

<style>

</style>