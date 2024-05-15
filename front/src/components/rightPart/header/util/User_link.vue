<template>
  <div style="float: right;">
    <el-dropdown  @command="handleCommand">
        <span class="el-dropdown-link">
            {{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu>
            <el-dropdown-item v-if="type == 1">升级账户</el-dropdown-item>
            <el-dropdown-item v-if="type == 0">控制台</el-dropdown-item>
            <el-dropdown-item command="exit">退出登录</el-dropdown-item>
        </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
    props : ["username", "type"],
    methods : {
        exitLog() {
            localStorage.setItem("username", "");
            localStorage.setItem("jwt", "");
            localStorage.setItem("uid", "");
            localStorage.setItem("email", "");
            localStorage.setItem("type", -1);
            setTimeout(function(){
                top.document.location.reload();
            }, 500);
        },
        handleCommand( command ) {
            if(command == "exit")
                this.exitLog();
        }
    }
}
</script>

<style scoped>
.el-dropdown-link {
    cursor: pointer;
}
</style>