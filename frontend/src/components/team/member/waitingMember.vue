<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="grid place-content-end">
                <router-link to="/team/memberList">
                <p class="bg-yellow-400 px-2 py-1 text-black font-semibold text-sm rounded">돌아가기</p>
                </router-link>
            </div>
            <waitingMemberItem v-for="item in member" :key="item.id"
                :createDate="item.createDate"
                :name="item.name"
                :userId="item.userId"
                :description="item.description"
            >
                <!-- <div class="grid grid-cols-7">
                    <div class="col-start-1 col-span-1">
                        <img src='@/assets/profiledefault.jpg' class="rounded-md h-10 w-10 md:h-20 md:w-20" alt="">
                    </div>
                    <div class="ml-1 md:ml-0 col-start-2 col-span-2 grid text-sm md:text-base sm:place-items-center">
                        {{item.name}}
                        <br>
                        {{item.level}}
                    </div>
                    <div class="col-start-3 col-span-1 grid text-sm md:text-base sm:place-items-center">
                        {{item.createDate}}
                    </div>
                    <div class="invisible sm:visible col-start-4 col-span-2 text-sm md:text-base md:grid sm:place-items-center">
                        {{item.phone}}
                    </div>
                    <div class="col-start-6 col-span-1 text-sm md:text-base grid justify-center">
                        <button><font-awesome-icon icon="comments"/></button>
                    </div>
                    <div class="col-start-7 col-span-1 text-xs sm:text-sm md:text-base flex justify-center">
                        <button @click="open = true">신청서</button>
                    </div>
                </div> -->
            </waitingMemberItem>
            <!-- <vue-modaltor :visible="open" @hideModal="hideModal">
                <template #body>
                    <p>
                    “Never forget what you are, for surely the world will not. Make it
                    your strength. Then it can never be your weakness. Armour yourself in
                    it, and it will never be used to hurt you. ” ― George R.R. Martin, A
                    Game of Thrones.
                    </p>
                </template>
            </vue-modaltor> -->
        </div>
  </div>
</template>

<script>
import waitingMemberItem from '@/components/team/member/waitingMemberItem.vue'
import { lookupApplyList } from '@/api/team.js'
import store from '@/store/index.js'

export default {
    components:{
        waitingMemberItem
    },
    data(){
        return{
            open: false,
            member:[]
        }
    },
    created: function(){
        const teamId = store.state.teamId;
        lookupApplyList(teamId)
        .then((res)=>{
            console.log(res.data)
            this.member = res.data
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods: {
        hideModal() {
        this.open = false;
        },
    },
}
</script>

<style>

</style>