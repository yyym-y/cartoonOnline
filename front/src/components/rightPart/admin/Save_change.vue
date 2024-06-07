<template>
  <div>
    <el-dialog title="提示"
        :visible.sync="dialogVisible"  width="36%">
        <el-steps :space="48" :active="finishNum" finish-status="success" direction="vertical">
            <!-- {{ ori_form }} -->
            <el-step title="修改影视名"></el-step>
            <el-step title="修改影视观看权限"></el-step>
            <el-step title="修改影视封面"></el-step>
            <el-step title="修改影视标签"></el-step>
            <el-step title="修改影视简介"></el-step>
            <el-step title="修改影视资源"></el-step>
        </el-steps>
    </el-dialog>
  </div>
</template>

<script>
import { EventBus } from '@/bus/Event_Bus'
export default {
    data() {
        return {
            dialogVisible : false,
            ori_form : {},
            final_form : {},
            finishNum : 0 
        }
    },
    methods : {
        changeCartoonName() {

        },
        changeCartoonPermit() {

        },
        changeCartoonCover() {

        },
        changeCartoonTags() {

        },
        changeDiscrip() {

        },
        changeEpisode() {
            let oriSet = new Set();
            let episodes = [];
            this.ori_form.episodeInfos.forEach(ele => {
                oriSet.add(JSON.stringify(ele));
            })
            this.final_form.episodeInfos.forEach(ele => {
                if(! oriSet.has(JSON.stringify(ele)))
                episodes.push({
                    cartoonId : this.ori_form.cartoonId,
                    m3u8_url : ele.m3u8_url,
                    num : ele.num,
                    type : ele.type
                })
            })
            console.log("driff", episodes)
            this.$api.admin.changeEpisode(episodes).then(res => {
                res = res.data;
                if(res.code == 1)
                    this.finishNum ++;
            })
        },
        process() {
            //console.log("---", this.ori_form.cartoonName,this.final_form.cartoonName, this.ori_form.cartoonName === this.final_form.cartoonName )
            if(this.ori_form.cartoonName === this.final_form.cartoonName)
                this.finishNum ++;
            else this.changeCartoonName();
            //console.log("---", this.ori_form.cartoonPermit,this.final_form.cartoonPermit, this.ori_form.cartoonPermit === this.final_form.cartoonPermit )
            if(this.ori_form.cartoonPermit === this.final_form.cartoonPermit)
                this.finishNum ++;
            else this.changecartoonPermit();
            //console.log("---", this.ori_form.cartoonCover,this.final_form.cartoonCover, this.ori_form.cartoonCover === this.final_form.cartoonCover )
            if(this.ori_form.cartoonCover === this.final_form.cartoonCover)
                this.finishNum ++;
            else this.changecartoonCover();
            //console.log("---", this.ori_form.tags,this.final_form.tags, JSON.stringify(this.ori_form.tags) == JSON.stringify(this.final_form.tags) )
            if(JSON.stringify(this.ori_form.tags) == JSON.stringify(this.final_form.tags))
                this.finishNum ++;
            else this.changeCartoonTags();
            //console.log("---", this.ori_form.discrip,this.final_form.discrip, this.ori_form.discrip === this.final_form.discrip )
            if(this.ori_form.discrip === this.final_form.discrip)
                this.finishNum ++;
            else this.changeDiscrip();
            if(JSON.stringify(this.ori_form.episodeInfos) == JSON.stringify(this.final_form.episodeInfos))
                this.finishNum ++;
            else this.changeEpisode();
        }
    },
    created() {
        EventBus.$on("saveChange", (payload) => {
            this.dialogVisible = true;
            this.ori_form = payload.ori_form;
            this.final_form = payload.final_form;
            this.process();
        })
    }
}
</script>

<style>

</style>