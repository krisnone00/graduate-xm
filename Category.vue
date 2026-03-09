<template>
  <div class="standard-page-container">
    <el-card shadow="never" class="table-card">
      <el-button type="primary" @click="handleAdd(null)">+ 新增根分类</el-button>
      <el-button @click="load">↻ 刷新</el-button>
    </el-card>

    <el-card shadow="never" class="table-card">
      <el-table
          :data="tableData"
          style="width: 100%"
          row-key="id"
          border
          stripe
          default-expand-all
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column prop="categoryName" label="分类名称" />
        <el-table-column prop="categoryCode" label="分类编码" />
        <el-table-column prop="sortOrder" label="排序" align="center" />

        <el-table-column prop="isEnabled" label="状态" align="center">
          <template #default="scope">
            <el-tag :type="scope.row.isEnabled === 1 ? 'success' : 'danger'">
              {{ scope.row.isEnabled === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="280" align="center">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="handleAdd(scope.row)">新增子分类</el-button>
            <el-button link type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="danger" size="small" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog
        v-model="dialogVisible"
        :title="title"
        width="500px"
        append-to-body
        :close-on-click-modal="false"
    >
      <el-form :model="form" label-width="100px" ref="formRef" :rules="rules">
        <el-form-item label="父级分类">
          <span v-if="form.parentId === 0" style="color: #999">根分类</span>
          <span v-else style="color: #409EFF">父级ID: {{ form.parentId }}</span>
        </el-form-item>
        <el-form-item label="分类名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="分类编码" prop="categoryCode">
          <el-input v-model="form.categoryCode" placeholder="请输入编码" />
        </el-form-item>
        <el-form-item label="排序" prop="sortOrder">
          <el-input-number v-model="form.sortOrder" :min="0" />
        </el-form-item>
        <el-form-item label="状态" prop="isEnabled">
          <el-radio-group v-model="form.isEnabled">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">禁用</el-radio>
          </el-radio-group>
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

const tableData = ref([])
const dialogVisible = ref(false)
const title = ref('新增分类')
const formRef = ref()

const form = reactive({
  id: null, categoryName: '', categoryCode: '', parentId: 0, sortOrder: 0, isEnabled: 1
})

const rules = {
  categoryName: [{ required: true, message: '请输入分类名称', trigger: 'blur' }],
  categoryCode: [{ required: true, message: '请输入分类编码', trigger: 'blur' }]
}

const load = () => {
  request.get('/category/tree').then(res => {
    if (res.code === 200) tableData.value = res.data
    else ElMessage.error(res.msg)
  })
}

onMounted(() => load())

const handleAdd = (row) => {
  title.value = row ? '新增子分类' : '新增根分类'
  dialogVisible.value = true
  form.id = null; form.categoryName = ''; form.categoryCode = ''; form.sortOrder = 0; form.isEnabled = 1; form.parentId = row ? row.id : 0
  if(formRef.value) formRef.value.clearValidate()
}

const handleEdit = (row) => {
  title.value = '编辑分类'
  dialogVisible.value = true
  Object.assign(form, row)
  if(formRef.value) formRef.value.clearValidate()
}

const save = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      const method = form.id ? 'put' : 'post'
      request[method]('/category', form).then(res => {
        if (res.code === 200) { ElMessage.success('操作成功'); dialogVisible.value = false; load() }
        else ElMessage.error(res.msg)
      })
    }
  })
}

const del = (id) => {
  ElMessageBox.confirm('确定删除该分类及其子分类吗？', '提示', { type: 'warning' }).then(() => {
    request.delete('/category/' + id).then(res => {
      if (res.code === 200) { ElMessage.success('删除成功'); load() }
      else ElMessage.error(res.msg)
    })
  })
}
</script>

<style scoped>
/* 🌟 核心：只在这里定义白色背景，不影响外面的赛博边框 */
.standard-page-container {
  height: 100%;
  width: 100%;
  background-color: transparent; /* 透明，透出底部的网格 */
}

.table-card {
  margin-bottom: 20px;
  /* 卡片稍微透明一点，或者纯白，看你喜好 */
  background: rgba(255, 255, 255, 0.95);
}
</style>