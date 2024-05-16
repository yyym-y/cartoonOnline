// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import axios from 'axios'
import api from './util/index.js'
require('vue-video-player/src/custom-theme.css')
require('video.js/dist/video-js.css')
import VideoPlayer from 'vue-video-player'
import hls from "videojs-contrib-hls"
import VueRouter from 'vue-router'
import router from "./router/index"
 
Vue.prototype.$baseURL = "http://10.252.116.34:8888"

Vue.prototype.$api = api
Vue.prototype.$axios = axios

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(VideoPlayer)
Vue.use(hls);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router
})
