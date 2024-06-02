<!-- 
    用户标签栏组件
 -->
<template>
  <div style="float: right;">
    <el-dropdown  @command="handleCommand">
        <span class="el-dropdown-link">
            {{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu>
            <el-dropdown-item command="updatetype" v-if="type == 1">升级账户</el-dropdown-item>
            <el-dropdown-item v-if="type == 0" command="admin">控制台</el-dropdown-item>
            <el-dropdown-item command="changepwd">修改密码</el-dropdown-item>
            <el-dropdown-item command="exit">退出登录</el-dropdown-item>
        </el-dropdown-menu>
    </el-dropdown>
    <el-dialog title="修改密码" :visible.sync="changepwd" width="31em">
        <changePasswordForm></changePasswordForm>
    </el-dialog>
    <el-dialog title="升级账户" :visible.sync="updatetype" width="31em">
        <changeTypeInfo></changeTypeInfo>
    </el-dialog>
  </div>
</template>

<script>
import changePasswordForm from './changePassword_form.vue';
import changeTypeInfo from './changeType_info.vue';
import { EventBus } from '@/bus/Event_Bus'
export default {
    components : {
        changePasswordForm, changeTypeInfo
    },
    props : ["username", "type"],
    data() {
        return {
            changepwd : false,
            updatetype : false,
        }
    },
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
            switch (command) {
                case "exit": {this.exitLog(); break;}
                case "changepwd": {this.changepwd = true; break;}
                case "updatetype": {this.updatetype = true; break;}
                case "admin": {this.jumpToAdmin(); break;}
            }
        },
        jumpToAdmin() {
            this.$router.push({name : "admin"}).catch(err => {})
        }
    },
    created() {
        EventBus.$on("logout", (payload) => {
            this.exitLog();
        })
    }
}
</script>

<style scoped>
.el-dropdown-link {
    cursor: pointer;
}
</style>