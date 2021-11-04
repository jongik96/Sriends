<template>
  <div>
      <vc-date-picker
          :value="null" title-position="left" color="orange" 
          :min-date="new Date()" :attributes="attributes" 
          :columns="$screens({ default: 1, xl: 1 })" 
          :rows="$screens({ default: 1, xl: 2 })"
        />
  </div>
</template>

<script>
export default {
  data(){
    const todos = [
      {
        description: 'Take Noah to basketball practice.',
        isComplete: false,
        dates: { weekdays: 6 }, // Every Friday
        color: 'red',
      },
    ];
    return {
      todos,
      
    }
  },
  computed: {
    attributes() {
      return [
        // Attributes for todos
        ...this.todos.map(todo => ({
          dates: todo.dates,
          dot: {
            color: todo.color,
            class: todo.isComplete ? 'opacity-75' : '',
          },
          popover: {
            label: todo.description,
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