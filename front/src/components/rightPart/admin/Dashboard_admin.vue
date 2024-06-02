<template>
  <div>
    <el-tabs v-model="activeName" style="margin-top: 8px;">
        <el-tab-pane label="影视管理" name="first">
            <VideoAdder></VideoAdder>
            <VideoController></VideoController>
        </el-tab-pane>
        <el-tab-pane label="用户管理" name="second"></el-tab-pane>
        <el-tab-pane label="其他" name="third"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import VideoAdder from './Video_adder.vue'
import VideoController from './Video_controller.vue'
export default {
    components : {
        VideoController, VideoAdder
    },
    data() {
        return {
         activeName : "first"   
        }
    }
}
</script>

<style>

</style>