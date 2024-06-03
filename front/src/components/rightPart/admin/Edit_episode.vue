<template>
  <div>
    <el-row>
        <el-col :span="4">第 {{ num }} 集:</el-col>
        <el-col :span="16">
            <el-link @click="jumpToVideo()" target="_blank">{{ abs_url }}</el-link>
        </el-col>
        <el-col :span="4">
            <el-button size="mini" @click="editVis = true">Edit</el-button>
        </el-col>
    </el-row>

    <el-dialog title="提示" :visible.sync="editVis" width="44%" append-to-body>
    </el-dialog>
  </div>
</template>

<script>
export default {
    props : ["num", "abs_url"],
    data() {
        return {
            editVis : false
        }
    },
    methods : {
        jumpToVideo() {

        }
    }
}
</script>

<style>
</style>