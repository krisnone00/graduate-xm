<template>
  <div style="padding: 20px;">
    <el-card shadow="never" style="margin-bottom: 20px;">
      <div style="display: flex; gap: 10px;">
        <el-input v-model="queryParams.keyword" placeholder="搜名称或联系人" style="width: 200px;" clearable @clear="load" @keyup.enter="load" />
        <el-button type="primary" @click="load">查询</el-button>
        <el-button type="success" @click="handleAdd">新增供应商</el-button>
      </div>
    </el-card>

    <el-card shadow="never">
      <el-table :data="tableData" border stripe v-loading="loading">
        <el-table-column prop="supplierCode" label="编码" width="120" />
        <el-table-column prop="supplierName" label="供应商名称" min-width="150" />
        <el-table-column prop="supplierTypeName" label="类型" width="100" />
        <el-table-column prop="contactPerson" label="联系人" width="100" />
        <el-table-column prop="contactPhone" label="电话" width="120" />
        <el-table-column prop="statusName" label="状态" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">{{ scope.row.statusName }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="updatedTime" label="更新时间" width="180" />
        <el-table-column label="操作" width="180" align="center">
          <template #default="scope">
            <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin-top: 20px; text-align: right;">
        <el-pagination
            background layout="prev, pager, next, total"
            :total="total"
            :current-page="queryParams.pageNum"
            :page-size="queryParams.pageSize"
            @current-change="handlePageChange" />
      </div>
    </el-card>

    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑供应商' : '新增供应商'" width="500px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="供应商名称" required>
          <el-input v-model="form.supplierName"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="form.supplierType" style="width: 100%">
            <el-option label="生产商" :value="1" />
            <el-option label="批发商" :value="2" />
            <el-option label="代理商" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="form.contactPerson"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="form.contactPhone"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status" :active-value="1" :inactive-value="0" active-text="启用" inactive-text="禁用"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submit">确认</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getSupplierPage, saveSupplier, deleteSupplier } from '@/api/supplier'
import { ElMessage, ElMessageBox } from 'element-plus'

const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const dialogVisible = ref(false)
const queryParams = reactive({ pageNum: 1, pageSize: 10, keyword: '' })
const form = ref({})

const load = async () => {
  loading.value = true
  const res = await getSupplierPage(queryParams)
  if(res.code === 200) {
    tableData.value = res.data.records
    total.value = res.data.total
  }
  loading.value = false
}

const handlePageChange = (val) => {
  queryParams.pageNum = val
  load()
}

const handleAdd = () => {
  form.value = { status: 1, supplierType: 1 }
  dialogVisible.value = true
}

const handleEdit = (row) => {
  form.value = JSON.parse(JSON.stringify(row))
  dialogVisible.value = true
}

const submit = async () => {
  const res = await saveSupplier(form.value)
  if(res.code === 200) {
    ElMessage.success('保存成功')
    dialogVisible.value = false
    load()
  } else {
    ElMessage.error(res.msg)
  }
}

const handleDelete = (id) => {
  ElMessageBox.confirm('确认删除?', '提示', { type: 'warning' }).then(async () => {
    const res = await deleteSupplier(id)
    if(res.code === 200) { ElMessage.success('删除成功'); load() }
  })
}

onMounted(() => load())
</script>