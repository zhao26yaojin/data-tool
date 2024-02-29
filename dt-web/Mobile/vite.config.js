import { defineConfig, loadEnv } from 'vite'
import uni from '@dcloudio/vite-plugin-uni'
// https://vitejs.dev/config/
// export default defineConfig({
//   plugins: [
//     uni(),
//   ],
//   server: {
//     proxy: {
//       [VITE_BASE_API]: {
//         target: `${VITE_APP_BASE_URL}:9012`,
//         rewrite: path => path.replace(new RegExp('^' + VITE_BASE_API), '')
//       }
//     }
//   }
// })

export default defineConfig(({ mode, command }) => {
  const env = loadEnv(mode, process.cwd())
  const { VITE_APP_ENV, VITE_APP_BASE_URL, VITE_BASE_API } = env
  return {
    base: VITE_APP_ENV === 'production' ? './' : '/',
    plugins: [
      uni(),
    ],
    server: {
      proxy: {
        [VITE_BASE_API]: {
          target: `${VITE_APP_BASE_URL}:9012`,
          rewrite: path => path.replace(new RegExp('^' + VITE_BASE_API), '')
        }
      }
    }
  }
})