<template>
  <div>
      <section class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-4 mt-12 mb-12">
    <article>
        <h2 class="text-2xl font-extrabold text-gray-900">나의 스렌즈</h2>
        <section class="mt-6 grid md:grid-cols-2 lg:grid-cols-4 gap-x-6 gap-y-8">
            <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                <div class="relative w-full h-80 md:h-64 lg:h-44">
                    <router-link to="/team">
                    <img src="@/assets/sideImg.png"
                        class="w-full h-full object-center object-cover">
                        </router-link>
                </div>
                <div class="px-3 py-4 flex items-center justify-center">
                        <a class="bg-yellow-400 py-1 px-2 font-semibold text-black rounded-lg" href="#">
                            동호회1
                        </a>
                </div>
            </article>
            <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                <div class="relative w-full h-80 md:h-64 lg:h-44">
                    <img src="@/assets/sideImg.png"
                        alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                        class="w-full h-full object-center object-cover">
                </div>
                <div class="px-3 py-4 flex items-center justify-center">
                        <a class="bg-yellow-400 py-1 px-2 font-semibold text-black rounded-lg" href="#">
                            동호회2
                        </a>
                </div>
            </article>
            <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                <div class="relative w-full h-80 md:h-64 lg:h-44">
                    <img src="@/assets/sideImg.png"
                        alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                        class="w-full h-full object-center object-cover">
                </div>
                <div class="px-3 py-4 flex items-center justify-center">
                        <a class="bg-yellow-400 py-1 px-2 font-semibold text-black rounded-lg" href="#">                         
                            동호회3
                        </a>      
                </div>
            </article>
            <!-- 새 스렌즈 만들기 -->
            <article class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
                <div class="relative w-full h-80 md:h-64 lg:h-44">
                    <router-link to="/teamCreate">
                        <img src="@/assets/plus.jpg"
                            alt="Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug."
                            class="w-full h-full object-center object-cover">
                    </router-link>
                </div>
                <div class="px-3 py-4 flex items-center justify-center">
                        <a class="bg-yellow-400 py-1 px-2 font-semibold text-black rounded-lg" href="#">                         
                            새 스렌즈 만들기
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