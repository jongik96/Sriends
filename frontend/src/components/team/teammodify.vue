<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <div class="grid grid-cols-6  mt-10">
              <div class="md:col-start-2 md:col-span-4 col-start-1 col-span-6 shadow-md border-solid border-2 border-yellow-500 rounded-md ml-2">
                      <div class="pt-10">
                          <p class="text-3xl font-bold flex justify-center">S-riends 수정</p>
                      </div>
                      <!-- name -->
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-xl font-bold">TeamImg</p>
                          <input type="file" v-on:change="fileSelect" id="image" ref="image" class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                          <div id="preview" class="grid grid-cols-2 profile-image" >
                            <div>
                            <p>현재 프로필</p>
                            <img class="profileImg h-10 w-15" :src=form.pictureDownloadUrl />
                            </div>
                            <div>
                            <p>변경할 프로필</p>
                            <img class="profileImg h-10 w-15" :src=modifyPicture />
                            </div>
                        </div>
                      </div>
                      <!-- leaderId -->
                      <div class="md:pt-10 md:pl-20 pl-5 pt-5">
                          <p class="text-xl font-bold">S-riends 이름</p>
                          <input type="text" v-model="form.name"  class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">최대인원</p>
                          <input type="text" v-model="form.maxCount"  class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                        <p class="text-xl font-bold">회비유무</p>
                        <input class="mt-3 " type="radio" id="moneyO" value=true v-model="form.membershipFee">
                        <label class="mr-3 font-semibold" for="O">O</label>
                        <input type="radio" id="moneyX" value=false v-model="form.membershipFee">
                        <label class="font-semibold" for="X ">X</label>
                        
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                        <p class="text-xl font-bold">모집 여부</p>
                        <input class="mt-3 " type="radio" id="O" value="true" v-model="form.recruitmentState">
                        <label class="mr-3 font-semibold" for="O">O</label>
                        <input type="radio" id="X" value="false" v-model="form.recruitmentState">
                        <label class="font-semibold" for="X ">X</label>
                        
                      </div>
                      
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                        <p class="text-xl font-bold">종목</p>
                        <p>
                            <span v-if="!form.city" class="text-yellow-600">종목을 선택해주세요</span>
                        </p>
                        <select class="border-2 border-solid border-yellow-500 rounded-md" v-model="form.sportCategory">
                            <option disabled value="">종목</option>
                            <option value="축구/풋살">축구/풋살</option>
                            <option value="배구">배구</option>
                            <option value="농구">농구</option>
                            <option value="배드민턴">배드민턴</option>
                            <option value="야구">야구</option>
                            <option value="테니스">테니스</option>
                            <option value="탁구">탁구</option>
                            <option value="기타">기타</option>
                        </select>  
                      </div>
                      <div class="md:pt-5 md:pl-20 pt-5 pl-5">
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
                        <div class="md:pt-5 md:pl-20 pt-5 pl-5">
                          <p class="text-xl font-bold">특이사항</p>
                          <textarea v-model="form.description" class=" text-xl w-3/4 h-20 rounded-md border-2 border-yellow-400"/>
                          <p>
                            <span class="text-yellow-600">S-riends 소개</span>
                        </p>
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <button @click="submitForm"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">수정하기</button>
                        <router-link to="/team">
                        <button  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10" >취소</button>
                        </router-link>
                        <button v-if="auth=='대표'" @click.self.prevent="clickDelete"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">삭제하기</button>
                    </div>       
              </div>
          </div>

      </div>
  </div>
</template>

<script>
import Swal from 'sweetalert2'
import axios from 'axios'
import store from '@/store/index.js'
import { deleteTeam } from '@/api/team.js'
import { getTeamInfo } from '@/api/team.js'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
export default {
    data() {
        return {
            selectDo : '',
            modifyPicture:'',
            form:{
                name: '',
                file: '',
                maxCount: '',
                membershipFee: '',
                city : '',
                description: '',
                sportCategory:'',
                recruitmentState: '',
                pictureDownloadUrl:'',
            },
            auth:store.state.auth
            
        }
    },
    created(){
        const teamId = store.state.teamId
        getTeamInfo(teamId)
        .then((res)=>{
            console.log(res)
            this.form.name = res.data.name
            
            // this.form.file = res.data.pictureDownloadUrl
            if(res.data.pictureDownloadUrl!=null){
                this.form.pictureDownloadUrl = res.data.pictureDownloadUrl
            }
            this.form.maxCount = res.data.maxCount
            this.form.description = res.data.description
            this.form.recruitmentState = res.data.recruitmentState
            this.form.membershipFee = res.data.membershipFee
            this.form.city = res.data.city
            this.form.sportCategory = res.data.sportCategory
            }).catch((err)=>{
            console.log(err)
        })

    },
    computed: {
        getToken(){
            const token = store.state.accessToken
            const config = {
                Authorization: `Bearer ${token}`
            }
            return config
        },
    },
    methods:{
        fileSelect(){
            console.log(this.$refs.image.files[0])
            const file = this.$refs.image.files[0]
            this.modifyPicture = URL.createObjectURL(file)
        },
        submitForm: function(){
            const teamId = store.state.teamId
            if(!this.form.name || !this.form.membershipFee || !this.form.city
                || !this.form.description || !this.form.sportCategory
                || !this.form.recruitmentState
                || !this.form.maxCount
            ){
                Swal.fire('입력되지 않은 칸이 있습니다.')
            }else{
                const token = store.state.accessToken
            const formData = new FormData();
            formData.append('file', this.$refs.image.files[0])
            formData.append('name', this.form.name)
            formData.append('maxCount', this.form.maxCount)
            formData.append('city', this.form.city)
            formData.append('membershipFee', this.form.membershipFee)
            formData.append('description', this.form.description)
            formData.append('sportCategory', this.form.sportCategory)
            formData.append('recruitmentState', this.form.recruitmentState)
            for(const element of formData){
                console.log(element)
            }
            axios({
                method: 'put',
                url: `${SERVER_URL}/teams/${teamId}`,
                headers: {
                    'Content-Type' : 'multipart/form-data',
                    Authorization : `Bearer ${token}`,
                    // // 'Content-Type' : 'application/json',
                },
                data: formData

            }).then((res)=>{
                console.log(res.data)
                Swal.fire('팀 수정이 완료되었습니다.')
                this.$router.push('/team')
            }).catch((err)=>{
                console.log(err)
            }) 
            }
        },
        clickDelete:function(){
            Swal.fire({
            title: '팀을 삭제하시겠습니까?',
            text: "다시 복구할 수 없습니다.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네',
            cancelButtonText: '아니오'
          })
          .then((result) => {
            if(result.isConfirmed){    
                deleteTeam()
                .then((res)=>{
                    console.log(res)
                    Swal.fire('팀이 삭제되었습니다')
                }).catch((err)=>{
                    console.log(err)
                })
            }})
        }       
    }

}
</script>

<style>

</style>