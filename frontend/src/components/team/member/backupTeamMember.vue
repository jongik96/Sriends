<template>
  <div class="shadow-md rounded-xl p-4 mt-5 mx-6 mb-4 min-w-300">
    <div class="grid grid-cols-7">
      <div class="col-start-1 col-span-1">
          <img :src=pictureUrl class="h-10 w-10 rounded-2xl md:h-20 md:w-20" alt="">
      </div>
      <div class="ml-1 md:ml-0 col-start-2 col-span-1 grid sm:place-items-center">
          <button class="h-1" @click="clickName" :disabled="id==myId">
          {{name}}
          </button>
          <br>
          {{gender}}
          <br>
      </div>
      <div class=" sm:visible col-start-3 col-span-2 grid sm:place-items-center ml-1 sm:ml-0">
        {{city}}
        <br/>
        {{age}}세
      </div>
      <div 
       class=" sm:visible col-start-5 col-span-2 grid sm:place-items-center">
          <div v-if="(myId!==id) && (authority=='회원')">
          <button  @click="giveManage" class="border-2 border-yellow-600 rounded-md w-12 sm:w-20">매니저 위임</button>
          </div>
          <button disabled  class="border-2 border-yellow-600 rounded-md w-12 sm:w-20">{{authority}}</button>
      </div>
      <!-- <div  
       class=" sm:visible col-start-5 col-span-1 grid sm:place-items-center">
          
      </div> -->
      <div v-if="myId!==id" class="col-start-7 col-span-1">
          <div class="flex justify-end">
          <button><font-awesome-icon icon="comments"/></button>
          </div>
          <div class="flex justify-end mt-5">
          <button @click="clickBan"><font-awesome-icon icon="ban"/></button>
          </div>
      </div>
      <!-- <div v-if="myId!==id" class="col-start-7 col-span-1 flex justify-center">
          
      </div> -->
    </div>
  </div>
</template>

<script>
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
      Myauth:'',
    }
  },
  created(){
    const teamId = store.state.teamId
    getPermitState(teamId)
        .then((res)=>{
            console.log(res)
            this.Myauth = res.data.authority
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
        this.pictureUrl = res.data.pictureUrl
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
              givePermit(teamId, this.tempId)
              .then((res)=>{
                console.log(res)
                this.$router.go();
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