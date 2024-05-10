<template>
  <div class="total-menu">
    <VideoItem
        :name="'RELEFT'"
        :coverUrl="'https://www.2rk.cc/video/LUUGv4gBOQ_doUrMYWg0/index.webp'"
        :videoID="'dadasss'"
    ></VideoItem>
  </div>
</template>

<script>
import VideoItem from '@/components/util/Video_item'
export default {
    name : "Total_Menu",
    components : {
        VideoItem
    }
}
</script>

<style scoped>
.total-menu {
    margin-top: 15px;
    background-color: gray;
    height: 58vh;
}
</style>