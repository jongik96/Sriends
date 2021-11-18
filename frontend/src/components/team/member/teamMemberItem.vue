<template>
  <div class="shadow-md rounded-xl p-4 h-36 sm:h-24 mt-5 mx-6 mb-4 min-w-300">
    <div class="grid grid-cols-7">
      <div class="col-start-1 col-span-3 sm:col-start-1 sm:col-span-1">
          <img :src=pictureUrl class="h-10 w-10 rounded-2xl md:h-20 md:w-20" @error="imgError">
      </div>
      <div class="ml-1 md:ml-0 col-start-4 col-span-4 sm:col-start-2 sm:col-span-1 grid sm:place-items-center">
          <div>
            <button class="h-1" @click="clickName" :disabled="id==myId">
            {{name}}
            </button>
          </div>
          <div>
            <p>
            {{age}}세
            </p>
          </div>
      </div>
      <div class=" sm:visible col-start-1 col-span-2 sm:col-start-3 sm:col-span-1 grid sm:place-items-center ml-1 sm:ml-0">
        <div>
        {{city}}
        </div>
        <div>
        {{gender}}
        </div>
        <div>
        <button class="border-2 cursor-default border-yellow-600 rounded-md w-12 sm:w-20">{{authority}}</button>
        </div>
      </div>
      <div v-if="id!=myId" 
       class="ml-1 sm:visible col-start-3 col-span-3 sm:col-start-4 sm:col-span-3 grid sm:place-items-center">
          <div v-if="Myauth">
            <select class="border-2 border-solid w-16 h-6 border-yellow-500 rounded-md" v-model="modifyAuth">
                            <option disabled value="">권한</option>
                            <option value="회원">회원</option>
                            <option value="매니저">매니저</option>
                            <option value="대표">대표</option>
            </select>  
            <span class="border-2 ml-2 mt-20 border-solid h-6 border-yellow-500 w-18 rounded-md">
              <button @click="giveManage">
              부여하기
              </button>
            </span>
          </div>
          
      </div>
      <div v-if="myId!==id" class="col-start-6 col-span-2 md-36 sm:md-0 sm:col-start-7 sm:col-span-1">
          <div class="flex justify-end">
            <button @click="clickDm"><font-awesome-icon icon="comments"/></button>
          </div>
          <div v-if="!noGrade" class="flex justify-end mt-5">
            <button @click="clickBan"><font-awesome-icon icon="ban"/></button>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import img from '@/assets/profile.png'
import { getTempProfileInfo } from '@/api/auth.js'
import store from '@/store/index.js'
import { kickMember } from '@/api/team.js'
import { givePermit } from '@/api/team.js'
import Swal from "sweetalert2"
import {getPermitState} from '@/api/team.js'
export default {
  props:{
    id:[String,Number],
    registerDate:[String,Date],
    authority:[String]
  },
  data(){
    return{
      myId: store.state.userId,

      tempId: '',
      name : '',
      gender : '',
      city : '',
      age :'',
      pictureUrl : '',
      modifyAuth:'',
      Myauth:false, // true면 대표 아니면 아무것도아님
      noGrade:false,
    }
  },
  created(){
    const teamId = store.state.teamId
    getPermitState(teamId)
        .then((res)=>{
            console.log(res)
            if(res.data.authority=='대표')
            {this.Myauth=true}
            if(res.data.authority=='회원')
            {this.noGrade=true}
        }).catch((err)=>{
            console.log(err)
        })
  },
  mounted(){
    getTempProfileInfo(this.id)
      .then((res)=>{
        console.log(res.data)
        this.tempId = res.data.id
        this.name = res.data.name
        this.gender = res.data.gender
        if(res.data.pictureUrl !=null){
        this.pictureUrl = res.data.pictureUrl
        }
        this.city = res.data.city
        console.log(this.pictureUrl)
        // string 형식 date로 바꿔서 나이계산
        let today = new Date();
        this.age = today.getFullYear() - new Date(res.data.birth).getFullYear()+1;
        console.log(new Date(res.data.birth))
        // this.$store.commit("setTempUserId", res.data.id)
      }).catch((err)=>{
        console.log(err)
      })
  },
  methods:{
    clickDm:function(){
      this.$store.commit('setChatOppenent', this.tempId)
      this.$store.commit('setPartnerId', this.tempId)
      this.$store.commit('setChatPath','profile')
      this.$store.commit('setPartnerName',this.name)
      this.$store.commit('setPartnerPicture',this.pictureUrl)
      this.$router.push('/chat')
    },
    imgError:function(e){
            e.target.src = img
        },
    clickName:function(){
      this.$store.commit("setTempUserId", this.tempId)
      this.$router.push('/user')
    },
    clickChat:function(){

    },
    clickBan:function(){
      Swal.fire({
            title: '정말 내보내실 건가요',
            text: "탈퇴된 회원은 다시 초대해야합니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네',
            cancelButtonText: '아니오'
          })
          .then((result) => {
            if(result.isConfirmed){
              const teamId = store.state.teamId
              kickMember(teamId,this.tempId)
              .then((res)=>{
                console.log(res)
                Swal.fire('해당 회원이 탈퇴되었습니다')
                this.$router.go()
              }).catch((err)=>{
                console.log(err)
              })
            }})
    },
    giveManage:function(){
      Swal.fire({
            title: '매니저 권한을 부여하시겠습니까?',
            text: "다시 뺏을 순 있습니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네',
            cancelButtonText: '아니오'
          })
          .then((result) => {
            if(result.isConfirmed){
              const teamId = store.state.teamId
              givePermit(teamId, this.tempId,this.modifyAuth)
              .then((res)=>{
                console.log(res)
                this.$router.go();
              }).catch((err)=>{
                console.log(this.modifyAuth)
                console.log(err)
              })
            }})
    }
  }
  
}
</script>

<style>

</style>