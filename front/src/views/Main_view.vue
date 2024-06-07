<template>
  <div class="menu-outter">
    <Carousel></Carousel>
    <TitleSub :msg="'全部影视目录'"></TitleSub>
    <MenuBar></MenuBar>
    <el-divider></el-divider>
    <TotalMenu></TotalMenu>
  </div>
</template>

<script>
import Carousel from '../components/rightPart/main/Carousel_view.vue'
import TitleSub from '@/components/util/Title_sub'
import MenuBar from '@/components/rightPart/main/Menu_bar.vue';
import TotalMenu from '../components/rightPart/main/Total_Menu.vue'
export default {
    name : "Main_view",
    components : {
        Carousel, TitleSub, TotalMenu, MenuBar
    }
}
</script>

<style scoped >
.pageSelect {
  margin-top: 20px;
  margin-left: 36vw;
}
</style>