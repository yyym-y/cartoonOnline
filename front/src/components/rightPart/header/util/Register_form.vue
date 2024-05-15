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
            this.$api.project.regester(this.registerInfo)
            .then(result => {
                result = result.data;
                if(result.code == 0) {
                    if(result.msg == "email has been used")
                        this.$message.error("该邮箱已经被注册")
                    if(result.msg == "confirm code wrong")
                        this.$message.error("验证码错误")
                    if(result.msg == "confirm code Time Out")
                        this.$message.error("验证码过期")
                    return;
                }
                localStorage.setItem('jwt', result.jwt);
                this.$uid = result.uid;
                this.$message.success("注册成功")
            }).catch(err => {
                this.$message.error("注册请求失败[服务器异常]")
            })
        },
        sendCode() {
            this.$api.project.confirmCode({email : this.registerInfo.email})
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
        }
    }
}
</script>

<style scoped>
.inputCode {
    width: 14.3em;
}
</style>