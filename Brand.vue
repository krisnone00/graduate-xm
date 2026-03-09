<template>
  <div class="standard-page-container">

    <el-card shadow="never" class="table-card">
      <div style="display: flex; justify-content: space-between; align-items: center;">
        <div style="display: flex; gap: 10px;">
          <el-input v-model="searchName" placeholder="输入品牌名称搜索..." style="width: 200px;" clearable @clear="load" />
          <el-button type="primary" @click="load">🔍 查询</el-button>
        </div>
        <el-button type="success" @click="handleAdd">+ 新增品牌</el-button>
      </div>
    </el-card>

    <el-card shadow="never" class="table-card">
      <el-table :data="tableData" style="width: 100%" border stripe v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" align="center" />

        <el-table-column label="品牌Logo" width="120" align="center">
          <template #default="scope">
            <el-image
                v-if="scope.row.brandLogo"
                style="width: 50px; height: 50px; border-radius: 4px;"
                :src="scope.row.brandLogo"
                :preview-src-list="[scope.row.brandLogo]"
                fit="cover"
                preview-teleported
            />
            <span v-else style="color:#ccc; font-size:12px;">无Logo</span>
          </template>
        </el-table-column>

        <el-table-column prop="brandName" label="品牌名称" min-width="150" />
        <el-table-column prop="brandCode" label="品牌编码" width="150">
          <template #default="scope">
            <el-tag type="info">{{ scope.row.brandCode }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="description" label="描述" show-overflow-tooltip />

        <el-table-column prop="createdTime" label="创建时间" width="170" align="center">
          <template #default="scope">{{ scope.row.createdTime?.replace('T', ' ') }}</template>
        </el-table-column>

        <el-table-column prop="updatedTime" label="更新时间" width="170" align="center">
          <template #default="scope">{{ scope.row.updatedTime?.replace('T', ' ') }}</template>
        </el-table-column>


        <el-table-column label="操作" width="200" align="center">
          <template #default="scope">
            <el-button link type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin-top: 20px; display: flex; justify-content: flex-end;">
        <el-pagination
            background
            layout="total, prev, pager, next"
            :total="total"
            :page-size="pageSize"
            :current-page="pageNum"
            @current-change="handlePageChange"
        />
      </div>
    </el-card>

    <el-dialog
        v-model="dialogVisible"
        :title="title"
        width="500px"
        append-to-body
        :close-on-click-modal="false"
    >
      <el-form :model="form" label-width="100px" ref="formRef" :rules="rules">

        <el-form-item label="品牌名称" prop="brandName">
          <el-input v-model="form.brandName" placeholder="如：可口可乐" />
        </el-form-item>

        <el-form-item label="品牌编码" prop="brandCode">
          <el-input v-model="form.brandCode" placeholder="如：BRD001" />
        </el-form-item>

        <el-form-item label="品牌Logo">
          <el-upload
              class="avatar-uploader"
              :action="'http://localhost:8080/api/file/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
            <img v-if="form.brandLogo" :src="form.brandLogo" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
          <div style="font-size: 12px; color: #999; line-height: 1.5;">支持 jpg/png，小于 2MB</div>
        </el-form-item>

        <el-form-item label="品牌描述">
          <el-input v-model="form.description" type="textarea" :rows="3" />
        </el-form-item>

      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import request from "@/utils/request";
import { ElMessage, ElMessageBox } from "element-plus";
import { Plus } from '@element-plus/icons-vue'

// 数据定义
const tableData = ref([])
const loading = ref(false)
const searchName = ref('')
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)

const dialogVisible = ref(false)
const title = ref('新增品牌')
const formRef = ref()

const form = reactive({
  id: null,
  brandName: '',
  brandCode: '',
  brandLogo: '',
  description: '',
  isEnabled: 1
})

const rules = {
  brandName: [{ required: true, message: '请输入品牌名称', trigger: 'blur' }],
  brandCode: [{ required: true, message: '请输入品牌编码', trigger: 'blur' }]
}

// 加载数据
const load = () => {
  loading.value = true
  request.get('/brand/page', {
    params: {
      pageNum: pageNum.value,
      pageSize: pageSize.value,
      name: searchName.value
    }
  }).then(res => {
    loading.value = false
    if (res.code === 200) {
      tableData.value = res.data.records
      total.value = res.data.total
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 初始化
onMounted(() => {
  load()
})

// 分页切换
const handlePageChange = (val) => {
  pageNum.value = val
  load()
}

// 新增
const handleAdd = () => {
  title.value = '新增品牌'
  dialogVisible.value = true
  form.id = null
  form.brandName = ''
  form.brandCode = ''
  form.brandLogo = ''
  form.description = ''
  if(formRef.value) formRef.value.clearValidate()
}

// 编辑
const handleEdit = (row) => {
  title.value = '编辑品牌'
  dialogVisible.value = true
  Object.assign(form, row)
  if(formRef.value) formRef.value.clearValidate()
}

// 保存
const save = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      const method = form.id ? 'put' : 'post'
      request[method]('/brand', form).then(res => {
        if (res.code === 200) {
          ElMessage.success('保存成功')
          dialogVisible.value = false
          load()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

// 删除
const del = (id) => {
  ElMessageBox.confirm('确定要删除该品牌吗？', '提示', { type: 'warning' }).then(() => {
    request.delete('/brand/' + id).then(res => {
      if (res.code === 200) {
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  })
}

// 上传成功回调
const handleAvatarSuccess = (res) => {
  // 假设后端返回结构是 {code: 200, data: "http://..."}
  // 这里要注意根据你的 FileController 实际返回来写
  if (res.code === '200' || res.code === 200) {
    form.brandLogo = res.data
    ElMessage.success('图片上传成功')
  } else {
    ElMessage.error('上传失败')
  }
}

// 上传前校验
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('图片必须是 JPG 或 PNG 格式!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('图片大小不能超过 2MB!')
    return false
  }
  return true
}
</script>

<style scoped>
/* 保持外层透明，配合 Manager.vue 的背景 */
.standard-page-container {
  height: 100%;
  width: 100%;
  background-color: transparent;
}
.table-card {
  margin-bottom: 20px;
  background: rgba(255, 255, 255, 0.95);
}

/* 上传组件样式 */
.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
  display: block;
  object-fit: cover;
  border-radius: 4px;
}
</style>

<style>
/* 全局样式覆盖 Element Upload，让它有个虚线框 */
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px; /* 垂直居中 */
}
</style>