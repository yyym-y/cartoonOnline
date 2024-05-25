<!-- 
    单一影视展示矩形，包含影视的封面、标题
    传入数据：
    1. {cartoonId: '', cartoonName: '', cartoonCover: '', cartoonPermit: 0}
    2. clickable: true [可选]点击是否要跳转到播放页面
 -->
<template>
  <div class="video-item">
    <img class="imgItem" :src="absUrl" @click="jumpTo()" >
    <div class="name" @click="jumpTo()" v-if="ifShowName">{{ show_name }}</div>
  </div>
</template>

<script>
export default {
    props : {"cartoonBaseInfo" : Object, "clickable" : {
        type: Boolean,
        default : true
    },"ifShowName" : {
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
            if (type == 1 && this.cartoonBaseInfo.cartoonPermit == 1) {
                this.$confirm("该影片为VIP影片,您是普通用户，请先升级账户~", "提示", {
                }).then(()=>{ this.$router.push({ name: "home"}, () => {}) })
                .catch(()=>{ this.$router.push({ name: "home"}, () => {}) })
                return;
            }
            console.log(type)

            this.$router.push({ name:"video", query: {cartoonBaseInfo : this.cartoonBaseInfo} })
        },
        initInfo() {
            if (this.cartoonBaseInfo.cartoonName.length > 10) {
                this.show_name = this.cartoonBaseInfo.cartoonName.substring(0, 15);
                this.show_name += "..."
            } else this.show_name = this.cartoonBaseInfo.cartoonName
            this.absUrl = this.$baseURL + this.cartoonBaseInfo.cartoonCover;
        }
    },
    watch: {
        cartoonBaseInfo() {
            this.initInfo();
        }
    },
    mounted() {
        this.initInfo();
    }
}
</script>

<style>
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