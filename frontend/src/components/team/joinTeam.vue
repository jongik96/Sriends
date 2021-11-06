<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-3xl text-yellow-500 font-bold">가입 요청</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-2 md:col-span-4 col-start-1 col-span-6 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form>
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">간단한 소개글을 적어주세요.</p>
                          <textarea v-model="description" id="description" rows=5 type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                        
                        <button @click="clickJoin"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10 ">
                            요청
                        </button>
                        <button   class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10 ml-3">
                            취소
                        </button>
                      </div>
                  </form>

              </div>
          </div>
      </div>
  </div>
</template>

<script>
import store from '@/store/index.js'
import { joinTeam } from '@/api/team.js'
export default {
    data(){
        return{
            description:'',
        }
    },
    methods:{
        clickJoin: function(){
            const teamId = store.state.teamId
            const userId = store.state.userId
            console.log(teamId, userId)
            joinTeam(teamId,userId,this.description)
            .then((res)=>{
                console.log(res)
                this.$router.push('/searchTeam')
            }).catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style>

</style>