<template>
  <div>
    <TitleSub :msg="'影视热榜排行'"></TitleSub>
    <el-tabs v-model="activeName" style="margin-top: 20px;">
      <el-tab-pane label="全部排行" name="first">
        <AllRank></AllRank>
      </el-tab-pane>
      <el-tab-pane label="本月排行" name="second">
        <MonthRank></MonthRank>
      </el-tab-pane>
      <el-tab-pane label="年度排行" name="third">
        <YearRank></YearRank>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import TitleSub from '@/components/util/Title_sub.vue';
import AllRank from '@/components/rightPart/rank/All_rank.vue'
import MonthRank from '@/components/rightPart/rank/Month_rank.vue';
import YearRank from '@/components/rightPart/rank/Year_rank.vue';
export default {
  components : {
    TitleSub, AllRank, MonthRank, YearRank
  },
  data() {
    return {
      activeName : 'first'
    }
  }
}
</script>

<style>

</style>