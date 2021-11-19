<template>
  <div class="grid grid-cols-6">
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4 border-b px-4 py-2 bg-white mt-10">
            <div>
                <p class="sm:text-3xl text-xl font-extrabold text-gray-900">경기/게스트 찾기</p>
            </div>
            <div class="mt-3 sm:mt-10">
                <form>
                    <div class="grid grid-cols-6">
                        <div class="md:col-start-1 md:col-span-3 col-start-1 col-span-6 mb-3">
                            <p class="text-base sm:text-2xl">글 분류를 선택해주세요</p>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md mt-3" v-model="form.category">
                                <option disabled>전체</option>
                                <option value="매칭">경기 매칭</option>
                                <option value="용병">게스트 모집</option>
                            </select>

                        </div>
                        <div class="md:col-start-1 md:col-span-3 col-start-1 col-span-3 sm:border-r border-yellow-300">
                            <p class="text-base sm:text-2xl">지역을 선택해주세요</p>
                            <select class="border-2 border-solid border-yellow-500 rounded-md mt-3" v-model="selectDo">
                            <option disabled value="">지역</option>
                            <option value="1">서울/인천/경기</option>
                            <option value="2">대전/충청</option>
                            <option value="3">대구/경북</option>
                            <option value="4">강원</option>
                            <option value="5">부산/울산/경남</option>
                            <option value="6">제주</option>
                            <option value="7">광주/전라</option>
                            </select>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md ml-0 sm:ml-3" v-if="this.selectDo=='1'" v-model="form.city">
                                <option disabled value="">시/군</option>
                                <option value="서울특별시">서울특별시</option>
                                <option value="인천광역시">인천광역시</option>
                                <option value="고양시">고양시</option>
                                <option value="과천시">과천시</option>
                                <option value="광명시">광명시</option>
                                <option value="광주시">광주시</option>
                                <option value="구리시">구리시</option>
                                <option value="군포시">군포시</option>
                                <option value="김포시">김포시</option>
                                <option value="남양주시">남양주시</option>
                                <option value="동두천시">동두천시</option>
                                <option value="부천시">부천시</option>
                                <option value="성남시">성남시</option>
                                <option value="수원시">수원시</option>
                                <option value="시흥시">시흥시</option>
                                <option value="안산시">안산시</option>
                                <option value="안성시">안성시</option>
                                <option value="안양시">안양시</option>
                                <option value="양주시">양주시</option>
                                <option value="여주시">여주시</option>
                                <option value="오산시">오산시</option>
                                <option value="용인시">용인시</option>
                                <option value="의왕시">의왕시</option>
                                <option value="의정부시">의정부시</option>
                                <option value="이천시">이천시</option>
                                <option value="파주시">파주시</option>
                                <option value="평택시">평택시</option>
                                <option value="포천시">포천시</option>
                                <option value="하남시">하남시</option>
                                <option value="화성시">화성시</option>
                                <option value="가평군">가평군</option>
                                <option value="양평군">양평군</option>
                                <option value="연천군">연천군</option>
                            </select>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md ml-0 sm:ml-3" v-if="this.selectDo=='2'" v-model="form.city">
                                <option disabled value="">시/군</option>
                                <option value="대전광역시">대전광역시</option>
                                <option value="세종특별자치시">세종특별자치시</option>
                                <option value="계룡시">계룡시</option>
                                <option value="공주시">공주시</option>
                                <option value="논산시">논산시</option>
                                <option value="당진시">당진시</option>
                                <option value="보령시">보령시</option>
                                <option value="서산시">서산시</option>
                                <option value="아산시">아산시</option>
                                <option value="천안시">천안시</option>
                                <option value="제천시">제천시</option>
                                <option value="청주시">청주시</option>
                                <option value="충주시">충주시</option>
                                <option value="금산군">금산군</option>
                                <option value="부여군">부여군</option>
                                <option value="서천군">서천군</option>
                                <option value="예산군">예산군</option>
                                <option value="청양군">청양군</option>
                                <option value="태안군">태안군</option>
                                <option value="홍성군">홍성군</option>
                                <option value="괴산군">괴산군</option>
                                <option value="단양군">단양군</option>
                                <option value="보은군">보은군</option>
                                <option value="영동군">영동군</option>
                                <option value="옥천군">옥천군</option>
                                <option value="음성군">음성군</option>
                                <option value="증평군">증평군</option>
                                <option value="진천군">진천군</option>
                            </select>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md ml-0 sm:ml-3" v-if="this.selectDo=='3'" v-model="form.city">
                                <option disabled value="">시/군</option>
                                <option value="대구광역시">대구광역시</option>
                                <option value="경산시">경산시</option>
                                <option value="경주시">경주시</option>
                                <option value="구미시">구미시</option>
                                <option value="김천시">김천시</option>
                                <option value="문경시">문경시</option>
                                <option value="상주시">상주시</option>
                                <option value="안동시">안동시</option>
                                <option value="영주시">영주시</option>
                                <option value="영천시">영천시</option>
                                <option value="포항시">포항시</option>
                                <option value="고령군">고령군</option>
                                <option value="군위군">군위군</option>
                                <option value="봉화군">봉화군</option>
                                <option value="성주군">성주군</option>
                                <option value="영덕군">영덕군</option>
                                <option value="영양군">영양군</option>
                                <option value="예천군">예천군</option>
                                <option value="울릉군">울릉군</option>
                                <option value="울진군">울진군</option>
                                <option value="의성군">의성군</option>
                                <option value="청도군">청도군</option>
                                <option value="청송군">청송군</option>
                                <option value="칠곡군">칠곡군</option>
                            </select>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md ml-0 sm:ml-3" v-if="this.selectDo=='4'" v-model="form.city">
                                <option disabled value="">시/군</option>
                                <option value="강릉시">강릉시</option>
                                <option value="동해시">동해시</option>
                                <option value="삼척시">삼척시</option>
                                <option value="속초시">속초시</option>
                                <option value="원주시">원주시</option>
                                <option value="춘천시">춘천시</option>
                                <option value="태백시">태백시</option>
                                <option value="고성군">고성군</option>
                                <option value="양구군">양구군</option>
                                <option value="양양군">양양군</option>
                                <option value="영월군">영월군</option>
                                <option value="인제군">인제군</option>
                                <option value="정선군">정선군</option>
                                <option value="철원군">철원군</option>
                                <option value="평창군">평창군</option>
                            </select>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md ml-0 sm:ml-3" v-if="this.selectDo=='5'" v-model="form.city">
                                <option disabled value="">시/군</option>
                                <option value="부산광역시">부산광역시</option>
                                <option value="울산광역시">울산광역시</option>
                                <option value="거제시">거제시</option>
                                <option value="김해시">김해시</option>
                                <option value="밀양시">밀양시</option>
                                <option value="사천시">사천시</option>
                                <option value="양산시">양산시</option>
                                <option value="진주시">진주시</option>
                                <option value="창원시">창원시</option>
                                <option value="통영시">통영시</option>
                                <option value="거창군">거창군</option>
                                <option value="고성군">고성군</option>
                                <option value="남해군">남해군</option>
                                <option value="산청군">산청군</option>
                                <option value="의령군">의령군</option>
                                <option value="창녕군">창녕군</option>
                                <option value="하동군">하동군</option>
                                <option value="함안군">함안군</option>
                                <option value="함양군">함양군</option>
                                <option value="합천군">합천군</option>
                            </select>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md  ml-0 sm:ml-3" v-if="this.selectDo=='6'" v-model="form.city">
                                <option disabled value="">시/군</option>
                                <option value="제주시">제주시</option>
                                <option value="서귀포시">서귀포시</option>
                            </select>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md ml-0 sm:ml-3" v-if="this.selectDo=='7'" v-model="form.city">
                                <option disabled value="">시/군</option>
                                <option value="광주광역시">광주</option>
                                <option value="광양시">전주</option>
                                <option value="광양시">군산</option>
                                <option value="광양시">익산</option>
                                <option value="광양시">남원</option>
                                <option value="광양시">정읍</option>
                                <option value="광양시">김제</option>
                                <option value="광양시">완주</option>
                                <option value="광양시">진안</option>
                                <option value="광양시">무주</option>
                                <option value="광양시">장수</option>
                                <option value="광양시">임실</option>
                                <option value="광양시">순창</option>
                                <option value="광양시">고창</option>
                                <option value="광양시">부안</option>
                                <option value="광양시">광양</option>
                                <option value="나주시">나주</option>
                                <option value="목포시">목포</option>
                                <option value="순천시">순천</option>
                                <option value="여수시">여수</option>
                                <option value="강진시">강진</option>
                                <option value="고흥시">고흥</option>
                                <option value="곡성시">곡성</option>
                                <option value="구례시">구례</option>
                                <option value="담양시">담양</option>
                                <option value="무안시">무안</option>
                                <option value="보성시">보성</option>
                                <option value="신안시">신안</option>
                                <option value="영광시">영광</option>
                                <option value="영암시">영암</option>
                                <option value="완도시">완도</option>
                                <option value="장성시">장성</option>
                                <option value="장흥시">장흥</option>
                                <option value="진도시">진도</option>
                                <option value="함평시">함평</option>
                                <option value="해남시">해남</option>
                                <option value="화순시">화순</option>
                            </select>
                            <br/>
                            <p v-if="this.form.city" class="mt-2 text-base md:text-xl font-medium">선택 : {{ this.form.city }} </p>
                        </div>
                        <div class="md:col-start-4 md:col-span-3 col-start-4 col-span-3  ml-0 md:ml-3">
                            <p class="text-base sm:text-2xl">종목을 선택해주세요</p>
                            <select multiple="multiple" class="border-2 border-solid border-yellow-500 rounded-md mt-3" v-model="form.sportCategory">
                            <option disabled>전체</option>
                            <option value="풋살">축구/풋살</option>
                            <option value="배구">배구</option>
                            <option value="농구">농구</option>
                            <option value="배드민턴">배드민턴</option>
                            <option value="야구">야구</option>
                            <option value="테니스">테니스</option>
                            <option value="탁구">탁구</option>
                            <option value="기타">기타</option>
                        </select>
                        <p v-if="this.form.sportCategory" class="mt-2 text-xl font-medium">선택종목 : {{ this.form.sportCategory }} </p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="mt-3 sm:mt-10 flex justify-end">
                <!-- <router-link :to="{'name':'searchTeamList', props:{'city':this.form.city, 'sportCategory': this.form.sportCategory}}">
                <button class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">조회하기</button>
                </router-link> -->
                <button @click="clickBtn()" class="border-solid border-2 border-yellow-500 rounded-md hover:bg-yellow-400 w-20 h-10">조회하기</button>
            </div>
        </div>
        <div class="col-start-1 col-span-6 md:col-start-2 md:col-span-4">
            {{myCity}} 의 전체 스렌즈 조회
            <defaultList></defaultList>
        </div>
  </div>
</template>

<script>
import defaultList from '@/components/matching/defaultList.vue'
import store from '@/store/index.js'
export default {
    components:{
        defaultList
    },
    data(){
        return{
            selectDo: '',
            myCity:store.state.myCity,
            form:{
                category:'',
                city: '',
                sportCategory: '',
            }
        }
    },
    methods:{
        clickBtn(){
            this.$store.commit("setCity",this.form.city)
            this.$store.commit("setSportCategory",this.form.sportCategory)
            this.$store.commit("setCategory",this.form.category)
            this.$router.push('/matchingList')
        }
    }
}
</script>

<style>

</style>