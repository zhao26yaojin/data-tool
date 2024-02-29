import { defineConfig, loadEnv } from 'vite'
import path from 'path'
import vue from "@vitejs/plugin-vue"

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

export default defineConfig(({ mode, command }) => {
    const env = loadEnv(mode, process.cwd())
    const { VITE_APP_ENV, VITE_APP_BASE_URL, VITE_BASE_API } = env
    return {
        base: VITE_APP_ENV === 'test' ? './' : '/',
        plugins: [
            vue(),
            // ...
            AutoImport({
                resolvers: [ElementPlusResolver()],
            }),
            Components({
                resolvers: [ElementPlusResolver()],
            })
        ],
        resolve: {
            alias: {
                // 设置别名
                '@': path.resolve(__dirname, './src')
            }
        },
        server: {
            proxy: {
                [VITE_BASE_API]: {
                    target: `${VITE_APP_BASE_URL}:9012`,
                    rewrite: path => path.replace(new RegExp('^' + VITE_BASE_API), '')
                }
                // '/lexical': {
                //     target: `${VITE_APP_BASE_URL}:9001`
                // },
                // '/statistics': {
                //     target: `${VITE_APP_BASE_URL}:9002`
                // }
            }
        }
    }
})