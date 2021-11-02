import Vue from 'vue';
import App from './App.vue';
import router from '@/router/index';
import "@/assets/tailwind.css";
import VueSweetalert2 from 'vue-sweetalert2';
import VCalendar from 'v-calendar';
import "../fontAwesomeIcon.js"
import Notifications from 'vue-notification'
import axios from 'axios'
import AxiosPlugin from 'vue-axios-cors'

Vue.use(AxiosPlugin)
axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

Vue.config.productionTip = false
Vue.use(VueSweetalert2, Notifications);
Vue.use(VCalendar, {
  componentPrefix: 'vc',
});
new Vue({
  render: h => h(App),
  router,

}).$mount('#app')
