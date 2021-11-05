<template>
  <div class="bg-white shadow-md grid grid-cols-6">
    <nav class="col-start-2 col-span-4 flex items-center justify-between flex-wrap py-6">
      <div class="flex items-center flex-shrink-0 text-yellow-500 mr-6">
        
        <a href="/main"><button class="font-semibold text-3xl">S-riends</button></a>
      </div>
      <div class=" sm:hidden">
        <a @mouseover="smallList = true" @click="clickInfo" @mouseleave="smallList = false" class="flex items-center  border rounded text-teal-200 border-teal-400 ">
          <svg class="fill-current h-5 w-5" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/></svg>
       
          <transition name="fade">
              
            <ul v-if="smallList" @click="smallList = false" class="fixed border-2 mr-10 mt-32 w-60 bg-white">
              <li>
                <router-link to="/teamList"><p class="hover:bg-yellow-500">스렌즈 찾기</p></router-link>
              </li>
              <li>
                <router-link to="/matchingList"><p class="hover:bg-yellow-500">경기/스렌드 찾기</p></router-link>
              </li>
              <li>
                <router-link to="/main"><p class="hover:bg-yellow-500">My S-riends</p></router-link>
              </li>
              <!-- <li>
                <router-link to="/modify"><p class="hover:bg-yellow-500">정보 수정</p></router-link>
              </li> -->
              <li>
               <button class="hover:bg-yellow-500">로그아웃</button>
              </li>
            </ul>
          
          </transition>
        </a>
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
          <button @click="logout()">
            <p class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-23 mr-4">
              로그아웃
            </p>
          </button>
          <!-- <a href="#responsive-header" class="block mt-4 sm:inline-block hover:bg-yellow-500 rounded-md lg:mt-0 text-teal-200 h-10 w-20">
            회원관리
          </a> -->
          <!-- <a @mouseover="listOne = true" @click="clickInfo" @mouseleave="listOne = false" class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-20">
            My Info
          <transition name="fade">
            
            <ul v-if="listOne" @click="listOne = false" class="fixed border-2 w-20">
              
              <li><button @click="logout" class="text-center bg-white text-md p-1">로그아웃</button></li>
              <li><router-link to="/modify"><p class="text-center bg-white text-md p-1">정보수정</p></router-link></li>
            </ul>
          
          </transition></a> -->
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  data(){
    return{
      listOne: false,
      smallList: false,
    }
  },
  methods:{
    clickInfo: function(){
      if(this.listOne == false){
        this.listOne == true;
      }else{
        this.listOne == false;
      }
    },
    logout: function(){
      localStorage.removeItem('vuex')
      this.$router.push('/')
    }
  }

}
</script>

<style>

</style>