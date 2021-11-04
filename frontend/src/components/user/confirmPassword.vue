<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-4xl text-yellow-500 font-bold">S-riends</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-2 md:col-span-4 col-start-1 col-span-6 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">Password</p>
                          <input id="password" type="text" class="text-xl w-3/4 rounded-md border-2 border-yellow-400 mt-2"/>
                          <p class="mt-3">
                              <span class="text-yellow-600">Password 입력해주세요.</span>
                          </p>
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <!-- <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">Log In</button> -->
                        <button type="submit"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">
                            확인
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
     form:{
       password:'',
     }
   }
 }
}
</script>

<style>

</style>