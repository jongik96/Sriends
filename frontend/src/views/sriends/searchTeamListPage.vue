<template>
  <div>
      <topMenu></topMenu>
      <searchTeamList v-bind:city="this.city" v-bind:sportCategory="this.sportCategory"></searchTeamList>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import searchTeamList from '@/components/sriends/searchTeamListResult.vue'
export default {
    props:{
        city: String,
        sportCategory: String
    },
    components:{
        topMenu,
        searchTeamList
        
    }

}
</script>

<style>

</style>