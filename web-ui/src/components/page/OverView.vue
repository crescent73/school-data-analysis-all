<template>
    <div class="height_all">
        <el-row class="border height_all">
            <el-col :span="6" class="border height_all">
                <el-row class="border height_half">
                    <div ref="level" class="full"></div>
                </el-row>
                <el-row class="border height_half">
                    <div ref="gender" class="full"></div>
                </el-row>
            </el-col>
            <el-col :span="12" class="border height_all">

                <div ref="birthplace" class="full"></div>
            </el-col>
            <el-col :span="6" class="border height_all">
                <div ref="nation" class="full"></div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import 'echarts/map/js/china'
    import 'echarts/map/json/china'
    export default {
        name: "overview",
        data() {
            return {

            }
        },
        methods:{
            sendRequest(){
                this.$api.main.birthplaceAnalysis().then(res=>{
                    if (res.data!=null){
                        this.birthplace(res.data)
                    }
                })
                this.$api.main.genderAnalysis().then(res=>{
                    if (res.data!=null){
                        this.gender(res.data)
                    }
                })
                this.$api.main.levelAnalysis().then(res=>{
                    if (res.data!=null){
                        this.level(res.data)
                    }
                })
                this.$api.main.nationAnalysis().then(res=>{
                    if (res.data!=null){
                        this.nation(res.data)
                    }
                })
            },
            birthplace(data){
                var geoCoordMap = {
                    "广东":[113.23333,23.16667],
                    '云南':[102.714601,24.882],
                    '内蒙古':[111.660351,40.828319],
                    '湖北':[114.3162,30.581084],
                    '海南':[109.330802,19.022071],
                    '陕西':[108.939,34.342],
                    '香港':[114.10000,22.20000],
                    '西藏':[91.111891,29.662557],
                    '天津':[117.20000,39.13333],
                    '广西':[108.297234,22.806493],
                    '贵州':[106.709177,26.629907],
                    '江苏':[118.778074,32.057236],
                    '河南':[113.649644,34.75661],
                    '宁夏':[106.206479,38.502621],
                    '福建':[119.330221,26.047125],
                    '黑龙江':[126.657717,45.773225],
                    '重庆':[106.55,29.5647],
                    '辽宁':[123.432791,41.808645],
                    '安徽':[117.282699,31.866942],
                    '澳门':[113.33,22.13],
                    '山东':[117.024967,36.682785],
                    '湖南':[112.979353,28.213478],
                    '青海':[101.767921,36.640739],
                    '台湾':[121.10,24.20],
                    '上海':[121.4788,31.2303],
                    '山西':[112.550864,37.890277],
                    '吉林':[125.3222,43.816],
                    '四川':[104.0648,30.57],
                    '浙江':[120.219375,30.259244],
                    '江西':[115.893528,28.689578],
                    '甘肃':[103.823305,36.064226],
                    '河北':[114.522082,38.048958],
                    '北京':[116.395645,39.929986],
                };
                var convertData = function (data) {
                    var res = [];
                    for (var i = 0; i < data.length; i++) {
                        var geoCoord = geoCoordMap[data[i].name];
                        if (geoCoord) {
                            res.push({
                                name: data[i].name,
                                value: geoCoord.concat(data[i].value)
                            });
                        }
                    }
                    return res;
                };
                let option = {
                    title : {
                        text: '学生地区分布',
                        left: 'center',
                        top: 'top',
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    tooltip: {},
                    legend: {
                        left: 'left',
                        data: ['强', '中', '弱'],
                        textStyle: {
                            color: '#ccc'
                        }
                    },
                    backgroundColor: {
                        type: 'linear',
                        x: 0,
                        y: 0,
                        // x2: 1,
                        // y2: 1,
                        colorStops: [{
                            offset: 0, color: '#0f2c70' // 0% 处的颜色
                        }, {
                            offset: 1, color: '#091732' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    geo: {
                        map: 'china',
                        show: true,
                        roam: false,
                        effect:{
                            show:true
                        },
                        label: {
                            emphasis: {
                                show: false
                            }
                        },
                        itemStyle: {
                            normal: {
                                areaColor: '#091632',
                                borderColor: '#1773c3',
                                shadowColor: '#1773c3',
                                shadowBlur: 20
                            }
                        }
                    },
                    series: [
                        {
                            name:'省份',
                            type: 'map',
                            map: 'china',
                            geoIndex: 1,
                            effect:{
                                show:true
                            },
                            aspectScale: 0.75, //长宽比
                            showLegendSymbol: true, // 存在legend时显示
                            label: {
                                normal: {
                                    formatter: '{b}',
                                    show: false,

                                },
                                emphasis: {
                                    show: false,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            roam: false, // 是否可以滚动放大
                            itemStyle: {
                                normal: {
                                    areaColor: '#031525',
                                    borderColor: '#3B5077',
                                    borderWidth: 1,
                                    color: 'transparent',
                                },
                                emphasis: {
                                    areaColor: '#0f2c70'
                                }
                            },
                            data:data,
                        },
                        {
                            name: '城市',
                            type: 'scatter',
                            coordinateSystem: 'geo',
                            data: convertData(data),
                            symbolSize: function (val) {
                                if(val[2]>3000)
                                    return val[2]/500;
                                else if(val[2]>300)
                                    return val[2]/100;
                                else if(val[2]>100)
                                    return val[2]/50;
                                else
                                    return val[2]/15;
                            },
                            roam: false,
                            label: {
                                normal: {
                                    formatter: '{b}',
                                    position: 'right',
                                    show: true
                                },
                                emphasis: {
                                    show: false
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: '#ddb926'
                                }
                            },
                            tooltip:{
                                show:false
                            }

                        },
                        {
                            name: '前5',
                            type: 'effectScatter',
                            coordinateSystem: 'geo',
                            roam: false,
                            data: convertData(data.sort(function (a, b) {
                                return b.value - a.value;
                            }).slice(0, 6)),
                            symbolSize: function (val) {
                                if(val[2]>3000)
                                    return val[2]/300;
                                else
                                    return val[2]/100;
                            },
                            showEffectOn: 'render',
                            rippleEffect: {
                                brushType: 'stroke'
                            },
                            hoverAnimation: true,
                            label: {
                                normal: {
                                    formatter: '{b}',
                                    position: 'right',
                                    show: false
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: '#f4e925',
                                    shadowBlur: 10,
                                    shadowColor: '#333'
                                }
                            },
                            zlevel: 1
                        }
                    ]
                }
                this.birthplaceChart.setOption(option)
            },
            gender(data){
                let option = {
                    // title: {
                    //     text: '男女比例',
                    //     left: 'center',
                    //     textStyle: {
                    //         color: '#fff',
                    //     }
                    // },
                    backgroundColor: '#090d1a',
                    legend: {
                        orient: 'vertical',
                        show: true,
                        right: '5%',
                        y: 'center',
                        itemWidth: 3,
                        itemHeight: 30,
                        // itemGap: 20,
                        textStyle: {
                            color: '#7a8c9f',
                            fontSize: 14,
                            lineHeight: 20,
                            rich: {
                                percent: {
                                    color: '#fff',
                                    fontSize: 16,
                                },
                            },
                        },
                    },
                    tooltip: {
                        show: true,
                    },
                    series: [
                        {
                            name:'16',
                            type: 'pie',
                            radius: ['65%', '75%'],
                            center: ['40%', '50%'],
                            hoverAnimation: false,
                            z: 10,
                            label: {
                                position: 'center',
                                formatter: () => {
                                    return '男女比例\n3:7';
                                },
                                color: '#fff',
                                fontSize: 16,
                                lineHeight: 30,
                            },
                            data: [
                                {
                                    value: 30,
                                    name: '男',
                                    itemStyle: {
                                        color: '#0286ff',
                                    },
                                },
                                {
                                    value: 40,
                                    name: '女',
                                    itemStyle: {
                                        color: '#fb5274',
                                    },
                                }
                            ],
                            labelLine: {
                                show: false,
                            },
                        },
                        {
                            name:'17',
                            type: 'pie',
                            radius: ['54%', '64%'],
                            center: ['40%', '50%'],
                            hoverAnimation: false,
                            label: {
                                show: false,
                            },
                            data: [
                                {
                                    value: 20,
                                    name: '男',
                                    itemStyle: {
                                        color: '#0286ff',
                                        opacity: 0.6,
                                    },
                                },
                                {
                                    value: 30,
                                    name: '女',
                                    itemStyle: {
                                        color: '#fb5274',
                                        opacity: 0.6,
                                    },
                                }
                            ],
                            labelLine: {
                                show: false,
                            },
                        },
                        {
                            name:'18',
                            type: 'pie',
                            radius: ['43%', '53%'],
                            center: ['40%', '50%'],
                            hoverAnimation: false,
                            label: {
                                show: false,
                            },
                            data: [
                                {
                                    value: 10,
                                    name: '男',
                                    itemStyle: {
                                        color: '#0286ff',
                                        opacity: 0.4,
                                    },
                                },
                                {
                                    value: 30,
                                    name: '女',
                                    itemStyle: {
                                        color: '#fb5274',
                                        opacity: 0.4,
                                    },
                                }
                            ],
                            labelLine: {
                                show: false,
                            },
                        },
                        {
                            name:'19',
                            type: 'pie',
                            radius: ['32%', '42%'],
                            center: ['40%', '50%'],
                            hoverAnimation: false,
                            label: {
                                show: false,
                            },
                            data: [
                                {
                                    value: 10,
                                    name: '男',
                                    itemStyle: {
                                        color: '#0286ff',
                                        opacity: 0.2,
                                    },
                                },
                                {
                                    value: 30,
                                    name: '女',
                                    itemStyle: {
                                        color: '#fb5274',
                                        opacity: 0.2,
                                    },
                                }
                            ],
                            labelLine: {
                                show: false,
                            },
                        },
                    ],

                };
                data.forEach(x=>{
                    switch (x.grade) {
                        case "2019":
                            option.series[0].name = x.grade
                            if(x.gender == "男")
                                option.series[0].data[0].value = x.percentage;
                            else
                                option.series[0].data[1].value = x.percentage;
                            break;
                        case "2018":
                            option.series[1].name = x.grade
                            if(x.gender == "男")
                                option.series[1].data[0].value = x.percentage;
                            else
                                option.series[1].data[1].value = x.percentage;
                            break;
                        case "2017":
                            option.series[2].name = x.grade
                            if(x.gender == "男")
                                option.series[2].data[0].value = x.percentage;
                            else
                                option.series[2].data[1].value = x.percentage;
                            break;
                        case "2016":
                            option.series[3].name = x.grade
                            if(x.gender == "男")
                                option.series[3].data[0].value = x.percentage;
                            else
                                option.series[3].data[1].value = x.percentage;
                            break;
                    }
                })
                this.genderChart.setOption(option)

            },
            level(data){
                let levelObject = {
                    levelArray:new Array(),
                    name:new Array()
                }
                data.forEach((x,i)=>{
                    levelObject.levelArray[i] = []
                    levelObject.levelArray[i][0] = x.researchInnovationAvg
                    levelObject.levelArray[i][1] = x.professionalSkillAvg
                    levelObject.levelArray[i][2] = x.stylisticSpecialtiesAvg
                    levelObject.levelArray[i][3] = x.groupWorkAvg
                    levelObject.levelArray[i][4] = x.socialPracticeAvg
                    levelObject.name[i] = x.grade
                })
                let option = {
                    title : {
                        text: '年级各维度对比',
                        left: 'center',
                        top: 'top',
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    tooltip:{

                    },
                    backgroundColor: '#101736',
                    color: ['#00c2ff', '#f9cf67', '#e92b77'],
                    legend: {
                        show: true,
                        // icon: 'circle',//图例形状
                        bottom: 15,
                        center: 0,
                        itemWidth: 14, // 图例标记的图形宽度。[ default: 25 ]
                        itemHeight: 14, // 图例标记的图形高度。[ default: 14 ]
                        itemGap: 21, // 图例每项之间的间隔。[ default: 10 ]横向布局时为水平间隔，纵向布局时为纵向间隔。
                        textStyle: {
                            fontSize: 14,
                            color: '#ade3ff'
                        },
                        data: levelObject.name,
                    },

                    radar: [{

                        indicator: [
                            {
                                text: '科研创新',
                                max: 5
                            },
                            {
                                text: '专业技能',
                                max: 13
                            },
                            {
                                text: '文体佳绩',
                                max: 4
                            },
                            {
                                text: '团学工作',
                                max: 8
                            },
                            {
                                text: '社会实践',
                                max: 3
                            }
                        ],

                        textStyle: {
                            color: 'red'
                        },
                        center: ['50%', '50%'],
                        radius: 82,
                        startAngle: 90,
                        splitNumber: 3,
                        orient: 'horizontal', // 图例列表的布局朝向,默认'horizontal'为横向,'vertical'为纵向.
                        // shape: 'circle',
                        // backgroundColor: {
                        //     image:imgPath[0]
                        // },
                        name: {
                            formatter: '{value}',
                            textStyle: {
                                fontSize: 10, //外圈标签字体大小
                                color: '#ade3ff' //外圈标签字体颜色
                            }
                        },
                        splitArea: { // 坐标轴在 grid 区域中的分隔区域，默认不显示。
                            show: true,
                            areaStyle: { // 分隔区域的样式设置。
                                color: ['#141c42', '#141c42'], // 分隔区域颜色。分隔区域会按数组中颜色的顺序依次循环设置颜色。默认是一个深浅的间隔色。
                            }
                        },
                        // axisLabel:{//展示刻度
                        //     show: true
                        // },
                        axisLine: { //指向外圈文本的分隔线样式
                            lineStyle: {
                                color: '#153269'
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                color: '#113865', // 分隔线颜色
                                width: 1, // 分隔线线宽
                            }
                        }
                    }, ],
                    series: [{
                        name: '雷达图',
                        type: 'radar',
                        itemStyle: {
                            emphasis: {
                                lineStyle: {
                                    width: 4
                                }
                            }
                        },
                        data: [{
                            name: levelObject.name[0],
                            // value: [85, 65, 55, 90, 82],
                            value:levelObject.levelArray[0],
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
                                            color: '#00c2ff'
                                        }, {
                                            offset: 0.5,
                                            color: 'rgba(0,0,0,0)'
                                        }, {
                                            offset: 1,
                                            color: '#00c2ff'
                                        }],
                                        globalCoord: false
                                    },
                                    opacity: 1 // 区域透明度
                                }
                            },
                            symbolSize: 2.5, // 单个数据标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示宽和高，例如 [20, 10] 表示标记宽为20，高为10。
                            label: {                    // 单个拐点文本的样式设置
                                normal: {
                                    show: true,             // 单个拐点文本的样式设置。[ default: false ]
                                    position: 'top',        // 标签的位置。[ default: top ]
                                    distance: 2,            // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。[ default: 5 ]
                                    color: '#6692e2',          // 文字的颜色。如果设置为 'auto'，则为视觉映射得到的颜色，如系列色。[ default: "#fff" ]
                                    fontSize: 14,           // 文字的字体大小
                                    formatter:function(params) {
                                        return params.value;
                                    }
                                }
                            },
                            itemStyle: {
                                normal: { //图形悬浮效果
                                    borderColor: '#00c2ff',
                                    borderWidth: 2.5
                                }
                            },
                            // lineStyle: {
                            //     normal: {
                            //         opacity: 0.5// 图形透明度
                            //     }
                            // }
                        }, {
                            name: levelObject.name[1],
                            value:levelObject.levelArray[1],
                            symbolSize: 2.5,
                            itemStyle: {
                                normal: {
                                    borderColor: '#f9cf67',
                                    borderWidth: 2.5,
                                }
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
                                            color: '#f9cf67'
                                        }, {
                                            offset: 0.5,
                                            color: 'rgba(0,0,0,0)'
                                        }, {
                                            offset: 1,
                                            color: '#f9cf67'
                                        }],
                                        globalCoord: false
                                    },
                                    opacity: 1 // 区域透明度
                                }
                            },
                            label: {                    // 单个拐点文本的样式设置
                                normal: {
                                    show: false,             // 单个拐点文本的样式设置。[ default: false ]
                                    position: 'top',        // 标签的位置。[ default: top ]
                                    distance: 2,            // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。[ default: 5 ]
                                    color: '#f9cf67',          // 文字的颜色。如果设置为 'auto'，则为视觉映射得到的颜色，如系列色。[ default: "#fff" ]
                                    fontSize: 14,           // 文字的字体大小
                                    formatter:function(params) {
                                        return params.value;
                                    }
                                }
                            },
                            // lineStyle: {
                            //     normal: {
                            //         opacity: 0.5// 图形透明度
                            //     }
                            // }
                        }, {
                            name: levelObject.name[2],
                            value:levelObject.levelArray[2],
                            symbolSize: 2.5,
                            itemStyle: {
                                normal: {
                                    borderColor: '#e92b77',
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
                                            color: '#e92b77'
                                        }, {
                                            offset: 0.5,
                                            color: 'rgba(0,0,0,0)'
                                        }, {
                                            offset: 1,
                                            color: '#e92b77'
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
                                    color: '#e92b77',          // 文字的颜色。如果设置为 'auto'，则为视觉映射得到的颜色，如系列色。[ default: "#fff" ]
                                    fontSize: 14,           // 文字的字体大小
                                    formatter:function(params) {
                                        return params.value;
                                    }
                                }
                            },
                        }]
                    }, ]
                };
                this.levelChart.setOption(option)
            },
            nation(data){
                let nationObject = {
                    name:new Array(),
                    value:new Array()
                }
                var myColor = ['#eb2100', '#eb3600', '#d0570e', '#d0a00e', '#34da62', '#00e9db', '#00c0e9', '#0096f3', '#33CCFF', '#33FFCC'];
                let option = {
                    backgroundColor: '#0e2147',
                    grid: {
                        left: '25%',
                        top: '12%',
                        right: '25%',
                        bottom: '8%',
                        containLabel: true
                    },
                    xAxis: [{
                        show: false,
                    }],
                    yAxis: [{
                        axisTick: 'none',
                        axisLine: 'none',
                        offset: '27',
                        axisLabel: {
                            textStyle: {
                                color: '#ffffff',
                                fontSize: '16',
                            }
                        },
                        data: ['南坪', '工贸', '石桥铺', '沙坪坝', '嘉州路', '红旗河沟', '两路口', '观音桥', '光电园', '小什字']
                    }, {
                        axisTick: 'none',
                        axisLine: 'none',
                        axisLabel: {
                            textStyle: {
                                color: '#ffffff',
                                fontSize: '0',
                            }
                        },
                        data:[10,9,8,7,6,5,4,3,2,1]
                    }, {
                        name: '少数民族人数TOP10',
                        nameGap: '30',
                        nameTextStyle: {
                            color: '#ffffff',
                            fontSize: '20',
                        },
                        axisLine: {
                            lineStyle: {
                                color: 'rgba(0,0,0,0)'
                            }
                        },
                        data: [],
                    }],
                    series: [{
                        name: '条',
                        type: 'bar',
                        yAxisIndex: 0,
                        data: [6647, 7473, 8190, 8488, 9491, 11726, 12745, 13170, 21319, 24934],
                        label: {
                            normal: {
                                show: true,
                                position: 'right',
                                textStyle: {
                                    color: '#ffffff',
                                    fontSize: '16',
                                }
                            }
                        },
                        barWidth: 12,
                        itemStyle: {
                            normal: {
                                color: function(params) {
                                    var num = myColor.length;
                                    return myColor[params.dataIndex % num]
                                },
                            }
                        },
                        z: 2
                    }, {
                        name: '白框',
                        type: 'bar',
                        yAxisIndex: 1,
                        barGap: '-100%',
                        data: [99, 99.5, 99.5, 99.5, 99.5, 99.5, 99.5, 99.5, 99.5, 99.5],
                        barWidth: 20,
                        itemStyle: {
                            normal: {
                                color: '#0e2147',
                                barBorderRadius: 5,
                            }
                        },
                        z: 1
                    }, {
                        name: '外框',
                        type: 'bar',
                        yAxisIndex: 2,
                        barGap: '-100%',
                        data: [100, 100, 100, 100, 100, 100, 100, 100, 100, 100],
                        barWidth: 4,
                        itemStyle: {
                            normal: {
                                color: function(params) {
                                    var num = myColor.length;
                                    return myColor[params.dataIndex % num]
                                },
                                barBorderRadius: 2,
                            }
                        },
                        z: 0
                    },
                        {
                            name: '外圆',
                            type: 'scatter',
                            hoverAnimation: false,
                            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                            yAxisIndex: 2,
                            symbolSize: 20,
                            itemStyle: {
                                normal: {
                                    color: function(params) {
                                        var num = myColor.length;
                                        return myColor[params.dataIndex % num]
                                    },
                                    opacity: 1,
                                }
                            },
                            z: 2
                        }
                    ]
                };

                data.sort((a,b)=>a.count-b.count)
                data.forEach((x,i)=>{
                    nationObject.name[i] = x.nation;
                    nationObject.value[i] = x.count;
                })
                option.series[0].data = nationObject.value
                option.yAxis[0].data = nationObject.name
                this.nationChart.setOption(option)

            }


        },
        created() {

        },
        mounted() {
            this.nationChart = this.$echarts.init(this.$refs.nation);
            this.levelChart= this.$echarts.init(this.$refs.level)
            this.genderChart = this.$echarts.init(this.$refs.gender)
            this.birthplaceChart = this.$echarts.init(this.$refs.birthplace);
            window.onresize = () => {
                //  根据窗口大小调整曲线大小
                this.nationChart.resize();
                this.levelChart.resize();
                this.genderChart.resize();
                this.birthplaceChart.resize();
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