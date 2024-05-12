<template>
  <div>
    <!-- <img
        class="carousel-img-item"
        :src="absUrl"
        style="object-fit: contain;"
        > -->
    <VideoItem
        class="carousel-video-item"
        :name="baseInfo.cartoonName"
        :coverUrl="baseInfo.cartoonCover"
        :videoId="baseInfo.cartoonId"
        :type="baseInfo.cartoonPermit"></VideoItem>
    <img
        class="carousel-img-item"
        :src="absUrl"
        style="object-fit: cover;"
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