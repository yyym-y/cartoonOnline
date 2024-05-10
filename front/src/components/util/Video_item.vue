<template>
  <div class="video-item">
    
    <el-image
      style="width: 192px; height: 250px; border-radius: 4px;"
      :src="coverUrl"
      @click="jumpTo()"
      ></el-image> 
    <span class="type" @click="jumpTo()">VIP</span>  
    <div class="name" @click="jumpTo()">{{ show_name }}</div>
  </div>
</template>

<script>
export default {
    props : ["name", "coverUrl", "videoID"],
    data() {
        return {
            show_name : ""
        }
    },
    methods : {
        jumpTo() {
            alert("jump to " + this.videoID);
        }
    },
    mounted() {
        if (this.name.length > 15) {
            this.show_name = this.name.substring(0, 15);
            this.show_name += "..."
        } else this.show_name = this.name

    }
}
</script>

<style scoped>
.video-item {
    width: 192px;
    height: 276px;
    text-align: center;
    font-size: 16px;
}
.type {
    float: right;
    width: 30px;
    transform: translateY(-253px);
    background-color: yellow;
}
.name {
    width: 192px;
}
</style>