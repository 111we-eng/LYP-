<template>
  <div>
    <div class="toolbar">
      <el-input v-model="keyword" placeholder="搜索无人机型号或名称..." style="width: 300px;"
        clearable @clear="fetchData" @keyup.enter="fetchData">
        <template #append>
          <el-button @click="fetchData" icon="Search" />
        </template>
      </el-input>
      <div class="toolbar-actions">
        <el-button type="success" @click="handleGenerate" :loading="generating">AI生成无人机</el-button>
        <el-button type="primary" @click="$router.push('/drone/add')">添加无人机</el-button>
      </div>
    </div>

    <el-table :data="tableData" border stripe v-loading="loading" empty-text="暂无数据">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="model" label="型号" />
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="manufacturer" label="厂商" />
      <el-table-column prop="weight" label="重量(kg)" width="100">
        <template #default="{ row }">{{ row.weight ? row.weight.toFixed(2) : '-' }}</template>
      </el-table-column>
      <el-table-column prop="maxSpeed" label="最大速度(km/h)" width="130">
        <template #default="{ row }">{{ row.maxSpeed ? row.maxSpeed.toFixed(2) : '-' }}</template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="row.status === '正常' ? 'success' : 'warning'" size="small">{{ row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220" fixed="right">
        <template #default="{ row }">
          <el-button size="small" type="info" @click="$router.push(`/drone/detail/${row.id}`)">详情</el-button>
          <el-button size="small" type="warning" @click="$router.push(`/drone/edit/${row.id}`)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { droneApi } from '../api/index'

const loading = ref(false)
const generating = ref(false)
const keyword = ref('')
const tableData = ref([])

const fetchData = async () => {
  loading.value = true
  try {
    const res = await droneApi.list(keyword.value || undefined)
    tableData.value = res.data
  } finally {
    loading.value = false
  }
}

const handleGenerate = async () => {
  generating.value = true
  try {
    const res = await droneApi.generate()
    ElMessage.success('AI生成无人机成功')
    // 跳转到添加页并带上生成的数据
    const drone = res.data
    // 直接保存
    await droneApi.save(drone)
    ElMessage.success('AI生成的无人机已保存')
    fetchData()
  } catch (e) {
    // 错误已处理
  } finally {
    generating.value = false
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm(`确定要删除 "${row.name}" 吗?`, '确认', {
    type: 'warning'
  }).then(async () => {
    try {
      await droneApi.delete(row.id)
      ElMessage.success('删除成功')
      fetchData()
    } catch (e) {
      // 错误已处理
    }
  }).catch(() => {})
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
  background: #fff;
  padding: 16px;
  border-radius: 4px;
}
.toolbar-actions {
  display: flex;
  gap: 8px;
}
</style>
