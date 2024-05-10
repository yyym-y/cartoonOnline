<template>
  <div>
    <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose">

      <el-menu-item index="1">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-menu-item index="2">
        <i class="el-icon-menu"></i>
        <span slot="title">数据</span>
      </el-menu-item>

      <el-menu-item index="3">
        <i class="el-icon-document"></i>
        <span slot="title">排行</span>
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
export default {
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>
