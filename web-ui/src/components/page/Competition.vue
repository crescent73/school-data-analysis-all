<template>
    <div class="height_all">
        <el-row class="border height_all">
<!--            <el-col :span="8" class="border height_all">-->
<!--                <div ref="ranking" class="full"></div>-->
<!--            </el-col>-->
            <el-col :span="24" class="border height_all">
                <el-row class="border height_half">
                    <div ref="competition" class="full"></div>
                </el-row>
                <el-row class="border height_half">
                    <el-col :span="12" class="border height_all">
                        <div ref="level" class="full"></div>
                    </el-col>
                    <el-col :span="12" class="border height_all">
                        <div ref="academy" class="full"></div>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "competition",
        methods:{
            sendRequest(){
                this.$api.competition.analysis().then(res=>{
                    if (res.data!= null) {
                        // academy 数据展示
                        this.academy(res.data.filter(x=>x.academy === 'all'))
                        // competiton 数据显示
                        this.competition(res.data.filter(x=>x.academy != 'all'))
                    }
                })
                this.$api.competition.levelAnalysis().then(res=>{
                    if(res.data != null) {
                        this.level(res.data)
                    }
                })
            },
            competition(data){
                const colors = {
                    middleColor:['rgba(245,77,77,0.68)','rgba(255,250,32,0.6)','rgba(114,255,75,0.69)'
                        ,'rgba(60,211,255,0.67)','rgba(195,46,255,0.69)'],
                    topColor:['rgb(245,77,77)','rgb(255,250,32)','rgb(114,255,75)'
                        ,'rgb(60,211,255)','rgb(195,46,255)']
                }
                let academyObject = {
                    'xAxis':[],// 横轴，各学院名
                    'series':[],
                    'legend':[]
                }
                let groupedData = {}
                data.forEach(x=>{
                    groupedData[x.type] = groupedData[x.type] || []
                    groupedData[x.type].push(x)
                })
                let seriesData=[]
                let index = 0;
                for(let key in groupedData) {
                    seriesData = []
                    let i;
                    academyObject.legend.push(key);
                    groupedData[key].forEach(x=>{
                        if ((i = academyObject.xAxis.indexOf(x.academy))<0) {
                            academyObject.xAxis.push(x.academy)
                            seriesData.push(x.count)
                        } else {
                            seriesData[i] = x.count
                        }
                    })
                    academyObject.series.push({
                        name:key,
                        type:'bar',
                        data:seriesData,
                        barWidth: 8,
                        barGap:0.2,//柱间距离
                        label: {//图形上的文本标签
                            normal: {
                                show: false,
                                position: 'top',
                                textStyle: {
                                    color: '#a8aab0',
                                    fontStyle: 'normal',
                                    fontFamily: '微软雅黑',
                                    fontSize: 12,
                                },
                            },
                        },
                        itemStyle: {//图形样式
                            normal: {
                                barBorderRadius: [5, 5, 0, 0],
                                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                    {
                                        offset: 1, color: colors.middleColor[index]
                                    }, {
                                        offset: 0, color: colors.topColor[index]
                                    }], false),
                            },
                        },
                    })
                    index++;
                }
                let option = {
                    title : {
                        text: '各学院参加各大赛事数据图',
                        // left: '1%',
                        top: '1%',
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    backgroundColor: '#17163a',
                    tooltip: { //提示框组件
                        trigger: 'axis',
                        // formatter: '{b}<br />{a0}: {c0}<br />{a1}: {c1}',
                        axisPointer: {
                            type: 'shadow',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        },
                        textStyle: {
                            color: '#fff',
                            fontStyle: 'normal',
                            fontFamily: '微软雅黑',
                            fontSize: 12,
                        },
                    },
                    grid: {
                        left: '1%',
                        right: '2%',
                        bottom: '5%',
                        top:'15%',
                        padding:'0 0 10 0',
                        containLabel: true,
                    },
                    legend: {//图例组件，颜色和名字
                        right:10,
                        top:'1%',
                        itemGap: 16,
                        itemWidth: 17,
                        itemHeight: 17,
                        data:academyObject.legend,
                        textStyle: {
                            color: '#fff',
                            fontStyle: 'normal',
                            fontFamily: '微软雅黑',
                            fontSize: 14,
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: true,//坐标轴两边留白
                            data: academyObject.xAxis,
                            axisLabel: { //坐标轴刻度标签的相关设置。
                                interval: 0,//设置为 1，表示『隔一个标签显示一个标签』
                                margin:15,
                                textStyle: {
                                    color: '#ffffff',
                                    fontStyle: 'normal',
                                    fontFamily: '微软雅黑',
                                    fontSize: 12,
                                },
                                formatter: function (value) {
                                    return value.substr(0,3)+'...'
                                },
                            },
                            axisTick:{//坐标轴刻度相关设置。
                                show: false,
                            },
                            axisLine:{//坐标轴轴线相关设置
                                lineStyle:{
                                    color:'#fff',
                                    opacity:0.2
                                }
                            },
                            splitLine: { //坐标轴在 grid 区域中的分隔线。
                                show: false,
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            splitNumber: 5,
                            axisLabel: {
                                textStyle: {
                                    color: '#a8aab0',
                                    fontStyle: 'normal',
                                    fontFamily: '微软雅黑',
                                    fontSize: 12,
                                }
                            },
                            axisLine:{
                                show: false
                            },
                            axisTick:{
                                show: false
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    color: ['#fff'],
                                    opacity:0.06
                                }
                            }

                        }
                    ],
                    series : academyObject.series
                };
                this.competitionChart.setOption(option)
            },
            academy(data){

                let data1 = data.map(x=>{
                    return {
                        'name':x.type,
                        'percent':x.percentage,
                        'value':x.count
                    }
                })
                var color = ["#FFC2C2", "#FF8C8C", "#FFD4AB", "#FFB46E", "#FFEE99", "#FFE048", ];
                var max = data1[0].value;
                data1.forEach(function(d) {
                    max = d.value > max ? d.value : max;
                });

                var renderData = [{
                    value: [],
                    name: [],
                    symbol: 'none',
                    lineStyle: {
                        normal: {
                            color: '#ecc03e',
                            width: 2
                        }
                    },
                    label: {                    // 单个拐点文本的样式设置
                        normal: {
                            show: true,             // 单个拐点文本的样式设置。[ default: false ]
                            position: 'top',        // 标签的位置。[ default: top ]
                            distance: 2,            // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。[ default: 5 ]
                            color: '#fff',          // 文字的颜色。如果设置为 'auto'，则为视觉映射得到的颜色，如系列色。[ default: "#fff" ]
                            fontSize: 14,           // 文字的字体大小
                            // formatter:function(params) {
                            //     return params.value;
                            // }
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: new this.$echarts.graphic.LinearGradient(0, 0, 1, 0,
                                [{
                                    offset: 0,
                                    color: 'rgba(203, 158, 24, 0.8)'
                                }, {
                                    offset: 1,
                                    color: 'rgba(190, 96, 20, 0.8)'
                                }],
                                false)
                        }
                    }
                }];

                data1.forEach(function(d, i) {
                    var value = data1.map(() => '');
                    value[i] = d.value;
                    renderData[0].value[i] = d.value;
                    renderData[0].name[i] = d.name;
                    renderData[0].areaStyle.normal.color = 'transparent';
                    renderData.push({
                        value: value,
                        symbol: 'circle',
                        symbolSize: d.percent * 80,
                        lineStyle: {
                            normal: {
                                color: 'transparent'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: color[i],
                            }
                        },
                        label: {                    // 单个拐点文本的样式设置
                            normal: {
                                show: true,             // 单个拐点文本的样式设置。[ default: false ]
                                position: 'top',        // 标签的位置。[ default: top ]
                                distance: 2,            // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。[ default: 5 ]
                                color: '#fff',          // 文字的颜色。如果设置为 'auto'，则为视觉映射得到的颜色，如系列色。[ default: "#fff" ]
                                fontSize: 14,           // 文字的字体大小
                                formatter:function(params) {
                                    if(params.value != '')
                                        return params.value+"人";
                                    else
                                        return params.value;
                                }
                            }
                        },
                    })
                })
                var indicator = [];
                data1.forEach(function(d) {
                    indicator.push({
                        name: d.name,
                        max: max + 10,
                        color: '#fff'
                    })
                })

                let contentArr = data1.map((d) => `${d.name}: ${d.value}人, 占比：${(d.percent*100).toFixed(2)}%`);
                contentArr.unshift('各赛事维度对比');
                let option = {
                    title : {
                        text: '各大赛事参与人数对比',
                        // left: '1%',
                        top: '1%',
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    backgroundColor: '#01193d',
                    tooltip: {
                        show: true,
                        trigger: "item",
                        formatter: contentArr.join('<br />')
                    },
                    radar: {
                        center: ["50%", "55%"],
                        radius: "70%",
                        startAngle: 90, // 起始角度
                        splitNumber: 4,
                        shape: "circle",
                        splitArea: {
                            areaStyle: {
                                color: 'transparent'
                            }
                        },
                        axisLabel: {
                            show: false,
                            fontSize: 20,
                            color: "#000",
                            fontStyle: "normal",
                            fontWeight: "normal"
                        },
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: "rgba(255, 255, 255, 0.5)"
                            }
                        },
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: "rgba(255, 255, 255, 0.5)"
                            }
                        },
                        indicator: indicator
                    },
                    series: [{
                        type: "radar",
                        data: renderData
                    }]
                }
                this.academyChart.setOption(option)
            },
            level(data){
                let colors = {
                    lightColor:['rgba(252,211,3, 0)','rgb(155,252,68,0)','rgb(52,252,245,0)',
                        'rgb(252,96,113,0)','rgb(156,114,252,0)',],
                    mediumColor:['rgba(252,211,3, 0.3)','rgba(155,252,68,0.3)','rgba(52,252,245,0.3)',
                        'rgba(252,96,113,0.3)','rgba(156,114,252,0.3)',],
                    darkColor:['rgba(252,211,3, 1)','rgba(155,252,68,1)','rgba(52,252,245,1)',
                        'rgba(252,96,113,1)','rgba(156,114,252,1)',]
                }
                let groupedData = {}
                data.forEach(x=>{
                    groupedData[x.cLevel] = groupedData[x.cLevel] || [];
                    groupedData[x.cLevel].push(x)
                })
                let levelObject = {
                    'indicatorData':[],
                    'series':[],
                    'legendData':[]
                }
                let index = 0;
                let seriesData = []
                for(let key in groupedData){
                    seriesData = []
                    let i;
                    levelObject.legendData.push(key)
                    groupedData[key].forEach(x=>{
                        // 如果level不存在
                        if ((i = levelObject.indicatorData.findIndex((value)=>{
                            return value.text === x.type
                        }))<0) {
                            // 添加type
                            levelObject.indicatorData.push({
                                text:x.type
                            });
                            // 添加type对应的count
                            seriesData.push(x.count)

                        } else {
                            // 如果level存在，更新count的值
                            seriesData[i] = x.count
                        }
                    })

                    levelObject.series.push({
                        name: key,
                        value:seriesData,
                        symbolSize: 2.5,
                        itemStyle: {
                            normal: {
                                borderColor: colors.darkColor[index],
                                borderWidth: 2.5,
                            },

                        },
                        areaStyle: {
                            normal: { // 单项区域填充样式
                                color: {
                                    type: 'linear',
                                    x: 0, //右
                                    y: 0, //下
                                    x2: 1, //左
                                    y2: 1, //上
                                    colorStops: [{
                                        offset: 0,
                                        color: colors.mediumColor[index]
                                    }, {
                                        offset: 0.5,
                                        color: colors.lightColor[index]
                                    }, {
                                        offset: 1,
                                        color: colors.mediumColor[index]
                                    }],
                                    globalCoord: false
                                },
                                opacity: 1 // 区域透明度
                            },
                        },
                        label: {                    // 单个拐点文本的样式设置
                            normal: {
                                show: false,             // 单个拐点文本的样式设置。[ default: false ]
                                position: 'top',        // 标签的位置。[ default: top ]
                                distance: 2,            // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。[ default: 5 ]
                                color: colors.darkColor[index],          // 文字的颜色。如果设置为 'auto'，则为视觉映射得到的颜色，如系列色。[ default: "#fff" ]
                                fontSize: 14,           // 文字的字体大小
                                formatter:function(params) {
                                    return params.value;
                                }
                            }
                        }
                    })
                    index++;
                }
                let option = {
                    backgroundColor: '#000928',
                    color: colors.darkColor,
                    tooltip: {

                    },
                    title : {
                        text: '年级各维度对比',
                        // left: '1%',
                        top: '1%',
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    legend: {
                        show: true,
                        icon: "circle",
                        bottom: 10,
                        center: 0,
                        itemWidth: 14,
                        itemHeight: 14,
                        itemGap: 21,
                        orient: "horizontal",
                        data: levelObject.legendData,
                        textStyle: {
                            fontSize: '14',
                            color: '#fff'
                        },
                    },

                    radar: {
                        // shape: 'circle',
                        radius:'70%',
                        center: ['50%', '53%'],
                        // triggerEvent: true,
                        name: {
                            textStyle: {
                                color: '#fff',
                                fontSize:'12',
                                borderRadius: 3,
                                padding: [3, 5]
                            }
                        },
                        nameGap:'2',
                        indicator: levelObject.indicatorData,
                        splitArea: {
                            areaStyle: {
                                color: [
                                    'rgba(222,134,85, 0.1)', 'rgba(222,134,85, 0.2)',
                                    'rgba(222,134,85, 0.4)', 'rgba(222,134,85, 0.6)',
                                    'rgba(222,134,85, 0.8)', 'rgba(222,134,85, 1)'
                                ].reverse()
                            }
                        },
                        // axisLabel:{//展示刻度
                        //     show: true
                        // },
                        axisLine: { //指向外圈文本的分隔线样式
                            lineStyle: {
                                color: 'rgba(255,113,204,0.65)'
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                width:2,
                                color: [
                                    'rgba(224,134,82, 0.1)', 'rgba(224,134,82, 0.2)',
                                    'rgba(224,134,82, 0.4)', 'rgba(224,134,82, 0.6)',
                                    'rgba(224,134,82, 0.8)', 'rgba(224,134,82, 1)'
                                ].reverse()
                            }
                        },

                    },

                    series: {
                        name: 'key',
                        type: 'radar',
                        itemStyle: {
                            emphasis: {
                                lineStyle: {
                                    width: 4
                                }
                            }
                        },
                        data: levelObject.series
                    }
                }
                this.levelChart.setOption(option)

            },
            // ranking(){
            //     let ranking = this.$echarts.init(this.$refs.ranking)
            //     ranking.showLoading();
            //
            //
            //     var data1 = [{
            //         name: "危险弘佐",
            //         label: {
            //             normal: {
            //                 backgroundColor: '#725bb8'
            //             }
            //         },
            //         children: [{
            //             name: "hskdfa",
            //             children: [{
            //                 name: "娱乐场所治安管理工作"
            //             }]
            //         }, {
            //             name: "安保工作",
            //             children: [{
            //                 name: "输油气管道安保工作"
            //             }, {
            //                 name: "校园安保工作"
            //             }, {
            //                 name: "节假日、重大活动、大型群众性活动安保和敏感期社会面管控工作"
            //             }]
            //         }, {
            //             name: "精神病人管控工作",
            //             children: [{
            //                 name: "精神病人肇事肇祸案事件"
            //             }]
            //         }, {
            //             name: "物流寄递业",
            //             children: [{
            //                 name: "物流寄递业治安管理工作"
            //             }]
            //         }, {
            //             name: "推进基层基础工作创新",
            //             children: [{
            //                 name: "加强派出所建设"
            //             }]
            //         }, {
            //             name: "地网建设工作",
            //             children: [{
            //                 name: "加强二轮电动车防盗登记备案装置安装工作"
            //             }]
            //         }, {
            //             name: "无人机管理",
            //             children: [{
            //                 name: "加强无人机管理"
            //             }]
            //         }, {
            //             name: "加强社会面巡逻防控",
            //             children: [{
            //                 name: "武装联勤巡逻"
            //             }]
            //         }]
            //     }];
            //     var data2 = [{
            //         name: "治安管理工作",
            //         label: {
            //             normal: {
            //                 backgroundColor: '#725bb8'
            //             }
            //         },
            //         children: [{
            //             name: "娱乐场所治安管理",
            //             children: [{
            //                 name: "娱乐场所治安管理工作"
            //             }]
            //         }, {
            //             name: "安保工作",
            //             children: [{
            //                 name: "输油气管道安保工作"
            //             }, {
            //                 name: "校园安保工作"
            //             }, {
            //                 name: "节假日、重大活动、大型群众性活动安保和敏感期社会面管控工作"
            //             }]
            //         }, {
            //             name: "精神病人管控工作",
            //             children: [{
            //                 name: "精神病人肇事肇祸案事件"
            //             }]
            //         }, {
            //             name: "物流寄递业",
            //             children: [{
            //                 name: "物流寄递业治安管理工作"
            //             }]
            //         }, {
            //             name: "推进基层基础工作创新",
            //             children: [{
            //                 name: "加强派出所建设"
            //             }]
            //         }, {
            //             name: "地网建设工作",
            //             children: [{
            //                 name: "加强二轮电动车防盗登记备案装置安装工作"
            //             }]
            //         }, {
            //             name: "无人机管理",
            //             children: [{
            //                 name: "加强无人机管理"
            //             }]
            //         }, {
            //             name: "加强社会面巡逻防控",
            //             children: [{
            //                 name: "武装联勤巡逻"
            //             }]
            //         }]
            //     }];
            //
            //     ranking.hideLoading();
            //     let option = {
            //         backgroundColor: '#011746',
            //         tooltip: {
            //             trigger: 'item',
            //             formatter: '{b}'
            //         },
            //         legend: {
            //             top: '12%',
            //             left: '3%',
            //             bottom: '2%',
            //             orient: 'radial',
            //             selected:{
            //                 '治安管理工作':true,
            //                 '危爆物品管理工作':false
            //             },
            //             textStyle:{
            //                 color:'#fff'
            //             }
            //         },
            //         series: [{
            //             type: 'tree',
            //             name: '治安管理工作',
            //             data: data1,
            //             top: '20%',
            //             left: '1%',
            //             symbolSize: 1,
            //             initialTreeDepth: 5,
            //             label: {
            //                 normal: {
            //                     position: 'center',
            //                     verticalAlign: 'middle',
            //                     align: 'left',
            //                     backgroundColor: '#7049f0',
            //                     color: '#fff',
            //                     padding: 3,
            //                     formatter: [
            //                         '{box|{b}}'
            //                     ].join('\n'),
            //                     rich: {
            //                         box: {
            //                             height: 30,
            //                             color: '#fff',
            //                             padding: [0, 5],
            //                             align: 'center'
            //                         }
            //                     }
            //                 }
            //             },
            //             leaves: {
            //                 label: {
            //                     normal: {
            //                         position: 'center',
            //                         verticalAlign: 'middle',
            //                         align: 'left',
            //                         backgroundColor: '#c44eff',
            //                         formatter: [
            //                             '{box|{b}}'
            //                         ].join('\n'),
            //                         rich: {
            //                             box: {
            //                                 height: 18,
            //                                 color: '#fff',
            //                                 padding: [0, 5],
            //                                 align: 'center'
            //                             }
            //                         }
            //                     }
            //                 }
            //             },
            //             expandAndCollapse: true,
            //             animationDuration: 550,
            //             animationDurationUpdate: 750
            //         },
            //             {
            //                 type: 'tree',
            //                 name: '危爆物品管理工作',
            //                 data: data2,
            //                 top: '20%',
            //                 right: '1%',
            //                 symbolSize: 1,
            //                 initialTreeDepth: 10,
            //                 label: {
            //                     normal: {
            //                         position: 'center',
            //                         verticalAlign: 'middle',
            //                         align: 'left',
            //                         backgroundColor: '#7049f0',
            //                         color: '#fff',
            //                         padding: 3,
            //                         formatter: [
            //                             '{box|{b}}'
            //                         ].join('\n'),
            //                         rich: {
            //                             box: {
            //                                 height: 30,
            //                                 color: '#fff',
            //                                 padding: [0, 5],
            //                                 align: 'center'
            //                             }
            //                         }
            //                     }
            //                 },
            //                 leaves: {
            //                     label: {
            //                         normal: {
            //                             position: 'center',
            //                             verticalAlign: 'middle',
            //                             align: 'left',
            //                             backgroundColor: '#c44eff',
            //                             formatter: [
            //                                 '{box|{b}}'
            //                             ].join('\n'),
            //                             rich: {
            //                                 box: {
            //                                     height: 18,
            //                                     color: '#fff',
            //                                     padding: [0, 5],
            //                                     align: 'center'
            //                                 }
            //                             }
            //                         }
            //                     }
            //                 },
            //                 expandAndCollapse: true,
            //                 animationDuration: 550,
            //                 animationDurationUpdate: 750
            //             }
            //         ]
            //     }
            //
            //     ranking.setOption(option);
            //     ranking.on('legendselectchanged', function (params) {
            //         // 获取点击图例的选中状态
            //         var isSelected = params.selected[params.name];
            //         // 在控制台中打印
            //         console.log((isSelected ? '选中了' : '取消选中了') + '图例' + params.name);
            //         // 打印所有图例的状态
            //         console.log(params.selected);
            //     })
            // }



        },
        mounted(){
            this.competitionChart = this.$echarts.init(this.$refs.competition)
            this.levelChart = this.$echarts.init(this.$refs.level)
            this.academyChart = this.$echarts.init(this.$refs.academy)

            window.onresize = () => {
                //  根据窗口大小调整曲线大小
                this.competitionChart.resize()
                this.levelChart.resize()
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