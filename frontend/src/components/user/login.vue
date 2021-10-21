<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-4xl text-yellow-500 font-bold">S-riends</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="col-start-1 col-span-3 mr-2">
                  <p class="text-2xl font-bold flex justify-center">우리동네 스포츠동호회 찾기</p>
                  <img src="@/assets/sideImg.png" width="450"  alt="">
              </div>
              <div class="col-start-4 col-span-4 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form action="" class="">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">Log In</p>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">E-mail</p>
                          <input type="text" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">Password</p>
                          <input type="password" class="text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                  </form>
                  <div class="flex justify-center p-2 mt-10">
                    <button class="border-solid border-2 border-yellow-500 rounded-md">Log In</button>
                  </div>
                  <div class="flex justify-center p-2 ">
                      <button><p>비밀번호 찾기</p></button>
                  </div>
                  <div class="flex justify-center p-2">
                    <button class="border-none border-yellow-500">아직 회원이 아니신가요?</button>
                  </div>
              </div>
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