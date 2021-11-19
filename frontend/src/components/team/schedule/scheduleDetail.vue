<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div v-if="!modifyState" class="border-2 border-yellow-500 rounded-md">
                <div class="border-b">
                    <p class="text-2xl">{{name}}</p>
                    
                </div>
                <div class="">
                    <div class="mt-5 grid grid-cols-6">
                        <div class="col-start-1 col-span-5">
                            <p class="text-base">일시 : {{schedule}}</p>
                        </div>
                        <div @click="clickUser" class="col-start-6 col-span-1 ml-5">
                            <img :src="writer.pictureUrl" @error="imgError" class="rounded-xl h-10 w-10" alt="">
                            <p>{{writer.name}}</p>
                        </div>
                    </div>
                    <div v-if="userId == writer.id" class="float-right">
                       
                        <button @click="modifyState=true">수정하기</button>

                        
                    </div>
                </div>
                <div class="mt-1 text-xl ">
                    <p class="break-words">
                        {{content}}
                    </p>
                </div>
                
            </div>
            <!-- 수정할때 쓰는거 -->
            <div v-if="modifyState" class="border-2 border-yellow-500 rounded-md">
                <div class="ml-3 mt-3">
                    <p class="text-xl">제목</p>
                    <input type="text" v-model="name" class=" text-medium w-2/4 rounded-md border-2 border-yellow-400">
                </div>
                <div class="mt-5 ml-3">
                    <p class="text-xl">날짜</p>
                    <input type="datetime-local" v-model="schedule" class=" text-medium w-2/4 rounded-md border-2 border-yellow-400">
                </div>
                <div class="mt-5 ml-3">
                    <p class="text-xl">내용</p>
                    <textarea rows="5" type="text" v-model="content" class=" text-medium w-3/4 rounded-md border-2 border-yellow-400"></textarea>
                    <p v-if="content.length>100" class="text-yellow-600">100자 이내로 입력해주세요</p>
                </div>
                <div class="mt-7 ml-3">
                    <button @click="modifyCalendar" :disabled="btnDisabled">수정하기</button>
                    <button @click="deleteCalendar" class="ml-5">삭제</button>
                    <button class="ml-5" @click="modifyState=false">취소</button>

                </div>
            </div>
        </div>
  </div>
</template>

<script>
import img from '@/assets/profile.png'
import store from '@/store/index.js'
import { getSchedule } from '@/api/schedule.js'
import { putSchedule } from '@/api/schedule.js'
// import axios from 'axios'
// const baseURL = process.env.VUE_APP_SERVER_URL
// import {timestamp} from '@/utils/time.js'
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
            writer:{
                id:'',
                name:'',
                pictureUrl:'',
            },
            userId:store.state.userId
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
            this.writer.id = res.data.writer.id
            this.writer.name = res.data.writer.name
            if(res.data.writer.pictureUrl){
            this.writer.pictureUrl = res.data.writer.pictureUrl
            }
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        modifyCalendar: function(){
            const calendarId = store.state.calendarId
            const teamId = store.state.teamId
            // axios({
            //     method:'post',
            //     url:`${baseURL}/teams/calendar/${calendarId}`,
            //     headers: this.getToken,
            //     data: {
            //         teamId:teamId,
            //         writerId:this.writerId,
            //         content:this.content,
            //         createDate:this.createDate,
            //         name:this.name
            //     }
            // })
            putSchedule(calendarId,teamId,this.writer.id,this.name,this.content,this.schedule)
            .then((res)=>{
                console.log(res.data)
                Swal.fire('일정이 수정되었습니다.')
                this.modifyState = false
                this.$router.go()
                // this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
        },
        deleteCalendar: function(){
            Swal.fire({
             title: '일정을 삭제하시겠습니까?',
              text: "삭제한 일정은 복구할 수 없습니다.",
               icon: 'warning',
                showCancelButton: true,
                 confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                   confirmButtonText: '네',
                    cancelButtonText: '아니오'
             })
            .then((result) => {
                if(result.isConfirmed){
                const calendarId = store.state.calendarId
                deleteSchedule(calendarId)
                .then((res)=>{
                    console.log(res.data)
                    Swal.fire('일정이 삭제되었습니다.')
                    this.$router.push('/team/calendar')
                }).catch((err)=>{
                    console.log(err)
                })
            }})
        },
        clickUser: function(){
            this.$store.commit('setTempUserId', this.writer.id)
            this.$router.push('/user')
        },
        imgError:function(e){
            e.target.src = img
            console.log(img)
            console.log(e.target.src)
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
            if(!this.modifyName || (this.modifyContent.length>100) || (this.modifyContent.length ==0) || !this.modifyDate ){
                return true
            }
            return false
        },
    }
}
</script>

<style>

</style>