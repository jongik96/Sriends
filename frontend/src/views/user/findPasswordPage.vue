<template>
  <div>
    <topmenu></topmenu>
    <findPassword></findPassword>
  </div>
</template>

<script>
import topmenu from '@/components/topMenu.vue'
import findPassword from '@/components/user/findPassword.vue'
export default {
  components:{
    topmenu,
    findPassword
  }
}
</script>

<style>

</style>