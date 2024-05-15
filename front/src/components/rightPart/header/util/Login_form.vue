<template>
  <div>
    <el-form :label-position="'left'" label-width="50px" :model="fromInfo">
        <el-form-item label="账户">
            <el-input v-model="fromInfo.uid"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input v-model="fromInfo.password" show-password></el-input>
            <el-link style="float: right;" :underline="false"
                @click="registerVisible = true">注册账号</el-link>
            <i style="float: right; margin-left: 5px; margin-right: 5px">|</i>
            <el-link style="float: right;" :underline="false"
                @click="refindVisible = true">找回密码</el-link>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm()">登录</el-button>
        </el-form-item>
    </el-form>

    <el-dialog title="找回密码" :visible.sync="refindVisible" width="31em">
        <RefindForm></RefindForm>
    </el-dialog>
    <el-dialog title="注册账户" :visible.sync="registerVisible" width="31em">
        <RegisterForm></RegisterForm>
    </el-dialog>
  </div>
</template>

<script>
import RefindForm from './Refind_form.vue'
import RegisterForm from './Register_form.vue'
export default {
    components : {
        RefindForm, RegisterForm
    },
    data() {
        return {
            fromInfo : {
                uid : "",
                password : ""
            },
            refindVisible :false,
            registerVisible : false
        }
    },
    methods: {
        submitForm() {
            this.$api.project.logIn({
                uid : this.fromInfo.uid,
                password : this.fromInfo.password
            }).then(result => {
                result = result.data;
                if (result.code == 0) {
                    this.$message.error("账户名或密码错误")
                    return;
                }
                result = result.data
                console.log(result)
                localStorage.setItem("jwt", result.jwt);
                localStorage.setItem("username", result.user.username);
                console.log(localStorage.getItem("username"));
                localStorage.setItem("uid", result.user.uid);
                localStorage.setItem("password", result.user.password);
                localStorage.setItem("email", result.user.email);
                localStorage.setItem("type", result.user.type);
                this.$message.success("登录成功")
                setTimeout(function(){
                    top.document.location.reload();
                }, 700);
            }).catch(err => {
                this.$message.error("登录请求失败[服务器异常]")
            })
        },
    }
}
</script>

<style>

</style>