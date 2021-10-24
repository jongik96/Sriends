<template>
    <div>
        <topMenu></topMenu>
        <modify></modify>
    </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import modify from '@/components/user/modify.vue'
export default {
    components: {
        modify,
        topMenu
    }
}
</script>

<style>

</style>