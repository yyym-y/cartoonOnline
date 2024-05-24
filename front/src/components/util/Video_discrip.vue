<!-- 
    影视简介栏组件，可用于搜索后展示结果，排行，播放页面底部展示
    传入数据：
    1. {cartoonId: '', cartoonName: '', cartoonCover: '', cartoonPermit: 0}
 -->
<template>
  <div class="descrip-item">
    <videoItem :cartoonBaseInfo="cartoonBaseInfo" class="videoItem" :ifShowName="false"></videoItem>
    <div class="descrip">
        <div class="cartoonName">{{ cartoonBaseInfo.cartoonName }}</div>
        <div class="cartoonDescrip">{{ descrip }}</div>
    </div>
    <divLine></divLine>
  </div>
</template>

<script>
import videoItem from './Video_item.vue'
import divLine from './div_line.vue'
export default {
    props : ["cartoonBaseInfo"],
    components : {
        videoItem, divLine
    },
    data() {
        return { descrip : "" }
    },
    mounted() {
        let info = this.cartoonBaseInfo;
        this.$api.project.getDescrip({cartoonId : info.cartoonId})
        .then(result => {
            result = result.data;
            // console.log(result)
            if(result.code == 0) {
                this.$message.error(info.cartoonName + "简介请求失败...");
                return;
            }
            result = result.data;
            if( result == null ) {
                this.descrip = "本影片暂时没有简介哦~~"
               return;
            } 
            this.descrip = result.descrip;
        }).catch(err => { this.$message.error("请求" + info.cartoonName + "时数据异常..."); })
    }
}
</script>

<style>
.videoItem {
    float: left;
}
.descrip {
    height: 14em;
    margin-top: 1em;
}
.cartoonName {
    font-size: 1.2em;
    font-weight: 600;
    transform: translateY(0.9em);
}
.cartoonDescrip {
    margin-top: 30px;
}
</style>