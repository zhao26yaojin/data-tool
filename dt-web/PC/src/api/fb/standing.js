import { fbService } from "@/utils/request"

export function list(params) {
    return fbService({
        url: '/standing/list',
        method: 'get',
        params
    })
}
