<template>
  <div class="grid grid-cols-6 font-sans">

      <div class="col-start-2 col-span-4 ">
          <div class="flex justify-center mt-10">
              <p class="text-4xl text-yellow-500 font-bold">S-riends</p>
          </div>
      </div>
      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-2 md:col-span-4 col-start-1 col-span-6 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="pt-10 font-mono">
                          <p class="text-3xl font-bold flex justify-center">Sign Up</p>
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-base font-bold">profileImg</p>
                          <input type="file" v-on:change="fileSelect" id="image" ref="image" class=" text-base w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-xl font-bold">E-mail *</p>
                          <input type="text" v-model="form.email" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                              <span v-if="(form.email).length>0 && !isEmailValid" class="text-yellow-600">올바른 이메일 형식이 아닙니다.</span>
                          </p>
                          <button @click.self.prevent="duplicateEmail" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-8 font-semibold text-sm">중복검사</button>
                          &nbsp;
                          <button @click.self.prevent="clickEmailAuth" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-8 font-semibold text-sm">인증</button>
                          <div v-if="EmailAuthBtn" class="mt-2">
                            <p class="text-md font-bold">5분 이내에 인증번호를 입력해주세요</p>
                            <input type="text" v-model="authCode" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                            <br/>
                            <button @click.self.prevent="checkEmail" class="border-solid border-2 mt-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-8 font-semibold text-sm">확인</button>
                          </div>
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">Password *</p>
                          <input type="password" v-model="form.password" class="text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p class="w-3/4">
                              <span v-if="!isPasswordValid" class=" text-yellow-600">비밀번호는 영문,숫자,특수문자가 포함된 8자 이상으로 이루어져야합니다.</span>
                          </p>
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">Password Confirm *</p>
                          <input type="password" v-model="form.passwordConfirm" class="text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p class="w-3/4">
                              <span v-if="(form.password).length>0 && (form.password != form.passwordConfirm)" class=" text-yellow-600">비밀번호가 일치하지 않습니다!</span>
                          </p>
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">이름 *</p>
                          <input type="text" v-model="form.name" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                              <span v-if="!form.name" class="text-yellow-600">이름은 2자 이상 6자 이하로 입력해주세요.</span>
                          </p>
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">생년월일 *</p>
                          <input type="date" v-model="form.birth" class="text-md md:text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <!-- <p>
                              <span v-if="!form.birth" class="text-yellow-600">생년월일을 선택해주세요.</span>
                          </p> -->
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                        <p class="text-xl font-bold">성별 *</p>
                        <input class="mt-3 " type="radio" id="man" value="남성" v-model="form.gender">
                        <label class="mr-3 font-semibold" for="man">남성</label>
                        <input type="radio" id="woman" value="여성" v-model="form.gender">
                        <label class="font-semibold" for="woman">여성</label>
                        <br>
                        <span>{{ this.form.gender }}</span>
                        <!-- <p>
                            <span v-if="!form.gender" class="text-yellow-600">성별을 선택해주세요.</span>
                        </p> -->
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">연락처</p>
                          <input type="text" v-model="form.phone" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                            <span v-if="!isPhoneValid" class="text-yellow-600">올바른 전화번호를 입력해주세요.</span>
                        </p>
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                        <p class="text-xl font-bold">거주 지역 *</p>
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
                            <option value="7">광주/전라</option>
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
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='7'" v-model="form.city">
                            <option disabled value="">시/군</option>
                            <option value="광주광역시">광주</option>
                            <option value="광양시">전주</option>
                            <option value="광양시">군산</option>
                            <option value="광양시">익산</option>
                            <option value="광양시">남원</option>
                            <option value="광양시">정읍</option>
                            <option value="광양시">김제</option>
                            <option value="광양시">완주</option>
                            <option value="광양시">진안</option>
                            <option value="광양시">무주</option>
                            <option value="광양시">장수</option>
                            <option value="광양시">임실</option>
                            <option value="광양시">순창</option>
                            <option value="광양시">고창</option>
                            <option value="광양시">부안</option>
                            <option value="광양시">광양</option>
                            <option value="나주시">나주</option>
                            <option value="목포시">목포</option>
                            <option value="순천시">순천</option>
                            <option value="여수시">여수</option>
                            <option value="강진시">강진</option>
                            <option value="고흥시">고흥</option>
                            <option value="곡성시">곡성</option>
                            <option value="구례시">구례</option>
                            <option value="담양시">담양</option>
                            <option value="무안시">무안</option>
                            <option value="보성시">보성</option>
                            <option value="신안시">신안</option>
                            <option value="영광시">영광</option>
                            <option value="영암시">영암</option>
                            <option value="완도시">완도</option>
                            <option value="장성시">장성</option>
                            <option value="장흥시">장흥</option>
                            <option value="진도시">진도</option>
                            <option value="함평시">함평</option>
                            <option value="해남시">해남</option>
                            <option value="화순시">화순</option>
                        </select>
                        <br/>
                        <p v-if="this.form.city" class="mt-2 font-medium">선택지역 : {{ this.form.city }} </p>

                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <button type="submit"  class="border-solid border-2 font-semibold border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">가입하기</button>
                    </div>
                    <div class="flex justify-center p-2 ">
                        <router-link to="/">
                            <button type="submit" class="rounded-md hover:bg-gray-200"><p>이미 계정이 있습니다</p></button>
                        </router-link>
                    </div>          
                  </form>
                  
              </div>
          </div>

      </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
import axios from 'axios'
import { validateEmail } from '@/utils/validation.js';
import { validatePassword } from '@/utils/passwordValidation.js';
import { validatePhone } from '@/utils/phoneNumberValidation.js';
import { duplicatedCheckEmail } from '@/api/index.js'
import { certificationEmail } from '@/api/index.js'
import { certificationEmailCode } from '@/api/index.js'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
export default {
    data() {
        return {
            selectDo : '',
            EmailAuthBtn : false,
            authCode: '',
            passwordConfirm: '',
            isDuplicated: true,
            form:{
                email: '',
                password: '',
                name: '',
                birth: '',
                phone: '',
                city : '',
                gender: '',
                file:'',
            }
            
        }
    },
    computed: {
        btnDisabled(){
            if(this.isDuplicated || !this.isEmailValid || !this.isPasswordValid || !this.form.bitrh || !this.form.gender || !this.form.city || !this.form.name){
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
        fileSelect(){
            console.log(this.$refs.image.files[0])
            // this.form.uuid = this.$refs.image.files[0]
        },
        //회원가입
        submitForm: function(){
            if(this.form.phone==''){
                this.form.phone = null
            }
            const formData = new FormData();
            formData.append('file', this.$refs.image.files[0])
            formData.append('email', this.form.email)
            formData.append('password', this.form.password)
            formData.append('name', this.form.name)
            formData.append('birth', this.form.birth)
            formData.append('phone', this.form.phone)
            formData.append('city', this.form.city)
            formData.append('gender', this.form.gender)
            for(const element of formData){
                console.log(element)
            }
            axios({
                method: 'post',
                url: `${SERVER_URL}/auth/sign-up`,
                headers: {'Content-Type' : 'multipart/form-data'},
                data: formData
            }).then((res)=>{
                console.log(res.data)
                console.log(this.form.password)
                if(res.data=='정상 가입'){
                Swal.fire('회원가입이 완료되었습니다!')}
                this.$router.push('/')
            }).catch((err)=>{
                console.log(err)
            }) 
        },
        // 이메일 인증번호 전송
        clickEmailAuth: function(){
            this.EmailAuthBtn = true;
            certificationEmail(this.form.email)
            .then((res)=>{
                console.log(res)
                if(res.data == true){
                    Swal.fire('인증번호가 전송되었습니다!')
                }
            }).catch((err)=>{
                console.log(err)
            })
        },

        checkEmail: function(){
            this.EmailAuthBtn = false;
            certificationEmailCode(this.form.email, this.authCode)
            .then((res)=>{
                console.log(res)
                Swal.fire('인증이 완료되었습니다.')
            }).catch((err)=>{
                console.log(err)
                Swal.fire('인증번호 전송에 실패했습니다.')
            })
        },
        //이메일 중복검사
        duplicateEmail: function(){
            duplicatedCheckEmail(this.form.email)
            .then((res)=>{
                if(res.data == true){
                    Swal.fire('이미 가입된 ID입니다.')
                    this.isDuplicated = false
                }else{
                    Swal.fire('사용가능한 ID입니다.')
                }
            }).catch((err)=>{
                console.log(err)
            })
        }
    }

}
</script>

<style>

</style>