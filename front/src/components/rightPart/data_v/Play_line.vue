<!-- 
    影视数据播放量图表
 -->
<template>
  <div id="play-line"></div>
</template>

<script>
import * as echarts from 'echarts'
export default {
    data() {
        return {
            seriesList : [],
            filterData : new Map(),
            dataTime : new Set()
        }
    },
    methods : {
        initData( res ) {
            res.sort(function(a, b) {
                if(a.year != b.year) return a.year - b.year;
                return a.month - b.month;
            })
            let id_map = new Map();
            echarts.util.each(res, (item) => {
                if(! this.filterData[item.cartoonId])
                    this.filterData[item.cartoonId] = []
                this.filterData[item.cartoonId].push(item.playTime);
                id_map.set(item.cartoonId, item.cartoon);
                this.dataTime.add(String(item.year) + "-" + String(item.month));
            })
            for (let [id, item] of id_map.entries()) {
                this.seriesList.push({
                    type : 'line',
                    stack: 'x',
                    data : this.filterData[id],
                    name : item.cartoonName,
                    endLabel: {
                        show: true,
                        formatter: function (params) {
                            let ori_Name = params.seriesName
                            if(ori_Name.length > 12) {
                                ori_Name = params.seriesName.substring(0,12);
                                ori_Name += "..."
                            }
                            return ori_Name
                        }
                    },
                    labelLayout: {
                        moveOverlap: 'shiftY'
                    },
                });
            }
        },
        runEchart() {
            let chartDom = document.getElementById('play-line');
            let myChart = echarts.init(chartDom);
            console.log(Array.from(this.dataTime))
            let option = {
                animationDuration: 1000,
                tooltip: {
                    order: 'valueDesc',
                    trigger: 'axis'
                },
                xAxis: {
                    data : Array.from(this.dataTime)
                },
                yAxis: {},
                grid: { right: 140, left:50, top : 70, bottom : 30 },
                labelLayout: {
                    moveOverlap: 'shiftY'
                },
                tooltip: {
                    order: 'valueDesc',
                    trigger: 'axis'
                },
                series: this.seriesList
            };
            myChart.setOption(option);
        }
    },
    mounted() {
        this.$api.project.getPlayLineData().then(result => {
            result = result.data;
            if(result.data == 0) {
                this.$message.error("数据读取失败...");
                return;
            }
            result = result.data;
            this.initData(result);
            this.runEchart(result)
        })
    }
}
</script>

<style scoped>
#play-line {
    width: 100%;
    height: 30em;
}
</style>