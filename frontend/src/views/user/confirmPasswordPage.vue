<template>
    <div>
        <topmenu></topmenu>
        <confirmPassword></confirmPassword>
    </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import confirmPassword from '@/components/user/confirmPassword.vue'
export default {
    components:{
        topmenu,
        confirmPassword
    }
}
</script>

<style>

</style>