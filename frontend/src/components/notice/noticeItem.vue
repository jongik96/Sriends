<template>
<div>
    <div v-if="!checked" class="grid grid-cols-6 bg-yellow-400 border-b-2 border-yellow-300 h-20  md:h-12 item-center">
        <div @click="clickNotice" class="col-start-1 col-span-5 md:col-start-1 md:col-span-6">
            {{content}}
        </div>
        <div class="col-start-1 col-span-6 md:col-start-1 md:col-span-4 cursor-default"></div>
        <div class="col-start-1 col-span-6 md:col-start-5 md:col-span-2 text-gray-600">
            {{getTime}}
            <button class="ml-3" @click="clickDelete"><font-awesome-icon icon="trash-alt"/></button>
        </div>
    </div>
    <div v-if="checked" class="grid grid-cols-6 bg-yellow-100 border-b-2 border-yellow-300 h-20 md:h-12 item-center">
        <div @click="clickNotice" class="col-start-1 col-span-5 md:col-start-1 md:col-span-6">
            {{content}}
        </div>
        <div class="col-start-1 col-span-6 md:col-start-1 md:col-span-4 cursor-default"></div>
        <div class="col-start-1 col-span-6 md:col-start-5 md:col-span-2 text-gray-600">
            {{getTime}}
            <button class="ml-3" @click="clickDelete"><font-awesome-icon icon="trash-alt"/></button>
        </div>
    </div>
</div>
</template>

<script>
import {getDate} from '@/utils/date.js'
import { putNotice } from '@/api/auth.js'
import { deleteNotice } from '@/api/auth.js'
export default {
    props:{
        content:[String],
        type:[String],
        postId:[String,Number],
        createAt:[String,Date],
        checked:[Boolean,String],
        notificationId:[String,Number]

    },
    data(){
        return{
            isDelete:false,
            isChecked:'',
        }
    },
    computed:{
            getTime(){
            const time = getDate(this.createAt)
            return time
        }
    },
    methods:{
        clickNotice: function(){
            putNotice(this.notificationId)
            .then((res)=>{
                console.log(res.data)
                this.isChecked=true
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
            if(this.type=='MATCH'){
                this.$store.commit('setMatchingId', this.postId)
                this.$router.push('/matchingDetail')
            }else if(this.type=='TEAMJOIN'){
                this.$store.commit('setTeamId',this.postId)
                this.$router.push('/team')
            }else if(this.type=='TEAMBOARD'){
                this.$store.commit('setBoardId',this.postId)
                this.$router.push('/team/articleDetail')
            }else if(this.type=='COMMENT'){
                this.$store.commit('setBoardId',this.postId)
                this.$router.push('/team/articleDetail')
            }
        },
        readNotice:function(){

        },
        clickDelete:function(){
            deleteNotice(this.notificationId)
            .then((res)=>{
                console.log(res.data)
                this.isDelete=false
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style>

</style>