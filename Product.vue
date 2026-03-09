<template>
  <div style="padding: 20px;">
    <div style="margin-bottom: 15px;">
      <el-input v-model="queryParams.name" placeholder="商品名称搜索" style="width: 200px" clearable @keyup.enter="load" @clear="load"/>
      <el-button type="primary" style="margin-left: 10px" @click="load">查询</el-button>
      <el-button type="success" @click="handleAdd">新增商品</el-button>
    </div>

    <el-table :data="tableData" border stripe v-loading="loading">
      <el-table-column prop="id" label="ID" width="70" sortable />
      <el-table-column prop="productName" label="商品名称" />
      <el-table-column prop="brandName" label="品牌" />
      <el-table-column prop="categoryName" label="分类" />
      <el-table-column prop="price" label="价格(元)" width="100" />
      <el-table-column prop="stock" label="库存" width="100" />
      <el-table-column prop="updateTime" label="最后更新时间" width="180" />
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 20px; text-align: right;">
      <el-pagination
          background
          layout="prev, pager, next, total"
          :total="total"
          :current-page="queryParams.pageNum"
          :page-size="queryParams.pageSize"
          @current-change="handlePageChange"
      />
    </div>

    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑商品' : '新增商品'" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="form.productName"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input-number v-model="form.price" :precision="2" :step="0.1"></el-input-number>
        </el-form-item>
        <el-form-item label="库存">
          <el-input-number v-model="form.stock" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.description"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
// ✨ 核心变化：引入 API 方法，不再直接引入 request
import { getProductPage, saveProduct, deleteProduct } from '@/api/product'

// --- 数据定义 ---
const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const dialogVisible = ref(false)

// 建议：把查询参数封装到一个对象里，比散着放更规范
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  name: ''
})

const form = ref({})

// --- 方法定义 ---

// 1. 加载数据
const load = async () => {
  loading.value = true
  try {
    // ✨ 核心变化：直接调用 API 函数，代码语义化更强
    const res = await getProductPage(queryParams)
    if (res.code === 200 || res.code === '200') { // 兼容一下你的后端可能返回字符串或数字
      tableData.value = res.data.records
      total.value = res.data.total
    }
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

// 翻页
const handlePageChange = (pageNum) => {
  queryParams.pageNum = pageNum
  load()
}

// 打开新增
const handleAdd = () => {
  form.value = { stock: 0 } // 重置表单
  dialogVisible.value = true
}

// 打开编辑
const handleEdit = (row) => {
  form.value = JSON.parse(JSON.stringify(row)) // 深拷贝
  dialogVisible.value = true
}

// 2. 保存 (新增/修改)
const save = async () => {
  const res = await saveProduct(form.value)
  if (res.code === 200 || res.code === '200') {
    ElMessage.success('保存成功')
    dialogVisible.value = false
    load()
  } else {
    ElMessage.error(res.msg)
  }
}

// 3. 删除
const handleDelete = (id) => {
  ElMessageBox.confirm('确定删除该商品吗？', '提示', { type: 'warning' }).then(async () => {
    const res = await deleteProduct(id)
    if (res.code === 200 || res.code === '200') {
      ElMessage.success('删除成功')
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 初始化
onMounted(() => {
  load()
})
</script>

<style scoped>
/* 你的样式 */
</style>