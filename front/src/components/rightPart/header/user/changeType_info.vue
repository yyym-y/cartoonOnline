<!-- 
    用户升级组件
 -->
<template>
  <div>
    <div>
      支付宝支付 1 元 永久解锁VIP 权限....  
    </div>
    <el-button type="primary" @click="changeType">升级为VIP账户</el-button>
  </div>
</template>

<script>
export default {
    methods: {
        changeType() {
            let uid = localStorage.getItem("uid");
            location.href = this.$baseURL + "/alipay/pay?userId=" + uid;
        }
    }
}
</script>

<style scoped>

</style>