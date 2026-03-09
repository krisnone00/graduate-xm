<template>
  <div class="login-container">
    <!-- MP4视频背景 -->
    <video class="video-bg" autoplay muted loop playsinline>
      <source src="@/assets/videos/【哲风壁纸】宇宙-星云-科幻.mp4" type="video/mp4">
    </video>

    <!-- ring div starts here -->
    <div class="ring">
      <i style="--clr:#00ff0a;"></i>
      <i style="--clr:#ff0057;"></i>
      <i style="--clr:#fffd44;"></i>

      <div class="login">
        <h2>Register</h2> <!-- 改为注册标题 -->

        <!-- 用户名输入框 -->
        <div class="inputBx">
          <input
              v-model="data.form.username"
              @blur="validateField('username')"
              @input="handleInput('username')"
              placeholder="请输入账号"
          >
          <span
              v-if="data.form.username"
              class="clear-btn"
              @click="clearField('username')"
          >X</span>
          <div v-if="shouldShowError('username')" class="error-msg">
            请输入账号
          </div>
        </div>

        <!-- 密码输入 -->
        <div class="inputBx">
          <input
              v-model="data.form.password"
              @blur="validateField('password')"
              @input="handleInput('password')"
              placeholder="请输入密码"
              type="password"
          >
          <span
              v-if="data.form.password"
              class="clear-btn"
              @click="clearField('password')"
          >X</span>
          <div v-if="shouldShowError('password')" class="error-msg">
            请输入密码
          </div>
        </div>

        <!-- 确认密码输入 -->
        <div class="inputBx">
          <input
              v-model="data.form.confirmPassword"
              @blur="validateConfirmPassword"
              @input="handleInput('confirmPassword')"
              placeholder="请确认密码"
              type="password"
          >
          <span
              v-if="data.form.confirmPassword"
              class="clear-btn"
              @click="clearField('confirmPassword')"
          >X</span>
          <div v-if="shouldShowError('confirmPassword')" class="error-msg">
            {{ data.confirmPasswordError }}
          </div>
        </div>

        <!-- 登录按钮改为注册按钮 -->
        <div class="inputBx">
          <input
              type="submit"
              :value="data.loading ? '注册中...' : '注册'"
              @click="register"
              :disabled="data.loading"
          >
        </div>

        <!-- 已有账号链接 -->
        <div class="links">
          <a href="/login">已有账号？去登录</a>
        </div>
      </div>
    </div>
    <!-- ring div ends here -->
  </div>
</template>

<script setup>
import { reactive, ref, onMounted, onUnmounted } from "vue";
import request from "@/utils/request.js";
import { ElMessage } from "element-plus";

// 响应式数据
const data = reactive({
  form: {
    username: '',
    password: '',
    confirmPassword: ''
  },
  loading: false,
  fieldErrors: {
    username: false,
    password: false,
    confirmPassword: false
  },
  confirmPasswordError: '请确认密码'
})

// 输入时清除错误状态
const handleInput = (field) => {
  data.fieldErrors[field] = false
  if (field === 'confirmPassword') {
    validateConfirmPassword()
  }
}

// 验证字段（失去焦点时调用）
const validateField = (field) => {
  data.fieldErrors[field] = !data.form[field].trim()
}

// 验证确认密码
const validateConfirmPassword = () => {
  if (!data.form.password.trim()) {
    data.fieldErrors.confirmPassword = true
    data.confirmPasswordError = '请先输入密码'
  } else if (!data.form.confirmPassword.trim()) {
    data.fieldErrors.confirmPassword = true
    data.confirmPasswordError = '请确认密码'
  } else if (data.form.password !== data.form.confirmPassword) {
    data.fieldErrors.confirmPassword = true
    data.confirmPasswordError = '两次输入的密码不一致'
  } else {
    data.fieldErrors.confirmPassword = false
  }
}

// 判断是否应该显示错误
const shouldShowError = (field) => {
  return data.fieldErrors[field]
}

// 清空字段内容
const clearField = (field) => {
  data.form[field] = ''
  if (field === 'password' || field === 'confirmPassword') {
    validateConfirmPassword()
  } else {
    validateField(field)
  }
}

// 注册逻辑
const register = async () => {
  // 提交前验证
  let hasError = false

  if (!data.form.username.trim()) {
    data.fieldErrors.username = true
    hasError = true
  }

  if (!data.form.password.trim()) {
    data.fieldErrors.password = true
    hasError = true
  }

  validateConfirmPassword()
  if (data.fieldErrors.confirmPassword) {
    hasError = true
  }

  if (hasError) {
    ElMessage.warning('请正确填写所有信息')
    return
  }

  data.loading = true

  try {
    const res = await request.post('/register', data.form)
    if (res.code === '200') {
      ElMessage.success('注册成功')
      setTimeout(() => {
        location.href = '/login'
      }, 1000)
    } else {
      ElMessage.error(res.msg)
      data.loading = false
    }
  } catch (error) {
    ElMessage.error('注册请求失败')
    data.loading = false
  }
}
</script>

<style scoped>
/* 视频背景样式 */
.video-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: 1;
}

/* 清除按钮样式 */
.clear-btn {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
  color: rgba(255, 255, 255, 0.6);
  font-size: 20px;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 3;
}

.clear-btn:hover {
  color: #fff;
}
</style>

<style>
/* 全局引入CSS文件（和登录页一样） */
@import '@/assets/css/login-animation.css';
</style>