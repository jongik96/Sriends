<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-2 md:col-span-4 col-start-1 col-span-6 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">S-riends 수정</p>
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-xl font-bold">S-riends 명</p>
                          <input type="text"  class=" text-xl w-2/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-xl font-bold">S-riends 대표</p>
                          <input type="text"  class=" text-xl w-2/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-xl font-bold">회비가격</p>
                          <input type="text"  class=" text-xl w-2/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-xl font-bold">특이사항</p>
                          <input type="text"  class=" text-xl w-3/4 h-24  rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                        <p class="text-xl font-bold">종목</p>
                        <p> 
                            <span v-if="!form.city" class="text-yellow-600">종목</span>
                        </p>
                        <select class="border-2 border-solid border-yellow-500 rounded-md" v-model="selectDo">
                            <option disabled value="">종목을 선택하세요 </option>
                            <option value="1">풋살/축구</option>
                            <option value="2">배구</option>
                            <option value="3">농구</option>
                            <option value="4">배드민턴</option>
                            <option value="5">야구</option>
                        </select>  
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                        <p class="text-xl font-bold">거주 지역</p>
                        <p>
                            <span v-if="!form.city" class="text-yellow-600">지역을 선택해주세요</span>
                        </p>
                        <select class="border-2 border-solid border-yellow-500 rounded-md" v-model="selectDo2">
                            <option disabled value="">지역</option>
                            <option value="1">서울/인천/경기</option>
                            <option value="2">대전/충청</option>
                            <option value="3">대구/경북</option>
                            <option value="4">강원</option>
                            <option value="5">부산/울산/경남</option>
                            <option value="6">제주</option>
                        </select>
                        </div>
                      <div class="flex justify-center p-2 mt-10">
                        <button type="submit" :disabled="!btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">수정하기</button>
                        <button type="submit" :disabled="!btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10" >취소</button>
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