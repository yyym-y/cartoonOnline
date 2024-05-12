<template>
  <div class="video-item">
    <img
      class="imgItem"
      :src="absUrl"
      @click="jumpTo()"
      >
    <div class="name" @click="jumpTo()">{{ show_name }}</div>
  </div>
</template>

<script>
export default {
    props : ["name", "coverUrl", "videoID", "type"],
    data() {
        return {
            show_name : "",
            absUrl : ""
        }
    },
    methods : {
        jumpTo() {
            alert("jump to " + this.videoID);
        }
    },
    mounted() {
        if (this.name.length > 10) {
            this.show_name = this.name.substring(0, 15);
            this.show_name += "..."
        } else this.show_name = this.name
        this.absUrl = this.$baseURL + this.coverUrl;
    }
}
</script>

<style scoped>
.video-item {
    width: 10em;
    max-width: 270px;
    min-width: 70px;
    aspect-ratio: 4/3;
    text-align: center;
    font-size: 16px;
    margin-right: 5px;
    margin-top:10px;
    border-width: 1px;
    border-style: solid !important;
    border-color: white
}
.name {
    width: 10em;
    max-width: 270px;
    min-width: 70px;
}
.imgItem {
    width: 100%; border-radius: 4px;
}
</style>