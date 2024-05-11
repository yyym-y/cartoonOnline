<template>
  <div class="total-menu">
    <VideoItem
        v-for="(item, index) in itemList"
        :key="index"
        :name="item.cartoon_name"
        :coverUrl="item.cartoon_cover"
        :videoID="item.cartoon_id"
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
        this.itemList = result.data;
      })
    }
}
</script>

<style scoped>
.total-menu {
    margin-top: 15px;
}
</style>