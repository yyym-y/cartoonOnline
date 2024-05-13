<template>
  <div>
    <div style="width: 100px; height: 60px; "></div>
    <TitleSub :msg="cartoonName"></TitleSub>
    <div style="width: 100px; height: 20px; "></div>
    <VideoPlay :source="sourceUrl" ref="videoInfo"></VideoPlay>
    <TitleSub :msg="'集数选择'"></TitleSub>
    <el-button  v-for="i in total_num" :key="'button' + i" plain
      class="choise-btn" @click="choiceEpisode(i)">{{ i }}</el-button>
  </div>
</template>

<script>
import VideoPlay from '@/components/util/Video_play.vue';
import TitleSub from '@/components/util/Title_sub.vue';
import PlayBotton from '@/components/rightPart/main/Play_bottom.vue'
export default {
    components : {
        VideoPlay, TitleSub, PlayBotton
    },
    data() {
        return {
          cartoonId : "",
          cartoonName : "",
          total_num : 0,
          m3u8List : [],
          sourceUrl : ""
        }
    },
    mounted() {
      this.cartoonId = this.$route.query.cartoonId
      this.cartoonName = this.$route.query.cartoonName
      console.log(this.cartoonId)
      this.$api.project.getVideo({cartoonId : this.cartoonId})
          .then((result) => {
        result = result.data
        if(result.code == 0) {
          this.$message.error('请求失败');
          return;
        }
        console.log(result)
        result = result.data
        this.total_num = result.num
        this.m3u8List = result.infos
        this.sourceUrl = this.getUrl(this.cartoonId, 1, result.infos[0].m3u8Url)
        console.log(this.sourceUrl)
        this.$refs.videoInfo.changeSource(this.sourceUrl);
      })
    },
    methods: {
      getUrl( cartoonId, num, m3u8Url ) {
        let ss = this.$baseURL + "/video/" + cartoonId + "/";
        ss += String(num) + "/" + m3u8Url;
        return ss;
      },
      choiceEpisode( i ) {
        this.sourceUrl = this.getUrl(this.cartoonId, i, this.m3u8List[i - 1].m3u8Url)
        this.$refs.videoInfo.changeSource(this.sourceUrl);
      }
    }
}
</script>

<style>
.choise-btn {
  margin-top: 1em;
}
</style>