<template>
    <div class="height_all">
        <el-row class="border height_all">
            <el-col :span="18" class="border height_all">
                <el-row class="border height_half">
                    <div ref="certificate_academy" class="full"></div>

                </el-row>
                <el-row class="border height_half">
                    <el-col :span="12" class="border height_all">
                        <div ref="academic_academy" class="full"></div>
                    </el-col>
                    <el-col :span="12" class="border height_all">
                        <div ref="authoring_type" class="full"></div>
                    </el-col>
                </el-row>
            </el-col>
            <el-col :span="6" class="border height_all">
                <div ref="certificate_type" class="full"></div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "academic_certificate",
        methods:{
            sendRequest(){
                // 请求数据
                this.$api.certificate.academyAnalysis().then(res=>{
                    if (res.data!=null){
                        this.certificateAcademy(res.data)
                    }
                })
                this.$api.academic.academyAnalysis().then(res=>{
                    if (res.data!=null){
                        this.academy(res.data)
                    }
                })
                this.$api.academic.authoringTypeAnalysis().then(res=>{
                    if (res.data!=null){
                        this.authoringType(res.data)
                    }
                })
                this.$api.certificate.typeAnalysis().then(res=>{
                    if (res.data!=null){
                        this.certificateType(res.data)
                    }
                })
            },
            certificateAcademy(data){
                const img = [
                    'image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABRCAYAAABFTSEIAAAACXBIWXMAAAsSAAALEgHS3X78AAAEp0lEQVR42u3cz4sjRRTA8W9Vd3Vn8mMmjj9WQWSRZQ+CsH+B7MnDIgiCd0E8CYJ/gOAIelo8ehUP/gF6WLw5/gMueFP2sIcF0dHd2Z1kknR11fOQZJJJMtlZd03H7HtQpNOTnpn+8Lrm1etmjIig8e/DKoECKqACKqCGAiqgAiqghgIqoAIqoIYCKqACKqCGAiqgAiqghgIqoAJudKTr+osZMNPvBUQBHwHsPF9fB9R0DeHMOQ6T6WOrhEzXBM4swDOL0M6CrArRVoq3t2dGUIb9fTvatg8ZZup1PDBgzPmy98mey6qfzjLz2WaWjEUZKEvGyi9nWyneMOvGIyFQo2Sbg4MUSChpU9IeTTUpJdsEajPZOJeJG5uBZj7rLLduWS5dGm6XNLEELOFUFj54ACJCaychkpDSASK3bwsXL0YgVpWJKwM0iy9Zy8HdGru7jvt3Pbu7w0wES7drTwAbjTHMGCsQcIAnYTC1/wRx0wEnl27JNgZI8HQ6Kc1mQq83RNzaMjPzXqDbjTQaJRFLxIyyMSxAXEkWrhrQzAAmo5HOjCQf7jflILxOkohL+aUPgV4vEGNJo+E5PAy02+UIMEwBxo0CPDP7Dg5SnEtpt1PA0e87XO25FOoh8IYIH2Y5b45RzGAQBiIltZoHxqMcjbksXAVgdc2EQMYzzzdotyeZWKuleULXJtwT4SODfC2QCWR+IF9KnjuX1Xbo99Op7LVE8iXlz0YBTk5SyLEEjo5OLuccEoFUvHfO+reuUPx4zftXAIcx1hdcF+/TvFab4A0Bs0VwqyhpVnkJT89/Q4DDQ0e77YCMwIUsFMeFZD856699URRvX4nxE4A/jbnxXp7v4Zw3ReGNSDHI8wFQjIafuoyn58L/fB6sth/Ybg9fez2TRC6QZcZYvgHsazF+MP7YCyLXcM7gvSXLDGBqYDg+NhwdmSpPoTrAkub0W+f4FSB1fDucIunMHSLpO8WAH0rSy8u+19MBCHB4OHzd2pI+CEUhpigEiN+l6WcdY252jLn5s7Wf472ImPcN8pUl/tEHoV4XWq1Ke4KrLmPsTA3oODpytFoOyJKSyzHyMSIxteWngMW5cSEdDJQUhTdZVgxOz3/+jFJm4+bA2e5JpNU6WZ4Fw99JwnWMKccwpeddP+B7GZTNUPKqybJy0O+Hs1YfMz9swwvpB8fbGDG0GuGkkK7V0hxSmZQpABI8l2z0v3sJf50qpAMJCd2qCulql3LD1lRGQjm7lEsDz0rkxTQOfiPPxUBcuJTbbhss/Y1eyi3NwsmKInmkZsKk5gtPUzNhvp11507CSy/X6XYStpvFudpZw1ZWIOF4Cq6SdtbKbioJyAhRTu3u9yMJXerN+ugvaQQsjcZ8Q3VnZwxlSDhe1lB9GjrSw5b+1avT8+Jw+979nNaOI6U3KpTrWAosxVQmygK4ld8X0ZtK/7eViExD7O1NQPb3T7fsl4/4sBpwYzPwjFbTo95Yl9l9Vd1YN1X/147HebSjary1AHyc5qc+XLQEQx9ve8Kg6xr6hKoCKqACKqCGAiqgAiqghgIqoAIqoIYCKqACKqCGAiqgAiqghgIq4JrHP8fEWV8FMTmOAAAAAElFTkSuQmCC',
                    'image://data:image/png;base64,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',
                    'image://data:image/png;base64,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',
                    'image://data:image/png;base64,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',
                ];
                const color =['#45dbf7','#00f15a','#ad46f3', '#dcf776'];
                let certificateAcademyObject = {
                    'XName': [], // 学院名称
                    'data': [], // 每个年级每个学院数据
                    'line': [] // 年级名称
                };
                let groupedData = {}
                data.forEach(index=>{
                    groupedData[index.grade] = groupedData[index.grade] || [];
                    groupedData[index.grade].push(index)
                });
                let index = 0;
                for(let key in groupedData) {
                    // 年级
                    certificateAcademyObject.line[index] = key;
                    certificateAcademyObject.data[index] = []
                    groupedData[key].forEach(item =>{
                        let i;
                        if((i = certificateAcademyObject.XName.indexOf(item.academy)) < 0) {
                            // 如果还没有记录学院信息
                            // 添加这个学院数据
                            // 在数据表中添加学院的数据
                            certificateAcademyObject.data[index][certificateAcademyObject.XName.length] = item.count
                            certificateAcademyObject.XName[certificateAcademyObject.XName.length] = item.academy;

                        } else {
                            // 如果已经包含了这个学院的信息
                            // 直接在数据表添加学院的数据
                            certificateAcademyObject.data[index][i] = item.count != null ? item.count:0;
                        }
                    });
                    // 循环检查data中是否有值，如果没有值补0
                    for(let m=0; m<certificateAcademyObject.data[index].length;m++) {
                        if (certificateAcademyObject.data[index][m] == null)
                            certificateAcademyObject.data[index][m]  = 0;
                    }
                    index++;
                }
                let datas = [];
                certificateAcademyObject.line.map((item,index)=>{
                    datas.push(
                        {
                            symbolSize: 100,
                            symbol: img[index%4],
                            name: item,
                            type: "line",
                            yAxisIndex: 1,
                            data: certificateAcademyObject.data[index] ,
                            itemStyle: {
                                normal: {
                                    borderWidth: 10,
                                    color: color[index]
                                }
                            }
                        }
                    )
                })

                let option = {
                    title: {
                        text: '各学院年级获取技能证书人数',
                        left: 'center',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    backgroundColor: '#0e2147',
                    grid: {
                        left: '8%',
                        top: '15%',
                        bottom: '15%',
                        right: '5%',
                    },
                    legend: {
                        type: "scroll",
                        data:certificateAcademyObject.line,
                        right: '5%',
                        itemWidth:38,
                        itemHeight:32,
                        textStyle: {
                            color:"#fff",
                            fontSize:14,
                        },
                        itemStyle:{

                        }
                        // borderWidth:5,
                    },
                    tooltip:{},
                    yAxis: [
                        {
                            type: 'value',
                            position: 'right',
                            splitLine: {
                                show: false
                            }
                            ,
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                show: false
                            }
                        },
                        {
                            type: 'value',
                            position: 'left',
                            nameTextStyle: {
                                color: '#00FFFF'
                            },
                            splitLine: {
                                lineStyle: {
                                    type: 'dashed',
                                    color: 'rgba(135,140,147,0.8)'
                                }
                            },
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                formatter: '{value}',
                                color: '#fff',
                                fontSize: 14
                            }
                        },
                    ],
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {
                                show: false
                            },
                            axisLine: {
                                show: false,
                                lineStyle: {
                                    color: '#6A989E',
                                }
                            },
                            axisLabel: {
                                inside: false,
                                textStyle: {
                                    color: '#fff',// x轴颜色
                                    fontWeight: 'normal',
                                    fontSize: '10',
                                    lineHeight: 22
                                },
                                formatter: function (value) {
                                    return value.substr(0,3)+'...'
                                }

                            },
                            data: certificateAcademyObject.XName,
                        },
                    ],
                    series: datas,
                };
                this.certificateAcademyChart.setOption(option)

            },
            academy(data){
                let academyObject = {
                    'angleData':[],
                    'data':[]
                }
                data.forEach((x,i)=>{
                    academyObject.angleData[i] = x.academy
                    academyObject.data[i] = x.count
                })
                let datas=[];
                const colors = ['#f54d4d','#f87544','#ffae00','#fffa20',
                    '#dcff00','#16a605','#2ed074','#42f4f7',
                    '#36cfff','#37b5ff','#007cff','#4245ff',
                    '#c32eff','#f27aff','#ff62e8','#ff60a9']
                academyObject.data.map((item,index)=>{
                    datas.push({
                        value: item,
                        itemStyle: {
                            normal: {
                                color: colors[index]
                            }
                        }
                    })
                })
                let option = {
                    title: {
                        text: '各学院发表学术论文人数',
                        left: 'center',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    backgroundColor: '#0e2147',
                    tooltip:{},
                    angleAxis: {
                        interval: 1,
                        type: 'category',
                        data:academyObject.angleData,

                        z: 10,
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: "#00c7ff",
                                width: 1,
                                type: "solid"
                            },

                        },
                        axisLabel: {
                            interval: 0,
                            show: true,
                            color: "#fff",
                            margin: 8,
                            fontSize: 12,
                            formatter: function (value) {
                                return value.substr(0,3)+'...'
                            },
                        },
                    },
                    radiusAxis: {
                        min: 0,
                        max: 45,
                        interval: 9,
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: "#00c7ff",
                                width: 1,
                                type: "solid"
                            },
                        },
                        axisLabel: {
                            formatter: '{value}',
                            show: false,
                            padding: [0, 0, 20, 0],
                            color: "#fff",
                            fontSize: 14
                        },
                        splitLine: {
                            lineStyle: {
                                color: "#00c7ff",
                                width: 1,
                                type: "solid"
                            }
                        }
                    },
                    polar: {
                        // radius: [[90]]
                        center: ['50%','55%'],
                        radius: "70%"

                        // radius: [[190]]//半径大小
                    },
                    series: [{
                        type: 'bar',
                        data: datas,
                        coordinateSystem: 'polar',
                    }],
                };
                this.academicAcademyChart.setOption(option)
            },
            authoringType(data){
                let option = {
                    backgroundColor:'#101736',
                    title: {
                        text: '学术论文编著类型',
                        left: 'center',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    tooltip:{},
                    series: [
                        {
                            // name: data[0].authoringType,
                            type: 'pie',
                            radius: ['30%', '35%'],
                            center: ['25%', '30%'],
                            startAngle: 225,
                            color: [new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#FDFF5C'
                            }, {
                                offset: 1,
                                color: '#FFDB5C'
                            }]), "transparent"],
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            label: {
                                normal: {
                                    position: 'center'
                                }
                            },
                            data: [
                                {
                                    value: 75,
                                    name: data[0].authoringType,

                                    label: {
                                        normal: {
                                            // formatter: '遥感解译信息1',
                                            // center: ['25%', '25%'],
                                            textStyle: {
                                                color: '#fff',
                                                fontSize: 16

                                            }
                                        }
                                    }
                                }, {
                                    value: 25,
                                    name: '%',
                                    label: {
                                        normal: {
                                            formatter: '\n\n'+data[0].count,
                                            textStyle: {
                                                color: '#007ac6',
                                                fontSize: 30

                                            }
                                        }
                                    }
                                },
                                {
                                    value: 0,
                                    name: '%',
                                    label: {
                                        normal: {
                                            formatter: '',
                                            textStyle: {
                                                color: '#fff',
                                                fontSize: 16

                                            }
                                        }
                                    }
                                }]
                        },
                        {
                            // name: data[1].authoringType,
                            type: 'pie',
                            radius: ['30%', '35%'],
                            center: ['75%', '30%'],
                            startAngle: 225,
                            color: [new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#9FE6B8'
                            }, {
                                offset: 1,
                                color: '#32C5E9'
                            }]), "transparent"],
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            label: {
                                normal: {
                                    position: 'center'
                                }
                            },
                            data: [{
                                value: 75,
                                name:  data[1].authoringType,
                                label: {
                                    normal: {
                                        // formatter: '局部监1测信息',
                                        textStyle: {
                                            color: '#fff',
                                            fontSize: 16

                                        }
                                    }
                                }
                            }, {
                                value: 25,
                                name: '%',
                                label: {
                                    normal: {
                                        formatter: '\n\n'+ data[1].count,
                                        textStyle: {
                                            color: '#007ac6',
                                            fontSize: 30

                                        }
                                    }
                                }
                            },
                                {
                                    value: 0,
                                    name: '%',
                                    label: {
                                        normal: {
                                            formatter: '',
                                            textStyle: {
                                                color: '#fff',
                                                fontSize: 16

                                            }
                                        }
                                    }
                                }]
                        },
                        {
                            // name:  data[2].authoringType,
                            type: 'pie',
                            radius: ['30%', '35%'],
                            center: ['25%', '75%'],
                            startAngle: 225,
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            label: {
                                normal: {
                                    position: 'center'
                                }
                            },
                            data: [{
                                value: 75,
                                "itemStyle": {
                                    "normal": {
                                        "color": new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                            "offset": 0,
                                            "color": '#FF9F7F'
                                        }, {
                                            "offset": 1,
                                            "color": '#FB7293'
                                        }]),
                                    }
                                },
                                name:  data[2].authoringType,
                                label: {
                                    normal: {
                                        // formatter: '告警设备数量',
                                        textStyle: {
                                            color: '#fff',
                                            fontSize: 16

                                        }
                                    }
                                }
                            }, {
                                value: 25,
                                name: '%',
                                label: {
                                    normal: {
                                        formatter: '\n\n'+ data[2].count,
                                        textStyle: {
                                            color: '#fee85c',
                                            fontSize: 30

                                        }
                                    }
                                }
                            },
                                {
                                    value: 0,
                                    name: '%',
                                    label: {
                                        normal: {
                                            formatter: '',
                                            textStyle: {
                                                color: '#fff',
                                                fontSize: 16

                                            }
                                        }
                                    }
                                }]
                        },
                        {
                            // name:  data[3].authoringType,
                            type: 'pie',
                            radius: ['30%', '35%'],
                            center: ['75%', '75%'],
                            startAngle: 225,
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            label: {
                                normal: {
                                    position: 'center'
                                }
                            },
                            data: [{
                                value: 75,
                                "itemStyle": {
                                    "normal": {
                                        "color": new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                            "offset": 0,
                                            "color": '#93ffbc'
                                        }, {
                                            "offset": 1,
                                            "color": '#2ed074'
                                        }]),
                                    }
                                },
                                name:  data[3].authoringType,
                                label: {
                                    normal: {
                                        // formatter: '告警设备数量',
                                        textStyle: {
                                            color: '#fff',
                                            fontSize: 16

                                        }
                                    }
                                }
                            }, {
                                value: 25,
                                name: '%',
                                label: {
                                    normal: {
                                        formatter: '\n\n'+ data[3].count,
                                        textStyle: {
                                            color: '#fee85c',
                                            fontSize: 30

                                        }
                                    }
                                }
                            },
                                {
                                    value: 0,
                                    name: '%',
                                    label: {
                                        normal: {
                                            formatter: '',
                                            textStyle: {
                                                color: '#fff',
                                                fontSize: 16

                                            }
                                        }
                                    }
                                }]
                        }
                    ]
                };
                this.authoringTypeChart.setOption(option)
            },
            certificateType(data){
                var color = ['rgba(255,168,0','rgba(26,206,74','rgba(75,243,255','rgb(46,138,255','rgba(178,80,255','rgba(248,195,248', 'rgb(255,134,100', 'rgb(255,241,136', 'rgb(185,248,93', 'rgb(26,255,115']
                let typeObject = {
                    typeList:[],
                    data:[]
                }
                data.forEach((x,i)=>{
                    typeObject.typeList[i] = x.awardName + "-" +x.skillLevel
                    typeObject.data[i] = x.count
                })
                let top10TypeList = typeObject.typeList
                let top10TypeData = typeObject.data
                let lineY = []
                for (var i = 0; i < top10TypeList.length; i++) {
                    var x = i
                    if (x > color.length - 1) {
                        x = color.length - 1
                    }
                    var data1 = {
                        name: top10TypeList[i],
                        color: color[x] + ')',
                        value: top10TypeData[i],
                        itemStyle: {
                            normal: {
                                show: true,
                                color: new this.$echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                    offset: 0,
                                    color: color[x] + ', 0.3)'
                                }, {
                                    offset: 1,
                                    color: color[x] + ', 1)'
                                }], false),
                                barBorderRadius: 10
                            },
                            emphasis: {
                                shadowBlur: 13,
                                shadowColor: 'rgba(0, 0, 0, 0.1)'
                            }
                        }
                    }
                    lineY.push(data1)
                }
                let option= {
                    title: {
                        text: '技能证书获得数TOP10',
                        left: 'center',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    backgroundColor:'#0e2147',
                    tooltip: {
                        trigger: 'item'
                    },
                    grid: {
                        borderWidth: 0,
                        top: '10%',
                        left: '7%',
                        right: '25%',
                        bottom: '3%'
                    },
                    color: color,
                    yAxis: [{
                        type: 'category',
                        inverse: true,
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: false
                        },
                        axisLabel: {
                            show: false,
                            inside: false
                        },
                        data: top10TypeList
                    }, {
                        type: 'category',
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            show: true,
                            inside: false,
                            textStyle: {
                                color: '#b3ccf8',
                                fontSize: '14',
                                fontFamily: 'PingFangSC-Regular'
                            },
                            formatter: function (val) {
                                return `${val}人`
                            }
                        },
                        splitArea: {
                            show: false
                        },
                        splitLine: {
                            show: false
                        },
                        data: top10TypeData.sort((a,b)=>a-b)
                    }],
                    xAxis: {
                        type: 'value',
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: false
                        },
                        splitLine: {
                            show: false
                        },
                        axisLabel: {
                            show: false
                        }
                    },
                    series: [{
                        name: '',
                        type: 'bar',
                        zlevel: 2,
                        barWidth: '15px',
                        data: lineY,
                        animationDuration: 1500,
                        label: {
                            normal: {
                                color: '#b3ccf8',
                                show: true,
                                position: [0, '-24px'],
                                textStyle: {
                                    fontSize: 13
                                },
                                formatter: function (a) {
                                    return a.name
                                }
                            }
                        }
                    }],
                    animationEasing: 'cubicOut'
                }
                this.certificateTypeChart.setOption(option)
            }

        },
        mounted(){
            this.certificateTypeChart = this.$echarts.init(this.$refs.certificate_type);
            this.authoringTypeChart = this.$echarts.init(this.$refs.authoring_type);
            this.academicAcademyChart = this.$echarts.init(this.$refs.academic_academy)
            this.certificateAcademyChart = this.$echarts.init(this.$refs.certificate_academy);
            window.onresize = () => {
                //  根据窗口大小调整曲线大小
                this.certificateTypeChart.resize()
                this.authoringTypeChart.resize()
                this.academicAcademyChart.resize()
                this.certificateAcademyChart.resize()

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