<template>
  <div class="bg-white shadow-md grid grid-cols-6">
    <nav class="col-start-2 col-span-4 flex items-center justify-between flex-wrap py-6">
      <div class="flex items-center flex-shrink-0 text-yellow-500 mr-6">
        
        <a href="/main"><button class="font-semibold text-3xl">S-riends</button></a>
      </div>
      <div class=" sm:hidden">
        <button class="flex items-center px-3 py-2 border rounded text-teal-200 border-teal-400 ">
          <svg class="fill-current h-3 w-3" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>Menu</title><path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/></svg>
        </button>
      </div>
      <div class="hidden sm:flex sm:items-center sm:w-auto">
        <div class="text-md">
          <router-link to="/teamList">
            <p class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200 w-22 mr-4">
            스렌즈 찾기
            </p>
          </router-link>
          <router-link to="/matchingList">
            <p class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-32 mr-4">
              경기/스렌드 찾기
            </p>
          </router-link>
          <router-link to="/main">
            <p class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-23 mr-4">
              My S-riends
            </p>
          </router-link>
          <!-- <a href="#responsive-header" class="block mt-4 sm:inline-block hover:bg-yellow-500 rounded-md lg:mt-0 text-teal-200 h-10 w-20">
            회원관리
          </a> -->
          <a @mouseover="listOne = true" @mouseleave="listOne = false" class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-20">
            My Info
          <transition name="fade">
            
            <ul v-if="listOne" @click="listOne = false" class="fixed border-2 w-20">
              
              <li><a href=""><button class="text-center bg-white text-md p-1">로그아웃</button></a></li>
              <li><a href="/modify"><button class="text-center bg-white text-md p-1">정보수정</button></a></li>
            </ul>
          
          </transition></a>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  data(){
    return{
      listOne: false
    }
  }

}
</script>

<style>

</style>