<template>
  <!-- <view class="content">
    <image class="logo" src="/static/logo.png"></image>
    <view class="text-area">
      <text class="title">{{ title }}</text>
    </view>
  </view> -->

  <view class="content">
    <view class="header">
      <u-icon name="list" size="50" @click="onMenuIconClick"></u-icon>
    </view>

    <DtSoccerStanding v-if="selectedL2Menu === 'standing'" />
    <DtSoccerTeam v-if="selectedL2Menu === 'team'" />

    <view class="l1-menu-container" v-show="showL1Menu">
      <ul>
        <li>
          <view @click="onL1MenuClick('Soccer')">Soccer</view>
          <ul class="l2-menu" v-show="showL1Menu && showL2Menu === 'Soccer'">
            <li @click="onL2MenuClick('team')">Team</li>
            <li @click="onL2MenuClick('standing')">Standing</li>
          </ul>
        </li>
        <li>
          <view @click="onL1MenuClick('Tennis')">Tennis</view>
        </li>
      </ul>
    </view>
  </view>
</template>

<script setup>
import { ref } from 'vue';

let showL1Menu = ref(false)
let showL2Menu = ref('')
let selectedL2Menu = ref('')

const onMenuIconClick = () => {
  showL1Menu.value = !showL1Menu.value
  showL2Menu.value = ''
}

const onL1MenuClick = (tag) => {
  if (showL2Menu.value === tag) {
    showL2Menu.value = ''
  } else {
    showL2Menu.value = tag
  }
}

const onL2MenuClick = (tag) => {
  selectedL2Menu.value = tag
  showL1Menu.value = false
}

</script>

<style scoped>
/* .content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.logo {
  height: 200rpx;
  width: 200rpx;
  margin-top: 200rpx;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 50rpx;
}

.text-area {
  display: flex;
  justify-content: center;
}

.title {
  font-size: 36rpx;
  color: #8f8f94;
} */

.content {
  position: relative;
}

.header {
  height: 100rpx;
  border-bottom: 2rpx #858a83 solid;
}

.l1-menu-container {
  position: absolute;
  left: 0;
  top: 100rpx;
  width: 320rpx;
  height: 100vh;
  background-color: #f3f3f3;
}

.l1-menu-container li {
  font-size: 30rpx;
  line-height: 2em;
  border-bottom: 1rpx #858a83 solid;
}

.l2-menu {
  background-color: #fff;
}

.l2-menu li {
  padding-left: 35rpx;
}
</style>
