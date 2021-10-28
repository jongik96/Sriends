<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div class="flex justify-end">
                <router-link to="/team/createMatching">
                <button class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-md md:text-xl rounded block text-center 
                    sm:inline-block mr-5">
                            경기 매칭구하기
                </button>
                </router-link>
            </div>
            <div v-for="item in items" :key="item.id" class="shadow-md p-4 mt-5 mx-6 mb-4 min-w-300 h-36 md:h-24">
                <h2 class="text-2xl font-semibold"><a href="#">{{ item.title }}</a></h2>
                <p class="truncate md:w-96 w-48 text-justify leading-tight text-gray-800">{{ item.description }}<p>
                <div class="mt-2">
                <span class="left">Posted On: {{ item.datePosted }}</span>
                <span class="float-right">By: <a class="text-purple-500" href="#">{{ item.postedBy }}</a></span>
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
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user525"
            },
            { id: 2,
                title: "동호회1",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user120"
            },
            { id: 3,
                title: "동호회1",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 4,
                title: "동호회1",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 5,
                title: "동호회1",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 6,
                title: "동호회1",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            },
            { id: 7,
                title: "동호회1",
                description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
                datePosted: "10/09/2020",
                postedBy: "user345"
            }
            ]
        }
  }
}
</script>

<style>

</style>