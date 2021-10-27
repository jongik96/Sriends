<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <br>
        <p class="text-3xl font-bold flex justify-center">공지사항 /팀관리 /일정관리</p>
          <div class="grid grid-cols-6  mt-10">
              <div class="col-start-2 col-span-4 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">공지사항 작성</p>
                      </div>
                      <div class="pt-10 pl-40">
                          <p class="text-xl font-bold">제목</p>
                          <input type="text"  class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-40">
                          <p class="text-xl font-bold">내용</p>
                          <input type="text"  class=" text-xl w-3/4 h-80  rounded-md border-2 border-yellow-400">
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