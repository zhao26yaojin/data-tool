import { ref } from 'vue'
import { fbService } from "@/utils/request"

export function list() {
    const list = ref([])

    const params = {}

    fbService({
        url: 'event/list',
        method: 'get',
        params
    }).then(res => {
        list.value = res.result || []
    })

    return list
}