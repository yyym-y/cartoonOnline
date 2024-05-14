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
            this.$message.success("提交成功")
        },
    }
}
</script>

<style>

</style>