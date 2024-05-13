<template>
  <div>
    <VideoPlay :source="sourceUrl"></VideoPlay>
  </div>
</template>

<script>
import VideoPlay from '@/components/util/Video_play.vue';
export default {
    components : {
        VideoPlay
    },
    data() {
        return {
            sourceUrl : "http://10.252.114.70:8888/video/32c8841/1/tem.m3u8"
        }
    }
}
</script>

<style>

</style>