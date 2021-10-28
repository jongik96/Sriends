<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="col-start-2 col-span-4 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">S-riends 생성</p>
                      </div>
                      <div class="pt-10 pl-20">
                          <p class="text-xl font-bold">S-riends 명</p>
                          <input type="text"  class=" text-xl w-1/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">Age(연령대)</p>
                          <input type="text"  class=" text-xl w-1/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                        <p class="text-xl font-bold">회비유무</p>
                        <input class="mt-3 " type="radio" id="O" value="회비O" v-model="form.money">
                        <label class="mr-3 font-semibold" for="O">O</label>
                        <input type="radio" id="X" value="회비X" v-model="form.money">
                        <label class="font-semibold" for="X ">X</label>
                        <br>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">회비가격</p>
                          <input type="text"  class=" text-xl w-1/4 rounded-md border-2 border-yellow-400">
                          <p>
                              <span class="text-yellow-600">숫자로 입력해주세요.(없으면 0원)</span>
                          </p>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">특이사항</p>
                          <input type="text"  class=" text-xl w-3/4 h-20 rounded-md border-2 border-yellow-400">
                          <p>
                            <span class="text-yellow-600">S-riends 소개</span>
                        </p>
                      </div>
                      <div class="pt-5 pl-20">
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
                      <div class="pt-5 pl-20">
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
                        <button type="submit" :disabled="!btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">생성하기</button>
                    </div>
                    <div class="flex justify-center p-2 ">
                        <router-link to="/main">
                            <button class="rounded-md hover:bg-gray-200"><p>취소</p></button>
                        </router-link>
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
            if(!this.isPasswordValid || !this.form.bitrh || !this.form.money){
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