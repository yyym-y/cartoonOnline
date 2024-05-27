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
      }
    }
  },
  methods : {
    handleClose(choice) {
        console.log(choice)
        switch(choice.belong) {
          case "pay": {
            this.forms.pay = "all"
            break;
          }
          case "area": {
            console.log(this.forms.area.indexOf(choice.name))
            this.forms.area.splice(this.forms.area.indexOf(choice.name), 1)
            console.log("----", this.forms.area)
            break;
          }
          case "tags": {
            break;
          }
        }
        //this.choices.splice(this.choices.indexOf(choice), 1);
    },
  },
  created() {
    this.$api.project.getMenu().then((result) => {
      result = result.data;
      if(result.code == 0) {
        this.$message.error('服务器连接成功,列表数据请求失败');
        return;
      }
      EventBus.$emit("menuBarChange", result.data);
    }).catch(err => {
      this.$message.error('服务器连接失败...');
    })
  },
  watch : {
    'forms.pay'(newV, oldV) {
        this.choices.splice(this.choices.indexOf({name : oldV}), 1);
        if(newV != "all")
          this.choices.push({name : newV, belong : "pay"})
    },
    'forms.area'(newV, oldV) {
      console.log("new", newV);
      console.log("old", oldV);
      //if(newV.length == oldV.length) return;
      if(newV.length > oldV.length) {
        newV.forEach(element => {
          if(oldV.indexOf(element) == -1)
            this.choices.push({name : element, belong : "area"})
        });
      } else {
        oldV.forEach(element => {
          if(newV.indexOf(element) == -1)
            this.choices.splice(this.choices.indexOf({name : element, belong : "area"}), 1);
        })
      }
    },
    'forms.tags'(newV, oldV) {
      //if(newV.length == oldV.length) return;
      if(newV.length > oldV.length) {
        newV.forEach(element => {
          if(oldV.indexOf(element) == -1)
            this.choices.push({name : element, belong : "tags"})
        });
      } else {
        oldV.forEach(element => {
          if(newV.indexOf(element) == -1)
            this.choices.splice(this.choices.indexOf({name : element, belong : "tags"}), 1);
        })
      }
    }
  }
}
</script>

<style>

</style>