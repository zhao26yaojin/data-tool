import axios from 'axios'

const fbService = axios.create({
    baseURL: import.meta.env.VITE_FB_BASE
})

// 请求前处理
const requestSuccess = config => {
    return config
}

const requestError = error => {
    return error
}

fbService.interceptors.request.use(requestSuccess, requestError)

// 响应成功
const responseSuccess = response => {
    return response.data
}

// 响应错误
const responseError = error => {
    return Promise.reject(error)
}

fbService.interceptors.response.use(responseSuccess, responseError)

export { fbService }