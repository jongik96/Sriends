<template>
  <div class="grid grid-cols-6">
        <div class="col-start-2 col-span-4 border-b px-4 py-2 bg-white mt-10">
            <p class="text-2xl">댓글</p>
            <div>
                <textarea v-model="content" id="comment" rows=3 type="text" class="text-xl w-full rounded-md border-2 border-yellow-400 mt-2"/>
            </div>
            <div class="flex justify-end">
                <button @click="postComment" class="bg-yellow-500 px-2 py-1 
                    text-white font-semibold text-sm rounded block text-center 
                    sm:inline-block mr-5">
                            댓글 작성하기
                </button>
            </div>
        </div>
  </div>
</template>

<script>
import { postArticleComments } from '@/api/comment.js'
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
            const boardId = store.state.boardId
            postArticleComments(boardId,this.content,this.parentId)
            .then((res)=>{
                console.log(res)
                Swal.fire('댓글이 작성되었습니다.')
                this.$router.go()
            }).catch((err)=>{
                console.log(err)
                console.log(this.content, this.parentId)
            })
        }
    }
}
</script>

<style>

</style>