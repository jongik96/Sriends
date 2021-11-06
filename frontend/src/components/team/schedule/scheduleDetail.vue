<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div v-if="!modifyState" class="border-2 border-yellow-500 rounded-md">
                <div>
                    <p class="text-2xl">{{name}}</p>
                </div>
                <div class="mt-5">
                    <p>일시 : {{schedule}}</p>
                </div>
                <div>
                    <p>작성자 : {{writerName}}</p>
                </div>
                <div class="mt-5">
                    <p>
                        {{content}}
                    </p>
                </div>
                <div class="mt-7">
                    <button @click="modifyState=true">수정</button>
                </div>
            </div>
            <!-- 수정할때 쓰는거 -->
            <div v-if="modifyState" class="border-2 border-yellow-500 rounded-md">
                <div class="ml-3 mt-3">
                    <p class="text-xl">제목</p>
                    <input type="text" v-model="modifyName" class=" text-medium w-2/4 rounded-md border-2 border-yellow-400">
                </div>
                <div class="mt-5 ml-3">
                    <p class="text-xl">날짜</p>
                    <input type="date" v-model="modifyDate" class=" text-medium w-2/4 rounded-md border-2 border-yellow-400">
                </div>
                <div class="mt-5 ml-3">
                    <p class="text-xl">내용</p>
                    <textarea rows="5" type="text" v-model="modifyContent" class=" text-medium w-3/4 rounded-md border-2 border-yellow-400"></textarea>
                </div>
                <div class="mt-7 ml-3">
                    <button @click="modifyCalendar">수정하기</button>
                    <button @click="deleteCalendar" class="ml-5">삭제</button>
                    <button class="ml-5" @click="modifyState=false">취소</button>

                </div>
            </div>
        </div>
  </div>
</template>

<script>
import store from '@/store/index.js'
import { getSchedule } from '@/api/schedule.js'
import { putSchedule } from '@/api/schedule.js'
import { deleteSchedule } from '@/api/schedule.js'
import Swal from 'sweetalert2'
export default {
    data(){
        return{
            modifyName:'',
            modifyContent:'',
            modifyDate:'',
            modifyState:false,
            content:'',
            id:'',
            name:'',
            schedule:'',
            writerId:'',
            writerName:'',
            
        }
    },
    created(){
        const calendarId = store.state.calendarId
        getSchedule(calendarId)
        .then((res)=>{
            console.log(res)
            this.content = res.data.content
            this.id = res.data.id
            this.name = res.data.name
            this.schedule = res.data.schedule
            this.writerId = res.data.writerId
            this.writerName = res.data.writerName
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        modifyCalendar: function(){
            const calendarId = store.state.calendarId
            const teamId = store.state.teamId
            putSchedule(calendarId,teamId,this.writerId,this.modifyName,this.modifyContent,this.modifyDate)
            .then((res)=>{
                console.log(res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        deleteCalendar: function(){
            const calendarId = store.state.calendarId
            deleteSchedule(calendarId)
            .then((res)=>{
                console.log(res.data)
                Swal.fire('일정이 삭제되었습니다.')
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