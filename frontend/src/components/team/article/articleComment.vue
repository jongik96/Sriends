<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <p class="text-2xl">댓글</p>
            <div>
                <textarea id="comment" rows=3 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
            </div>
            <div class="flex justify-end">
                <button class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            댓글 작성하기
                </button>
            </div>
        </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>