<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-4xl text-yellow-500 font-bold">S-riends</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="col-start-2 col-span-4 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">Sign Up</p>
                      </div>
                      <div class="pt-10 pl-20">
                          <p class="text-xl font-bold">profileImg</p>
                          <input type="file"  class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">E-mail</p>
                          <input type="text" v-model="form.email" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                              <span v-if="!isEmailValid || !form.email" class="text-yellow-600">올바른 이메일 형식이 아닙니다.</span>
                          </p>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">Password</p>
                          <input type="password" v-model="form.password" class="text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p class="w-3/4">
                              <span v-if="!isPasswordValid" class=" text-yellow-600">비밀번호는 영문,숫자,특수문자가 포함된 8자 이상으로 이루어져야합니다.</span>
                          </p>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">이름</p>
                          <input type="text" v-model="form.name" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                              <span v-if="!form.name" class="text-yellow-600">이름을 입력해주세요.</span>
                          </p>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">생년월일</p>
                          <input type="date" v-model="form.bitrh" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                              <span v-if="!form.birth" class="text-yellow-600">생년월일을 선택해주세요.</span>
                          </p>
                      </div>
                      <div class="pt-5 pl-20">
                        <p class="text-xl font-bold">성별</p>
                        <input class="mt-3 " type="radio" id="man" value="man" v-model="form.gender">
                        <label class="mr-3 font-semibold" for="man">남성</label>
                        <input type="radio" id="woman" value="woman" v-model="form.gender">
                        <label class="font-semibold" for="woman">여성</label>
                        <br>
                        <span>{{ this.form.gender }}</span>
                        <p>
                            <span v-if="!form.gender" class="text-yellow-600">성별을 선택해주세요.</span>
                        </p>
                      </div>
                      <div class="pt-5 pl-20">
                          <p class="text-xl font-bold">연락처</p>
                          <input type="text" v-model="form.phone" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                            <span v-if="!isPhoneValid" class="text-yellow-600">올바른 전화번호를 입력해주세요.</span>
                        </p>
                      </div>
                      <div class="pt-5 pl-20">
                        <p class="text-xl font-bold">거주 지역</p>
                        <p>
                            <span v-if="!form.city" class="text-yellow-600">지역을 선택해주세요</span>
                        </p>
                        <select class="border-2 border-solid border-yellow-500 rounded-md" v-model="selectDo">
                            <option disabled value="">지역</option>
                            <option value="1">서울/인천/경기</option>
                            <option value="2">대전/충청</option>
                            <option value="3">대구/경북</option>
                            <option value="4">강원</option>
                            <option value="5">부산/울산/경남</option>
                            <option value="6">제주</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='1'" v-model="form.city">
                            <option disabled value="">시/군</option>
                            <option value="서울특별시">서울특별시</option>
                            <option value="인천광역시">인천광역시</option>
                            <option value="고양시">고양시</option>
                            <option value="과천시">과천시</option>
                            <option value="광명시">광명시</option>
                            <option value="광주시">광주시</option>
                            <option value="구리시">구리시</option>
                            <option value="군포시">군포시</option>
                            <option value="김포시">김포시</option>
                            <option value="남양주시">남양주시</option>
                            <option value="동두천시">동두천시</option>
                            <option value="부천시">부천시</option>
                            <option value="성남시">성남시</option>
                            <option value="수원시">수원시</option>
                            <option value="시흥시">시흥시</option>
                            <option value="안산시">안산시</option>
                            <option value="안성시">안성시</option>
                            <option value="안양시">안양시</option>
                            <option value="양주시">양주시</option>
                            <option value="여주시">여주시</option>
                            <option value="오산시">오산시</option>
                            <option value="용인시">용인시</option>
                            <option value="의왕시">의왕시</option>
                            <option value="의정부시">의정부시</option>
                            <option value="이천시">이천시</option>
                            <option value="파주시">파주시</option>
                            <option value="평택시">평택시</option>
                            <option value="포천시">포천시</option>
                            <option value="하남시">하남시</option>
                            <option value="화성시">화성시</option>
                            <option value="가평군">가평군</option>
                            <option value="양평군">양평군</option>
                            <option value="연천군">연천군</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='2'" v-model="form.city">
                            <option disabled value="">시/군</option>
                            <option value="대전광역시">대전광역시</option>
                            <option value="세종특별자치시">세종특별자치시</option>
                            <option value="계룡시">계룡시</option>
                            <option value="공주시">공주시</option>
                            <option value="논산시">논산시</option>
                            <option value="당진시">당진시</option>
                            <option value="보령시">보령시</option>
                            <option value="서산시">서산시</option>
                            <option value="아산시">아산시</option>
                            <option value="천안시">천안시</option>
                            <option value="제천시">제천시</option>
                            <option value="청주시">청주시</option>
                            <option value="충주시">충주시</option>
                            <option value="금산군">금산군</option>
                            <option value="부여군">부여군</option>
                            <option value="서천군">서천군</option>
                            <option value="예산군">예산군</option>
                            <option value="청양군">청양군</option>
                            <option value="태안군">태안군</option>
                            <option value="홍성군">홍성군</option>
                            <option value="괴산군">괴산군</option>
                            <option value="단양군">단양군</option>
                            <option value="보은군">보은군</option>
                            <option value="영동군">영동군</option>
                            <option value="옥천군">옥천군</option>
                            <option value="음성군">음성군</option>
                            <option value="증평군">증평군</option>
                            <option value="진천군">진천군</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='3'" v-model="form.city">
                            <option disabled value="">시/군</option>
                            <option value="대구광역시">대구광역시</option>
                            <option value="경산시">경산시</option>
                            <option value="경주시">경주시</option>
                            <option value="구미시">구미시</option>
                            <option value="김천시">김천시</option>
                            <option value="문경시">문경시</option>
                            <option value="상주시">상주시</option>
                            <option value="안동시">안동시</option>
                            <option value="영주시">영주시</option>
                            <option value="영천시">영천시</option>
                            <option value="포항시">포항시</option>
                            <option value="고령군">고령군</option>
                            <option value="군위군">군위군</option>
                            <option value="봉화군">봉화군</option>
                            <option value="성주군">성주군</option>
                            <option value="영덕군">영덕군</option>
                            <option value="영양군">영양군</option>
                            <option value="예천군">예천군</option>
                            <option value="울릉군">울릉군</option>
                            <option value="울진군">울진군</option>
                            <option value="의성군">의성군</option>
                            <option value="청도군">청도군</option>
                            <option value="청송군">청송군</option>
                            <option value="칠곡군">칠곡군</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='4'" v-model="form.city">
                            <option disabled value="">시/군</option>
                            <option value="강릉시">강릉시</option>
                            <option value="동해시">동해시</option>
                            <option value="삼척시">삼척시</option>
                            <option value="속초시">속초시</option>
                            <option value="원주시">원주시</option>
                            <option value="춘천시">춘천시</option>
                            <option value="태백시">태백시</option>
                            <option value="고성군">고성군</option>
                            <option value="양구군">양구군</option>
                            <option value="양양군">양양군</option>
                            <option value="영월군">영월군</option>
                            <option value="인제군">인제군</option>
                            <option value="정선군">정선군</option>
                            <option value="철원군">철원군</option>
                            <option value="평창군">평창군</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='5'" v-model="form.city">
                            <option disabled value="">시/군</option>
                            <option value="부산광역시">부산광역시</option>
                            <option value="울산광역시">울산광역시</option>
                            <option value="거제시">거제시</option>
                            <option value="김해시">김해시</option>
                            <option value="밀양시">밀양시</option>
                            <option value="사천시">사천시</option>
                            <option value="양산시">양산시</option>
                            <option value="진주시">진주시</option>
                            <option value="창원시">창원시</option>
                            <option value="통영시">통영시</option>
                            <option value="거창군">거창군</option>
                            <option value="고성군">고성군</option>
                            <option value="남해군">남해군</option>
                            <option value="산청군">산청군</option>
                            <option value="의령군">의령군</option>
                            <option value="창녕군">창녕군</option>
                            <option value="하동군">하동군</option>
                            <option value="함안군">함안군</option>
                            <option value="함양군">함양군</option>
                            <option value="합천군">합천군</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='6'" v-model="form.city">
                            <option disabled value="">시/군</option>
                            <option value="제주시">제주시</option>
                            <option value="서귀포시">서귀포시</option>
                        </select>
                        <br/>
                        <p v-if="this.form.city" class="mt-2 font-medium">선택지역 : {{ this.form.city }} </p>

                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <button type="submit" :disabled="!btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">가입하기</button>
                    </div>
                    <div class="flex justify-center p-2 ">
                        <router-link to="/">
                            <button class="rounded-md hover:bg-gray-200"><p>이미 계정이 있습니다</p></button>
                        </router-link>
                    </div>          
                  </form>
                  
              </div>
          </div>

      </div>
  </div>
</template>

<script>
import { validateEmail } from '@/utils/validation.js';
import { validatePassword } from '@/utils/passwordValidation.js';
import { validatePhone } from '@/utils/phoneNumberValidation.js';
export default {
    data() {
        return {
            selectDo : '',
            form:{
                email: '',
                password: '',
                name: '',
                bitrh: '',
                phone: '',
                city : '',
                gender: '',
            }
            
        }
    },
    computed: {
        btnDisabled(){
            if(!this.isEmailValid || !this.isPasswordValid || !this.form.bitrh || !this.form.gender || !this.form.city || !this.form.name){
                return false
            }
            return true
        },
        isEmailValid(){
            return validateEmail(this.form.email);
        },
        isPasswordValid(){
            return validatePassword(this.form.password);
        },
        isPhoneValid(){
            if((this.form.phone).length==0 || (validatePhone(this.form.phone) && (this.form.phone).length == 11)){

                return true;
            }
            return false;
        }
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