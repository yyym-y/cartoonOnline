<template>
  <div>
    <el-container>
        <el-aside width="200px">
            <LeftView></LeftView>
        </el-aside>
        <el-container>
            <el-header>
              <HeaderView></HeaderView>
            </el-header>
            <el-main>
              <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
  </div>
</template>

<script>
import LeftView from './leftpart/Left_view'
import HeaderView from './rightPart/header/Header_view'
import { EventBus } from '@/bus/Event_Bus'
export default {
    name : "Index_view",
    components : {
        LeftView, HeaderView
    },
    methods : {
      clear() {
        localStorage.setItem("username", "");
        localStorage.setItem("jwt", "");
        localStorage.setItem("uid", "");
        localStorage.setItem("email", "");
        localStorage.setItem("type", -1);
      }
    },
    mounted() {
      try{
        const jwt = localStorage.getItem("jwt");
        this.$api.project.checkJwt({jwt : jwt})
          .then(result => {
          result = result.data;
          if(result.code == 1) {
            this.$message.success(localStorage.getItem("username") + ", 欢迎回来~");
            EventBus.$emit("local_user", {
              username : localStorage.getItem("username"),
              email : localStorage.getItem("email"),
              uid : localStorage.getItem("uid"),
              type : localStorage.getItem("type")
            }) 
          } else {
            this.$message.error("Jwt 令牌失效， 请重新登陆")
            this.clear();
          }
        }).catch(err => { console.log("jwt get wrong"); this.clear();})
      } catch (e) { console.log("jwt get wrong"); this.clear(); }
    }
}
</script>

<style>
  .el-header {
    color: #333;
    line-height: 60px;
    background-color: #f7f8f9;
    min-width: 700px;
  }
  .el-aside {
    color: #333;
    text-align: center;
    line-height: 200px;
    height: 100vh;
    border-right-style: solid;
    border-right-color: #C0C4CC;
    border-right-width: 1px;

  }
  .el-main {
    color: #333;
    background-color: #f7f8f9;
  }
</style>
