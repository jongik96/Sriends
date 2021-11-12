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
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">간단한 소개글을 적어주세요.</p>
                          <textarea v-model="description" id="description" rows=5 type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                        
                        <button @click="clickJoin" :disabled="!this.description"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10 ">
                            요청
                        </button>
                        <router-link to="/team"   >
                            <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10 ml-3">취소</button>
                        </router-link>
                      </div>

              </div>
          </div>
      </div>
  </div>
</template>

<script>
import store from '@/store/index.js'
import Swal from 'sweetalert2'
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
                Swal.fire('가입신청이 완료되었습니다')
                this.$router.push('/team')
            }).catch((err)=>{
                console.log(err.response.data.message)
                Swal.fire('이미 가입신청이 되어 있습니다!')
            })
        }
    }
}
</script>

<style>

</style>