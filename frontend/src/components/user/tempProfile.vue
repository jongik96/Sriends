<template>
  <div>
      <header class="flex flex-wrap items-center p-4 md:py-8">

      <div class="md:w-3/12 md:ml-16">
        <!-- profile image -->
        <img  class="w-30 h-30 md:w-50 md:h-72  object-cover rounded-xl
                     border-2 border-yellow-500 p-1" :src=pictureUrl alt="profile">
      </div>
      <!-- profile meta -->
      <div class="w-7/12 md:w-7/12 = md:ml-5 ml-4 2xl:ml-20">
        <div class="md:flex md:flex-wrap md:items-center mb-4">
          <div class="text-3xl font-semibold inline-block md:mr-2 mb-2 sm:mb-0">
            {{this.name}}
          </div>
         
        </div>
        <div>
        <!-- post, following, followers list for medium screens -->
        <ul class="md:flex text-xl">
          <li class="">
            성별 :
            <span class="text-medium">{{this.gender}}</span>
          </li>
          <li class="md:ml-5">
            지역 :
            <span class="">{{this.city}}</span>
          </li>
          <li class="md:ml-5">
            나이 :
            <span class="">{{this.age}}</span>
          </li>
        </ul>
        <ul class="md:flex text-xl">
          <li v-if="phone!=null">
            전화번호 :
            <span class="">{{this.phone}}</span>
          </li>
        </ul>
        <ul v-if="sports.length!=0" class="md:flex text-xl">
          <li class="">
            관심종목 :
            <span v-for="item in sports" :key="item.id" class=""> {{item.interest}}</span>
          </li>
          
        </ul>
        </div>
        
        <!-- <div class="hidden md:block">
          <h1 class="font-semibold text-lg">내 이름은 박범진.</h1>
          <span>Travel, Nature and Music</span>
          <p>Lorem ipsum dolor sit amet consectetur</p>
        </div> -->

      </div>

      
      <!-- <div class="md:hidden text-sm my-2">
        <h1 class="font-semibold">내 이름은 박범진</h1>
        <span>Travel, Nature and Music</span>
        <p>Lorem ipsum dolor sit amet consectetur</p>
      </div> -->

    </header>
  </div>
</template>

<script>
import {getTempProfileInfo} from '@/api/auth.js'
import { getInterest } from '@/api/auth.js'
import store from '@/store/index.js'
export default {
  data(){
    return{
      selectDo:'',
      modifyState: false,
      // 수정할 항목들

      // 수정 전 받아오는 항목들
      id: '',
      email : '',
      name : '',
      birth : '',
      phone : '',
      gender : '',
      city : '',
      age :'',
      pictureUrl : '',
      sports:[],
    }
  },
  created: function(){
    const userId = store.state.tempUserId
    getTempProfileInfo(userId)
    .then((res)=>{
      console.log(res.data)
      this.id = res.data.id
      this.name = res.data.name
      this.phone = res.data.phone
      this.gender = res.data.gender
      this.pictureUrl = res.data.pictureUrl
      this.city = res.data.city
      console.log(this.pictureUrl)
      // string 형식 date로 바꿔서 나이계산
      this.birth = new Date(res.data.birth)
      let today = new Date();
      this.age = today.getFullYear() - new Date(res.data.birth).getFullYear()+1;
      console.log(new Date(res.data.birth))
      // localStorage.setItem('userid',res.data.id)
      this.$store.commit("setTempUserId", res.data.id)
    }).catch((err)=>{
      console.log(err)
    }),
    getInterest()
    .then((res)=>{
      console.log(res)  
      this.sports = res.data    
    }).catch((err)=>{
      console.log(err)
    })
  },
  methods:{
  },
  computed:{

    // calcAge(){
    //   let today = new Date();
    //   console.log(this.birth)
    //   console.log(today)
    //   let age = today.getFullYear() - birth.getFullYear()+1;
    //   return age
    // },
  }



  
}
</script>

<style>

</style>