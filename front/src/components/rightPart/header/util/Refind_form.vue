<template>
      <el-form :label-position="'left'" label-width="80px" :model="refindInfo">
        <el-form-item label="邮箱">
            <el-input v-model="refindInfo.email"></el-input>
        </el-form-item>
        <el-form-item label="验证码">
            <el-input v-model="refindInfo.confirmCode" class="inputCode"></el-input>
            <el-button type="primary" @click="sendCode()" style="float: right;">发送验证码</el-button>                
        </el-form-item>
        <el-form-item label="密码">
            <el-input v-model="refindInfo.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
            <el-input v-model="refindInfo.re_password" show-password></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm()">找回密码</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    data() {
        return {
            refindInfo : {
                email : "",
                confirmCode : "",
                password : "",
                re_password : ""
            }
        }
    },
    methods : {
        sendCode() {

        },
        submitForm() {

        }
    }
}
</script>

<style>
.inputCode {
    width: 14.3em;
}
</style>
