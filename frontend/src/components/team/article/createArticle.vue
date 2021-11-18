<template>
  <div class="grid grid-cols-6">

      <div class="col-start-1 col-span-6  md:col-start-2 md:col-span-4">
          <br>
        <p class="text-3xl font-bold flex justify-center">게시글 작성</p>
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
                            <p v-if="form.content.length>400" class="text-yellow-600">400자 이내로 입력해주세요</p>
                        </div>
                        <div class="flex justify-center p-2 mt-10">
                            <button type="submit" :disabled="!btnDisabled"  class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">작성</button>
                            <router-link to="articleList">
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
import { createArticleInfo } from '@/api/article.js'
import Swal from 'sweetalert2'
import store from '@/store/index.js'
export default {
    data() {
        return {
            form:{
                title:'',
                content:''
            }
            
        }
    },
    computed: {
        btnDisabled(){
            if(!this.form.title || (this.form.content.length>400) || (this.form.content.length ==0) ){
                return false
            }
            return true
        },
    },
    methods:{
        submitForm: function(){
            const teamId = store.state.teamId

            createArticleInfo(teamId,this.form)
            .then((res)=>{
                console.log(res)
                this.$store.commit('setBoardId',res.data.id)
                Swal.fire('작성 완료!')
                this.$router.push('/team/articleDetail')
                // this.$router.push('/team/articleDetail')
            }).catch((err)=>{
                console.log(err)
            })
        }        
    },

}
</script>

<style>

</style>