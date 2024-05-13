import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '@/views/Main_view.vue'
import videoView from '@/views/Video_view.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', name: "home", component: MainView },
  { path: '/video', name: "video", component: videoView },
]

const router = new VueRouter({
  routes
})

export default router
