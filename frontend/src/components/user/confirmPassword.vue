<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-4xl text-yellow-500 font-bold">S-riends</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-2 md:col-span-4 col-start-1 col-span-6 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">Password</p>
                          <input id="password" v-model="form.password" type="password" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                          <p class="mt-3">
                              <span class="text-yellow-600">Password 입력해주세요.</span>
                          </p>
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                        <button type="submit"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">
                            확인
                        </button>
                      </div>
                  </form>

              </div>
          </div>
      </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
import { confirmPassword } from '@/api/auth.js'
import { deleteUser } from '@/api/auth.js'
// import store from '@/store/index.js'
export default {
 data(){
   return{
     form:{
       password:'',
     }
   }
 },
 methods:{
   submitForm(){
    //  const userid = store.state.userId

    confirmPassword(this.form)
     .then((res)=>{
       console.log(res)
       if(res.data==true){

        deleteUser()
         .then((res)=>{
           console.log(res)
           Swal.fire('회원삭제 되었습니다.')
           this.$router.push('/')
         }).catch((err)=>{
           console.log(err)
         })
       }else{
         Swal.fire('비밀번호가 틀린데요?')
       }
     }).catch((err)=>{
       console.log(err)
       Swal.fire('비밀번호가 틀립니다!')
     })
   }
 },


}
</script>

<style>

</style>