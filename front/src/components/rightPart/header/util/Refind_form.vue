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
            this.$api.project.confirmCode()
            .then(result => {
                result = result.data;
                if(result.code == 0) {
                    this.$message.error("验证码发送错误: " + result.msg)
                    return;
                }
                this.$message.success("验证码发送成功")
            }).catch(err => {
                this.$message.error("验证码发送错误[服务器异常]")
            })
        },
        submitForm() {
            this.$api.project.refind(this.refindInfo)
            .then(result => {
                result = result.data;
                if(result.code == 0) {
                    if(result.msg == "confirm code wrong")
                        this.$message.error("验证码错误")
                    if(result.msg == "confirm code Time Out")
                        this.$message.error("验证码过期")
                    return;
                }
            }).catch(err => {
                this.$message.error("找回密码错误[服务器异常]")
            })
        }
    }
}
</script>

<style>
.inputCode {
    width: 14.3em;
}
</style>
