<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="flex justify-end">
                <router-link to="/team/createArticle">
                <button class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            모집글 작성하기
                </button>
                </router-link>
            </div>
            <div v-for="item in items" :key="item.id" class="border-2 border-yellow-500 rounded-xl shadow-md p-4 mt-5 mx-6 mb-4 min-w-300 h-48 grid grid-cols-6">
                <div class="grid col-start-1 col-span-2">
                    <img src='@/assets/logo.png' class="rounded-md h-20 w-20 ml-10" alt="">
                    <button class="bg-yellow-500 px-2 py-1 w-24
                    text-white font-semibold text-sm rounded block text-center ml-8
                    sm:inline-block mr-5 h-10"
                    @click="joinBtn"
                    >
                        가입 요청
                    </button>
                </div>
                <div class="grid col-start-3 col-span-4">
                    <div>
                        <p>팀명 : {{item.title}} </p>
                    </div>
                    <div>
                        <p>대표 : {{item.postedBy}} </p>
                    </div>
                    <div>
                        <p>연락처 : </p>
                    </div>
                    <div class="">
                        <p class="truncate w-96">소개 : {{item.description}} </p>
                    </div>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
export default {
    data() {
        return{
            items: [
            { id: 1,
                title: "동호회1",
                description: "동호회소개 어쩌구저쩌구 ㅁㄴㅇㄻㄴ아럼ㄴ;이러",
                datePosted: "10/09/2020",
                postedBy: "user525"
            },
            { id: 2,
                title: "Random post",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user120"
            },
            { id: 3,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dumasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdmy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 4,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 5,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 6,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 7,
                title: "Urgent!!, this post is a test, click now!!",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            }
            ]
        }
  },
  methods:{
      joinBtn: function(){
          this.$router.push('/joinTeam')
      }
  }
}
</script>

<style>

</style>