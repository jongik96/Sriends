import Vue from 'vue';
import App from './App.vue';
import router from '@/router/index';
import "@/assets/tailwind.css";
import VueSweetalert2 from 'vue-sweetalert2';
import VCalendar from 'v-calendar';
import "../fontAwesomeIcon.js"

Vue.config.productionTip = false
Vue.use(VueSweetalert2);
Vue.use(VCalendar, {
  componentPrefix: 'vc',
});
new Vue({
  render: h => h(App),
  router,

}).$mount('#app')
