<template>
  <div>
    <el-table style="width: 100%" :data="tableData">
        <el-table-column type="expand">
            <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="影视封面:">
                        <span>{{ props.row.cartoonCover }}</span>
                    </el-form-item>
                    <el-form-item label="影视标签:">
                        <el-tag v-for="(pr, index) in props.row.tags" :key="pr + index"
                            style="margin-right: 4px;">{{ pr.name }}</el-tag>
                    </el-form-item>
                    <el-form-item label="影视简介:">
                        <el-input type="textarea" autosize
                            v-model="props.row.discrip" class="discrip">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="每集信息">
                        <el-collapse style="width: 60vw;">
                            <el-collapse-item name="1">
                                <div v-for="(pr, index) in props.row.episodeInfos" :key="pr + index"
                                    style="margin-top: 4px;">
                                    <el-col :span="4">
                                        第 {{ pr.num }} 集 :
                                    </el-col>
                                    <el-col :span="20">
                                        <el-link @click="jumpToVideo()" target="_blank">{{ pr.m3u8_url }}</el-link>
                                    </el-col>
                                </div>
                            </el-collapse-item>
                        </el-collapse>
                    </el-form-item>
                </el-form>
            </template>
        </el-table-column>
        <el-table-column label="影视 ID" prop="cartoonId"></el-table-column>
        <el-table-column label="影视名称" prop="cartoonName"></el-table-column>
        <el-table-column label="影视观看权限" prop="cartoonPermit"></el-table-column>
        <el-table-column label="总共集数" prop="episode"></el-table-column>
        <el-table-column align="right">
            <template slot="header" slot-scope="scope">
                <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
            </template>
            <template slot-scope="scope">
                <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
            </template>     
        </el-table-column>
    </el-table>
    <VideoEditDrawer></VideoEditDrawer>
  </div>
</template>

<script>
import VideoEditDrawer from './Video_editDrawer.vue';
import { EventBus } from '@/bus/Event_Bus'
export default {
    components : {
        VideoEditDrawer
    },
    data() {
        return {
            search : "",
            tableData: []
        }
    },
    methods : {
        handleEdit(index, row) {
            EventBus.$emit("VideoEdit", { row: JSON.parse(JSON.stringify(row)) })
        },
        handleDelete(index, row) {
            console.log(index, row);
            this.$confirm('此操作将永久删除该影视（所有视频）, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({ type: 'success', message: '删除成功!'});
            }).catch(() => {
                this.$message({ type: 'info', message: '已取消删除' });          
            });
        },
        jumpToVideo() {

        }
    },
    created() {
        this.$api.project.getAllTableInfos().then(result => {
            result = result.data;
            if(result.code == 0) {
                this.$message.error("数据出错..."); return;
            }
            result = result.data;
            result.forEach(element => {
                element.episodeInfos.sort(function(a, b) {
                    return a.num - b.num;
                })
            });
            this.tableData = result;
        }).catch(err => { this.$message.err("服务器连接错误...") })
    }
}
</script>

<style>
.demo-table-expand {
    font-size: 0;
    width: 100%;
}
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
    margin-left: 16px;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
}
.discrip {
    width: 60vw;
    margin-top: 14px;
}
</style>
