<template>
  <div class="menu-bar" style="margin-top: 10px;">
    按热度排行  <el-switch v-model="value"
      active-color="#13ce66" inactive-color="#ff4949"></el-switch>
    <el-tabs type="card" v-model="activeName" style="margin-top: 10px;">
    <el-tab-pane label="影视标签" name="tags">
      <el-checkbox v-for="tag in tags" :key="tag" :label="tag" v-model="forms.tags"></el-checkbox>
    </el-tab-pane>
    <el-tab-pane label="地区" name="region">
      <el-checkbox v-for="(area, index) in areas" :key="'area' + index" :label="area" v-model="forms.area"></el-checkbox>
    </el-tab-pane>
    <el-tab-pane label="付费/免费" name="pay">
      <el-radio v-model="forms.pay" label="all">无限制</el-radio>
      <el-radio v-model="forms.pay" label="免费">免费</el-radio>
      <el-radio v-model="forms.pay" label="VIP">VIP</el-radio>
    </el-tab-pane>
    </el-tabs>
    <div class="tags" style="margin-top: 15px;">
      <el-tag v-for="(choice, index) in choices" :key="'key' + choice.name + 'index' + index" closable 
        :type="choice.type" @close="handleClose(choice)">
        {{choice.name}}
      </el-tag>
    </div>
  </div>
</template>

<script>
import { EventBus } from '@/bus/Event_Bus'
export default {
  data() {
    return {
      activeName : "tags",
      value : true,
      choices : [],
      areas : ["内地", "中国台湾", "中国香港", "日本"],
      tags : ["恋爱", "喜剧", "动作", "搞笑"],
      forms : {
        pay : "all",
        area : [],
        tags : []
      },
      setOneGet : null,
      fliterOri : [],
      send_arr : []
    }
  },
  methods : {
    handleClose(choice) {
        switch(choice.belong) {
          case "pay": {
            this.forms.pay = "all"
            break;
          }
          case "area": {
            this.forms.area.splice(this.forms.area.indexOf(choice.name), 1)
            break;
          }
          case "tag": {
            this.forms.tags.splice(this.forms.tags.indexOf(choice.name), 1)
            break;
          }
        }
    },
    RankByPlatTime() {
      let final_ori = this.fliterOri;
      if(this.value) {
        final_ori.sort(function(a, b) {
          return b.playTime - a.playTime;
        })
      }
      this.send_arr = []
      final_ori.forEach(element => {
        this.send_arr.push(element.cartoon)
      })
    },
    changeMenu() {
      this.$api.project.getTagMenu(this.choices).then((result) => {
        result = result.data
        console.log("result", result);
        if(result.code == 0) {
          this.$message.error('一些莫名其妙的错误发生了...'); return;
        }
        this.fliterOri = [];
        for(let i = 0 ; i < result.data.length ; i++) {
          let element = result.data[i];
          if(this.forms.pay != "all") {
            if(this.forms.pay == "VIP" && element.cartoon.cartoonPermit == 0)
              continue;
            if(this.forms.pay == "免费" && element.cartoon.cartoonPermit == 1)
              continue;
          }
          this.fliterOri.push(element);
        }
        this.RankByPlatTime();
        console.log(this.send_arr)
        EventBus.$emit("menuBarChange", this.send_arr);
      }).catch(err => {
        this.$message.error('服务器连接失败...');
      })
    }
  },
  created() {
    let _ = require('lodash')
    this.setOneGet = _.debounce(this.changeMenu, 500);
    this.changeMenu();
  },
  computed: {
    computedArea() {
      return [...this.forms.area];
    },
    computedTags() {
      return [...this.forms.tags];
    }
  },
  watch: {
    'forms.pay'(newV, oldV) {
      if (oldV !== "all") {
        const index = this.choices.findIndex(c => c.name === oldV && c.belong === "pay");
        if (index !== -1) this.choices.splice(index, 1);
      }
      if (newV !== "all") {
        this.choices.push({ name: newV, belong: "pay", tagId : -1});
      }
      this.setOneGet();
    },
    computedArea : {
      handler: function(newV, oldV) {
        if (newV.length > oldV.length) {
          newV.forEach(element => {
            if (!oldV.includes(element)) {
              this.choices.push({ name: element, belong: "area", tagId : -1});
            }
          });
        } else {
          oldV.forEach(element => {
            if (!newV.includes(element)) {
              const index = this.choices.findIndex(c => c.name === element && c.belong === "area");
              if (index !== -1) this.choices.splice(index, 1);
            }
          });
        }
        this.setOneGet();
      },
      deep: true
    },
    computedTags : {
      handler: function(newV, oldV) {
        if (newV.length > oldV.length) {
          newV.forEach(element => {
            if (!oldV.includes(element)) {
              this.choices.push({ name: element, belong: "tag", tagId : -1});
            }
          });
        } else {
          oldV.forEach(element => {
            if (!newV.includes(element)) {
              const index = this.choices.findIndex(c => c.name === element && c.belong === "tag");
              if (index !== -1) this.choices.splice(index, 1);
            }
          });
        }
        this.setOneGet();
      },
      deep: true
    },
    value() {
      this.RankByPlatTime();
      EventBus.$emit("menuBarChange", this.send_arr);
    }    
  },

}
</script>

<style>

</style>