<template>
  <div class="shadow-md rounded-xl p-4 h-36 sm:h-24 mt-5 mx-6 mb-4 min-w-300">
    <div class="grid grid-cols-7">
      <div class="col-start-1 col-span-3 sm:col-start-1 sm:col-span-1">
          <img :src=pictureUrl class="h-24 w-24 rounded-2xl md:h-20 md:w-20" @error="imgError">
      </div>
      <div class="ml-1 md:ml-0 col-start-4 col-span-4 sm:col-start-2 sm:col-span-5 grid grid-cols-4 sm:place-items-center">
            <div class="col-start-1 col-span-4 sm:col-start-1 sm:col-span-1">
                <button class="h-1" @click="clickName" :disabled="id==myId">
                {{name}}
                </button>
            </div>
            <div class="col-start-1 col-span-4 sm:col-start-2 sm:col-span-1">
                <p>
                {{age}}세
                </p>
            </div>
            <div class="col-start-1 col-span-4 sm:col-start-1 sm:col-span-1">
            {{city}}
            </div>
            <div class="col-start-1 col-span-4 sm:col-start-2 sm:col-span-1">
            {{gender}}
            </div>
      </div>
    </div>
  </div>
</template>

<script>
import img from '@/assets/profile.png'
import { getTempProfileInfo } from '@/api/auth.js'
import store from '@/store/index.js'
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
      this.$router.push('/chat')
    },
    imgError:function(e){
            e.target.src = img
        },
    clickName:function(){
      this.$store.commit("setTempUserId", this.tempId)
      this.$router.push('/user')
    },



  }
  
}
</script>

<style>

</style>