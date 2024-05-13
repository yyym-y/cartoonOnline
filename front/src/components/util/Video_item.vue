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
    props : ["baseInfo"],
    data() {
        return {
            show_name : "",
            absUrl : ""
        }
    },
    methods : {
        jumpTo() {
            this.$router.push({ name:"video", query:{ cartoonId : this.baseInfo.cartoonId} })
        }
    },
    mounted() {
        if (this.baseInfo.cartoonName.length > 10) {
            this.show_name = this.baseInfo.cartoonName.substring(0, 15);
            this.show_name += "..."
        } else this.show_name = this.baseInfo.cartoonName
        this.absUrl = this.$baseURL + this.baseInfo.cartoonCover;
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