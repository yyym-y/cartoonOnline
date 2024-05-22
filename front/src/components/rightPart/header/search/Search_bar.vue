<template>
    <div class="search-bar">
        <el-input v-model="input"
            placeholder="请输入要搜索的内容, 宁可少字也不要错字喵~~" class="search-input"></el-input>
        <el-button plain class="search-but" @click="toSearch">
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
    },
    methods : {
        toSearch() {
            if(this.input == "") {
                this.$message.error("搜索内容不能为空喵~");
                return;
            }
            this.$api.project.search({input: this.input})
            .then(result => {
                result = result.data;
                if(result.code == 0) {
                    this.$message.error("抱歉...没找到相应的内容:(");
                    return;
                }
                this.$message.success("搜索成功...");
                result = result.data;
                this.$router.push({name:"search", query : {
                    res : result,
                    msg : this.input
                }}).catch(err => {})
            }).catch(err => {
                this.$message.error("连接服务器异常...")
            })
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