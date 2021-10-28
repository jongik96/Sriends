<template>
  <div class="">
      <div class="grid place-content-end">
          <router-link to="/team/makeSchedule">
          <p class="bg-yellow-400 px-2 py-1 text-black font-semibold text-sm rounded">일정 생성하기</p>
          </router-link>
      </div>
      <div class="border-b rounded-md py-3">
            <div class="grid grid-cols-6">
                <div class="grid col-start-1 col-span-2 place-items-center">
                    <p>
                        일정이름
                    </p>
                </div>
                <div class="grid col-start-3 col-span-3 place-items-center ">
                    일정 내용
                </div>
                <div class="grid col-start-6 col-span-1 place-items-center">
                    일시
                </div>
            </div>
      </div>
      <div v-for="item in todos" :key="item.id" class="border-b rounded-md py-1">
            <div class="grid grid-cols-6">
                <div class="grid col-start-1 col-span-2 place-items-center">
                    <p>
                        {{item.title}}
                    </p>
                </div>
                <div class="grid col-start-3 col-span-3 place-items-center ">
                    {{item.content}}
                </div>
                <div class="grid col-start-6 col-span-1 place-items-center">
                    {{item.date}}
                </div>
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



            ]
        }
    }
}
</script>

<style>

</style>