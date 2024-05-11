<template>
  <div class="total-menu">
    <VideoItem
        class="videoItem"
        v-for="(item, index) in itemList"
        :key="index"
        :name="item.cartoonName"
        :coverUrl="item.cartoonCover"
        :videoID="item.cartoonId"
        :type="item.cartoonPermit"
    ></VideoItem>
  </div>
</template>

<script>
import VideoItem from '@/components/util/Video_item'
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
    mounted() {
      this.$api.project.getMenu().then((result) => {
        result = result.data;
        if(result.code == 0) {
          this.$message.error('初始化失败');
          return;
        }
        this.itemList = result.data
        // console.log(this.itemList)
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
.videoItem:last-child {
  margin-right: auto;
}
</style>