<template>
  <VideoItem :baseInfo="baseInfo"></VideoItem>
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
    mounted() {
    }
}
</script>

<style>

</style>