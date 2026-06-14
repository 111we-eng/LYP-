<template>
  <div class="layout">
    <el-container>
      <el-header class="header">
        <div class="header-left">
          <h2>无人机管理系统</h2>
        </div>
        <div class="header-right">
          <el-button type="danger" size="small" @click="handleLogout">退出登录</el-button>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<style scoped>
.layout { min-height: 100vh; background: #f0f2f5; }
.header {
  background: #409eff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #fff;
}
.header-left h2 { margin: 0; }
.header-right { display: flex; align-items: center; }
</style>
