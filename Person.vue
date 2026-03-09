<template>
  <div class="card" style="width: 50%; padding: 40px 20px">
    <el-form ref="formRef" :rules="data.rules" :model="data.form" label-width="80px" style="padding-right: 40px; padding-top: 20px">
      <!-- 浮动头像区域 -->
      <div style="width: 100%; display: flex; justify-content: center; margin-bottom: 20px">
        <div class="floating-avatar-container">
          <!-- 隐藏的Element UI上传组件 -->
          <el-upload
              ref="avatarUploadRef"
              class="hidden-avatar-upload"
              action="http://localhost:8080/files/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              accept="image/*"
              style="display: none"
          >
          </el-upload>

          <!-- 自定义的视觉元素 -->
          <div class="custom-avatar-visual" @click="triggerAvatarUpload">
            <div v-if="data.form.avatar" class="avatar-with-image">
              <img :src="data.form.avatar" class="avatar-image" />
              <div class="avatar-overlay">
                <el-icon class="overlay-icon"><Camera /></el-icon>
                <span class="overlay-text">更换头像</span>
              </div>
            </div>
            <div v-else class="avatar-placeholder">
              <el-icon class="placeholder-icon"><Plus /></el-icon>
              <span class="placeholder-text">上传头像</span>
            </div>
          </div>
        </div>
      </div>

      <!-- 表单字段保持不变 -->
      <el-form-item label="账号" prop="username">
        <el-input disabled v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"/>
      </el-form-item>
      <div v-if="['CASHIER', 'STOCK', 'NOT YET'].includes(data.user.role)">
        <el-form-item label="性别">
          <el-radio-group v-model="data.form.sex">
            <el-radio value="男" label="男">男</el-radio>
            <el-radio value="女" label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item disabled label="工号" prop="no">
          <el-input v-model="data.form.no" autocomplete="off" placeholder="请输入工号"/>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number style="width: 180px" :min="18" v-model="data.form.age" autocomplete="off" placeholder="请输入年龄"/>
        </el-form-item>
        <el-form-item label="个人介绍">
          <el-input :rows="3" type="textarea" v-model="data.form.description" autocomplete="off" placeholder="请输入个人介绍"/>
        </el-form-item>
      </div>

      <div style="text-align: center">
        <el-button @click="updateUser" type="primary" style="padding: 20px 30px">更新个人信息</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { reactive, ref, nextTick } from "vue";
import { ElMessage } from "element-plus";
import { Plus, Camera } from "@element-plus/icons-vue";
import request from "@/utils/request.js";

const formRef = ref()
const avatarUploadRef = ref() // el-upload组件的引用

const data = reactive({
  user: JSON.parse(localStorage.getItem('bs-xm-user')),
  form: {},
  rules: {
    username: [
      { required: true, message: '请输入账号', trigger: 'blur' }
    ],
    name: [
      { required: true, message: '请输入名称', trigger: 'blur' }
    ],
    no: [
      { required: true, message: '请输入工号', trigger: 'blur' }
    ]
  }
})

// 触发文件上传
const triggerAvatarUpload = () => {
  // 确保组件已渲染
  nextTick(() => {
    if (avatarUploadRef.value) {
      // 触发隐藏的el-upload的点击事件
      const uploadElement = avatarUploadRef.value.$el
      const fileInput = uploadElement.querySelector('input[type="file"]')
      if (fileInput) {
        fileInput.click()
      }
    }
  })
}

// 上传前的校验
const beforeAvatarUpload = (file) => {
  const isImage = file.type.startsWith('image/')
  const isLt5M = file.size / 1024 / 1024 < 5

  if (!isImage) {
    ElMessage.error('只能上传图片文件')
    return false
  }
  if (!isLt5M) {
    ElMessage.error('图片大小不能超过5MB')
    return false
  }
  return true
}

// 上传成功处理
const handleAvatarSuccess = (res) => {
  console.log('上传结果:', res)
  if (res.code === '200') {
    data.form.avatar = res.data
    ElMessage.success('头像上传成功')
  } else {
    ElMessage.error(res.msg || '上传失败')
  }
}

// 其余代码保持不变
const emit = defineEmits(['updateUser'])
if(data.user.role === 'USER') {
  request.get('user/selectById/' + data.user.id).then(res => {
    data.form = res.data
  })
} else {
  data.form = data.user
}

const updateUser = () => {
  if (data.user.role === 'USER') {
    request.put('user/update', data.form).then(res => {
      if (res.code === '200') {
        ElMessage.success('更新成功')
        localStorage.setItem('bs-xm-user', JSON.stringify(data.form))
        emit('updateUser')
      } else {
        ElMessage.error(res.msg)
      }
    })
  } else {
    request.put('admin/update', data.form).then(res => {
      if (res.code === '200') {
        ElMessage.success('更新成功')
        localStorage.setItem('xm-pro-user', JSON.stringify(data.form))
        emit('updateUser')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }
}
</script>

<style scoped>
/* 外层容器 */
.floating-avatar-container {
  position: relative;
}

/* 自定义视觉元素 - 完全独立于Element UI样式 */
.custom-avatar-visual {
  cursor: pointer;
  transition: transform 0.3s ease;
}

.custom-avatar-visual:hover {
  transform: scale(1.05);
}

/* 有图片时的样式 */
.avatar-with-image {
  position: relative;
  width: 150px;
  height: 150px;
  box-sizing: border-box;
  border: 5px white solid;
  border-radius: 50%;
  overflow: hidden;
  box-shadow: 0 5px 15px 0px rgba(0,0,0,0.6);
  transform: translatey(0px);
  animation: float 6s ease-in-out infinite;
}

.avatar-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 鼠标悬停覆盖层 */
.avatar-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.avatar-with-image:hover .avatar-overlay {
  opacity: 1;
}

.overlay-icon {
  font-size: 28px;
  margin-bottom: 8px;
}

.overlay-text {
  font-size: 14px;
}

/* 占位符样式 */
.avatar-placeholder {
  width: 150px;
  height: 150px;
  box-sizing: border-box;
  border: 5px white solid;
  border-radius: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  background: rgba(255, 255, 255, 0.1);
  box-shadow: 0 5px 15px 0px rgba(0,0,0,0.6);
  transform: translatey(0px);
  animation: float 6s ease-in-out infinite;
}

.placeholder-icon {
  font-size: 40px;
  margin-bottom: 10px;
}

.placeholder-text {
  font-size: 14px;
}

/* 浮动动画 */
@keyframes float {
  0% {
    box-shadow: 0 5px 15px 0px rgba(0,0,0,0.6);
    transform: translatey(0px);
  }
  50% {
    box-shadow: 0 25px 15px 0px rgba(0,0,0,0.2);
    transform: translatey(-20px);
  }
  100% {
    box-shadow: 0 5px 15px 0px rgba(0,0,0,0.6);
    transform: translatey(0px);
  }
}
</style>

<!-- 可以完全删除原有的Element UI头像上传样式，因为我们已经不再使用它 -->
<!-- 如果需要，可以保留其他Element UI组件的默认样式 -->