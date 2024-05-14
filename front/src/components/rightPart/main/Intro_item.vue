<template>
    <VideoItem :baseInfo="baseInfo" :clickable="false"></VideoItem>
</template>
  
<script>
  import VideoItem from '@/components/util/Video_item'
  export default {
      props : [ "baseInfo" ],
      components : {
          VideoItem
      },
      data() {
          return {
  
          }
      },
  }
</script>
  
<style>
  
</style>