<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <p class="text-2xl">댓글</p>
            <div>
                <textarea v-model="content" id="comment" rows=3 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
                 <p v-if="content.length>100"> 100자 이하로 작성 가능합니다</p>
            </div>
            <div class="flex justify-end">
                <button @click="postComment" :disabled="btnDisabled" class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            댓글 작성하기
                </button>
            </div>
        </div>
  </div>
</template>

<script>
import { postArticleComments } from '@/api/matchComment.js'
import store from '@/store/index.js'
import Swal from 'sweetalert2'
export default {
    props:{
        boardId: [String, Number]
    },
    data(){
        return {
            content: '',
            parentId: 0
        }
    },
    methods:{
        postComment: function(){
            const postId = store.state.postId
            postArticleComments(postId,this.parentId,this.content)
            .then((res)=>{
                console.log(res)
                Swal.fire('댓글이 작성되었습니다.')
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
                console.log(this.content, this.parentId)
            })
        }
    },
    computed:{
        btnDisabled(){
            if((this.content.length>100) || (this.content.length ==0) ){
                return true
            }else{
                return false
            }
        }
    }
}
</script>

<style>

</style>