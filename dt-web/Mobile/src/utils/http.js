// 添加拦截器
const httpInterceptor = {
    // 拦截前触发
    invoke(options) {
        // 拼接地址
        options.url = import.meta.env.VITE_FB_BASE + options.url
    }
}

uni.addInterceptor('request', httpInterceptor)

export const http = (options) => {
    return new Promise((resolve, reject) => {
        uni.request({
            ...options,
            success(res) {
                if (res.statusCode >= 200 && res.statusCode < 300) {
                    // 访问成功，提取数据
                    resolve(res.data)
                } else if (res.statusCode === 401) {
                    // 401错误 -> 清理用户信息，跳转到登录页

                    reject(res)
                } else {
                    // 其他错误
                    uni.showToast({
                        title: '请求错误',
                        icon: 'none'
                    })
                    reject(res)
                }
            },
            fail(err) {
                uni.showToast({
                    title: '网络错误',
                    icon: 'none'
                })
                reject(err)
            }
        })
    })
} 