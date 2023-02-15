const path = require('path')
const CopyPlugin = require('copy-webpack-plugin')

module.exports = {
    mode: 'production', // development
    entry: './src/main.ts',
    devServer: {
        static: {
            directory: path.join(__dirname, "./dist")
        },
    },
    output: {
        filename: 'app.js',
        path: path.join(__dirname, 'dist')
    },
    plugins: [
        new CopyPlugin({
            patterns: [{ from: "public" }],
        })
    ],
    resolve: {
        extensions: [ '.ts', '.js' ]
    },
    module: {
        rules: [{
            test: /\.ts$/,
            use: 'ts-loader',
            exclude: /node_modules/
        }]
    },
    performance: {
        hints: false,
        maxEntrypointSize: 512000,
        maxAssetSize: 512000
    }
}
