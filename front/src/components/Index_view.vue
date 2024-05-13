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
export default {
    name : "Index_view",
    components : {
        LeftView, HeaderView
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
