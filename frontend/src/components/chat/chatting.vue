<template>
<div class="flex antialiased text-gray-800">
    <div class="flex flex-row h-full w-full  overflow-x-hidden">
      <div class="flex flex-col flex-auto  h-full p-6">
        <div
          class="flex flex-col flex-auto flex-shrink-0 rounded-2xl  bg-gray-100 h-full p-4"
        >
          <div class="flex flex-col h-120 overflow-x-auto mb-4">
            <div class="flex flex-col  h-full">
              <div class="grid grid-cols-12 gap-y-2">
                <!-- <div class="col-start-1 col-end-8 p-3 rounded-lg">
                  <div class="flex flex-row items-center">
                    <div
                      class="flex items-center justify-center h-10 w-10 rounded-full bg-yellow-200 flex-shrink-0"
                    >
                      A
                    </div>
                    <div
                      class="relative ml-3 text-sm bg-white py-2 px-4 shadow rounded-xl"
                    >
                      <div>Hey How are you today?</div>
                    </div>
                  </div>
                </div>

                <div class="col-start-6 col-end-13 p-3 rounded-lg">
                  <div class="flex items-center justify-start flex-row-reverse">
                    <div
                      class="flex items-center justify-center h-10 w-10 rounded-full bg-yellow-500 flex-shrink-0"
                    >
                      A
                    </div>
                    <div
                      class="relative mr-3 text-sm bg-yellow-300 py-2 px-4 shadow rounded-xl"
                    >
                      <div>I'm ok what about you?</div>
                    </div>
                  </div>
                </div> -->
                <div v-for="item in recvList" :key="item.id" class="col-start-1 col-end-8 p-3 rounded-lg">
                  <div v-if="item.sender==56" class="flex flex-row items-center">
                    <div
                      class="flex items-center justify-center h-10 w-10 rounded-full bg-yellow-200 flex-shrink-0"
                    >
                      {{item.name}}
                    </div>
                    <div
                      class="relative ml-3 text-sm bg-white py-2 px-4 shadow rounded-xl"
                    >
                      <div>{{item.message}}</div>
                    </div>
                  </div>
                </div>
                <div v-for="item in recvList" :key="item.id" class="col-start-6 col-end-13 p-3 rounded-lg">
                  <div v-if="item.sender==58" class="flex items-center justify-start flex-row-reverse">
                    <div
                      class="flex items-center justify-center h-10 w-10 rounded-full bg-yellow-500 flex-shrink-0"
                    >
                      {{item.name}}
                    </div>
                    <div
                      class="relative mr-3 text-sm bg-yellow-300 py-2 px-4 shadow rounded-xl"
                    >
                      <div>{{item.message}}</div>
                    </div>
                  </div>
                </div>




              </div>
            </div>
          </div>
          <div
            class="flex flex-row items-center h-16 rounded-xl bg-white w-full px-4"
          >
            <div>
            </div>
            <div class="flex-grow ml-4">
              <div class="relative w-full">
                <input
                  type="text" v-model="message"
                  class="flex w-full border rounded-xl focus:outline-none focus:border-indigo-300 pl-4 h-10"
                />
              </div>
            </div>
            <div class="ml-4">
              <button @click="sendMessage"
                class="flex items-center justify-center bg-yellow-500 hover:bg-yellow-600 rounded-xl text-white px-4 py-1 flex-shrink-0"
              >
                <span>Send</span>
                <span class="ml-2">
                  <svg
                    class="w-4 h-4 transform rotate-45 -mt-px"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"
                    ></path>
                  </svg>
                </span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
// import store from '@/store/index.js'
// import axios from 'axios'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
export default {

    data(){
        return{
            reconnect:0,
            message:'',
            roomId:2,
            opponentId:3,
            userName:56,
            recvList:[],
            Myid:1,
            // items:[
            //     {
            //         name:'testA',
            //         message:'HiHi',
            //         userId:1
            //     },
            //     {
            //         name:'testB',
            //         message:'Hello',
            //         userId:3
            //     }
            // ]
        }
    },

    created() {
        // App.vue가 생성되면 소켓 연결을 시도합니다.
        // this.findRoom();
        this.connect()
    },
    methods: {
        sendMessage () {
            this.send()
            //  위의 sendMessage는 유효성검사
            // 조건에 충족하면 아래 send()이벤트 실행
        },    

        send() {
        console.log("Send message:" + this.message);
        // ws = this.stompClient
        if (this.stompClient && this.stompClient.connected) {
            let today = new Date(); 
            const msg = { 
            // userName: this.userName,
            roomId: this.roomId,
            message: this.message,
            sender: this.userName,
            time: today 
            };
            this.stompClient.send(`/pub/chat/message`, JSON.stringify(msg), {});
        }else{
          console.log("not connect!")
        }
        },    

        connect() {
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
                this.stompClient.subscribe('/sub/chat/room/' + this.roomId, res => {
                    console.log('res=>'+res)
                    console.log('subscribe 로 받은 메시지 입니다.', res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    this.recvList.push(JSON.parse(res.body))
                    console.log(this.recvList)
                });
            },
            error => {
            // 소켓 연결 실패
            console.log('소켓 연결 실패', error);
            this.connected = false;
            }
        );        
        }
    }


}



</script>

<style>

</style>