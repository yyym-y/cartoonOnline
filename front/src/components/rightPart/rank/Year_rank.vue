<template>
  <div>
    <el-date-picker v-model="choiceYear" style="height: 100%;"
      type="year" placeholder="选择展示哪一年热榜" value-format="yyyy"></el-date-picker>
      <el-button @click="queryData">确认</el-button>

    <el-tabs :tab-position="'top'">
        <el-tab-pane label="图表">
          <RankImg :data="rankInfo" :title="choiceYear + '年度影视排行'"></RankImg>
        </el-tab-pane>
        <el-tab-pane label="列表">
          <VideoDiscrip v-for="(pr, index) in rankInfo" :key="'yearrank' + index"
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
            choiceYear : '',
            rankInfo : [],
        }
    },
    methods : {
      queryData() {
        this.$api.project.getYearPlayData({
          year : this.choiceYear
        }).then(res => {
          res = res.data;
          if(res.code == 0) {
            this.$message.error(this.choiceYear +  "排行数据请求失败....");
            return;
          }
          res = res.data;
          res.sort(function(a, b) {
            return b.playTime - a.playTime;
          })
          if(res.length == 0) {
            this.$message.error("没有" + this.choiceYear +  "年的数据...");
            return;
          }
          this.rankInfo = res;
        }).catch(err => {this.$message.error(this.choiceYear +  "排行数据请求失败....");})
      }
    },
    created() {
      this.choiceYear = String(new Date().getFullYear())
      this.queryData()
    }
}
</script>

<style>

</style>