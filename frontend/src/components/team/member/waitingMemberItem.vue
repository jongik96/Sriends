<template>
  <div class="shadow-md rounded-xl p-4 mt-5 mx-6 mb-4 min-w-300">
                <div class="grid grid-cols-7">
                    <div class="ml-1 md:ml-0 col-start-1 col-span-2 grid text-sm md:text-base sm:place-items-center">
                         {{name}}
                        <br>
                        <!-- {{item.level}} -->
                    </div>
                    <div class="invisible sm:visible col-start-3 col-span-3 text-sm md:text-base md:grid sm:place-items-center">
                        {{createDate}}
                    </div>
                    <div class="col-start-6 col-span-1 text-sm md:text-base grid justify-center">
                        <button><font-awesome-icon icon="comments"/></button>
                    </div>
                    <div class="col-start-7 col-span-1 text-xs sm:text-sm md:text-base flex justify-center">
                        <button @click="open = true">신청서</button>
                    </div>
                </div>
                <div v-if="open" class="grid grid-cols-7 mt-2 border-2">
                    <div class="col-start-3 col-span-3">
                        <button @click="open=false">숨기기</button>
                        <button class="float-right">가입 승인</button>
                    </div>
                    <div class="col-start-3 col-span-3 border-b mt-3">
                        <p>{{description}}</p>
                    </div>
                </div>
            </div>
</template>

<script>
export default {
    props:{
        userId: [String,Number],
        createDate: [String, Date],
        name: [String],
        description:[String]
    },
    data(){
        return{
            open:false
        }
    }
}
</script>

<style>

</style>