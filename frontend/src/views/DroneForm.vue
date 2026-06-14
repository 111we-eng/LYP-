<template>
  <div class="form-page">
    <div class="page-header">
      <h3>{{ isEdit ? '编辑无人机' : '添加无人机' }}</h3>
      <el-button @click="$router.back()">返回列表</el-button>
    </div>

    <el-card>
      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px" v-loading="loading">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="型号" prop="model">
              <el-input v-model="form.model" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="厂商">
              <el-input v-model="form.manufacturer" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态">
              <el-select v-model="form.status" style="width: 100%">
                <el-option label="正常" value="正常" />
                <el-option label="维护中" value="维护中" />
                <el-option label="退役" value="退役" />
                <el-option label="训练中" value="训练中" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="重量(kg)">
              <el-input-number v-model="form.weight" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大速度(km/h)">
              <el-input-number v-model="form.maxSpeed" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="最大高度(m)">
              <el-input-number v-model="form.maxAltitude" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="飞行范围(km)">
              <el-input-number v-model="form.flightRange" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="最大飞行时间(min)">
              <el-input-number v-model="form.maxFlightTime" :min="0" :precision="2" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="推进系统">
              <el-input v-model="form.propulsionSystem" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="控制系统">
              <el-input v-model="form.controlSystem" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="通信协议">
              <el-input v-model="form.communicationProtocol" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="描述">
          <el-input v-model="form.description" type="textarea" :rows="3" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSubmit" :loading="submitting">保存</el-button>
          <el-button @click="$router.back()">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { droneApi } from '../api/index'

const route = useRoute()
const router = useRouter()
const formRef = ref(null)
const loading = ref(false)
const submitting = ref(false)

const isEdit = computed(() => !!route.params.id)

const form = reactive({
  id: null,
  model: '',
  name: '',
  manufacturer: '',
  weight: null,
  maxSpeed: null,
  maxAltitude: null,
  flightRange: null,
  maxFlightTime: null,
  propulsionSystem: '',
  controlSystem: '',
  communicationProtocol: '',
  status: '正常',
  description: '',
  createTime: null,
  updateTime: null
})

const rules = {
  model: [{ required: true, message: '请输入型号', trigger: 'blur' }],
  name: [{ required: true, message: '请输入名称', trigger: 'blur' }]
}

const fetchDetail = async (id) => {
  loading.value = true
  try {
    const res = await droneApi.detail(id)
    Object.assign(form, res.data)
  } finally {
    loading.value = false
  }
}

const handleSubmit = async () => {
  const valid = await formRef.value.validate().catch(() => false)
  if (!valid) return

  submitting.value = true
  try {
    await droneApi.save({ ...form })
    ElMessage.success(isEdit.value ? '更新成功' : '添加成功')
    router.push('/drone/list')
  } finally {
    submitting.value = false
  }
}

onMounted(() => {
  if (isEdit.value) {
    fetchDetail(route.params.id)
  }
})
</script>

<style scoped>
.form-page { background: #fff; padding: 20px; border-radius: 4px; }
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.page-header h3 { margin: 0; }
</style>
