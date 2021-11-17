import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

export default new Vuex.Store({
  plugins:[createPersistedState()],
  state: {
    myPicture:'',
    chatPath:'',
    accessToken: '',
    userId: '',
    userName:'',
    tempUserId:'',
    teamId: '',
    boardId: '',
    commentId:'',
    matchCommentId:'',
    postId:'',
    calendarId:'',
    teamName:'',
    selectCity: '',
    selectSportCategory: '',
    auth:'',
    category:'',
    myCity:'',
    mySportCategory:'',
    chatOppenent:'',
    roomId:'',
    partnerName:'',
    partnerPicture:'',
    partnerId:'',
  },
  mutations: {
    setMyPicture(state,myPicture){
      state.myPicture = myPicture
    },
    setChatPath(state,chatPath){
      state.chatPath = chatPath
    },
    setPartnerId(state,partnerId){
      state.partnerId = partnerId
    },
    setPartnerName(state,partnerName){
      state.partnerName = partnerName
    },
    setPartnerPicture(state,partnerPicture){
      state.partnerPicture = partnerPicture
    },
    setRoomId(state,roomId){
      state.roomId = roomId
    },
    setChatOppenent(state,chatOppenent){
      state.chatOppenent = chatOppenent
    },
    setMySportCategory(state,mySportCategory){
      state.mySportCategory = mySportCategory
    },
    setMyCity(state,myCity){
      state.myCity = myCity
    },
    setAuth(state,auth){
        state.auth = auth
    },
    setCategory(state,category){
        state.category = category
    },
    setMatchingId(state,postId){
        state.postId = postId
    },
    setMatchCommentId(state,matchCommentId){
        state.matchCommentId = matchCommentId
    },
    setAccessToken(state,accessToken){
        state.accessToken = accessToken
    },
    setUserId(state, userId){
        state.userId = userId
    },
    setUserName(state, userName){
        state.userName = userName
    },
    setTempUserId(state, tempUserId){
        state.tempUserId = tempUserId
    },
    setTeamId(state, teamId){
      state.teamId = teamId
    },
    setBoardId(state,boardId){
      state.boardId = boardId
    },
    setCommentId(state,commentId){
      state.commentId = commentId
    },
    setCalendarId(state,calendarId){
      state.calendarId = calendarId
    },
    setTeamName(state,teamName){
      state.teamName = teamName
    },
    setCity(state,selectCity){
      state.selectCity = selectCity
    },
    setSportCategory(state,selectSportCategory){
      state.selectSportCategory = selectSportCategory
    }
  }
})