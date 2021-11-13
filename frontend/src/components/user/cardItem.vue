<template>
    <div v-if="state" class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
        <div class="relative w-full h-80 md:h-64 lg:h-44">
            <!-- <router-link to="/team"> -->
            <button @click="clickTeam">
            <!-- <router-link :to="{'name': 'team', params:{'teamId':this.id}}"> -->
            <img :src=this.pictureDownloadUrl @error="imgError"
                class="w-full h-80 md:w-full md:h-full object-center object-contain">
            <!-- </router-link> -->
            </button>
        </div>
        <div @click="clickTeam" class="px-3 py-4 flex items-center justify-center">
                <a class="bg-yellow-400 py-1 px-2 font-semibold text-black rounded-lg" href="#">
                    {{this.name}}
                </a>
        </div>
    </div>
</template>

<script>
import img from '@/assets/sports.png'
// import store from '@/store/index.js'
import {getTeamInfo} from '@/api/team.js'
export default {
    props:{
        id:[String,Number]
    },
    data(){
        return{
            name : '',
            pictureDownloadUrl : '',
            state:true
        }
    },
    mounted(){
        getTeamInfo(this.id)
        .then((res)=>{
            console.log(res)
            this.name = res.data.name
            if(res.data.pictureDownloadUrl!=null){
            this.pictureDownloadUrl = res.data.pictureDownloadUrl
            }
        }).catch((err)=>{
            console.log(err)
            this.state=false
        })
    },
    methods:{
        clickTeam: function(){
            this.$store.commit('setTeamId',this.id)
            this.$store.commit('setTeamName',this.name)
            this.$router.push('/team')
        },
        imgError:function(e){
            e.target.src = img
        }

    }
}
</script>

<style>

</style>