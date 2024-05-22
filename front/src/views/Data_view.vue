<template>
  <div>
    <!-- <TitleSub :msg="'影视播放量'"></TitleSub> -->
    <PlayLine></PlayLine>
    <TitleSub :msg="'用户登录时间表'"></TitleSub>
    <TitleSub :msg="'搜索词云图'"></TitleSub>
  </div>
</template>

<script>
import TitleSub from '@/components/util/Title_sub.vue';
import PlayLine from '@/components/rightPart/data_v/Play_line.vue'
export default {
  components : {
    TitleSub, PlayLine
  }
}
</script>

<style>

</style>