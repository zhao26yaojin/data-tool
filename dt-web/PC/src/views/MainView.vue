<template>
    <div class="container">
        <div class="sidebar">
            <h5 class="mb-2">Pedia</h5>
            <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo" default-active="2"
                text-color="#fff" @open="handleOpen" @close="handleClose">
                <el-sub-menu index="1">
                    <template #title>
                        <el-icon>
                            <location />
                        </el-icon>
                        <span>Soccer</span>
                    </template>
                    <router-link to="/fb/team"><el-menu-item index="1-1">Team</el-menu-item></router-link>
                    <router-link to="/fb/standing"><el-menu-item index="1-2">Standing</el-menu-item></router-link>
                    <!-- <el-sub-menu index="1-4">
                        <template #title>item four</template>
                        <el-menu-item index="1-4-1">item one</el-menu-item>
                    </el-sub-menu> -->
                </el-sub-menu>
                <el-menu-item index="2">
                    <el-icon><icon-menu /></el-icon>
                    <span>Navigator Two</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                    <el-icon>
                        <document />
                    </el-icon>
                    <span>Navigator Three</span>
                </el-menu-item>
                <el-menu-item index="4">
                    <el-icon>
                        <setting />
                    </el-icon>
                    <span>Navigator Four</span>
                </el-menu-item>
            </el-menu>
        </div>
        <router-view></router-view>
    </div>
</template>

<script setup>
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'

const handleOpen = (key, keyPath) => {
    console.log(key, keyPath)
}

const handleClose = (key, keyPath) => {
    console.log(key, keyPath)
}
</script>

<style scoped>
.container {
    min-height: 100vh;
    display: flex;
}

.sidebar {
    width: 200px;
    background-color: #545c64;
}

.mb-2 {
    height: 50px;
    color: white;
    font-size: 20px;
    text-align: center;
    line-height: 50px;
}

.el-menu-vertical-demo {
    height: 100%;
}
</style>