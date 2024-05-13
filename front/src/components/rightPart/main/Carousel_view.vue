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
      carouselList : []
    }
  },
  mounted() {
    this.$api.project.getCarousel().then((result) => {
      result = result.data;
      if(result.code == 0) {
        this.$message.error('初始化失败');
        return;
      }
      this.carouselList = result.data;
    })
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