const path = require("path")
const {VueLoaderPlugin}=require('vue-loader')
module.exports = {
    mode: "development",
    // 入口文件，可以相对路径
    entry: './src/main.ts',
    // 输出
    output: {
        // 绝对路径，输出文件夹
        // 用path是因为，不同操作系统的文件目录分隔符不一致
        path: path.resolve(__dirname, "dist"),
        // 打包后文件
        filename: "bundle.js",
    },
    // 模块
    module: {
        // 打包规则
        rules: [{
            test: /\.vue$/,
            use: ['vue-loader']
        },{
            test: /\.s[ca]ss$/i,
            // 使用顺序，从后往前
            use: ['style-loader','css-loader','sass-loader']
        },{
            test: /\.(png|svg|jpe?g|gif)$/i,
            // 老语法
            // use: {
            //     loader: "file-loader",
            //     options: {
            //         esModule:false,
            //     }
            // }
            type: "asset/resource"
        },{
            // es module的模块
            test: /\.m?js$/,
            use: [{
                loader: "babel-loader",
                options: {
                    presets:['@babel/preset-env']
                }
            },'ts-loader']
        }]
    },
    // 每一个插件数组元素都是构造函数的实例
    plugins: [
        // 当遇到vue中的其他文件时，要使用其他loader进行转换
        new VueLoaderPlugin(),
    ]
}