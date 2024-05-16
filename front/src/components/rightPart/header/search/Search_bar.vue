<template>
    <div class="search-bar">
        <el-input v-model="input" placeholder="请输入内容" class="search-input"></el-input>
        <el-button plain class="search-but">
            <i class="el-icon-search"></i>搜索
        </el-button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            input : ""
        }
    }
}
</script>

<style>
.search-bar {
    margin-left: 32px;
    width: 50%;
    max-width: 650px;
    float: left;
}
.search-input {
    width: 80%;
}
.search-but i{
    margin-right: 5px;
}
</style>