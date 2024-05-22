import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '@/views/Main_view.vue'
import videoView from '@/views/Video_view.vue'
import DataView from '@/views/Data_view.vue'
import RankView from '@/views/Rank_view.vue'
import SearchView from '@/views/Search_view.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', name: "home", component: MainView },
  { path: '/video', name: "video", component: videoView },
  { path: '/data', name: "data", component: DataView },
  { path: '/rank', name: "rank", component: RankView },
  { path: '/search', name: "search", component: SearchView },
]

const router = new VueRouter({
  routes
})

export default router
