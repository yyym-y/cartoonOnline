<template>
  <div>
    <el-tag v-for="(pr, index) in tags" :key="pr + index + 'edit'"
                    style="margin-right: 4px;" closable @close="closeTag(pr)">{{ pr.name }}</el-tag>

    <el-popover placement="top" width="400" trigger="click">
        <el-button slot="reference" size="small" @click="showInput">+ New Tag</el-button>
    </el-popover>
  </div>
</template>

<script>
export default {
    props : ["tags"],
    data() {
        return {

        }
    },
    methods : {
        showInput() {
            
        },
        closeTag(tag) {
            this.tags.splice(this.tags.indexOf(tag), 1);
        },
    }
}
</script>

<style>

</style>