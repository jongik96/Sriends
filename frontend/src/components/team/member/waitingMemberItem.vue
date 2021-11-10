<template>
  <div class="shadow-md rounded-xl p-4 mt-5 mx-6 mb-4 min-w-300">
                <div class="grid grid-cols-7">
                    <div class="col-start-1 col-span-1">
                        <img :src=pictureUrl class="h-10 w-10 rounded-md md:h-20 md:w-20" alt="">
                    </div>
                    <div class="ml-1 md:ml-0 col-start-2 col-span-1 grid text-sm md:text-base sm:place-items-center">
                         {{name}}
                        <br>
                        <!-- {{item.level}} -->
                    </div>
                    <div class="invisible sm:visible col-start-3 col-span-3 text-sm md:text-base md:grid sm:place-items-center">
                        {{calcDate}}
                    </div>
                    <div class="col-start-6 col-span-1 text-sm md:text-base grid justify-center">
                        <button><font-awesome-icon icon="comments"/></button>
                    </div>
                    <div class="col-start-7 col-span-1 text-xs sm:text-sm md:text-base flex justify-center">
                        <button @click="open=true"><font-awesome-icon icon="file-alt"/></button>
                        <button class="hidden md:visible" @click="open = true">신청서</button>
                    </div>
                </div>
                <div v-if="open" class="grid grid-cols-7 rounded-xl mt-2 border-2 border-yellow-500">
                    <div class="md:col-start-3 md:col-span-3 border-b border-yellow-500 col-start-1 col-span-6">
                        <button class="ml-2" @click="open=false">숨기기</button>
                        <button @click="okBtn" class="float-right">가입 승인</button>
                    </div>
                    <div class="col-start-1 col-span-6 break-words  md:col-start-3 md:col-span-3 border-b mt-3">
                        <p>{{description}}</p>
                    </div>
                </div>
            </div>
</template>

<script>
import store from '@/store/index.js'
// import {getDate} from '@/utils/date.js'
import { permitTeam } from '@/api/team.js'
import { getDate } from '@/utils/date.js'
export default {
    props:{
        userId: [String,Number],
        createDate: [String, Date],
        name: [String],
        description:[String],
        pictureUrl:[String]
    },
    data(){
        return{
            open:false,
            data:'',
        }
    },
    methods:{
        okBtn:function(){
            const teamId = store.state.teamId
            permitTeam(teamId, this.userId)
            .then((res)=>{
                console.log(res)
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
            })
        }
    },
    computed:{
        calcDate:function(){
            return getDate(this.createDate)
        }
    }

}
</script>

<style>

</style>