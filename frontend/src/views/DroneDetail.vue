<template>
  <div class="detail-page">
    <div class="page-header">
      <h3>无人机详情</h3>
      <div>
        <el-button @click="$router.back()">返回列表</el-button>
        <el-button type="warning" @click="$router.push(`/drone/edit/${drone.id}`)">编辑</el-button>
      </div>
    </div>

    <el-card v-loading="loading">
      <el-descriptions :column="2" border v-if="drone">
        <el-descriptions-item label="ID">{{ drone.id }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag :type="drone.status === '正常' ? 'success' : 'warning'" size="small">{{ drone.status }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="型号">{{ drone.model }}</el-descriptions-item>
        <el-descriptions-item label="名称">{{ drone.name }}</el-descriptions-item>
        <el-descriptions-item label="厂商">{{ drone.manufacturer }}</el-descriptions-item>
        <el-descriptions-item label="重量">{{ drone.weight ? drone.weight.toFixed(2) + ' kg' : '-' }}</el-descriptions-item>
        <el-descriptions-item label="最大速度">{{ drone.maxSpeed ? drone.maxSpeed.toFixed(2) + ' km/h' : '-' }}</el-descriptions-item>
        <el-descriptions-item label="最大高度">{{ drone.maxAltitude ? drone.maxAltitude.toFixed(2) + ' m' : '-' }}</el-descriptions-item>
        <el-descriptions-item label="飞行范围">{{ drone.flightRange ? drone.flightRange.toFixed(2) + ' km' : '-' }}</el-descriptions-item>
        <el-descriptions-item label="最大飞行时间">{{ drone.maxFlightTime ? drone.maxFlightTime.toFixed(2) + ' min' : '-' }}</el-descriptions-item>
        <el-descriptions-item label="推进系统">{{ drone.propulsionSystem }}</el-descriptions-item>
        <el-descriptions-item label="控制系统">{{ drone.controlSystem }}</el-descriptions-item>
        <el-descriptions-item label="通信协议">{{ drone.communicationProtocol }}</el-descriptions-item>
        <el-descriptions-item label=""></el-descriptions-item>
        <el-descriptions-item label="描述" :span="2">{{ drone.description }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ formatTime(drone.createTime) }}</el-descriptions-item>
        <el-descriptions-item label="更新时间">{{ formatTime(drone.updateTime) }}</el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { droneApi } from '../api/index'

const route = useRoute()
const router = useRouter()
const drone = ref(null)
const loading = ref(true)

const formatTime = (time) => {
  if (!time) return '-'
  return time.replace('T', ' ').substring(0, 19)
}

onMounted(async () => {
  try {
    const res = await droneApi.detail(route.params.id)
    drone.value = res.data
  } catch (e) {
    if (e.response && e.response.data && e.response.data.code === 404) {
      router.push('/drone/list')
    }
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.detail-page { background: #fff; padding: 20px; border-radius: 4px; }
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.page-header h3 { margin: 0; }
</style>
