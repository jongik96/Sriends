<template>
  <div class="">
      <div class="grid place-content-end">
          <router-link to="/team/makeSchedule">
          <p class="bg-yellow-400 px-2 py-1 text-black font-semibold text-sm rounded">일정 생성하기</p>
          </router-link>
      </div>
      <div class="border-b rounded-md py-3">
            <div class="grid grid-cols-6">
                <div class="grid col-start-1 col-span-1 place-items-center">
                    <p>
                        일정이름
                    </p>
                </div>
                <div class="grid col-start-2 col-span-2 place-items-center ">
                    일정 내용
                </div>
                <div class="grid col-start-4 col-span-1 place-items-center ">
                    작성자
                </div>
                <div class="grid col-start-5 col-span-2 place-items-center">
                    일시
                </div>
            </div>
      </div>
      <todoListItem v-for="item in todos" :key="item.id" class="border-b rounded-md py-1"
        :calendarId="item.id"
        :writerId="item.writerId"
      >
            <!-- <div class="grid grid-cols-6">
                <div class="grid col-start-1 col-span-2 place-items-center">
                    <p>
                        
                    </p>
                </div>
                <div class="grid col-start-3 col-span-3 place-items-center ">
                   
                </div>
                <div class="grid col-start-6 col-span-1 place-items-center">
                    
                </div>
            </div> -->
      </todoListItem>
  </div>
</template>

<script>
import todoListItem from '@/components/team/schedule/teamTodoListItem.vue'
import { getListMonth } from '@/api/schedule.js'
import store from '@/store/index.js'
export default {
    components:{
        todoListItem
    },
    data(){
        return{
            teamId: store.state.teamId,
            todos: []
        }
    },
    created(){
        const year = new Date().getFullYear()
        const month = new Date().getMonth()+1
        getListMonth(this.teamId,year,month)
        .then((res)=>{
            this.todos = res.data
        }).catch((err)=>{
            console.log(err)
        })
    }
}
</script>

<style>

</style>