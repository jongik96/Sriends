<template>
  <div>
      <section class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-4 mt-12 mb-12">
        <article>
            <h2 class="text-2xl font-extrabold text-gray-900">종목 선택</h2>
            <section class="mt-6 grid grid-cols-2 lg:grid-cols-4 gap-x-6 gap-y-8">
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        
                        <img src="@/assets/football.jpg"
                            class="w-full h-full object-center object-cover">
                        
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">
                                축구/풋살
                            </p>
                            <input class="h-5 w-5 ml-3" type="checkbox" value="축구/풋살" v-model="sportCategory">
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        
                        <img src="@/assets/basketball.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                       
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">
                                농구
                            </p>
                            <input class="h-5 w-5 ml-3" type="checkbox" value="농구" v-model="sportCategory">
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                       
                        <img src="@/assets/baseball.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                       
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                야구
                            </p>
                            <input class="h-5 w-5 ml-3" type="checkbox" value="야구" v-model="sportCategory">      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <img src="@/assets/volleyball.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                배구
                            </p>
                            <input class="h-5 w-5 ml-3" type="checkbox" value="배구" v-model="sportCategory">      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                       
                        <img src="@/assets/badminton.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                       
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                배드민턴
                            </p>
                            <input class="h-5 w-5 ml-3" type="checkbox" value="배드민턴" v-model="sportCategory">      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <img src="@/assets/tennis.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                테니스
                            </p>    
                            <input class="h-5 w-5 ml-3" type="checkbox" value="테니스" v-model="sportCategory">  
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">

                        <img src="@/assets/pingpong.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">

                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                탁구
                            </p>   
                            <input class="h-5 w-5 ml-3" type="checkbox" value="탁구" v-model="sportCategory">   
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">

                        <img src="@/assets/guitar.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">

                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <p class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                기타
                            </p>     
                            <input class="h-5 w-5 ml-3" type="checkbox" value="기타" v-model="sportCategory"> 
                    </div>
                </article>
            </section>
            <div class="float-right mt-5">   
                <button @click="selectCategory()" class="border-2 rounded-xl border-yellow-500 text-xl  font-medium w-40 h-10 mt-3">관심종목 등록</button>
            </div>
        </article>
    </section>
  </div>
</template>

<script>
// import axios from 'axios'
// const SERVER_URL = process.env.VUE_APP_SERVER_URL
import Swal from 'sweetalert2'
import { selectSports } from '@/api/auth.js'
export default {
    data(){
        return{
            sportCategory: [],
        }
    },
    methods: {
        selectCategory(){
            // axios({
            //     method:'post',
            //     url: `${SERVER_URL}/users/interest`,
            //     headers: this.getToken,
            //     data: {
            //         'sportCategory' : this.sportCategory
            //     }
            // })
            selectSports(this.sportCategory)
            .then((res)=>{
                console.log(res)
                Swal.fire('종목 선택이 완료되었습니다.')
                this.$router.push('/main')
            }).catch((err)=>{
                console.log(err)
                console.log(this.sportCategory)
            })
        }
    },

}
</script>

<style>

</style>