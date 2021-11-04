<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-3xl text-yellow-500 font-bold">가입 요청</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-2 md:col-span-4 col-start-1 col-span-6 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form>
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">간단한 소개글을 적어주세요.</p>
                          <textarea v-model="joinIntro" id="joinIntro" rows=5 type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                        <button type="submit"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">
                            취소
                        </button>
                        <button type="submit"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10 ml-3">
                            요청
                        </button>
                      </div>
                  </form>

              </div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
 data(){
   return{
       joinIntro:'',
   }
 }
}
</script>

<style>

</style>