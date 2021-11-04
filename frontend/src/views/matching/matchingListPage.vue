<template>
  <div>
    <topMenu></topMenu>
    <matchingList></matchingList>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import matchingList from '@/components/matching/matchingList.vue'
export default {
    components:{
        topMenu,
        matchingList
        
    }

}
</script>

<style>

</style>