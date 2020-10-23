module.exports = {
    assetsDir: 'static',
    productionSourceMap: false,
    devServer: {
        proxyTable: {
            '/': {
                target: 'http://localhost:8090',
                changeOrigin: true,
                pathRewrite: {
                    '/': '/'
                }
            }
        }

    }
}