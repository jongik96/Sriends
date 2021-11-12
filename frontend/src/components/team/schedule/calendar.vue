<template>
  <div>
      <vc-date-picker is-expanded
          :value="null" title-position="center" color="orange" 
          :min-date="new Date()" :attributes="attributes" 
          :columns="$screens({ default: 1, xl: 1 })" 
          :rows="$screens({ default: 1, xl: 1 })"
        />
  </div>
</template>

<script>
import {getListMonth} from '@/api/schedule.js'
import store from '@/store/index.js'
export default {
  data(){
    const todos = [];
    return {
      incId: todos.length,
      todos,
      teamId: store.state.teamId
    }
  },
  created(){
    const year = new Date().getFullYear()
        console.log(year)
        const month = new Date().getMonth()+1
        console.log(month)
        getListMonth(this.teamId,year,month)
        .then((res)=>{
            console.log(res)
            this.todos = res.data
        }).catch((err)=>{
            console.log(err)
        })
  },
  computed: {
    attributes() {
      return [
        // Attributes for todos
        ...this.todos.map(todo => ({
          dates: todo.schedule,
          dot: {
            color: todo.color,
            class: todo.isComplete ? 'opacity-75' : '',
          },
          popover: {
            label: todo.name,
            visibility: 'focus'
          },
          customData: todo,
        })),
      ];
    },
  },
}
</script>

<style>

</style>