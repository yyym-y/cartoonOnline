<template>
  <div>
    <el-tabs :tab-position="'left'">
        <el-tab-pane label="图表">
          <RankImg :data="rankInfo" :title="'全部影视排行'"></RankImg>
        </el-tab-pane>
        <el-tab-pane label="列表">
          <VideoDiscrip v-for="(pr, index) in rankInfo" :key="'allrank' + index"
            :cartoonBaseInfo="pr.cartoon"></VideoDiscrip>
        </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import VideoDiscrip from '@/components/util/Video_discrip.vue';
import RankImg from '@/components/rightPart/rank/rankImg.vue';
export default {
    components : {
      VideoDiscrip, RankImg
    },
    data() {
      return {
        rankInfo : []
      }
    },
    created() {
      this.$api.project.getAllPlayData().then(res => {
        res = res.data;
        if(res.code == 0) {
          this.$message.error("全部排行数据请求失败....");
          return;
        }
        res = res.data;
        res.sort(function(a, b) {
          return b.playTime - a.playTime;
        })
        this.rankInfo = res;
        // console.log(res)
      }).catch(err => {this.$message.error("全部排行数据请求失败....");})
    }
}
</script>

<style>

</style>