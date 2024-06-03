<template>
  <div>
    <img :src="imgUrl" class="imgSize" @click="uploadImg()">
    <el-dialog title="上传封面图片" :visible.sync="uploadImgVis" width="40%" append-to-body>
        <el-upload class="uploadImg" drag
            action="https://jsonplaceholder.typicode.com/posts/">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件,且不超过500kb</div>
        </el-upload>
    </el-dialog>
  </div>
</template>

<script>
export default {
    props : ["abs_url"],
    data() {
        return {
            imgUrl : "",
            uploadImgVis : false
        }
    },
    created() {
        this.imgUrl = this.$baseURL + this.abs_url;
    },
    methods : {
        uploadImg() {
            this.uploadImgVis = true
        },
    },
    watch : {
        abs_url() {
            this.imgUrl = this.$baseURL + this.abs_url;
        }
    }
}
</script>

<style>

</style>