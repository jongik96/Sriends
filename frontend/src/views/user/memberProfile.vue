<template>
  <div>
      <topMenu></topMenu>
      <myTeamPage></myTeamPage>
  </div>
</template>

<script>
import topMenu from '@/components/topMenu.vue'
import myTeamPage from '@/components/user/memberPage.vue'
export default {
    components:{
        topMenu,
        myTeamPage
    }

}
</script>

<style>

</style>