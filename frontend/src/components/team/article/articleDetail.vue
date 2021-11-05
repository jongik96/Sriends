<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div>
                <div class="border-b">
                    <h2 class="text-2xl font-semibold">공지사항입니다</h2>
                    <div class="float-right">
                        <button class="mr-3">수정</button>
                        <button class="mr-3">삭제</button>
                    </div>
                </div>
                <p class="text-justify leading-tight text-gray-800 h-48 mt-10">Lorem ipsum dolor sit amet consectetur adipisicing elit. 
                    Aspernatur nulla nostrum illum facere, non, asperiores, itaque necessitatibus laboriosam quae voluptates harum. 
                    Similique velit cumque iusto adipisci temporibus possimus eligendi delectus!<p>
                <div class="mt-2">
                    <span class="left">Posted On: 2021/11/20</span>
                    <span class="float-right flex">By: <p class="text-purple-500">박범진</p></span>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            name: '',
            title: '',
            content: '',
            createDate:'',
        }
    }
}
</script>

<style>

</style>