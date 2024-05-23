<template>
  <div id="login_time"></div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  data() {
    return {
      xdata : [],
      oriData : [],
      rates : []
    }
  },
  methods: {
    initData() {
      for(let i = 0 ; i < 24 ; i ++) {
        if(i < 10) this.xdata[i] = '0' + String(i) + ":00";
        else this.xdata[i] = String(i) + ":00";
        this.oriData[i] = Math.floor((Math.random()*100)+1);
      }
      let sum_num = 0;
      for(let i = 0 ; i < 24 ; i ++)
        sum_num += this.oriData[i];
      for(let i = 0 ; i < 24 ; i ++) {
        this.rates[i] = (this.oriData[i] / sum_num);
      }
    }
  },
  mounted() {
    var chartDom = document.getElementById('login_time');
    var myChart = echarts.init(chartDom);
    this.initData();
    let option = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      toolbox: {
        feature: {
          dataView: { show: true, readOnly: false },
          magicType: { show: true, type: ['line', 'bar'] },
          restore: { show: true },
          saveAsImage: { show: true }
        }
      },
      legend: {
        data: ['eachNum', 'rate']
      },
      xAxis: [
        {
          type: 'category',
          data: this.xdata,
          axisPointer: {
            type: 'shadow'
          }
        }
      ],
      grid: { right: 100, left:50, top : 90, bottom : 30 },
      yAxis: [
        {
          type: 'value',
          name: 'eachNum',
          min: 0,
          max: 250,
          interval: 50,
        },
        {
          type: 'value',
          name: 'rate',
          min: 0,
          max: 1,
          interval: 0.1,
        }
      ],
      series : [
        {
          name: 'eachNum',
          type: 'bar',
          data: this.oriData
        },
        {
          name: 'rate',
          type: 'line',
          yAxisIndex: 1,
          data: this.rates
        },
      ]
    }
    myChart.setOption(option)
  }
}
</script>

<style>
#login_time {
    width: 100%;
    height: 30em;
}
</style>