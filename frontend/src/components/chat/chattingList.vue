<template>
<div class="flex antialiased text-gray-800 border-b-4 border-l-4 border-r-4 border-yellow-200 rounded-lg">
    <div class="flex flex-row h-full w-full overflow-x-hidden">
      <div class="flex flex-col py-8 pl-6 pr-2 w-full  flex-shrink-0">
        <div class="flex flex-row items-center justify-center h-12 w-full">

          <div class=" font-bold text-2xl">채팅목록</div>
        </div>

        <div class="flex flex-col mt-3">
          <div class="flex flex-row items-center justify-between text-xs">
          </div>
          <div class="flex flex-col space-y-1 mt-4  h-48 overflow-y-auto">
            

            <div v-for="item in rooms" :key="item.id" class="border-2 border-yellow-500 rounded-xl mr-3">
            <button
              class="flex flex-row items-center hover:bg-gray-100 rounded-xl p-2"
            >
              <div
                class="flex items-center justify-center h-8 w-8 bg-yellow-200 rounded-full"
              >
                Image
              </div>
              <div class="ml-7 text-xl font-semibold">{{item.oppenentName}}</div>
            </button>                       
            </div>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL
import axios from 'axios'
import store from '@/store/index.js'
export default {
    data(){
        return{
            rooms:[
                {
                    roomId:1,
                    oppenentId:3,
                    oppenentName:'testA'
                },
                {
                    roomId:1,
                    oppenentId:3,
                    oppenentName:'testB'
                }
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
      }).catch((err)=>{
        console.log(err)
      })
    }


}
</script>

<style>

</style>