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
    props : {"baseInfo" : Object, "clickable" : {
        type: Boolean,
        default : true
    }},
    data() {
        return {
            show_name : "",
            absUrl : ""
        }
    },
    methods : {
        jumpTo() {
            if (! this.clickable) return;
            let type = localStorage.getItem("type")
            if (type == -1 || type == null) {
                this.$confirm("你还未登录, 请登录体验完整功能~", "提示", {
                }).then(()=>{ this.$router.push({ name: "home"}, () => {}) })
                .catch(()=>{ this.$router.push({ name: "home"}, () => {}) })
                return;
            }
            if (type == 1 && this.baseInfo.cartoonPermit == 1) {
                this.$confirm("该影片为VIP影片,您是普通用户，请先升级账户~", "提示", {
                }).then(()=>{ this.$router.push({ name: "home"}, () => {}) })
                .catch(()=>{ this.$router.push({ name: "home"}, () => {}) })
                return;
            }
            this.$router.push({ name:"video", query:{ 
                cartoonId : this.baseInfo.cartoonId,
                cartoonName : this.baseInfo.cartoonName,
                cartoonCover : this.baseInfo.cartoonCover,
                cartoonPermit : this.baseInfo.cartoonPermit
            } })
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
    background-color: #f7f8f9;
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