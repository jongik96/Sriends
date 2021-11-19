<template>
  <div>
      <div class="flex flex-wrap items-center p-4 md:py-8">

      <div class="md:w-3/12 md:ml-16">
        <!-- profile image -->
        <img v-if="!this.modifyState" class="w-84 h-84 md:w-60 md:h-60  object-contain rounded-xl
                     border-2 border-yellow-500 p-1" :src=pictureUrl @error="imgError">
        <div v-if="this.modifyState">
          <p class="text-xl font-bold">profileImg</p>
          <input type="file"  accept=".png, .jpg" @change="fileSelect" id="image" ref="image" class=" text-sm w-full md:w-3/4 rounded-md border-2 border-yellow-400">
          <div id="preview" class="grid grid-cols-2 profile-image" >
            <div>
              <p>현재 프로필</p>
              <img class="profileImg object-contain rounded-xl border-2 border-yellow-500" :src=pictureUrl />
            </div>
            <div>
              <p>변경할 프로필</p>
              <img v-if="modifyPicture" class="profileImg object-contain rounded-xl border-2 border-yellow-500" :src=modifyPicture />
            </div> 
          </div>
        </div>
      </div>
      <!-- profile meta -->
      <div class="w-7/12 md:w-7/12 = md:ml-5 ml-4 2xl:ml-20">
        <div class="md:flex md:flex-wrap md:items-center mb-4">
          <div v-if="!this.modifyState" class="text-3xl font-semibold inline-block md:mr-2 mb-2 sm:mb-0">
            {{this.name}}
          </div>
          <!-- modify button -->
          <button v-if="!this.modifyState" @click="clickModify" class="ml-2">
            <p class="bg-yellow-500 px-2 py-1 
                        text-white font-semibold text-sm rounded block text-center 
                        sm:inline-block">정보수정</p>
          </button>
        </div>
        <div v-if="!this.modifyState">
        <!-- post, following, followers list for medium screens -->
        <ul class="md:flex text-xl">
          <li class="">
            성별 :
            <span class="text-medium">{{this.gender}}</span>
          </li>
          <li class="md:ml-5">
            지역 :
            <span class="">{{this.city}}</span>
          </li>
          <li class="md:ml-5">
            나이 :
            <span class="">{{this.age}}</span>
          </li>
        </ul>
        <ul class="md:flex md:text-xl text-base">
          <li v-if="phone!=null">
            전화번호 :
            <span class="">{{this.phone}}</span>
          </li>
        </ul>
        <ul v-if="sports.length!=0" class="md:flex text-xl">
          <li class="">
            관심종목 :
            <span v-for="item in sports" :key="item.id" class=""> {{item.interest}}</span>
          </li>
          
        </ul>
        </div>
        <div v-if="this.modifyState">
        <!-- post, following, followers list for medium screens -->
        <div class=" text-xl">
          <div>
            이름
            <input type="text" v-model="name" class="md:ml-8 text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                              <span v-if="!name" class="text-base text-yellow-600">이름은 2자 이상 6자 이하로 입력해주세요.</span>
                          </p>
          </div>
          <div class="mt-3">
            지역
            <select class="md:ml-8 border-2 border-solid border-yellow-500 rounded-md" v-model="selectDo">
                            <option disabled value="">지역</option>
                            <option value="1">서울/인천/경기</option>
                            <option value="2">대전/충청</option>
                            <option value="3">대구/경북</option>
                            <option value="4">강원</option>
                            <option value="5">부산/울산/경남</option>
                            <option value="6">제주</option>
                            <option value="7">광주/전라</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='1'" v-model="city">
                            <option disabled value="">시/군</option>
                            <option value="서울">서울특별시</option>
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
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='2'" v-model="city">
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
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='3'" v-model="city">
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
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='4'" v-model="city">
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
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='5'" v-model="city">
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
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='6'" v-model="city">
                            <option disabled value="">시/군</option>
                            <option value="제주시">제주시</option>
                            <option value="서귀포시">서귀포시</option>
                        </select>
                        <select class="border-2 border-solid border-yellow-500 rounded-md ml-3" v-if="this.selectDo=='7'" v-model="city">
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
                        <p v-if="this.city" class="mt-2 font-medium">선택지역 : {{ this.city }} </p>
          </div>
        </div>
        <div class=" text-xl mt-3">
          <div>
            전화번호
            <input type="text" v-model="phone" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <p>
                            <span v-if="!isPhoneValid" class="text-yellow-600">올바른 전화번호를 입력해주세요.</span>
                            <span v-if="!isPhoneValid" class="text-yellow-600">(-)기호를 빼주세요.</span>
                          </p>
          </div>
        </div>
        <div class="md:flex text-base mt-3">
          <div class="">
            
            <router-link to="/selectCategory">
              <button class="border-2 rounded-xl border-yellow-500  font-medium w-36 h-8 mt-3">관심종목 선택하기</button>
            </router-link>
            <router-link to="/modifyPassword">
              <button class="border-2 rounded-xl border-yellow-500  font-medium w-36 h-8 mt-3 md:ml-3">비밀번호 변경하기</button>
            </router-link>
          </div>
          
        </div>
          <div class="flex justify-end p-2 mt-1">
          <button @click="clickCancel"><p class="rounded-md hover:bg-gray-200 w-7">취소</p></button>
          <button :disabled="!btnDisabled" @click="ModifyUser" class="border-solid border-2 border-yellow-500 rounded-md ml-3 hover:bg-yellow-400 w-16 h-7">수정</button>
          <button @click.self.prevent="clickDelete"  class="border-solid border-2 border-yellow-500 ml-3 rounded-md hover:bg-yellow-400 w-16 h-7">삭제</button>
          </div>
        </div>
        
        <!-- <div class="hidden md:block">
          <h1 class="font-semibold text-lg">내 이름은 박범진.</h1>
          <span>Travel, Nature and Music</span>
          <p>Lorem ipsum dolor sit amet consectetur</p>
        </div> -->

      </div>

      
      <!-- <div class="md:hidden text-sm my-2">
        <h1 class="font-semibold">내 이름은 박범진</h1>
        <span>Travel, Nature and Music</span>
        <p>Lorem ipsum dolor sit amet consectetur</p>
      </div> -->

    </div>
  </div>
</template>

<script>
import img from '@/assets/profile.png'
import { validatePhone } from '@/utils/phoneNumberValidation.js';
import {getProfileInfo} from '@/api/auth.js'
import { getInterest } from '@/api/auth.js'
// import { modifyNumber } from '@/utils/phoneNumber.js'
import store from '@/store/index.js'
import axios from 'axios'
import Swal from 'sweetalert2'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
export default {
  data(){
    return{
      selectDo:'',
      modifyState: false,
      // 수정할 항목들
      form:{
        name : '',
        phone : '',
        city : '',
        uuid: '',
      },
      // 수정 전 받아오는 항목들
      id: '',
      email : '',
      name : '',
      birth : '',
      phone : '',
      gender : '',
      city : '',
      age :'',
      pictureUrl : '',
      modifyPicture:'',
      sports:[]
    }
  },
  created: function(){
    getProfileInfo()
    .then((res)=>{
      console.log(res.data)
      this.id = res.data.id
      this.name = res.data.name
      this.phone = res.data.phone
      this.gender = res.data.gender
      if(res.data.pictureUrl!=null){
      this.pictureUrl = res.data.pictureUrl
      }
      this.city = res.data.city
      console.log(this.pictureUrl)
      // string 형식 date로 바꿔서 나이계산
      this.birth = new Date(res.data.birth)
      let today = new Date();
      this.age = today.getFullYear() - new Date(res.data.birth).getFullYear()+1;
      console.log(new Date(res.data.birth))
      // localStorage.setItem('userid',res.data.id)
      this.$store.commit("setUserId", res.data.id)
      this.$store.commit("setUserName", res.data.name)
      this.$store.commit("setMyCity",res.data.city)
      this.$store.commit("setMyPicture",res.data.pictureUrl)
    }).catch((err)=>{
      console.log(err)
    }),
    getInterest()
    .then((res)=>{
      console.log(res)  
      this.sports = res.data    
    }).catch((err)=>{
      console.log(err)
    })
  },
  methods:{
    imgError:function(e){
            e.target.src = img
        },
    fileSelect(){
            console.log(this.$refs.image.files[0])
            const file = this.$refs.image.files[0]
            this.modifyPicture = URL.createObjectURL(file)
            // this.form.uuid = this.$refs.image.files[0]
    },
    clickModify(){
      if(this.modifyState==false){
        this.modifyState= true
      }
    },
    clickCancel(){
      this.$router.go()
      this.modifyState=false
      
    },
    clickDelete: function(){
        this.$router.push('/confirmPassword')
    },
    ModifyUser: function(){
            if(this.form.phone==''){
                this.form.phone = null
            }
            if(this.isPhoneValid == true){
            console.log('수정클릭')
            const token = store.state.accessToken
            const formData = new FormData();
            formData.append('file', this.$refs.image.files[0])
            formData.append('name', this.name)
            formData.append('phone', this.phone)
            formData.append('city', this.city)
            for(const element of formData){
                console.log(element)
            }
            console.log(formData)
            axios({
                method: 'put',
                url: `${SERVER_URL}/users`,
                headers: {
                    'Content-Type' : 'multipart/form-data',
                    // 'Content-Type' : 'application/json',
                    Authorization : `Bearer ${token}`,
                },
                data: formData

            }).then((res)=>{
                console.log(res.data)
                Swal.fire('수정 완료!')
                this.modifyState=false
                this.$router.go()
                // this.$router.push('/main')
            }).catch((err)=>{
                console.log(formData)
                console.log(err)
            })
            }else{
              Swal.fire('입력값이 잘못 되었습니다.')
            }
    } 
  },
  computed:{
    btnDisabled(){
            if(!this.city || !this.name){
                return false
            }
            return true
        },
    
    isPhoneValid(){
            if((this.phone).length==0 || (validatePhone(this.phone) && (this.phone).length == 11)){

                return true;
            }
            return false;
        },
    // calcAge(){
    //   let today = new Date();
    //   console.log(this.birth)
    //   console.log(today)
    //   let age = today.getFullYear() - birth.getFullYear()+1;
    //   return age
    // },
  }



  
}
</script>

<style>

</style>