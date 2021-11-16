<template>
<div>
    <div v-if="!checked" class="grid grid-cols-6 bg-yellow-400 border-b-2 border-yellow-300 h-12 item-center">
        <div @click="clickNotice" class="col-start-1 col-span-6">
            {{content}}
        </div>
        <div class="col-start-1 col-span-3 cursor-default"></div>
        <div class=" col-start-4 col-span-3 text-gray-600">
            {{getTime}}<button class="ml-3" @click="readNotice"><font-awesome-icon icon="check-circle"/></button>
            <button class="ml-3" @click="clickDelete"><font-awesome-icon icon="trash-alt"/></button>
        </div>
    </div>
    <div v-if="checked" class="grid grid-cols-6 bg-yellow-100 border-b-2 border-yellow-300 h-12 item-center">
        <div @click="clickNotice" class="col-start-1 col-span-6">
            {{content}}
        </div>
        <div class="col-start-1 col-span-3 cursor-default"></div>
        <div class=" col-start-4 col-span-3 text-gray-600">
            {{getTime}}<button class="ml-3" @click="readNotice"><font-awesome-icon icon="check-circle"/></button>
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
            this.$store.commit('setMatchingId', this.postId)
            if(this.type=='MATCH'){
                this.$router.push('/matchingDetail')
            }
        },
        readNotice:function(){
            putNotice(this.notificationId)
            .then((res)=>{
                console.log(res.data)
                this.isChecked=true
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
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