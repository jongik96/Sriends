<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-4xl text-yellow-500 font-bold">S-riends</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="col-start-2 col-span-4 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form action="" class="">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">Sign Up</p>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">E-mail</p>
                          <input type="text" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">Password</p>
                          <input type="password" class="text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">이름</p>
                          <input type="text" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">생년월일</p>
                          <input type="date" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">성별</p>
                          <input type="text" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">연락처</p>
                          <input type="text" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">거주지</p>
                          <button>선택하기</button>
                          <input type="text" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                  </form>
                  <div class="flex justify-center p-2 mt-10">
                    <button class="border-solid border-2 border-yellow-500 rounded-md">가입하기</button>
                  </div>
                  <div class="flex justify-center p-2 ">
                      <button><p>이미 계정이 있습니다</p></button>
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