<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-4xl text-yellow-500 font-bold">S-riends</p>
              
          </div>
          <div class="flex justify-center mt-3">
              <p class="text-2xl font-bold flex justify-center">우리동네 스포츠동호회 찾기</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-1 md:col-span-3 md:mr-2 md:visible ml-10 md:ml-5 col-start-1 col-span-5 ">
                  <img src="@/assets/sports.png" width="450"  alt="">
              </div>
              <div class="md:col-start-4 md:col-span-4  col-start-1 col-span-6  shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">Log In</p>
                      </div>
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">E-mail</p>
                          <input id="email" type="text" v-model="form.email" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400"/>
                          <p>
                              <span v-if="(form.email).length>0 && !isEmailValid" class="text-yellow-600">올바른 이메일 형식이 아닙니다.</span>
                          </p>
                      </div>
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">Password</p>
                          <input id="password" type="password" v-model="form.password" class="text-xl w-3/4 rounded-md border-2 border-yellow-400"/>
                          <!-- <p>
                              <span class="text-yellow-600">비밀번호를 입력해주세요.</span>
                          </p> -->
                      </div>
                    <div class="flex justify-center p-2 mt-10">
                        <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                        <button type="submit" :disabled="btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">
                            Log In
                        </button>
                        
                        </div>
                        <div class="flex justify-center p-2 ">
                        <router-link to="/findPassword">
                            <button class="rounded-md hover:bg-gray-200"><p>비밀번호 찾기</p></button>
                        </router-link>
                        </div>
                        <div class="flex justify-center p-2">
                        <router-link to="/signup">
                            <button class="rounded-md hover:bg-gray-200">아직 회원이 아니신가요?</button>
                        </router-link>
                    </div>
                  </form>

              </div>
          </div>
      </div>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL
import axios from 'axios'
import Swal from 'sweetalert2'
import { validateEmail } from '@/utils/validation.js';
export default {
    data() {
        return {
            form: {
                email : '',
                password : '',
            }
        }
    },
    computed:{
        btnDisabled(){
            if(!this.isEmailValid || !this.form.email || !this.form.password){
                return true
            }
            return false
        },
        isEmailValid(){
            return validateEmail(this.form.email);
        }
    },
    methods:{
        submitForm: function(){
            axios({
                method: 'post',
                url: `${SERVER_URL}/auth/login`,
                data: this.form
            }).then((res)=>{
                console.log(res.data)
                localStorage.setItem('token',res.data.accessToken)
                Swal.fire('로그인되었습니다.')
                this.$router.push({path: '/main'})
            }).catch((err)=>{
                console.log(err)
                Swal.fire('ID 또는 비밀번호를 확인해주세요.')
            }) 
            
        },
    }
}
</script>

<style>

</style>