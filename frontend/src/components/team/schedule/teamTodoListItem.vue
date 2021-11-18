<template>
  <div @click="clickItem" class="grid grid-cols-6 cursor-pointer">
                <div class="grid col-start-1 col-span-1 place-items-center">
                    <p>
                        {{this.name}}
                    </p>
                </div>
                <div class="grid col-start-2 truncate col-span-2 place-items-center ">
                        {{this.content}}
                </div>
                <div class="grid col-start-4 col-span-1 place-items-center ">
                        {{this.writer.name}}
                </div>
                <div class="grid col-start-5 col-span-2 place-items-center">
                        {{this.schedule}}
                </div>
            </div>
</template>

<script>
import { getSchedule } from '@/api/schedule.js'
// import {timestamp} from '@/utils/time.js'
export default {
    props:{
        calendarId: [String, Number],
    },
    data(){
        return{
            content:'',
            id:'',
            name:'',
            schedule:'',
            writer:{
                id:'',
                name:'',

            }
            
        }
    },
    mounted(){
        getSchedule(this.calendarId)
        .then((res)=>{
            console.log(res)
            this.content = res.data.content
            this.id = res.data.id
            this.name = res.data.name
            this.schedule = res.data.schedule
            this.writer.id = res.data.writer.id
            this.writer.name = res.data.writer.name
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        clickItem:function(){
            this.$store.commit('setCalendarId',this.calendarId)
            this.$router.push('/team/calendar/detail')
        }
    }
}
</script>

<style>

</style>