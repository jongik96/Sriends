<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <card></card>
      </div>
      <div class="col-start-2 col-span-4 ">
          <profile></profile>
      </div>
      
      
  </div>
</template>

<script>
import card from '@/components/user/card.vue'
import profile from '@/components/user/profile.vue'
export default {
  components:{
    card,
    profile
  }
}
</script>

<style>

</style>