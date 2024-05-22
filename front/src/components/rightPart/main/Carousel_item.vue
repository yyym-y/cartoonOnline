<!-- 
    走马灯中展示的数据
    传入数据：
    1. {
        cartoon : {cartoonId: '', cartoonName: '', cartoonCover: '', cartoonPermit: 0}
        carouselCover : ''
    }
 -->
<template>
  <div>
    <VideoItem class="carousel-video-item"
        :cartoonBaseInfo="carouselBaseInfo.cartoon" ref="carousel_item"></VideoItem>
    <img class="carousel-img-item" :src="absUrl"
        style="object-fit: contain;" @click="jumpTo()" >
  </div>
  
</template>

<script>
import VideoItem from '@/components/util/Video_item'

export default {
    name : "Carousel_item",
    props : ["carouselBaseInfo"],
    components : {
        VideoItem
    },
    data() {
        return {
            absUrl : "",
        }
    },
    mounted() {
        this.absUrl = this.$baseURL + this.carouselBaseInfo.carouselCover
    },
    methods: {
        jumpTo() {
            this.$refs.carousel_item.jumpTo();
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