<template>
  <div>
    <topMenu></topMenu>
      <div class="grid grid-cols-6">
        <chatting class="col-start-1 col-span-6 md:col-start-2 md:col-span-4"></chatting>
      </div>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import chatting from '@/components/chat/chatting.vue'
export default {
    components:{
        topMenu,
        chatting
    }

}
</script>

<style>

</style>