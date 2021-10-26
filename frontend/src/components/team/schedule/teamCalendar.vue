<template>
  <div class="grid grid-cols-6">
<!-- 
      <div class="col-start-2 col-span-3 border-b rounded-xl px-4 py-2 bg-yellow-500 overflow-y-auto h-3/5 mt-10">
        <todoList></todoList>
      </div>
      <div class="col-start-5 col-span-1 border-b px-4 py-2 bg-white mt-10">
        <calendar></calendar>
      </div>
       -->
      <div class="col-start-3 col-span-2 self-center xl:self-auto  xl:col-start-2 xl:col-span-1 xl:-ml-10 border-b bg-white mt-10">
        <calendar></calendar>
      </div>
      <div class="col-start-2 col-span-4  xl:ml-0 xl:col-start-3 xl:col-span-3 border-b rounded-xl bg-yellow-500 overflow-y-auto h-3/5 mt-10">
        <todoList></todoList>
      </div>
  </div>
</template>

<script>
import calendar from '@/components/team/schedule/calendar.vue'
import todoList from '@/components/team/schedule/teamTodoList.vue'
export default {
  components: {
    calendar,
    todoList
  }
}
</script>

<style>

</style>