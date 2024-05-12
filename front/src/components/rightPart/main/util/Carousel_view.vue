<template>
  <el-carousel height="24em">
    <el-carousel-item v-for="(item, index) in carouselList" :key="item + index">
      <CarouselItem
        :baseInfo="item"
      ></CarouselItem>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
import CarouselItem from './Carousel_item.vue';

export default {
  components : {
    CarouselItem
  },
  data() {
    return {
      carouselList : [
        {cartoonId : "af1de92", carouselCover : "/carousel/316741e2c0764bf9af20e7606.jpg", cartoonName : "你的名字", cartoonCover : "/cover/a36b94aa82f7488782fac5aed.png", cartoonPermit : "2"},
        {cartoonId : "32c8841", carouselCover : "/carousel/3a79b2766a654057a9846fc97.jpg", cartoonName : "魔女之旅", cartoonCover : "/cover/19ce48a915394d64b5b3ff47b.png", cartoonPermit : "1"}
      ]
    }
  }
}
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>