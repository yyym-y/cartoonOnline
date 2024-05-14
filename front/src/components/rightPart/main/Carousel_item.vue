<template>
  <div>
    <VideoItem
        class="carousel-video-item"
        :baseInfo="baseInfo.cartoon"></VideoItem>
    <img
        class="carousel-img-item"
        :src="absUrl"
        style="object-fit: cover;"
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
        console.log(this.absUrl)
    },
    methods: {
        jumpTo() {
            this.$router.push({ name:"video", query:{ 
                cartoonId : this.baseInfo.cartoon.cartoonId,
                cartoonName : this.baseInfo.cartoon.cartoonName,
                cartoonCover : this.baseInfo.cartoon.cartoonCover
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