<!-- 
    点击增加上传视频后弹出的组件
    传入参数 : actionUrl : ''[相应的后端api], unclickable[是否允许上传]
    广播的事件: (uploadVideo) => "资源url"[储存在tem文件夹中]
 -->
<template>
  <div>
    <el-upload style="margin-top: 16px"
      class="avatar-uploader el-upload--text"
      :drag="Plus"
      :action="actionUrl"
      :disabled="finished"
      :show-file-list="true"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :on-success="handleVideoSuccess"
      :before-upload="beforeUploadVideo"
      :file-list="fileList"
      :on-progress="uploadVideoProcess">

      <i v-if="Plus" class="el-icon-upload"></i>
      <div v-if="Plus" class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <el-progress v-if="finished" type="circle" :percentage="videoUploadPercent" style="margin-top:30px;"></el-progress>
    </el-upload>
  </div>
</template>

<script>
import { EventBus } from '@/bus/Event_Bus'
export default {
    props : [ "actionUrl" ],
    data() {
        return {
            videoFlag: false,
            Plus: true,
            videoUploadPercent: 0,
            finished : false,
            fileList : []
        }
    },
    methods : {
        beforeUploadVideo(file) {
            if (['video/mp4', 'video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb'].indexOf(file.type) === -1) {
                this.$message.error('请上传正确的视频格式')
                return false
            }
            return true;
        },
        uploadVideoProcess(event, file, fileList) {
            this.Plus = false
            this.finished = true
            this.videoUploadPercent = Number(file.percentage.toFixed(0))
        },
        handleVideoSuccess(res, file) {
            this.Plus = false
            this.finished = true;
            this.videoUploadPercent = 100
            if(res.code == 0) {
                this.$message.error("视频上传失败, 服务器错误");
                this.finished = false; return;
            }
            EventBus.$emit("uploadVideo", res.data)
            this.$message.success("视频上传成功~~");
        },
        handleRemove(file, fileList) {
            this.finished = false;
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        }
    }
}
</script>

<style>

</style>