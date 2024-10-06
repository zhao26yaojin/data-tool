<template>
    <div class="frame">
        <el-form :inline="true" :model="searchParam" class="demo-form-inline">
            <el-form-item label="Team Name">
                <el-input v-model="searchParam.teamName" placeholder="Team Name" clearable />
            </el-form-item>
            <el-form-item label="Event">
                <el-select v-model="searchParam.eventId" placeholder="Select Event" filterable clearable>
                    <el-option v-for="event in eventList" :key="event.id" :label="event.name" :value="event.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSearch">Query</el-button>
            </el-form-item>
        </el-form>

        <el-table class="table" :data="tableData" border style="width: 100%">
            <el-table-column prop="teamName" label="Name" width="180">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="onView(scope.row)">{{ scope.row.teamName
                    }}</el-button>
                </template>
            </el-table-column>
            <el-table-column prop="gamePlay" label="Game Play" />
            <el-table-column prop="win" label="Win" />
            <el-table-column prop="draw" label="Draw" />
            <el-table-column prop="loss" label="Loss" />
            <el-table-column prop="goalsFor" label="Goals For" />
            <el-table-column prop="goalsAgainst" label="Goals Against" />
            <el-table-column prop="goalsDifferential" label="Goals Differential" />
            <el-table-column prop="points" label="Points" />
        </el-table>
    </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { list } from '@/api/fb/standing'
import { list as getEventList } from '@/api/fb/event'

const router = useRouter()

const searchParam = reactive({
    eventId: null,
    teamName: ''
})

const tableData = reactive([])

const eventList = getEventList()

const onSearch = async () => {
    const { result } = await list(searchParam)

    tableData.splice(0, tableData.length)
    if (result && result.length > 0) {
        for (const record of result) {
            tableData.push(record)
        }
    }
    // tableData.splice(0, -1, ...result.records)
}

const onView = (item) => {
    router.push({ path: '/translate', query: { id: item.id } })
}

</script>

<style scoped>
.demo-form-inline .el-input {
    --el-input-width: 220px;
}

.demo-form-inline .el-select {
    --el-select-width: 220px;
}

.frame {
    width: 800px;
    margin: auto;
    padding-top: 30px;
}

.table {
    margin-top: 50px;
}

.demo-pagination-block {
    margin-top: 20px;
}
</style>