import { http } from "@/utils/http"

export function getEventList() {
    return http({
        method: 'GET',
        url: '/event/list'
    })
}