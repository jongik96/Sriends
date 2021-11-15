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
                  <div v-if="item.userId==3" class="flex flex-row items-center">
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
                  <div v-if="item.userId==1" class="flex items-center justify-start flex-row-reverse">
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
// import axios from 'axios'
const SERVER_URL = process.env.VUE_APP_SERVER_URL
export default {

    data(){
        return{
            reconnect:0,
            message:'',
            roomId:1,
            opponentId:3,
            userName:'',
            recvList:[],
            Myid:1,
            items:[
                {
                    name:'testA',
                    message:'HiHi',
                    userId:1
                },
                {
                    name:'testB',
                    message:'Hello',
                    userId:3
                }
            ]
        }
    },
    // created(){
    //     this.findRoom();
    // },
    // methods:{
    //     findRoom: function() {
    //         axios.get('/chat/room/'+this.roomId).then(response => { this.room = response.data; });
    //     },
    //     connect:function() {
    //         // pub/sub event
    //         Stomp.connect({}, function() {
    //             Stomp.subscribe("/sub/chat/room/"+this.roomId, function(sendText) {
    //                 var recv = JSON.parse(sendText.body);
    //                 Stomp.recvMessage(recv);
    //             });
    //             Stomp.send("/pub/chat/message", {}, JSON.stringify({type:'ENTER', roomId:this.roomId, sender:this.items.userId}));
    //         }, function(error) {
    //             console.log(error)
    //             if(this.reconnect++ <= 5) {
    //                 setTimeout(function() {
    //                     console.log("connection reconnect");
    //                     sock = new SockJS(`${SERVER_URL}/ws-stomp`);
    //                     Stomp = Stomp.over(sock);
    //                     this.connect();
    //                 },10*1000);
    //             }
    //         });
    //     },
    //     recvMessage: function(recv) {
    //         this.messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
    //     }
    // }
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
            const msg = { 
            // userName: this.userName,
            type: 'TALK',
            roomId: 1,
            message: this.message,
            sender: 1
            };
            this.stompClient.send(`${SERVER_URL}/pub/chat/message`, JSON.stringify(msg), {});
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
                this.stompClient.subscribe(`${SERVER_URL}/sub/chat/room`, res => {
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