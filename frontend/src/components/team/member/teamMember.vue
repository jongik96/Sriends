<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="grid place-content-end">
                <router-link to="/team/waitingMemberList">
                <p class="bg-yellow-400 px-2 py-1 text-black font-semibold text-sm rounded">대기중인 요청</p>
                </router-link>
            </div>
            <div v-for="item in member" :key="item.id" class="shadow-md rounded-xl p-4 mt-5 mx-6 mb-4 min-w-300">
                <div class="grid grid-cols-7">
                    <div class="col-start-1 col-span-1">
                        <img src='@/assets/profiledefault.jpg' class="h-10 w-10 rounded-md md:h-20 md:w-20" alt="">
                    </div>
                    <div class="ml-1 md:ml-0 col-start-2 col-span-2 grid sm:place-items-center">
                        {{item.name}}
                        <br>
                        {{item.level}}
                    </div>
                    <!-- <div class=" sm:visible col-start-3 col-span-1 grid sm:place-items-center">
                        {{item.level}}
                    </div> -->
                    <div class="invisible sm:visible sm:col-start-4 sm:col-span-2 grid sm:place-items-center">
                        {{item.phone}}
                    </div>

                    <div class="col-start-6 col-span-1 flex justify-center">
                        <button><font-awesome-icon icon="comments"/></button>
                    </div>
                    <div class="col-start-7 col-span-1 flex justify-center">
                        <button><font-awesome-icon icon="ban"/></button>
                    </div>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import { getTeamMemberList } from '@/api/team.js'
import store from '@/store/index.js'
export default {
    data(){
        return{
            applyList:[

            ],
            member:[
                { id: 1,
                    name: "하지훈",
                    phone: "01012341234",
                    level: "대표",
                },
                { id: 2,
                    name: "박범진",
                    phone: "01012341234",
                    level: "매니저",
                },
                { id: 3,
                    name: "권현지",
                    phone: "01012341234",
                    level: "회원",
                },
                { id: 4,
                    name: "권준엽",
                    phone: "01012341234",
                    level: "회원",
                },
                { id: 5,
                    name: "박종익",
                    phone: "01012341234",
                    level: "회원",
                },
                { id: 6,
                    name: "ㅎㅇㅎㅇ",
                    phone: "01012341234",
                    level: "회원",
                },
            ]
        }
    },
    created: function(){
        const teamId = store.state.teamId
        getTeamMemberList(teamId)
        .then((res)=>{
            console.log(res)
        }).catch((err)=>{
            console.log(err)
        })
    }
}
</script>

<style>

</style>