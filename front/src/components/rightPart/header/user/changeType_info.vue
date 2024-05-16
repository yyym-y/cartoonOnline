<template>
  <div>
    我们不打算收费, 升级为VIP用户直接按下面的按钮即可:
    <el-button type="primary" @click="changeType">升级为VIP账户</el-button>
  </div>
</template>

<script>
import { EventBus } from '@/bus/Event_Bus'
export default {
    methods: {
        changeType() {
            this.$api.project.changeUserType({
                uid : localStorage.getItem("uid"),
                type : 2
            }).then(result => {
                result = result.data;
                if(result.code == 0) {
                    this.$message.error("升级账户失败...")
                    return;
                }
                this.$message.success("升级账户成功, 请重新登录~")
                EventBus.$emit("logout");
            }).catch(err => {
                this.$message.error("服务器连接错误");
            })
        }
    }
}
</script>

<style scoped>

</style>