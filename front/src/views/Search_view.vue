<template>
  <div>
    <TitleSub :msg="'“' + input + '” 的搜索结果为：'"></TitleSub>
    <el-tabs v-model="activeName" style="margin-top: 5px">
      <el-tab-pane label="片名搜索" name="first">
        <el-empty v-if="res.cartoon_name == null" description="没有找到相关内容哦"></el-empty>
        <VideoDiscrip v-for="(pr, index) in res.cartoon_name" :key="'cartoon_name' + index"
          :cartoonBaseInfo="pr"></VideoDiscrip>
      </el-tab-pane>
      <el-tab-pane label="导演搜索" name="second">
        <el-empty v-if="res.director == null" description="没有找到相关内容哦"></el-empty>
        <VideoDiscrip v-for="(pr, index) in res.director" :key="'director' + index"
          :cartoonBaseInfo="pr"></VideoDiscrip>
      </el-tab-pane>
      <el-tab-pane label="主演搜索" name="third">
        <el-empty v-if="res.actor == null" description="没有找到相关内容哦"></el-empty>
        <VideoDiscrip v-for="(pr, index) in res.actor" :key="'actor' + index"
          :cartoonBaseInfo="pr"></VideoDiscrip>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import VideoDiscrip from '@/components/util/Video_discrip.vue';
import TitleSub from '@/components/util/Title_sub.vue';
export default {
    components : {
        VideoDiscrip, TitleSub
    },
    data() {
      return {
        activeName : "first",
        res : {},
        input : "",
        show : true
      }
    },
    created() {
      this.input = this.$route.query.msg;
      this.res = this.$route.query.res;
      console.log(this.res)
    }
}
</script>

<style>

</style>