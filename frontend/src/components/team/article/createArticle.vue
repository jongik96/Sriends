<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <br>
        <p class="text-3xl font-bold flex justify-center">게시글 작성</p>
          <div class="grid grid-cols-6  mt-10">
              <div class="col-start-1 col-span-6  md:col-start-2 md:col-span-4 shadow-md border-solid border-2 bor    der-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">제목</p>
                          <input type="text"  class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">내용</p>
                          <input type="text"  class=" text-xl w-3/4 h-60  rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <button type="submit" :disabled="!btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">작성</button>
                        <button type="submit" :disabled="!btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">취소</button>
                    </div>       
                  </form>
              </div>
          </div>

      </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            selectDo : '',
            selectDo2 : '',
            form:{
                email: '',
                name: '',
                bitrh: '',
                phone: '',
                city : '',
                money: '',
                uuid:'',
            }
            
        }
    },
    computed: {
        btnDisabled(){
            if(!this.isEmailValid || !this.isPasswordValid || !this.form.bitrh || !this.form.money){
                return false
            }
            return true
        },
    },
    methods:{
        submitForm: function(){
            console.log('click')
        }        
    }

}
</script>

<style>

</style>