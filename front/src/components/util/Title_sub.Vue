<!-- 
  标题栏， 用于展示一些数据
  传入数据：
  1. msg: ''  标题栏上展示的数据
 -->
<template>
  <div class="title_sub">
    {{ msg }}
  </div>
</template>

<script>
export default {
    props : [ "msg" ]
}
</script>

<style>
.title_sub {
    margin-top: 16px;
    height: 30px;
    font-size: 18px;
    font-weight: 1000;
    border-bottom-style: solid;
    border-bottom-width: 1.9px;
    border-bottom-color: gray;
}
</style>
