<template>
  <div>
      <topMenu></topMenu>
      <selectCategory v-bind:city="this.city" v-bind:sportCategory="this.sportCategory"></selectCategory>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import selectCategory from '@/components/matching/selectCategory.vue'
export default {
    props:{
        city: String,
        sportCategory: String
    },
    components:{
        topMenu,
        selectCategory
        
    }

}
</script>

<style>

</style>