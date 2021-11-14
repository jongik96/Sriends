<template>
<div class="flex antialiased text-gray-800">
    <div class="flex flex-row h-full w-full overflow-x-hidden">
      <div class="flex flex-col py-8 pl-6 pr-2 w-full bg-white flex-shrink-0">
        <div class="flex flex-row items-center justify-center h-12 w-full">

          <div class="ml-2 font-bold text-2xl">채팅목록</div>
        </div>

        <div class="flex flex-col mt-8">
          <div class="flex flex-row items-center justify-between text-xs">
          </div>
          <div class="flex flex-col space-y-1 mt-4 -mx-2 h-48 overflow-y-auto">
            
            <button
              class="flex flex-row items-center hover:bg-gray-100 rounded-xl p-2"
            >
              <div
                class="flex items-center justify-center h-8 w-8 bg-yellow-300 rounded-full"
              >
                J
              </div>
              <router-link to="/chat" class="ml-2 text-sm font-semibold">Jerry Guzman</router-link>
            </button>
            <div v-for="item in rooms" :key="item.id">
            <button
              class="flex flex-row items-center hover:bg-gray-100 rounded-xl p-2"
            >
              <div
                class="flex items-center justify-center h-8 w-8 bg-yellow-200 rounded-full"
              >
                Image
              </div>
              <div class="ml-2 text-sm font-semibold">{{item.oppenentName}}</div>
            </button>                       
            </div>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    data(){
        return{
            rooms:[
                {
                    roomId:1,
                    oppenentId:3,
                    oppenentName:'testA'
                }
            ]
        }
    }


}
</script>

<style>

</style>