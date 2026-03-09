<template>
  <!-- 使用修改后的类名 -->
  <div class="login-container">

    <!-- MP4视频背景 -->
    <video class="video-bg" autoplay muted loop playsinline>
      <source src="@/assets/videos/【哲风壁纸】宇宙-星云-科幻.mp4" type="video/mp4">
    </video>

        <!--ring div starts here-->
        <div class="ring">
          <i style="--clr:#00ff0a;"></i>
          <i style="--clr:#ff0057;"></i>
          <i style="--clr:#fffd44;"></i>
          <div class="login">
            <h2>Login</h2>
            <!-- 用户名输入框 -->
            <div class="inputBx">
              <input
                  v-model="data.form.username"
                  @blur="validateField('username')"
                  @focus="handleFocus('username')"
                  @input="handleInput('username')"
                  placeholder="请输入账号"
              >
              <span
                  v-if="data.form.username"
                  class="clear-btn"
                  @click="clearField('username')"
              >
                X
              </span>
              <!-- 错误提示 - 绝对定位，不撑开布局 -->
              <div v-if="shouldShowError('username')" class="error-msg">
                请输入账号
              </div>
            </div>

            <!-- 密码输入 -->
            <div class="inputBx">
              <input
                  v-model="data.form.password"
                  @blur="validateField('password')"
                  @focus="handleFocus('password')"
                  @input="handleInput('password')"
                  placeholder="请输入密码"
              >
              <span
                  v-if="data.form.password"
                  class="clear-btn"
                  @click="clearField('password')"
              >
                X
              </span>

              <div v-if="shouldShowError('password')" class="error-msg">
                请输入密码
              </div>
            </div>

            <!-- 角色选择 - 自定义透明下拉框 -->
            <div class="inputBx custom-dropdown">
              <!-- 下拉触发按钮 -->
              <div
                  class="dropdown-trigger"
                  @click="toggleDropdown"
                  :class="{ 'dropdown-open': isDropdownOpen }"
              >
              <span class="selected-text">
                {{ getSelectedRoleText() }}
              </span>
                <span class="dropdown-arrow">▼</span>
              </div>

              <!-- 透明下拉菜单 -->
              <div v-if="isDropdownOpen" class="dropdown-menu">
                <!-- 1 对应 ROLE_SUPER_ADMIN -->
                <div
                    class="dropdown-item"
                    @click="selectRole(1)"
                    :class="{ 'selected': data.form.role === 1 }"
                >
                  管理员
                </div>
                <!-- 2 对应 ROLE_CASHIER -->
                <div
                    class="dropdown-item"
                    @click="selectRole(2)"
                    :class="{ 'selected': data.form.role === 2 }"
                >
                  收款员
                </div>
                <!-- 3 对应 ROLE_WAREHOUSE_KEEPER -->
                <div
                    class="dropdown-item"
                    @click="selectRole(3)"
                    :class="{ 'selected': data.form.role === 3 }"
                >
                  仓管员
                </div>
                <!-- “暂无职位” 可根据需要定义为一个常量，例如 0 -->
                <div
                    class="dropdown-item"
                    @click="selectRole(0)"
                    :class="{ 'selected': data.form.role === 0 }"
                >
                  暂无职位
                </div>
              </div>
            </div>

            <!-- 登录按钮 -->
            <div class="inputBx">
              <input
                  type="submit"
                  :value="data.loading ? '登录中...' : '登录'"
                  @click="login"
                  :disabled="data.loading"
              >
            </div>

            <!-- 注册链接 -->
            <div class="links">
              <a href="/register">注册</a>
            </div>
          </div>


        </div>
        <!--ring div ends here-->



  </div>

</template>


<script setup>
import { reactive, ref, onMounted, onUnmounted } from "vue";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
import { useForm } from 'vee-validate'


// 添加的下拉框状态（在data对象外）
const isDropdownOpen = ref(false)

// 获取当前选中角色的显示文本
const getSelectedRoleText = () => {
  // 键名改为整数，与 EmployeeConstant.java 和修改后的 data.form.role 保持一致
  const roleMap = {
    1: '超级管理员',    // 对应 EmployeeConstant.ROLE_SUPER_ADMIN
    2: '收银员',       // 对应 EmployeeConstant.ROLE_CASHIER
    3: '仓管员',       // 对应 EmployeeConstant.ROLE_WAREHOUSE_KEEPER
    0: '暂无职位'      // 建议为“暂无职位”定义一个明确的常量，例如 0
  };
  // 现在 data.form.role 是数字，可以直接用它作为键来查找
  return roleMap[data.form.role] || '请选择'
}

// 切换下拉框显示/隐藏
const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value
}

// 选择角色
const selectRole = (roleCode) => {
  data.form.role = roleCode
  isDropdownOpen.value = false
}

// 点击页面其他地方关闭下拉框
const handleClickOutside = (event) => {
  const dropdown = document.querySelector('.custom-dropdown')
  if (dropdown && !dropdown.contains(event.target)) {
    isDropdownOpen.value = false
  }
}

// 设置点击外部关闭监听
onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside)
})

const data = reactive({
  form: {
    username: '',
    password: '',
    role: 1
  },
  // 控制按钮加载状态
  loading: false,
  // 只用一个状态：记录每个字段是否需要显示错误
  fieldErrors: {
    username: false,   // true表示显示错误，false表示不显示
    password: false
  }
})

// 处理聚焦事件
const handleFocus = (field) => {
  // 聚焦时不改变任何状态，只是标记已聚焦
  // 这样错误信息会保持显示
}

// 处理输入事件
const handleInput = (field) => {
  // 用户开始输入时，清除错误状态
  // 输入时无条件清除错误显示
  // 这是关键：无论输入什么内容，都清除错误
  data.fieldErrors[field] = false
}

// 验证字段（失去焦点时调用）
const validateField = (field) => {
  // 失焦时判断：如果内容为空，显示错误；否则不显示
  if (!data.form[field].trim()) {
    data.fieldErrors[field] = true  // 显示错误
  } else {
    data.fieldErrors[field] = false // 不显示错误
  }
}

// 判断是否应该显示错误
const shouldShowError = (field) => {
  // return data.errors[field].showError
  // 只有当fieldErrors[field]为true时才显示错误
  return data.fieldErrors[field]
}

// 清空字段内容
const clearField = (field) => {
  data.form[field] = ''
  // 清空后立即验证（因为用户可能直接清空内容）
  validateField(field)
}

const login = async () => {

  // 提交前检查是否有空字段
  let hasError = false

  if (!data.form.username.trim()) {
    data.fieldErrors.username = true
    hasError = true
  }

  if (!data.form.password.trim()) {
    data.fieldErrors.password = true
    hasError = true
  }

  if (hasError) {
    ElMessage.warning('账号或密码不能为空')
    return
    }

  data.loading = true

  const res = await request.post('login',data.form)
  if ( res.code === 200 ) {   // 登录成功
    //储存后台返回的用户数据信息
    localStorage.setItem('bs-xm-user', JSON.stringify(res.data))  //把json对象转成 json字符串存储
    ElMessage.success('登录成功')

    setTimeout( () => {
      location.href = '/manager/home'
    },1000)

  } else {
    data.loading = false
    ElMessage.error(res.msg)
  }

}

// 动画结束后的处理
const handleAnimationEnd = () => {
  // 跳转到主页面
  location.href = '/manager/home'
}

</script>

<style scoped>

/* 只需要添加视频样式，其他都在CSS文件中 */
.video-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: 1;
}

/* 自定义下拉框 */
.custom-select select {
  appearance: none;
  background: rgba(255, 255, 255, 0.1)
  url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='white'%3e%3cpath d='M7 10l5 5 5-5z'/%3e%3c/svg%3e")
  no-repeat right 20px center;
  background-size: 16px;
  cursor: pointer;
  padding-right: 50px;
}

/* 清除按钮定位调整 */
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
  z-index: 3; /* 确保在最上层 */
}

.clear-btn:hover {
  color: #fff;
}

</style>

<style>
/* 全局引入CSS文件 */
@import '@/assets/css/login-animation.css';
</style>