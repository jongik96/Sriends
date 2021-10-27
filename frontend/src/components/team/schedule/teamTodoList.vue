<template>
  <div class="">
      <div v-for="item in todos" :key="item.id" class="border-b rounded-md">
          <div>
              <p>
                {{item.title}}
              </p>
          </div>
            <div>
                {{item.content}}
            </div>
            <div>
                {{item.date}}
            </div>

      </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            todos: [
                {
                    id : 1,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-10-29',
                },
                {
                    id : 2,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-10-30',
                },
                {
                    id : 3,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-10-31',
                },
                {
                    id : 4,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-1',
                },
                {
                    id : 5,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-2',
                },
                {
                    id : 6,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-3',
                },
                {
                    id : 7,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-4',
                },
                {
                    id : 8,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-5',
                },
                {
                    id : 9,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-5',
                },
                {
                    id : 10,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-5',
                },
                {
                    id : 11,
                    title : '축구하자',
                    content : '구미인동풋살장에서 ㄱㄱ',
                    date : '2021-11-5',
                },



            ]
        }
    }
}
</script>

<style>

</style>