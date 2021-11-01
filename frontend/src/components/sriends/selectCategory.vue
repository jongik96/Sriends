<template>
  <div>
      <section class="max-w-5xl mx-auto px-4 sm:px-6 lg:px-4 mt-12 mb-12">
        <article>
            <h2 class="text-2xl font-extrabold text-gray-900">종목 선택</h2>
            <section class="mt-6 grid md:grid-cols-2 lg:grid-cols-4 gap-x-6 gap-y-8">
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/football.jpg"
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">
                                축구/풋살
                            </a>
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/basketball.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">
                                농구
                            </a>
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/baseball.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                야구
                            </a>      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/volleyball.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                배구
                            </a>      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/badminton.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                배드민턴
                            </a>      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/tennis.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                테니스
                            </a>      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/pingpong.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                탁구
                            </a>      
                    </div>
                </article>
                <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                    <div class="relative w-full h-80 md:h-64 lg:h-44">
                        <router-link to="/teamList">
                        <img src="@/assets/guitar.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                        </router-link>
                    </div>
                    <div class="px-3 py-4 flex items-center justify-center">
                            <a class="bg-yellow-400 py-1 px-2 font-base text-lg text-black rounded-lg" href="#">                         
                                기타
                            </a>      
                    </div>
                </article>
            </section>
        </article>
    </section>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>