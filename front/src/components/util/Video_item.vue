<template>
  <div class="video-item">
    <el-image
      style="width: 100%; border-radius: 4px;"
      :src="coverUrl"
      @click="jumpTo()"
      ></el-image> 
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
    width: 15vw;
    max-width: 270px;
    min-width: 100px;
    aspect-ratio: 4/3;
    text-align: center;
    font-size: 16px;
}
.name {
    width: 15vw;
}
</style>