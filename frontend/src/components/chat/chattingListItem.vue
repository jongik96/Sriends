<template>
  <div @click="clickItem" class="border-2 border-yellow-500 rounded-xl mr-3">
        <button
            class="flex flex-row items-center hover:bg-gray-100 rounded-xl p-2"
        >
            <div
            class="flex items-center justify-center h-8 w-8 bg-yellow-200 rounded-full"
            >
            Image
            </div>
            <div class="ml-7 text-xl font-semibold">{{partnerName}}</div>
        </button>                       
        <p class="float-right">{{latestMessageDate}}</p>
  </div>
</template>

<script>
export default {
    props:{
        partnerName:[String],
        latestMessageDate:[String,Date],
        roomId:[String,Number],
        partnerPicture:[String,File]

    },
    methods:{
        clickItem:function(){
            this.$store.commit('setRoomId',this.roomId)
            this.$router.push('/chat')
        }
    }
}
</script>

<style>

</style>