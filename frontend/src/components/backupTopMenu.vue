<template>
  <div class="bg-white shadow-md grid grid-cols-6">
    <nav class="col-start-2 col-span-4 flex items-center justify-between flex-wrap py-6">
      <div class="flex items-center flex-shrink-0 text-yellow-500 mr-6">
        
        <a href="/main"><button class="font-semibold text-3xl">S-riends</button></a>
      </div>
       
      <div class=" sm:hidden">
        <a @mouseover="notifiList = true" @click="clickInfo" @mouseleave="notifiList = false" class="flex items-center cursor-pointer  border rounded text-teal-200 border-teal-400 ">
          <font-awesome-icon icon="bell" class=""/>
       
          <transition name="fade">
              
            <ul v-if="notifiList" @click="notifiList = false" class="fixed border-2 mr-10 mt-96 w-60 h-96  bg-white">
              <li>
                a
              </li>
              <li>
               b
              </li>
              <li>
                c
              </li>
              <li>
               d
              </li>

            </ul>
          
          </transition>
        </a>
      </div>
      <div class=" sm:hidden">
        <a @mouseover="smallList = true" @click="clickInfo" @mouseleave="smallList = false" class="flex items-center  border rounded text-teal-200 border-teal-400 ">
          <svg class="fill-current h-5 w-5" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/></svg>
       
          <transition name="fade">
              
            <ul v-if="smallList" @click="smallList = false" class="fixed border-2 mr-10 mt-32 w-60 bg-white">
              <li>
                <router-link to="/teamList"><p class="hover:bg-yellow-500">스렌즈 찾기</p></router-link>
              </li>
              <li>
                <router-link to="/matchingCategory"><p class="hover:bg-yellow-500">경기/스렌드 찾기</p></router-link>
              </li>
              <li>
                <router-link to="/main"><p class="hover:bg-yellow-500">My S-riends</p></router-link>
              </li>
              <li>
                <router-link to="/chatList"><p class="hover:bg-yellow-500">Chat</p></router-link>
              </li>
              <!-- <li>
                <router-link to="/modify"><p class="hover:bg-yellow-500">정보 수정</p></router-link>
              </li> -->
              <li>
               <button @click="logout" class="hover:bg-yellow-500">로그아웃</button>
              </li>
            </ul>
          
          </transition>
        </a>
      </div>
      
      <div class="hidden sm:flex sm:items-center sm:w-auto">
        <div class="text-md">
          <router-link to="/teamList">
            <p class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200 w-22 mr-4">
            스렌즈 찾기
            </p>
          </router-link>
          <router-link to="/matchingCategory">
            <p class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-32 mr-4">
              경기/스렌드 찾기
            </p>
          </router-link>
          <router-link to="/main">
            <p class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-23 mr-4">
              My S-riends
            </p>
          </router-link>
          <router-link to="/chatList">
            <font-awesome-icon icon="comments"/>
          </router-link>
          <span class="">
            <a @mouseover="smallList = true" @click="clickInfo" @mouseleave="smallList = false" class="cursor-pointer ml-3 border rounded text-teal-200 border-teal-400 ">
              <font-awesome-icon icon="bell" class=""/>
          
              <transition name="fade">
                  
                <ul v-if="smallList" class="fixed border-2 ml-80 w-72 min-h-96 h-96 bg-white">
                  <div class="mb-2">
                    <span class="cursor-default">전체알림 확인하기</span> <button @click="readAllNotice"><font-awesome-icon icon="check-circle"/></button>
                    <button class="ml-3" @click="clickDeleteAll"><font-awesome-icon icon="trash-alt"/></button>
                    
                  </div>
                  <noticeItem v-for="item in recvlist" :key="item.id"
                    :content="item.content"
                    :type="item.type"
                    :postId="item.postId"
                    :createAt="item.createAt"
                    :checked="item.checked"
                    :notificationId="item.notificationId"
                  >
                  </noticeItem>
                  
                </ul>
              
              </transition>
            </a>
          </span>
          <button @click="logout()">
            <p class="ml-3 text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-23 mr-4">
              로그아웃
            </p>
          </button>
          <!-- <a href="#responsive-header" class="block mt-4 sm:inline-block hover:bg-yellow-500 rounded-md lg:mt-0 text-teal-200 h-10 w-20">
            회원관리
          </a> -->
          <!-- <a @mouseover="listOne = true" @click="clickInfo" @mouseleave="listOne = false" class="text-center block mt-4 sm:inline-block hover:bg-yellow-400 rounded-md lg:mt-0 text-teal-200  w-20">
            My Info
          <transition name="fade">
            
            <ul v-if="listOne" @click="listOne = false" class="fixed border-2 w-20">
              
              <li><button @click="logout" class="text-center bg-white text-md p-1">로그아웃</button></li>
              <li><router-link to="/modify"><p class="text-center bg-white text-md p-1">정보수정</p></router-link></li>
            </ul>
          
          </transition></a> -->
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import noticeItem from '@/components/notice/noticeItem.vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

// 알림관련
import { putAllNotice } from '@/api/auth.js'
// import { putNotice } from '@/api/auth.js'
import { deleteAllNotice } from '@/api/auth.js'
// import { deleteNotice } from '@/api/auth.js'

const SERVER_URL = process.env.VUE_APP_SERVER_URL
import store from '@/store/index.js'
import { getNotice } from '@/api/auth.js'
export default {
  components:{
    noticeItem
  },
  data(){
    return{
      notifiList:false,
      listOne: false,
      smallList: false,
      recvlist:[],
      status:'',
      connected:''
    }
  },
  created(){
    this.connect();
    getNotice()
    .then((res)=>{
      console.log(res)
      this.recvlist = res.data
    }).catch((err)=>{
      console.log(err)
    })
  },
  computed:{

  },
  methods:{
    clickDeleteAll:function(){
      deleteAllNotice()
      .then((res)=>{
        console.log(res.data)
        this.recvlist = res.data
      }).catch((err)=>{
        console.log(err)
      })
    },
    readAllNotice:function(){
      putAllNotice()
      .then((res)=>{
        console.log(res.data)
        this.recvlist = res.data
      }).catch((err)=>{
        console.log(err)
      })
    },
    clickInfo: function(){
      if(this.listOne == false){
        this.listOne == true;
      }else{
        this.listOne == false;
      }
    },
    // clickNotice: function(){
    //   if(this.notifiList == false){
    //     this.notifiList == true;
    //   }else{
    //     this.notifiList == false;
    //   }
    // },
    logout: function(){
      localStorage.removeItem('vuex')
      this.disconnect()
      this.$router.push('/')
      
    },
    connect() {
          const userId = store.state.userId
          let socket = new SockJS(`${SERVER_URL}/ws-stomp`);
          this.stompClient = Stomp.over(socket);
          console.log(`소켓 연결을 시도합니다. 서버 주소:'https://k5d106.p.ssafy.io:/api/ws-stomp'`)
          this.stompClient.connect(
              {},
              frame => {
              // 소켓 연결 성공
              this.connected = true;
              console.log('소켓 연결 성공', frame);
              // 서버의 메시지 전송 endpoint를 구독합니다.
              // 이런형태를 pub sub 구조라고 합니다.
                  this.stompClient.subscribe(`/topic/${userId}`, res => {
                      console.log('subscribe 로 받은 메시지 입니다.', res.body);

                      // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                      this.recvlist.push(JSON.parse(res.body))
                      console.log(this.recvList)
                  });
              },
              error => {
              // 소켓 연결 실패
              console.log('소켓 연결 실패', error);
              this.connected = false;
              }
          );        
        },
    disconnect(){
      let socket = new SockJS(`${SERVER_URL}/ws-stomp`);
      socket.close();
      this.connected=false;
      this.recvlist=[];
      console.log('소켓연결해제')
    }
  }

}
</script>

<style>

</style>