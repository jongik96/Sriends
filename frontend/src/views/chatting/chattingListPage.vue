<template>
  <div>
      <topMenu></topMenu>
      <div class="grid grid-cols-6">
        <chattingList class="col-start-1 col-span-6 md:col-start-2 md:col-span-4"></chattingList>
      </div>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import chattingList from '@/components/chat/chattingList.vue'
export default {
    components:{
        topMenu,
        chattingList,
    }

}
</script>

<style>

</style>