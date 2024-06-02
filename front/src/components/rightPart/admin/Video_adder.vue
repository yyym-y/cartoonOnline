<template>
  <div>
    <el-button type="primary" icon="el-icon-edit" @click="dialogVisible = true"
        size="medium" style="margin-bottom: 8px;"> 添加影视</el-button>

    <el-dialog title="添加影视" :visible.sync="dialogVisible" width="44%">

    </el-dialog>
  </div>
</template>

<script>
export default {
    data() {
        return {
            dialogVisible : false
        }
    }
}
</script>

<style>

</style>