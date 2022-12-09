import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'

Vue.config.productionTip = false
axios.defaults.baseURL = 'http://服务器IP地址:端口号'
Vue.prototype.$http = axios

new Vue({
  render: h => h(App)
}).$mount('#app')
