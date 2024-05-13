<template>
  <div>
    <Carousel></Carousel>
    <TitleSub :msg="'全部影视目录'"></TitleSub>
    <TotalMenu></TotalMenu>
  </div>
</template>

<script>
import Carousel from '../components/rightPart/main/Carousel_view.vue'
import TitleSub from '@/components/util/Title_sub'
import TotalMenu from '../components/rightPart/main/Total_Menu.vue'
export default {
    name : "Main_view",
    components : {
        Carousel, TitleSub, TotalMenu
    }
}
</script>

<style>

</style>