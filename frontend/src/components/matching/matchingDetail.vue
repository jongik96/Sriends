<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div>
                <div class="border-b">
                    <h2 @click="clickTeam" class="text-2xl font-semibold">{{team.name}}</h2>
                    <div class="float-right" v-if="writer.id==myId">
                        <button @click="clickModify" class="mr-3">수정</button>
                        <button @click="clickDelete" class="mr-3">삭제</button>
                    </div>
                </div>
                <p class="text-justify break-words leading-tight border-b-2 text-gray-800 h-36 mt-10">
                    {{content}}
                <p>
                <div class="grid grid-cols-6 mt-5">
                    <div class="col-start-1 col-span-3">
                        <p>경기일자 : {{playingTime}} </p>
                        <p>경기지역 : {{city}} {{place}}</p>
                        <p>모집인원 : {{recruitmentCount}} 명</p>
                    </div>
                    <div class="col-start-4 col-span-3">
                        <p>분류 : {{matchBoardCategory}}</p>
                        <p>상태 : {{recruited}} </p>
                        <p>종목 : {{sportCategory}}</p>
                    </div>
                </div>
                <div class="mt-2 float-right">
                    <img @click="clickUser" :src="writer.pictureUrl" @error="imgError" class="h-10 w-10 rounded-xl" alt="">
                    <p @click="clickUser" class="">{{writer.name}}</p>
                    <p class="">{{createAt}}</p>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import img from '@/assets/profile.png'
import { getMatchDetail } from '@/api/matching.js'
import store from '@/store/index.js'
import { getDate } from '@/utils/date.js'
import { deleteMatching } from '@/api/matching.js'
import Swal from 'sweetalert2'
export default {
    data(){
        return{
            city:'',
            content:'',
            createAt:'',
            matchBoardCategory:'',
            place:'',
            playingTime:'',
            recruited:'',
            id:'',
            recruitmentCount:'',
            sportCategory:'',
            team:{
                id:'',
                name:'',
                pictureUrl:'',
            },
            writer:{
                id:'',
                name:'',
                pictureUrl:'',
            },
            myId: store.state.userId
        }
    },
    created(){
        const postId = store.state.postId
        getMatchDetail(postId)
        .then((res)=>{
            console.log(res)
            this.city = res.data.city
            this.id = res.data.postId
            this.content = res.data.content
            this.createAt = getDate(res.data.createAt)
            this.matchBoardCategory = res.data.matchBoardCategory
            this.place = res.data.place
            this.playingTime = res.data.playingTime
            if(res.data.recruited == false){
                this.recruited = '대기 중'
            }else{
                this.recruited = '매칭 완료'
            }
            this.recruitmentCount = res.data.recruitmentCount
            this.sportCategory = res.data.sportCategory
            this.team.id = res.data.team.id
            this.team.name = res.data.team.name
            this.team.pictureUrl = res.data.team.pictureUrl
            this.writer.id = res.data.writer.id
            this.writer.name = res.data.writer.name
            if(res.dadta.writer.pictureUrl!=null){
            this.writer.pictureUrl = res.data.writer.pictureUrl
            }
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        clickDelete:function(){
            Swal.fire({
             title: '게시글을 삭제하시겠습니까?',
              text: "삭제한 글은 복구할 수 없습니다.",
               icon: 'warning',
                showCancelButton: true,
                 confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                   confirmButtonText: '네',
                    cancelButtonText: '아니오'
             })
            .then((result) => {
                if(result.isConfirmed){
                    deleteMatching(this.id)
                    .then((res)=>{
                        console.log(res)
                        Swal.fire('삭제되었습니다')
                        this.$router.push('/matchingList')
                    }).catch((err)=>{
                        console.log(err)
                    })
                }})
        },
        clickModify:function(){
            this.$store.commit('setMatchingId',this.id)
            this.$router.push('/modifyMatching')
        },
        clickTeam:function(){
            this.$store.commit('setTeamId', this.team.id)
            this.$router.push('/team')
        },
        clickUser:function(){
            this.$store.commit('setTempUserId', this.writer.id)
            this.$router.push('/user')
        },
        imgError:function(e){
            e.target.src = img
        }
    }
}
</script>

<style>

</style>