<template>
  <div>
    <el-form :label-position="'left'" label-width="80px" :model="registerInfo">
        <el-form-item label="账户名">
            <el-input v-model="registerInfo.username"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
            <el-input v-model="registerInfo.email"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input v-model="registerInfo.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
            <el-input v-model="registerInfo.re_password" show-password></el-input>
        </el-form-item>
        <el-form-item label="验证码">
            <el-input v-model="registerInfo.confirmCode" class="inputCode"></el-input>
            <el-button type="primary" @click="sendCode()" style="float: right;">发送验证码</el-button>                
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm()">注册</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
    data() {
        return {
            registerInfo : {
                username : "",
                email : "",
                password : "",
                re_password : "",
                confirmCode : "",
            }
        }
    },
    methods : {
        submitForm() {
            console.log(this.registerInfo)
        }
    }
}
</script>

<style scoped>
.inputCode {
    width: 14.3em;
}
</style>