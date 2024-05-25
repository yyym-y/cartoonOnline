<!-- 
  左侧的布局组件 
-->
<template>
  <div>
    <Iconview></Iconview>
    <NavMenu></NavMenu>
  </div>
</template>

<script>
import Iconview from "./util/Icon_view.vue";
import NavMenu from './util/Nav_menu'

export default {
    name : "Left_View",
    components : {
        NavMenu, Iconview
    }
}
</script>

<style>

</style>
