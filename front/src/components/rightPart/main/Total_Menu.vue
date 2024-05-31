<template>
  <div class="total-menu">
    <el-empty v-if="itemList == null || itemList.length == 0" 
      description="没有找到相关的内容哦" style="width: 100%;"></el-empty>
    <VideoItem
        class="videoItem"
        v-for="(item, index) in itemList"
        :key="index"
        :cartoonBaseInfo="item"
    ></VideoItem>
    <i v-for="i in 10" :key="i + '<i>'"></i>
  </div>
</template>

<script>
import VideoItem from '@/components/util/Video_item'
import { EventBus } from '@/bus/Event_Bus'
export default {
    name : "Total_Menu",
    components : {
        VideoItem
    },
    data() {
      return {
        itemList : []
      }
    },
    created() {
      EventBus.$on("menuBarChange", (payload) => {
        this.itemList = payload
      })
    }
}
</script>

<style scoped>
.total-menu {
    margin-top: 15px;
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
}
.total-menu > i {
  width: 10em;
  max-width: 270px;
  min-width: 70px;
  margin-right: 5px;
}
</style>