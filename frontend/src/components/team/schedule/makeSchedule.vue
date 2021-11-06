<template>
    <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-3xl text-yellow-500 font-bold">일정 추가</p>
          </div>
        </div>
        <div class="col-start-2 col-span-4 ">
            <div class="grid grid-cols-6  mt-10">
                <div class="col-start-2 col-span-4 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                    <form>
                        <div class="pt-5 pl-20">
                            <p class="text-xl font-bold">제목</p>
                            <input v-model="name" type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                        </div>
                        <div class="pt-5 pl-20">
                            <p class="text-xl font-bold">내용</p>
                            <textarea v-model="content" rows=5 type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                        </div>
                        <div class="pt-5 pl-20">
                            <p class="text-xl font-bold">일시</p>
                            <input v-model="createDate" type="date" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                        </div>
                        <div class="flex justify-center p-2 mt-10">
                            <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                            <router-link to="/team/calendar">
                            <button type="submit"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">
                                취소
                            </button>
                            </router-link>
                            <button @click="addSchedule"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10 ml-3">
                                작성
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
import { postSchedule } from '@/api/schedule.js'
// import Swal from 'sweetalert2'
export default {
    data(){
        return{
            name:'',
            createDate:'',
            content:'',
            teamId : store.state.teamId,
            writerId : store.state.userId,
        }
    },
    methods:{
        addSchedule: function(){
            postSchedule(this.teamId,this.writerId,this.name,this.content,this.createDate)
            .then((res)=>{
                console.log(res)
                this.$router.push('/team/calendar')
            }).catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style>

</style>