<template>
    <view>
        <!-- 注意，如果需要兼容微信小程序，最好通过setRules方法设置rules规则 -->
        <u-form labelPosition="left" :model="model" :rules="rules">
            <u-form-item label="Team" prop="standingInfo.teamName" borderBottom ref="item1">
                <u-input v-model="model.standingInfo.teamName" border="none"></u-input>
            </u-form-item>
            <u-form-item label="Event" prop="standingInfo.event" borderBottom @click="showEvent = true; hideKeyboard()"
                ref="item1">
                <u-input v-model="model.standingInfo.event" disabled disabledColor="#ffffff" placeholder="请选择联赛"
                    border="none"></u-input>
                <template #right>
                    <u-icon name="arrow-right"></u-icon>
                </template>
            </u-form-item>
        </u-form>
        <u-action-sheet :show="showEvent" :actions="eventList" title="请选择联赛" @close="showEvent = false"
            @select="eventSelect">
        </u-action-sheet>
        <u-button @click="onQuery">查询</u-button>
    </view>

    <uni-table class="table" border stripe emptyText="暂无更多数据">
        <!-- 表头行 -->
        <uni-tr>
            <uni-th align="center">teamName</uni-th>
            <uni-th align="center">gamePlay</uni-th>
            <uni-th align="left">win</uni-th>
            <uni-th align="center">draw</uni-th>
            <uni-th align="center">loss</uni-th>
            <uni-th align="left">goalsFor</uni-th>
            <uni-th align="center">goalsAgainst</uni-th>
            <uni-th align="center">goalsDifferential</uni-th>
            <uni-th align="left">points</uni-th>
        </uni-tr>
        <!-- 表格数据行 -->
        <uni-tr v-for="standing in standingList" :key="standing.id">
            <uni-td>{{ standing.teamName }}</uni-td>
            <uni-td>
                <view class="name">{{ standing.gamePlay }}</view>
            </uni-td>
            <uni-td align="center">{{ standing.win }}</uni-td>
            <uni-td>{{ standing.draw }}</uni-td>
            <uni-td>{{ standing.loss }}</uni-td>
            <uni-td>{{ standing.goalsFor }}</uni-td>
            <uni-td>{{ standing.goalsAgainst }}</uni-td>
            <uni-td>{{ standing.goalsDifferential }}</uni-td>
            <uni-td>{{ standing.points }}</uni-td>
        </uni-tr>
    </uni-table>
</template>

<script setup>
import { onLoad } from "@dcloudio/uni-app"
import { ref, reactive } from "vue";
import { getStandingList } from "@/api/fb/standing"
import { getEventList } from "@/api/fb/event"

let standingList = reactive([])
const getStandingData = async (params) => {
    const { result } = await getStandingList(params)

    if (result && result.length > 0) {
        standingList.splice(0, standingList.length)
        standingList.push(...result)
    }
}

let eventList = reactive([])
const getEventData = async () => {
    const { result } = await getEventList()

    if (result && result.length > 0) {
        eventList.splice(0, eventList.length)
        eventList.push(...result)
    }

}


onLoad(() => {
    getEventData()
})

let showEvent = ref(false)
let model = reactive({
    standingInfo: {
        teamName: '',
        event: '',
    },
})

const rules = reactive({
    'standingInfo.event': {
        type: 'string',
        max: 1,
        required: true,
        message: '请选择联赛',
        trigger: ['blur', 'change']
    },
})

const eventSelect = (e) => {
    model.standingInfo.event = e.name
    model.standingInfo.eventId = e.id
}

const onQuery = () => {
    getStandingData(model.standingInfo)
}

</script>

<style scoped>
.table {
    margin-top: 80rpx;
}
</style>