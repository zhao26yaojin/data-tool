import { http } from "@/utils/http"

export function getStandingList(data) {
    return http({
        method: 'GET',
        url: '/standing/list',
        data
    })
}