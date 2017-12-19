

  
	  // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

     function getData(){
var jsonstr = [];
for (var i = 0; i < mac.length; i++) {
var json = {};
json.name = mac[i];
json.x = (i+1)*100;
json.y = 300;
jsonstr.push(json);
}
return jsonstr;
};


 function getlink(){
var linkstr = [];
for (var i = 0; i < mac.length-1; i++) {
var json = {};
json.source = mac[i];
json.target = mac[i+1];
linkst.rpush(json);
}
return linkstr;
};

var data=[] ;
 data = getData();





       option = {
    title: {
        text: 'Graph 简单示例'
    },
    tooltip: {},
    animationDurationUpdate: 1500,
    animationEasingUpdate: 'quinticInOut',
    series : [
        {
            type: 'graph',
            layout: 'none',
            symbolSize: 50,
            roam: true,
            label: {
                normal: {
                    show: true
                }
            },
            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [4, 10],
            edgeLabel: {
                normal: {
                    textStyle: {
                        fontSize: 20
                    }
                }
            },
            data: data,
            // links: [],
            links: [{
                source: 0,
                target: 1,
                symbolSize: [5, 20],
                label: {
                    normal: {
                        show: true
                    }
                },
                lineStyle: {
                    normal: {
                        width: 5,
                        curveness: 0.2
                    }
                }
            }
          
            ],
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 2,
                    curveness: 0
                }
            }
        }
    ]
};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);