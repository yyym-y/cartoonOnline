<template>
    <div id="rankListImg">
    </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
    props : ["data", "title"],
    data() {
        return {
            yData : [],
            xData : [],
            myChart: null
        }
    },
    methods : {
        initData() {
            for(let i = 0 ; i < this.data.length ; i ++) {
                this.yData[i] = this.data[i].cartoon.cartoonName;
                this.xData[i] = this.data[i].playTime;
            }
            this.yData.reverse();
            this.xData.reverse();
        },
        initChart() {
            var chartDom = document.getElementById('rankListImg');
            chartDom.style.height = String(55 * this.data.length) + 'px';
            this.myChart = echarts.init(chartDom);
            this.initData();
            var option = {
                title: { text: this.title },
                grid: { left: "12%"},
                xAxis: { 
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis : {
                    type: 'category',
                    data : this.yData
                },
                series: [
                    {
                        type: 'bar',
                        data: this.xData
                    }
                ]
            }
            this.myChart.setOption(option);
            window.addEventListener('resize', () => {
                chartDom.style.height = String(55 * this.data.length) + 'px';
                this.myChart.resize();
            });
        }
    },
    watch: {
        data: {
            handler() {
                if (this.data && this.data.length > 0) {
                    this.initChart();
                }
            },
            immediate: true
        }
    }
}
</script>


<style scoped>
#rankListImg {
    width: 100%;
}
</style>