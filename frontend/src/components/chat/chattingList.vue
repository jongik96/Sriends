<template>
<div class="flex antialiased text-gray-800 border-b-4 border-l-4 h-full border-r-4 border-yellow-200 rounded-lg">
    <div class="flex flex-row h-full w-full overflow-x-hidden">
      <div class="flex flex-col py-8 pl-6 pr-2 w-full h-96 min-h-96 flex-shrink-0">
        <div class="flex flex-row items-center justify-center h-12 w-full">

          <div class=" font-bold text-2xl">채팅목록</div>
        </div>

        <div class="flex flex-col mt-3">
          <div class="flex flex-row items-center justify-between text-xs">
          </div>
          <div class="flex flex-col space-y-1 mt-4  h-48 overflow-y-auto">
            

            <chattingListItem v-for="item in rooms" :key="item.id"
              :partnerName="item.partnerName"
              :roomId="item.roomId"
              :latestMessageDate="item.latestMessageDate"
              :partnerPicture="item.partnerPicture"
              :partnerId="item.partnerId"
            >

            </chattingListItem>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import chattingListItem from '@/components/chat/chattingListItem.vue'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
import axios from 'axios'
import store from '@/store/index.js'
export default {
  components:{
    chattingListItem
  },
    data(){
        return{
            rooms:[

            ]
        }
    },
    created(){
      const token = store.state.accessToken
      axios({
        method: 'get',
        url: `${SERVER_URL}/chat/list`,
        headers: {
            'Authorization': `Bearer ${token}`
        }
      }).then((res)=>{
        console.log(res.data)
        this.rooms = res.data
      }).catch((err)=>{
        console.log(err)
      })
    }


}
</script>

<style>

</style>