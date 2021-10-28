<template>
  <div>
    <topMenu></topMenu>
    <createMatching></createMatching>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import createMatching from '@/components/matching/createMatching.vue'
export default {
    components:{
        topMenu,
        createMatching
        
    }

}
</script>

<style>

</style>