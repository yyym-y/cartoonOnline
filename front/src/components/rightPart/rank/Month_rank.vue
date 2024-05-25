<template>
    <div>
        <el-tabs :tab-position="'left'" >
            <el-tab-pane label="图表">
            <RankImg :data="rankInfo" :title="title"></RankImg>
            </el-tab-pane>
            <el-tab-pane label="列表">
            <VideoDiscrip v-for="(pr, index) in rankInfo" :key="'monthrank' + index"
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
        rankInfo : [],
        title : ""
      }
    },
    created() {
      let year = new Date().getFullYear();
      let month = new Date().getMonth() + 1;
      this.title = year + "年" + month + "月影视排行";
      this.$api.project.getMonthPlayData().then(res => {
        res = res.data;
        if(res.code == 0) {
          this.$message.error("本月排行数据请求失败....");
          return;
        }
        res = res.data;
        res.sort(function(a, b) {
          return b.playTime - a.playTime;
        })
        this.rankInfo = res;
      }).catch(err => {this.$message.error("本月排行数据请求失败....");})
    }
}
</script>

<style>

</style>