<template>
    <div>
        <topMenu></topMenu>
        <teamcreate></teamcreate>
    </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import teamcreate from '@/components/user/teamcreate.vue'
export default {
    components: {
        teamcreate,
        topMenu
        
    }
}
</script>

<style>

</style>