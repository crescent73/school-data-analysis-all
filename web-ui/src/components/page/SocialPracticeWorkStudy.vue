<template>
    <div class="height_all">
        <el-row class="border height_half">
            <el-col :span="12" class="border height_all">
                <div ref="project" class="full"></div>
            </el-col>
            <el-col :span="12" class="border height_all">
                <div ref="job" class="full"></div>
            </el-col>
        </el-row>
        <el-row class="border height_half">
            <el-col :span="12" class="border height_all">
                <div ref="social_practice_type" class="full"></div>
            </el-col>
            <el-col :span="12" class="border height_all">
                <div ref="participation" class="full"></div>
            </el-col>
        </el-row>

    </div>
</template>

<script>
    export default {
        name: "socialPractice_workStudy",
        methods:{
            sendRequest(){
                this.$api.socialPractice.typeAnalysis().then(res=>{
                    if (res.data != null) {
                        this.socialPracticeType(res.data)
                    }
                })
                this.$api.workStudy.participationAnalysis().then(res=>{
                    if (res.data != null) {
                        this.workStudyParticipation(res.data)
                    }
                })
                this.$api.workStudy.jobAnalysis().then(res=>{
                    if (res.data != null) {
                        this.job(res.data)
                    }
                })
                this.$api.socialPractice.projectAnalysis().then(res=>{
                    if (res.data != null) {
                        this.project(res.data)
                    }
                })
            },
            socialPracticeType(data) {
                let groupedData = {}
                let typeObject = {
                    'xlabel':[],
                    'person':[],
                    'group':[],
                    'legendName':['个体','团体']
                }
                data.forEach(index=>{
                    groupedData[index.socialPracticeLevel] = groupedData[index.socialPracticeLevel] || [];
                    groupedData[index.socialPracticeLevel].push(index)
                });
                let index = 0;
                for(let key in groupedData) {
                    typeObject.xlabel.push(key)
                    groupedData[key].forEach(x=>{
                        if(x.awardType == "个体")
                            typeObject.person[index] = x.percentage
                        else
                            typeObject.group[index] = x.percentage
                    })
                    index++;
                }
                // 确实数据补0
                for (let m=0;m<typeObject.xlabel.length;m++){
                    if (typeObject.person[m] == null)
                        typeObject.person[m] = 0
                    if (typeObject.group[m] == null)
                        typeObject.group[m] = 0
                }

                let lineData = [1, 1, 1, 1]

                let option = {
                    baseOption: {
                        title: {
                            text: '社会实践个人与团体项目人数比',
                            // left: 'center',
                            left:'1%',
                            top:'1%',
                            textStyle: {
                                color: '#fff',
                            }
                        },
                        backgroundColor: "#0e2147",
                        timeline: {
                            show: false,
                            top: 0,
                            data: []
                        },
                        tooltip:{},
                        legend : {
                            top : '2%',
                            right : '5%',
                            itemWidth : 10,
                            itemHeight : 10,
                            itemGap: 20,
                            icon : 'horizontal',
                            textStyle : {
                                color : '#ffffff',
                                fontSize : 13,
                            },
                            data: typeObject.legendName
                        },
                        grid: [{
                            show: false,
                            left: '5%',
                            top: '10%',
                            bottom: '8%',
                            containLabel: true,
                            width: '37%'
                        }, {
                            show: false,
                            left: '51%',
                            top: '10%',
                            bottom: '8%',
                            width: '0%'
                        }, {
                            show: false,
                            right: '5%',
                            top: '10%',
                            bottom: '8%',
                            containLabel: true,
                            width: '37%'
                        }],
                        xAxis: [{
                            type: 'value',
                            inverse: true,
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            position: 'top',
                            axisLabel: {
                                show: false
                            },
                            splitLine: {
                                show: false
                            }
                        }, {
                            gridIndex: 1,
                            show: false
                        }, {
                            gridIndex: 2,
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            position: 'top',
                            axisLabel: {
                                show: false
                            },
                            splitLine: {
                                show: false
                            }
                        }],
                        yAxis: [{
                            type: 'category',
                            inverse: true,
                            position: 'right',
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                show: false
                            },
                            data: typeObject.xlabel
                        }, {
                            gridIndex: 1,
                            type: 'category',
                            inverse: true,
                            position: 'left',
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#ffffff',
                                    fontSize: 14
                                }

                            },
                            data: typeObject.xlabel.map(function(value) {
                                return {
                                    value: value,
                                    textStyle: {
                                        align: 'center'
                                    }
                                }
                            })
                        }, {
                            gridIndex: 2,
                            type: 'category',
                            inverse: true,
                            position: 'left',
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                show: false

                            },
                            data: typeObject.xlabel
                        }],
                        series: []

                    },
                    options: []
                };

                option.options.push({
                    series: [
                        {
                            type: 'pictorialBar',
                            xAxisIndex: 0,
                            yAxisIndex: 0,
                            symbol: 'rect',
                            itemStyle: {
                                normal: {
                                    color: 'rgba(3,147,114,0.27)'
                                }
                            },
                            barWidth: 10,
                            symbolRepeat: true,
                            symbolSize: 14,
                            data: lineData,
                            barGap: '-100%',
                            barCategoryGap: 0,
                            label: {
                                normal: {
                                    show: true,
                                    formatter: (series) => {
                                        // console.log("series",series)
                                        return typeObject.person[series.dataIndex]*100 + '%'
                                    },
                                    position: 'insideTopLeft',
                                    textStyle:{
                                        color: '#ffffff',
                                        fontSize: 13,
                                    },
                                    offset: [0, -25],
                                }
                            },
                            z: -100,
                            animationEasing: 'elasticOut',
                            animationDelay: function (dataIndex, params) {
                                return params.index * 30;
                            }
                        }, {
                            name: typeObject.legendName[0],
                            type: 'pictorialBar',
                            xAxisIndex: 0,
                            yAxisIndex: 0,
                            symbol: 'rect',
                            barWidth: 10,
                            itemStyle: {
                                normal: {
                                    barBorderRadius: 5,
                                    color: '#1aff73'
                                }
                            },
                            symbolRepeat: true,
                            symbolSize: 14,
                            data: typeObject.person,
                            animationEasing: 'elasticOut',
                            animationDelay: function (dataIndex, params) {
                                return params.index * 30 * 1.1;
                            }
                        },
                        {
                            type: 'pictorialBar',
                            xAxisIndex: 2,
                            yAxisIndex: 2,
                            symbol: 'rect',
                            itemStyle: {
                                normal: {
                                    color: 'rgba(54,215,182,0.27)'
                                }
                            },
                            barWidth: 10,
                            symbolRepeat: true,
                            symbolSize: 14,
                            data: lineData,
                            barGap: '-100%',
                            barCategoryGap: 0,
                            label: {
                                normal: {
                                    show: true,
                                    formatter: (series) => {
                                        return typeObject.group[series.dataIndex]*100 + '%'
                                    },
                                    position: 'insideTopRight',
                                    textStyle:{
                                        color: '#ffffff',
                                        fontSize: 13,
                                    },
                                    offset: [0, -25],
                                }
                            },
                            z: -100,
                            animationEasing: 'elasticOut',
                            animationDelay: function (dataIndex, params) {
                                return params.index * 30;
                            }
                        }, {
                            name: typeObject.legendName[1],
                            type: 'pictorialBar',
                            xAxisIndex: 2,
                            yAxisIndex: 2,
                            symbol: 'rect',
                            barWidth: 10,
                            itemStyle: {
                                normal: {
                                    barBorderRadius: 5,
                                    color: '#36d7b6'
                                }
                            },
                            symbolRepeat: true,
                            symbolSize: 14,
                            data: typeObject.group,
                            animationEasing: 'elasticOut',
                            animationDelay: function (dataIndex, params) {
                                return params.index * 30 * 1.1;
                            }
                        }
                    ]
                });

                this.typeChart.setOption(option)
            },
            workStudyParticipation(data){
                let participationObject = {
                    data:[],
                    yAxis:[],
                    legendData:[]
                }
                data.forEach((x,i)=>{
                    participationObject.legendData.push(x.grade)
                    // 画第一个饼图
                    participationObject.data.push({
                        name: '',
                        type: 'pie',
                        clockWise: false, //顺时加载
                        hoverAnimation: false, //鼠标移入变大
                        radius: [73 - i * 15 + '%', 68 - i * 15 + '%'],
                        center: ["30%", "55%"],
                        label: {
                            show: false
                        },
                        itemStyle: {
                            label: {
                                show: false,
                            },
                            labelLine: {
                                show: false
                            },
                            borderWidth: 5,
                        },
                        data: [{
                            value: x.count, // 参与人数
                            name: x.grade    // 年级名称
                        }, {
                            value: x.countAll-x.count, // 剩下没有参与的人数
                            name: x.grade, // 年级名称
                            itemStyle: {
                                color: "rgba(0,0,0,0)",
                                borderWidth: 0
                            },
                            tooltip: {
                                show: false
                            },
                            hoverAnimation: false
                        }]
                    })
                    // 画第二个饼图
                    participationObject.data.push({
                        name: '',
                        type: 'pie',
                        silent: true,
                        z: 1,
                        clockWise: false, //顺时加载
                        hoverAnimation: false, //鼠标移入变大
                        radius: [73 - i * 15 + '%', 68 - i * 15 + '%'],
                        center: ["30%", "55%"],
                        label: {
                            show: false
                        },
                        itemStyle: {
                            label: {
                                show: false,
                            },
                            labelLine: {
                                show: false
                            },
                            borderWidth: 5,
                        },
                        data: [{
                            value: 7.5,
                            itemStyle: {
                                color: "rgb(3, 31, 62)",
                                borderWidth: 0
                            },
                            tooltip: {
                                show: false
                            },
                            hoverAnimation: false
                        }, {
                            value: 2.5,
                            name: '',
                            itemStyle: {
                                color: "rgba(0,0,0,0)",
                                borderWidth: 0
                            },
                            tooltip: {
                                show: false
                            },
                            hoverAnimation: false
                        }]
                    })
                    // 添加yAxis
                    participationObject.yAxis.push((x.percentage*100).toFixed(2)+'%')
                })
                let option = {
                    title: {
                        text: '勤工助学的年级参与率',
                        // left: 'center',
                        left:'1%',
                        top:'1%',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    backgroundColor:'#0a0e22',
                    legend: {
                        show: true,
                        icon:"circle",
                        top: "center",
                        left: '60%',
                        data: participationObject.legendData,
                        width:50,
                        padding: [0, 5],
                        itemGap: 25,
                        formatter: (name) =>{ // legend，formatter
                            return "{title|" + name + "级}  {value|" +getValue(data,name) +"}  {title|人}"
                        },

                        textStyle: {
                            rich: {
                                title: {
                                    fontSize: 19,
                                    lineHeight: 15,
                                    color: "rgb(46,138,255)"
                                },
                                value: {
                                    fontSize: 20,
                                    lineHeight: 25,
                                    color: "#fff"
                                }
                            }
                        },
                    },
                    tooltip: {
                        show: true,
                        trigger: "item",
                        formatter: "{a}<br>{b}:{c}({d}%)"
                    },
                    color: ['rgb(24,213,170)', 'rgb(1,193,255)', 'rgb(248,175,85)', 'rgb(249,68,13)'],
                    grid: {
                        top: '16%',
                        bottom: '53%',
                        left: "30%",
                        containLabel: false
                    },
                    yAxis: [{
                        type: 'category',
                        inverse: true,
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            interval: 0,
                            inside: true,
                            textStyle: {
                                color: "#fff",
                                fontSize: 16,
                            },
                            show: true
                        },
                        data: participationObject.yAxis
                    }],
                    xAxis: [{
                        show: false
                    }],
                    series: participationObject.data
                };
                this.participationChart.setOption(option)

                function getValue(data,name){
                    let result;
                    data.forEach(x=>{
                        if (name == x.grade)
                            result = x.count
                    })
                    return result
                }

            },
            job(data){
                let jobObject = {
                    'jobName':[],
                    'count':[]
                }
                data.sort((a,b)=>b.count-a.count).slice(0,5).sort((a,b)=>a.count-b.count).map(x=>{
                    jobObject.jobName.push(x.jobName.split("-")[0])
                    jobObject.count.push(x.count)
                })
                var myColor = ['#81E7ED'];
                var dataLine = [10, 20, 30, 40, 50];
                var positionLeft = 25;
                // max = 100 + positionLeft;
                var g_cellBar0_y = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAoCAYAAAAhf6DEAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAA6SURBVEhLY2x8/vY/A4mg3zwcTDOBSTLBqGYSwahmEsGoZhLBqGYSwahmEsGoZhLBqGYSwZDUzMAAAJldBMF2UASmAAAAAElFTkSuQmCC';
                var g_cellBarImg0_y = new Image();
                g_cellBarImg0_y.src = g_cellBar0_y;

                var option = {
                    backgroundColor: '#101E44',
                    title: {
                        text: '勤工助学热门岗位TOP5',
                        // left: 'center',
                        left:'1%',
                        top:'1%',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    grid: [{
                        left: '8%',
                        top: '17%',
                        right: '5%',
                        bottom: '5%',
                        containLabel: true
                    },
                        {
                            left: '10%',
                            top: '17%',
                            right: '5%',
                            bottom: '5%',
                            containLabel: true
                        }
                    ],
                    xAxis: [{
                        //   max:max,
                        show: false
                    }],
                    yAxis: [{
                        axisTick: 'none',
                        axisLine: 'none',
                        axisLabel: {
                            inside: true,
                            align: 'left',
                            textStyle: {
                                color: '#81E7ED',
                                fontSize: '12' // 左边名称
                            }
                        },
                        z: 10,
                        data: jobObject.jobName
                    }, {
                        axisTick: 'none',
                        axisLine: 'none',
                        show: true,
                        axisLabel: {
                            inside: true,
                            align: 'right',
                            textStyle: {
                                color: '#ffffff',
                                fontSize: '10'// 右边数字
                            },
                            formatter:(value)=>{
                                return value+'人'
                            }
                        },
                        z: 10,
                        data: jobObject.count,

                    }, {

                        axisLine: {
                            lineStyle: {
                                color: 'rgba(0,0,0,0)'
                            }
                        },
                        data: []
                    }],
                    series: [

                        { //间距
                            type: 'bar',
                            barWidth: 15,
                            stack: 'b',
                            legendHoverLink: false,
                            itemStyle: {
                                normal: {
                                    color: 'rgba(0,0,0,0)'
                                }
                            },
                            z: 3,
                            data: [positionLeft, positionLeft, positionLeft, positionLeft, positionLeft]
                        }, {
                            name: '条',
                            type: 'bar',
                            stack: 'b',
                            yAxisIndex: 0,
                            data: dataLine,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'right',
                                    distance: 20,
                                    formatter: function(param) {
                                        return param.value + '%'
                                    },
                                    textStyle: {
                                        color: '#ffffff',
                                        fontSize: '16'
                                    }
                                }
                            },
                            barWidth: 15,
                            itemStyle: {
                                color: {
                                    image: g_cellBarImg0_y,
                                    repeat: 'repeat'
                                }
                                /*normal: {
                                    color: function(params) {
                                        var num = myColor.length
                                        return myColor[params.dataIndex % num]
                                    }
                                }*/
                            },
                            z: 2
                        }, {
                            name: '白框',
                            type: 'bar',
                            yAxisIndex: 1,
                            barGap: '-100%',
                            data: [99.8, 99.9, 99.9, 99.9, 99.9],
                            barWidth: 30,
                            itemStyle: {
                                normal: {
                                    color: '#0e2147',
                                    barBorderRadius: 2
                                }
                            },
                            z: 1
                        },
                        {
                            name: '外框',
                            type: 'bar',
                            yAxisIndex: 2,
                            barGap: '-100%',
                            data: [100, 100, 100, 100, 100],
                            barWidth: 31,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'right',
                                    distance: 10,
                                    formatter: function(data) {
                                        return dataLine[data.dataIndex] + "%";
                                    },
                                    textStyle: {
                                        color: '#ffffff',
                                        fontSize: '16'
                                    }
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: function(params) {
                                        var num = myColor.length
                                        return myColor[params.dataIndex % num]
                                    },
                                    barBorderRadius: [0, 0, 0, 0]
                                }
                            },
                            z: 0
                        },
                    ]
                }
                this.jobChart.setOption(option)
            },
            project(data){
                const colorList = ['#f54d4d','#ffae00','#fffa20',
                    '#afe435','#2ed074',
                    '#3cd3ff','#4245ff',
                    '#c32eff']

                let projectObject = []
                let awardName= []
                let index = 1;
                data.sort((a,b)=>a.count-b.count).forEach((x)=>{
                    if(awardName.indexOf(x.awardName )<0 ) {
                        awardName.push(x.awardName)
                        projectObject.push({
                            name:x.awardName,
                            value:index++,
                            count:x.count,
                            level:x.socialPracticeLevel
                        })
                    }
                })
                let option = {
                    title: {
                        text: '社会实践获奖奖项名称及人数',
                        // left: 'center',
                        left:'1%',
                        top:'1%',
                        textStyle: {
                            color: '#fff',
                        }
                    },
                    backgroundColor:'#0e1430',
                    calculable: true,
                    color:colorList,
                    series: [
                        {
                            name:'漏斗图',
                            type:'funnel',
                            left: '5%',
                            right:'55%',
                            top: '12%',
                            bottom: '10%',
                            minSize: '0%',
                            maxSize: '100%',
                            sort: 'ascending',
                            label: {
                                show: true,//"{a|{c}}\n{b|{b}}"
                                formatter: data=>{
                                    let name = data.data.name
                                    if (name.length>23)
                                        name = name.substring(0,23) + '...'
                                    return name+" - "+data.data.level+" - "+data.data.count+"人"
                                },
                                rich: {
                                    a: {
                                        color: 'rgba(222,240,255,1)',
                                        fontSize: 30
                                    },
                                    b: {
                                        color: 'rgba(101,166,196,1)',
                                        fontSize: 15
                                    }
                                }
                            },
                            labelLine: {
                                length: 10,
                                lineStyle: {
                                    width: 1,
                                    type: 'solid'
                                }
                            },
                            data: projectObject
                        }
                    ]
                };
                this.projectChart.setOption(option)
            }
        },
        mounted(){
            this.projectChart = this.$echarts.init(this.$refs.project);
            this.jobChart = this.$echarts.init(this.$refs.job);
            this.participationChart = this.$echarts.init(this.$refs.participation);
            this.typeChart = this.$echarts.init(this.$refs.social_practice_type);
            window.onresize = () => {
                //  根据窗口大小调整曲线大小
                this.projectChart.resize()
                this.jobChart.resize()
                this.participationChart.resize()
                this.typeChart.resize()
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