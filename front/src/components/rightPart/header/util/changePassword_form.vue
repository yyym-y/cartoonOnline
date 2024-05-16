<template>
  <div>
    <el-form :label-position="changePwd" label-width="80px" :model="changePwd">
    <el-form-item label="原密码">
        <el-input v-model="changePwd.password" show-password></el-input>
    </el-form-item>
    <el-form-item label="新密码">
        <el-input v-model="changePwd.new_password" show-password></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="submitForm()">修改密码</el-button>
    </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { EventBus } from '@/bus/Event_Bus'
export default {
    data() {
        return {
            changePwd : {
                password : "",
                new_password : ""
            }
        }
    },
    methods : {
        submitForm() {
            this.$api.project.changePassword({
                uid : localStorage.getItem("uid"),
                password : this.changePwd.password,
                newPwd : this.changePwd.new_password
            }).then(result => {
                result = result.data;
                if(result.code == 0) {
                    this.$message.error("uid 不存在或原密码错误");
                    return;
                }
                this.$message.success("密码修改成功, 请重新登录")
                EventBus.$emit("logout");
            }).catch(err => {
                this.$message.error("服务器连接错误");
            })
        }
    },
}
</script>

<style>

</style>