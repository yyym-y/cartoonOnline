<!-- 
  用于上传影视视频资源的组件
  传入数据： cartoonId : ''
  监听组件事件: (uploadVideo => "上传的视频url")
  广播的事件: (uploadVideoReturn) => {
    type : "new",  [标识是增加资源而不是修改]
    num : Number, [增加第几集的视频资源]
    localImport : Boolean, [是本地上传还是Url上传]
    realUrl : String [视频Url]
  }
 -->
<template>
  <div> 
    <el-button size="mini" @click="dialogVisible = true">增加集数</el-button>
    <el-dialog :visible.sync="dialogVisible" :title="'添加 ' + cartoonId + ' 集数'" width="44%" append-to-body>
      <el-form v-model="form" :label-position="'right'" label-width="20%">
        <el-form-item label="添加的集数">
          <el-input-number v-model="form.num" controls-position="right" :min="1" size="small"></el-input-number>
        </el-form-item>
        <el-form-item label="导入方式">
          <el-switch v-model="form.localImport" active-text="本地导入" inactive-text="url导入"></el-switch>
        </el-form-item>
        <el-form-item label="本地导入" v-show="form.localImport">
          <VideoUploader :actionUrl="uploadUrl" :key="timmer"></VideoUploader>
        </el-form-item>
        <el-form-item label="url导入" v-show="!form.localImport">
          <el-input v-model="form.internetUrl" placeholder="请输入绝对路径"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button size="medium" @click="submitUpload">确认添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import VideoUploader from './Video_uploader.vue'
import { EventBus } from '@/bus/Event_Bus'
export default {
    props : ["cartoonId"],
    components : {
      VideoUploader
    },
    data() {
        return {
            dialogVisible : false,
            form : {
              num : 1,
              localImport : true,
              internetUrl : ""
            },
            uploadUrl : "",
            serverUrl : "",
            unclickable : false,
            timmer : ''
        }
    },
    methods : {
      submitUpload() {
        let realUrl = this.form.localImport ? this.serverUrl : this.form.internetUrl;
        this.dialogVisible = false;
        EventBus.$emit("uploadVideoReturn", {
          type : "new",
          num : this.form.num,
          localImport :this.form.localImport,
          realUrl : realUrl
        })
      }
    },
    created() {
      this.uploadUrl = this.$baseURL + "/admin/uploadVideo"
      EventBus.$on("uploadVideo", (payload) => {
        this.serverUrl = payload
      })
    },
    watch : {
      dialogVisible() {
        this.timmer = new Date().getTime()
      }
    }
}
</script>

<style>

</style>