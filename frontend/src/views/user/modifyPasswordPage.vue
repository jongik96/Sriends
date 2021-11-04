<template>
  <div>
      <topmenu></topmenu>
      <modifyPassword></modifyPassword>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import modifyPassword from '@/components/user/modifyPassword.vue'
export default {
    components: {
        modifyPassword,
        topmenu
    }
}
</script>

<style>

</style>