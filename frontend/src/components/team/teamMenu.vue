<template>
  <div class="grid grid-cols-6">
            <!-- nav -->
    <nav class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-5">
    <div class="flex justify-between flex-wrap items-center  md:justify-around">
        <!-- logo -->
        <router-link to="/team">
            <h3>팀이름 적을곳</h3>
        </router-link>
        <div class="space-x-4">
            <router-link to="/team/articleList">
                <button class="inline-block bg-yellow-400 px-2 py-1 text-black font-semibold text-md rounded">
                    <p>공지사항</p>
                </button>
            </router-link>
            <router-link to="/team/calendar">
                <button class="inline-block bg-yellow-400 px-2 py-1 text-black font-semibold text-md rounded">
                    <p>일정관리</p>
                </button>
            </router-link>
            <router-link to="/team/memberList">
                <button class="inline-block bg-yellow-400 px-2 py-1 text-black font-semibold text-md rounded">
                    <p>팀 관리</p>
                </button>
            </router-link>
        </div>
    </div>
    </nav>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>