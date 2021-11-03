<template>
  <div>
      <header class="flex flex-wrap items-center p-4 md:py-8">

      <div class="md:w-3/12 md:ml-16">
        <!-- profile image -->
        <img class="w-30 h-30 md:w-50 md:h-50 object-cover rounded-xl
                     border-2 border-yellow-500 p-1" src="@/assets/profiledefault.jpg" alt="profile">
      </div>
      <!-- profile meta -->
      <div class="w-7/12 md:w-7/12 = md:ml-5 ml-4 2xl:ml-20">
        <div class="md:flex md:flex-wrap md:items-center mb-4">
          <h2 class="text-3xl font-semibold inline-block md:mr-2 mb-2 sm:mb-0">
            {{this.name}}
          </h2>

          <!-- badge -->
          <span class="inline-block fas fa-certificate fa-lg text-blue-500 
                               relative mr-6 text-xl transform -translate-y-2" aria-hidden="true">
            <i class="fas fa-check text-white text-xs absolute inset-x-0
                               ml-1 mt-px"></i>
          </span>

          <!-- modify button -->
          <router-link to='/modify'>
            <p class="bg-yellow-500 px-2 py-1 
                        text-white font-semibold text-sm rounded block text-center 
                        sm:inline-block">정보수정</p>
          </router-link>
        </div>

        <!-- post, following, followers list for medium screens -->
        <ul class="hidden md:flex space-x-8 mb-4">
          <li>
            성별
            <span class="font-semibold">{{this.gender}}</span>
          </li>
          <li>
            지역
            <span class="font-semibold">{{this.city}}</span>
          </li>
        </ul>

        
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
import {getProfileInfo} from '@/api/index.js'
export default {
  data(){
    return{
      id: '',
      email : '',
      name : '',
      birth : '',
      phone : '',
      gender : '',
      city : '',
      age :'',
      pictureDownloadUri : '',
      teams : [
        {    
          id : '',
          pictureDownloadUri: '',
          name : ''
        },
        {    
          id : '',
          pictureDownloadUri: '',
          name : ''
        },
      ],
    }
  },
  created: function(){
    getProfileInfo()
    .then((res)=>{
      console.log(res.data)
      this.id = res.data.id
      this.name = res.data.name
      this.birth = res.data.birth
      this.phone = res.data.phone
      this.gender = res.data.gender
      this.pictureDownloadUri = res.data.pictureDownloadUri
      this.city = res.data.city
    }).catch((err)=>{
      console.log(err)
    })
  },



  
}
</script>

<style>

</style>