<!-- 
    编辑某个影视选项的抽屉框选项:
    监听事件
-->
<template>
  <div>
    <el-drawer
        :title="title" :visible.sync="drawer" size="56%"
        :direction="'rtl'" :before-close="handleClose">
        <el-form :label-position="'right'" label-width="16%" :model="form" class="editFrom">
            <el-form-item label="影视名称">
                <el-input v-model="form.cartoonName"></el-input>
            </el-form-item>
            <el-form-item label="影视观看权限">
                <el-select v-model="form.cartoonPermit" placeholder="请选择观看权限">
                    <el-option :label="'免费观看'" :value="0"></el-option>
                    <el-option :label="'VIP专属'" :value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="影视封面">
                <UploadCover :abs_url="form.cartoonCover"></UploadCover>
            </el-form-item>
            <el-form-item label="影视标签">
                <TagAdder :tags="form.tags"></TagAdder>
            </el-form-item>
            <el-form-item label="影视简介">
                <el-input type="textarea" autosize
                            v-model="form.discrip" class="Editdiscrip">
                </el-input>
            </el-form-item>
            <el-form-item label="每集信息管理">
                <EposodeAdder :cartoonId="form.cartoonId"></EposodeAdder>
                <EditEpisode v-for="(pr, index) in form.episodeInfos" :key="'editEpisode' + pr + index"
                    :num="pr.num" :abs_url="pr.m3u8_url"></EditEpisode>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">保存更改</el-button>
            </el-form-item>
        </el-form>
    </el-drawer>
    <SaveChange></SaveChange>
  </div>
</template>

<script>
import { EventBus } from '@/bus/Event_Bus'
import UploadCover from './Upload_cover.vue'
import TagAdder from './Tag_adder.vue'
import EditEpisode from './Edit_episode.vue';
import EposodeAdder from './Episode_adder.vue'
import SaveChange from './Save_change.vue';
export default {
    components : {
        UploadCover, TagAdder, EditEpisode, EposodeAdder, SaveChange
    },
    data() {
        return {
            ori_info : {},
            form : {},
            drawer : false,
            title : "",
        }
    },
    methods : {
        handleClose(done) {
            this.$confirm('请确保你的更改已保存?').then( _ => {
                done();
            }).catch(_ => {});
        },
        onSubmit() {
            EventBus.$emit("saveChange", {
                ori_form : this.ori_info,
                final_form : this.form
            })
            console.log("submit")
        }
    },
    created() {
        EventBus.$on("VideoEdit", (payload) => {
            this.ori_info = payload.row
            this.form = JSON.parse(JSON.stringify(payload.row))
            this.imgUrl = this.$baseURL + payload.row.cartoonCover
            this.title = "编辑影视 " + payload.row.cartoonId + ":"
            this.drawer = true
        })
        EventBus.$on("uploadVideoReturn", (payload) => {
            if(payload.type == "new") {
                let arr = this.form.episodeInfos;
                console.log(this.form)
                for(let i = 0 ; i < arr.length ; i ++) {
                    console.log("---))",arr, i, arr[i].num, payload.num, arr[i].num == payload.num)
                    if(arr[i].num == payload.num) {
                        this.$message.error("该集资源已存在...");
                        return;
                    }
                }
                let type_str = payload.localImport ? "new_local" : "new_internet";
                this.form.episodeInfos.push({
                    num : payload.num,
                    type : type_str,
                    m3u8_url : payload.realUrl
                })
                this.$message.success("添加成功");
            }
        })
    }
}
</script>

<style>
.editFrom {
    margin-left: 8px;
}
.editFrom .el-form-item {
    width: 96%;
}
.imgSize {
    width: 200px;
    border: solid 1px rgba(0,0,0,0.2);
}
</style>