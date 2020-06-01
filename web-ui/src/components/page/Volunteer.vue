<template>
    <div class="height_all">
        <el-row class="border height_half">
            <el-col :span="8" class="border height_all">
                <div ref="grade_partition" class="full"></div>
            </el-col>
            <el-col :span="8" class="border height_all">
                <div ref="grade" class="full"></div>
            </el-col>
            <el-col :span="8" class="border height_all">
                <div ref="level" class="full"></div>
            </el-col>
        </el-row>
        <el-row class="border height_half">
            <el-col :span="24" class="border height_all">
                <div ref="academy" class="full"></div>
            </el-col>
        </el-row>

    </div>
</template>

<script>
    export default {
        name: "volunteer",
        methods:{
            sendRequest(){
                this.$api.volunteer.levelAnalysis().then(res=>{
                    if (res.data != null) {
                        this.level(res.data)
                    }
                })
                this.$api.volunteer.gradeParticipationAnalysis().then(res=>{
                    if (res.data != null) {
                        this.gradePartition(res.data)
                    }
                })
                this.$api.volunteer.academyParticipationAnalysis().then(res=>{
                    if (res.data != null) {
                        this.academy(res.data)
                    }
                })
                this.$api.volunteer.gradeAnalysis().then(res=>{
                    if (res.data != null) {
                        this.grade(res.data)
                    }
                })
            },
            level(data){
                let levelObject = {
                    value: 24.2,
                    text: '-',
                    color: ['rgba(36, 209, 209, 1)', 'rgba(252, 142, 108, 1)', 'rgb(92,252,158)', 'rgb(255,112,203)'],
                    seriesData: [],
                    titleData: [],
                }
                levelObject.titleData.push({
                    text: '志愿服务各维度获奖数',
                    left: 'center',
                    top:'1%',
                    textStyle: {
                        color: '#fff',
                    }
                })
                data.forEach((x,index)=>{
                    levelObject.titleData.push({
                        text:  x.volunteerLevel,
                        left: 18 * (index*1.2 + 1) - .5 + '%',
                        top: '70%',
                        textAlign: 'center',
                        textStyle: {
                            fontSize: '16',
                            color: levelObject.color[index],
                            fontWeight: 'bold',
                        }
                    });
                    levelObject.seriesData.push({
                        type: 'pie',
                        radius: ['25%', '28%'],
                        center: [18 * (index*1.2 + 1) + '%', '50%'],
                        hoverAnimation: false,
                        label: {
                            normal: {
                                position: 'center'
                            },
                        },
                        data: [{
                            value: x.count,
                            name: x.volunteerLevel,
                            itemStyle: {
                                normal: {
                                    color: levelObject.color[index],
                                }
                            },
                            label: {
                                normal: {
                                    show: false,
                                }
                            }
                        },
                            {
                                value: index*100,
                                name: '占位',
                                tooltip: {
                                    show: false
                                },
                                itemStyle: {
                                    normal: {
                                        color: '#edf1f4',
                                    }
                                },
                                label: {
                                    normal: {
                                        formatter: x.count+"人",
                                        textStyle: {
                                            fontSize: 16,
                                            color: 'rgba(255,255,255,1)',
                                        }
                                    },

                                }
                            }
                        ]
                    })
                })
                let option = {
                    backgroundColor: 'rgba(2,22,48,1)',
                    title: levelObject.titleData,
                    series: levelObject.seriesData,
                    tooltip:{},
                    grid: {
                        top: "30px",
                        left: "45px",
                        right: "0",
                        bottom: "39px"
                    }
                }
                this.levelChart.setOption(option)
            },
            gradePartition(data){
                let titleArr= [], seriesArr=[];
                let colors=[['#389af4', '#dfeaff'],['#ffc257', '#ffedcc'],
                    ['#a181fc', '#e3d9fe'],['#ff8c37', '#ffdcc3'],['#fd6f97', '#fed4e0']]

                data.forEach((x,index)=>{
                    titleArr.push(
                        {
                            text:x.grade+"级",
                            left: index * 30+ 19 +'%',
                            top: '70%',
                            textAlign: 'center',
                            textStyle: {
                                fontWeight: 'bold',
                                fontSize: '16',
                                color: colors[index][0],
                                textAlign: 'center',
                            },
                        }
                    );
                    seriesArr.push(
                        {
                            name: x.grade,
                            type: 'pie',
                            clockWise: false,

                            radius: ['30%', '33%'],
                            itemStyle:  {
                                normal: {
                                    color: colors[index][0],
                                    shadowColor: colors[index][0],
                                    shadowBlur: 0,
                                    label: {
                                        show: false
                                    },
                                    labelLine: {
                                        show: false
                                    },
                                }
                            },
                            hoverAnimation: false,
                            center: [index * 30 + 20 +'%', '50%'],
                            data: [{
                                value: x.percentage,
                                label: {
                                    normal: {
                                        formatter: function(){
                                            return (x.percentage*100).toFixed(2)+'%';
                                        },
                                        position: 'center',
                                        show: true,
                                        textStyle: {
                                            fontSize: '20',
                                            fontWeight: 'bold',
                                            color: colors[index][0]
                                        }
                                    }
                                },
                            }, {
                                value: 1-x.percentage,
                                name: 'invisible',
                                itemStyle: {
                                    normal: {
                                        color: colors[index][1]
                                    },
                                    emphasis: {
                                        color: colors[index][1]
                                    }
                                }
                            }]
                        }
                    )
                })
                titleArr.push({
                    text: '各年级志愿服务的参与率',
                    left: 'center',
                    top:'1%',
                    textStyle: {
                        color: '#fff',
                    }
                })

                let option = {
                    backgroundColor:"#101e44",
                    title:titleArr,
                    series: seriesArr,
                    tooltip:{}
                }
                this.gradePartitionChart.setOption(option)
            },
            academy(data){
                let academyObject = {
                    academy:[],
                    count:[],
                    countAll:[]
                }
                data.forEach(x=>{
                    academyObject.academy.push(x.academy)
                    academyObject.count.push(x.count)
                    academyObject.countAll.push(x.count_all)
                })
                let option = {
                    backgroundColor: '#0E2A43',
                    title: {
                        text: '各学院志愿服务的参与率',
                        left: 'center',
                        top:'1%',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    legend: {
                        top: '10%',
                        right:'3%',
                        textStyle: {
                            color: '#fff',
                        },
                        data: ['参与人数','总人数']
                    },
                    grid: {
                        left: '6%',
                        right: '3%',
                        bottom: '10%',
                        // containLabel: true
                    },

                    tooltip: {
                        show: "true",
                        trigger: 'axis',
                        axisPointer: { // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    yAxis: {
                        type: 'value',
                        // show:false,
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: '#fff',
                            }
                        },
                        splitLine: {
                            show: false,
                            lineStyle: {
                                color: '#aaa',
                            }
                        },
                    },
                    xAxis: [{
                        type: 'category',
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            interval:0,
                            formatter: function (value) {
                                if(value.length>6)
                                    return value.substr(0,6)+'...'
                                else
                                    return value
                            },
                            // rotate:40
                        },
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: '#fff',
                            }
                        },
                        data:academyObject.academy
                    }, {
                        type: 'category',
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            show: false
                        },
                        splitArea: {
                            show: false
                        },
                        splitLine: {
                            show: false
                        },
                        data: academyObject.academy
                    }

                    ],
                    series: [{
                        name: '总人数',
                        type: 'bar',
                        xAxisIndex: 1,

                        itemStyle: {
                            normal: {
                                show: true,
                                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#1270c6'
                                }, {
                                    offset: 1,
                                    color: '#64aeff'
                                }]),
                                barBorderRadius: 10,
                                borderWidth: 0,
                                borderColor: '#333',
                            }
                        },
                        barWidth: '25',
                        data: academyObject.countAll,

                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                    }, {
                        name: '参与人数',
                        type: 'bar',
                        barWidth: '25',
                        itemStyle: {
                            normal: {
                                show: true,
                                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#3023AE'
                                }, {
                                    offset: 1,
                                    color: '#C96DD8'
                                }]),
                                barBorderRadius: 10,
                                borderWidth: 0,
                                borderColor: '#333',
                            }
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        // barGap: '100%',
                        data: academyObject.count
                    }

                    ]
                };
                this.academyChart.setOption(option);

            },
            grade(data){
                var colorArr=["#db53f8", "#01cbb3", "#85e647", "#5d5cda", "#05c5b0", "#c29927"];
                var colorAlpha=['rgba(60,170,211,0.05)','rgba(1,203,179,0.05)','rgba(133,230,71,0.05)','rgba(93,92,218,0.05)','rgba(5,197,176,0.05)','rgba(194,153,39,0.05)']

                let data1=[]
                data.forEach((x,i)=>{
                    data1.push({
                        value: x.count,
                        name: x.grade,
                        itemStyle: {
                            borderColor: colorArr[i%colorArr.length],
                            borderWidth:2,
                            shadowBlur: 20,
                            shadowColor: colorArr[i%colorArr.length],
                            shadowOffsetx: 25,
                            shadowOffsety: 20,
                            color:colorAlpha[i%colorAlpha.length]
                        }
                    })
                })
                let option = {
                    backgroundColor:"#090e36",
                    title: {
                        text: '志愿服务年级占比',
                        left: 'center',
                        top:'1%',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{b} 级: {c} ({d}%)"
                    },
                    polar: {
                        radius: "70%"
                    },
                    angleAxis: {
                        interval: 1,
                        type: 'category',
                        data: [],
                        z: 10,
                        axisLine: {
                            show: false,
                            lineStyle: {
                                color: "#0B4A6B",
                                width: 1,
                                type: "solid"
                            },
                        },
                        axisLabel: {
                            interval: 0,
                            show: true,
                            color: "#0B4A6B",
                            margin: 8,
                            fontSize: 16
                        },
                    },
                    radiusAxis: {
                        min: 20,
                        max: 80,
                        interval: 20,
                        axisLine: {
                            show: false,
                            lineStyle: {
                                color: "#0B3E5E",
                                width: 1,
                                type: "solid"
                            },
                        },
                        axisLabel: {
                            formatter: '{value} %',
                            show: false,
                            padding: [0, 0, 0, 0],
                            color: "#0B3E5E",
                            fontSize: 16
                        },
                        splitLine: {
                            lineStyle: {
                                color: "#07385e",
                                width: 2,
                                type: "dashed"
                            }
                        }
                    },
                    calculable: true,
                    series: [ {
                        stack: 'a',
                        type: 'pie',
                        radius: '60%',
                        roseType: 'radius',
                        // zlevel:10,
                        // startAngle: 100,
                        label: {
                            normal: {
                                show:true,
                                formatter: '{b|{b}级：}{d|{d}%}',
                                // position:'inner',
                                rich: {
                                    b: {
                                        color: '#d0fffc',
                                        fontSize: 16,
                                        lineHeight: 20
                                    },
                                    d: {
                                        color: '#d0fffc',
                                        fontSize: 12,
                                        height: 20
                                    },
                                },
                            }
                        },
                        labelLine: {
                            normal: {
                                show: true,
                                length: 5,
                                length2: 10,
                                lineStyle: {
                                    color: '#0096b1'

                                }
                            },
                            emphasis: {
                                show: false
                            }
                        },
                        data: data1
                    }]
                }
                this.gradeChart.setOption(option)

            }

        },
        mounted(){
            this.levelChart = this.$echarts.init(this.$refs.level);
            this.gradePartitionChart = this.$echarts.init(this.$refs.grade_partition);
            this.gradeChart = this.$echarts.init(this.$refs.grade);
            this.academyChart = this.$echarts.init(this.$refs.academy);
            window.onresize = () => {
                //  根据窗口大小调整曲线大小
                this.levelChart.resize()
                this.gradePartitionChart.resize()
                this.gradeChart.resize()
                this.academyChart.resize()
            };
            this.sendRequest()
        }
    }
</script>

<style scoped>
    .height_all{
        height: 100%;
    }
    .height_half{
        height: 50%;
    }
    .full{
        height: 100%;
        width: 100%;
    }

</style>