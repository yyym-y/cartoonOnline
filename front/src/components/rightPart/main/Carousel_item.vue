<template>
  <div>
    <VideoItem
        class="carousel-video-item"
        :baseInfo="baseInfo.cartoon"></VideoItem>
    <img
        class="carousel-img-item"
        :src="absUrl"
        style="object-fit: contain;"
        @click="jumpTo()"
        >
  </div>
  
</template>

<script>
import VideoItem from '@/components/util/Video_item'

export default {
    name : "Carousel_item",
    props : ["baseInfo"],
    components : {
        VideoItem
    },
    data() {
        return {
            absUrl : "",
        }
    },
    mounted() {
        this.absUrl = this.$baseURL + this.baseInfo.carouselCover
    },
    methods: {
        jumpTo() {
            if (localStorage.getItem("type") == -1) {
                this.$confirm("你还未登录, 请登录体验完整功能~", "提示", {
                }).then(()=>{ this.$router.push({ name: "home"}, () => {}) })
                .catch(()=>{ this.$router.push({ name: "home"}, () => {}) })
                return;
            }
            if (localStorage.getItem("type") == 1 && this.baseInfo.cartoon.cartoonPermit == 1) {
                this.$confirm("该影片为VIP影片,您是普通用户，请先升级账户~", "提示", {
                }).then(()=>{ this.$router.push({ name: "home"}, () => {}) })
                .catch(()=>{ this.$router.push({ name: "home"}, () => {}) })
                return;
            }
            this.$router.push({ name:"video", query:{ 
                cartoonId : this.baseInfo.cartoonId,
                cartoonName : this.baseInfo.cartoonName,
                cartoonCover : this.baseInfo.cartoonCover,
                cartoonPermit : this.baseInfo.cartoonPermit
            } })
        }
    }
}
</script>

<style scoped>
.carousel-img-item{
    height: 24em;
    width: 100%;
}
.carousel-video-item {
    float: right;
    margin-right: 5vw;
    margin-top: 30px;
    position: absolute;
    transform: translate(62vw);
}
</style>