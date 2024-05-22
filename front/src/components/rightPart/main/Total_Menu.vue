<template>
  <div class="total-menu">
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
          this.$message.error('服务器连接成功,走马灯数据请求失败');
          return;
        }
        this.itemList = result.data
      }).catch(err => {
        this.$message.error('服务器连接失败...');
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