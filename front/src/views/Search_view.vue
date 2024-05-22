<template>
  <div>
    <TitleSub :msg="input + ' 的搜索结果为：'"></TitleSub>
    <el-collapse-transition>
        <div v-show="show">
          <div v-for="(item, index1) in res" :key="index1 + 'outter'">
            <VideoDiscrip v-for="(pr, index2) in item" :key="index2 + 'inner'"
              :cartoonBaseInfo="pr" ></VideoDiscrip>
          </div>
        </div>
    </el-collapse-transition>
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
        res : {},
        input : "",
        show : true
      }
    },
    created() {
      this.input = this.$route.query.msg;
      this.res = this.$route.query.res;
    }
}
</script>

<style>

</style>