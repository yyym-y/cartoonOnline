<template>
  <div>
    <TitleSub :msg="'欢迎来到管理员控制面板 :)'"></TitleSub>
    <Dashboard></Dashboard>
  </div>
</template>

<script>
import TitleSub from '@/components/util/Title_sub.vue';
import Dashboard from '@/components/rightPart/admin/Dashboard_admin.vue';
export default {
    components : {
        TitleSub, Dashboard
    }
}
</script>

<style>

</style>