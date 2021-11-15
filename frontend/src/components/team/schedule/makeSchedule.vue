<template>
    <div class="grid grid-cols-6">
        <!-- <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
            
          </div>
        </div> -->
        <div class="col-start-1 col-span-6  md:col-start-2 md:col-span-4">
              <p class="text-3xl text-yellow-500 font-bold">일정 추가</p>
            <div class="grid grid-cols-6  mt-10">
                <div class="col-start-1 col-span-6  md:col-start-2 md:col-span-4 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                        <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                            <p class="text-xl font-bold">제목</p>
                            <input v-model="name" type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                        </div>
                        <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                            <p class="text-xl font-bold">내용</p>
                            <textarea v-model="content" rows=5 type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                            <p v-if="content.length>400" class="text-yellow-600">400자 이내로 입력해주세요</p>
                        </div>
                        <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                            <p class="text-xl font-bold">일시</p>
                            <input v-model="createDate" type="datetime-local" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                        </div>
                        <div class="flex justify-center p-2 mt-10">
                            <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                            <button @click="addSchedule" :disabled="btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">
                                작성
                            </button>
                            <router-link to="/team/calendar">
                                <button   class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10 ml-3">
                                    취소
                                </button>
                            </router-link>
                        </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import store from '@/store/index.js'
import { postSchedule } from '@/api/schedule.js'
import Swal from 'sweetalert2'
export default {
    data(){
        return{
            name:'',
            createDate:'',
            content:'',
        }
    },
    methods:{
        addSchedule: function(){
            const teamId = store.state.teamId
            const writerId = store.state.userId

            ///// 현지님이랑 얘기해서 문제 해결되면 다시 schedule.js에 함수 불러서 쓰기
            postSchedule(teamId,writerId,this.name,this.content,this.createDate)

            .then((res)=>{
                console.log(res)
                Swal.fire('일정이 등록되었습니다.')
                this.$router.push('/team/calendar')
            }).catch((err)=>{
                console.log(err)
            })
        }
    },
    computed:{
        getToken(){
            const token = store.state.accessToken
            const config = {
                Authorization: `Bearer ${token}`
            }
            return config
        },
        btnDisabled(){
            if((this.content.length>400) || (this.content.length ==0) || !this.name || !this.createDate ){
                return true
            }else{
                return false
            }
        },
    }
}
</script>

<style>

</style>