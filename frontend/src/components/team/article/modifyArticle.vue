<template>
  <div class="grid grid-cols-6">

      <div class="col-start-2 col-span-4 ">
          <br>
        <p class="text-3xl font-bold flex justify-center">게시글 수정</p>
          <div class="grid grid-cols-6  mt-10">
              <div class="col-start-1 col-span-6  md:col-start-2 md:col-span-4 shadow-md border-solid border-2 bor    der-yellow-500 rounded-md ml-2">
                  <form @submit.prevent="submitForm">
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">제목</p>
                          <input type="text" v-model="form.title"  class=" text-xl w-3/4 rounded-md border-2 border-yellow-400">
                      </div>
                      <div class="md:pt-5 md:pl-20  pt-5 pl-10">
                          <p class="text-xl font-bold">내용</p>
                          <textarea v-model="form.content" class=" text-xl w-3/4 h-60  rounded-md border-2 border-yellow-400"/>
                          <p v-if="form.content.length>100" class="text-yellow-600">100자 이내로 입력해주세요</p>
                      </div>
                      <div class="flex justify-center p-2 mt-10">
                        <button type="submit" :disabled="!btnDisabled" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">수정</button>
                        <router-link to="/team/articleDetail">
                            <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">취소</button>
                        </router-link>
                    </div>       
                  </form>
              </div>
          </div>

      </div>
  </div>
</template>

<script>
import { modifyArticleInfo } from '@/api/article.js'
import Swal from 'sweetalert2'
import store from '@/store/index.js'
import { getArticleInfo } from '@/api/article.js'
export default {
    props:{
        boardId: [String, Number]
    },
    data() {
        return {
            form:{
                title:'',
                content:''
            }
            
        }
    },
    created(){
        console.log(this.userId)
        const teamId = store.state.teamId
        // this.$store.commit("setBoardId",this.boardId)
        const boardId = store.state.boardId
        getArticleInfo(teamId,boardId).
        then((res)=>{
            console.log(res)
            this.form.title = res.data.title
            this.form.content = res.data.content
            // this.$store.commit("setBoardId",this.boardId)
        }).catch((err)=>{
            console.log(err)
        })
    },
    computed: {
        btnDisabled(){
            if(!this.form.title || (this.form.content.length>100) || (this.form.content.length ==0) ){
                return false
            }
            return true
        },
    },
    methods:{
        submitForm: function(){
            const teamId = store.state.teamId
            const boardId = store.state.boardId
            modifyArticleInfo(teamId,boardId,this.form)
            .then((res)=>{
                console.log(res)
                Swal.fire('수정 완료!')
                this.$router.push('/team/articleList')
            }).catch((err)=>{
                console.log(err)
            })
        },

    }

}
</script>

<style>

</style>