<!-- 
  登录账户按钮组件
 -->
<template>
  <div class="login-btn">
    <el-button type="info" @click="dialogVisible=true" round>登录账户</el-button>

    <el-dialog title="账户登录" :visible.sync="dialogVisible" width="27em">
      <LoginForm></LoginForm>
    </el-dialog>
  </div>
</template>

<script>
import LoginForm from './Login_form.vue'
export default {
  components : {
    LoginForm
  },
  data() {
    return{
      dialogVisible: false,
      refindVisible :false,
      registerVisible : false
    }
  },
  methods: {
    closeItem() {
      this.showLogin()
      this.dialogVisible = false;
    },
    showRegister() {
      this.registerVisible = true
    },
    showRefind() {
      this.refindVisible = true
    },
  }
}
</script>

<style>
.login-btn {
    float : right;
}
</style>