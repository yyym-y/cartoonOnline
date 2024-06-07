<template>
  <div class="total-menu" ref="totalMenuDiv">
    <el-empty v-if="itemList == null || itemList.length == 0" 
      description="没有找到相关的内容哦" style="width: 100%;"></el-empty>
    <VideoItem
        class="videoItem"
        v-for="(item, index) in itemList"
        ref="video_item"
        :key="index"
        :cartoonBaseInfo="item"
    ></VideoItem>
    <i v-for="i in 10" :key="i + '<i>'"></i>
    <el-pagination layout="prev, pager, next" :page-size="page_size" :total="total_num" class="pageSelect"
      @next-click="nextPage" @prev-click="prePage" @current-change="jumpToPage"
      ></el-pagination>
  </div>
</template>

<script>
import VideoItem from '@/components/util/Video_item'
import { EventBus } from '@/bus/Event_Bus'
import Vue from 'vue';
export default {
    name : "Total_Menu",
    components : {
        VideoItem
    },
    data() {
      return {
        itemList : [],
        saveList : [],
        total_num : 0,
        page_size : 0,
        now_begin : 0
      }
    },
    created() {
      EventBus.$on("menuBarChange", (payload) => {
        this.itemList = payload
        this.saveList = payload
        this.total_num = payload.length
        this.$nextTick(() => {
          this.getNum()
        })
      })
    },
    methods: {
      getNum() {
        let totalMenuWidth = this.$refs.totalMenuDiv.offsetWidth
        let videoWidth = this.$refs.video_item[0].$el.offsetWidth + 5;
        let line_num = Math.floor(totalMenuWidth / videoWidth);
        this.page_size = Math.min(line_num * 2, this.itemList.length);
        this.itemList = this.itemList.slice(0, this.page_size - 1)
        this.now_begin = 0
      },
      nextPage() {
        this.now_begin += this.page_size,
        this.itemList = this.saveList.slice(this.now_begin, this.now_begin + this.page_size)
      },
      prePage() {
        this.now_begin -= this.page_size,
        this.itemList = this.saveList.slice(this.now_begin, this.now_begin + this.page_size)
      },
      jumpToPage(page) {
        console.log(page)
        this.now_begin = (page - 1) * (this.page_size);
        this.itemList = this.saveList.slice(this.now_begin, this.now_begin + this.page_size)
      }
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
.pageSelect {
  margin-top: 20px;
  margin-left: 36vw;
}
</style>