<template>
  <div> 
    <el-button size="mini" @click="dialogVisible = true">增加集数</el-button>
    <el-dialog :visible.sync="dialogVisible" :title="'添加集数'" width="44%" append-to-body>

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