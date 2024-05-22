<!-- 
  播放视频的组件[基于video-player]
  传入数据：
  1. source : ".m3u8"  播放源，m3u8格式
 -->
<template>
    <div>
      <video-player ref="videoPlayer" :options="playerOptions" class="video-player vjs-custom-skin"/>
    </div>
  </template>
  
  <script>
  export default {
      props : [ "source" ],
      data() {
          return {
              playerOptions: {
                  autoplay: false,
                  hls:true,
                  aspectRatio: '16:9',
                  fluid: false, 
                  sources: [
                  {
                      src: "",
                      type: 'application/x-mpegURL'
                  }
                  ],
              },
          }
      },
      mounted() {
        this.playerOptions['sources'][0]['src'] = this.source
      },
      methods: {
        changeSource(url) {
            this.playerOptions['sources'][0]['src'] = url
        }
      }
  }
  </script>
  
  <style>
  
  </style>