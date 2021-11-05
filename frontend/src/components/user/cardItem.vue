<template>
    <div class="bg-white group relative rounded-lg overflow-hidden shadow-lg hover:shadow-2xl transform duration-200">
        <div class="relative w-full h-80 md:h-64 lg:h-44">
            <!-- <router-link to="/team"> -->
            <button @click="clickTeam">
            <!-- <router-link :to="{'name': 'team', params:{'teamId':this.id}}"> -->
            <img :src=this.pictureId alt="@/assets/sideImg.png"
                class="w-full h-full object-center object-cover">
            <!-- </router-link> -->
            </button>
        </div>
        <div class="px-3 py-4 flex items-center justify-center">
                <a class="bg-yellow-400 py-1 px-2 font-semibold text-black rounded-lg" href="#">
                    {{this.name}}
                </a>
        </div>
    </div>
</template>

<script>
// import store from '@/store/index.js'
import {getTeamInfo} from '@/api/team.js'
export default {
    props:{
        id:[String,Number]
    },
    data(){
        return{
            name : '',
            pictureId : '',
        }
    },
    mounted(){
        
        getTeamInfo(this.id)
        .then((res)=>{
            console.log(res)
            this.name = res.data.name
            this.pictureId = res.data.pictureId

        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        clickTeam: function(){
            this.$store.commit('setTeamId',this.id)
            this.$router.push('/team')
        }

    }
}
</script>

<style>

</style>