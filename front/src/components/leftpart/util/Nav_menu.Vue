<template>
  <div>
    <el-menu
      default-active="1"
      class="el-menu-vertical-demo">

      <el-menu-item index="1" @click="returnHome()">
        <i class="el-icon-s-home"></i>
        <span slot="title" >首页</span>
      </el-menu-item>

      <el-menu-item index="2" @click="goToData()">
        <i class="el-icon-menu"></i>
        <span slot="title">数据</span>
      </el-menu-item>

      <el-menu-item index="3" @click="goToRank()">
        <i class="el-icon-s-data"></i>
        <span slot="title">排行</span>
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
export default {
  methods: {
    returnHome() {
      this.$router.push({name:"home"}).catch(err => {})
    },
    goToData() {
      this.$router.push({name:"data"}).catch(err => {})
    },
    goToRank() {
      this.$router.push({name:"rank"}).catch(err => {})
    }
  },
}
</script>
