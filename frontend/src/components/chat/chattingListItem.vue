<template>
  <div @click="clickItem" class="border-2 border-yellow-500 rounded-xl mr-3">
        <button
            class="flex flex-row items-center hover:bg-gray-100 rounded-xl p-2"
        >
            <img  
            :src="partnerPicture" @error="imgError" class="flex items-center justify-center h-10 w-10 bg-yellow-200 rounded-full"
            />
            <!-- <img v-if="this.partnerPicture==null"
            src="@/assets/profile.png" class="flex items-center justify-center h-10 w-10 bg-yellow-200 rounded-full"
            /> -->
            
            
            <div class="ml-7 text-2xl font-semibold">{{partnerName}}</div>
        </button>                       
        <p class="float-right mr-3">{{getTime}}</p>
  </div>
</template>

<script>
import {getDate} from '@/utils/date.js'
import img from '@/assets/profile.png'
export default {
    props:{
        partnerName:[String],
        latestMessageDate:[String,Date],
        roomId:[String,Number],
        partnerPicture:[String,File],
        partnerId:[String,Number]

    },
    methods:{
        clickItem:function(){
            this.$store.commit('setRoomId',this.roomId)
            this.$store.commit('setPartnerName',this.partnerName)
            this.$store.commit('setPartnerPicture',this.partnerPicture)
            this.$store.commit('setChatPath','list')
            this.$store.commit('setPartnerId',this.partnerId)
            this.$router.push('/chat')
        },
        imgError:function(e){
            e.target.src = img
        },
    },
    computed:{
        getTime(){
            const time= getDate(this.latestMessageDate)
            return time
        }
    }
}
</script>

<style>

</style>