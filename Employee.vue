<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <!--      <el-card>-->
      <!--        <el-input style="width: 240px" v-model="data.name" placeholder="请输入名称页面" prexfix-icon="Search"></el-input>-->
      <!--      </el-card>-->
      <el-input style="width: 240px; margin-right: 10px" v-model="data.name" placeholder="请输入名称" :prefix-icon="Search"></el-input>
      <el-button type="primary" @click="load">查 询</el-button>
      <el-button type="warning" @click="reset">重 置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-button type="primary" @click="handleAdd">新 增</el-button>
      <el-button type="danger" @click="delBatch">批量删除</el-button>
      <el-upload
          style="display: inline-block; margin: 0 10px"
          action="http://localhost:9090/user/import"
          :show-file-list="false"
          :on-success="importSuccess"
      >
        <el-button type="info">导入</el-button>
      </el-upload>
      <el-button type="success" @click="exportData">导出</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column label="账号" prop="username"/>
        <el-table-column label="头像">
          <template #default="scope">
            <img v-if="scope.row.avatar"  :src="scope.row.avatar" alt="" style="display: block; width: 40px; height: 40px; border-radius: 50%"/>
          </template>
        </el-table-column>
        <el-table-column label="名称" prop="name"/>
        <el-table-column label="员工职位" prop="role"/>
        <el-table-column label="性别">
          <template #default="scope">
            {{ scope.row.sex === 1 ? '男' : (scope.row.sex === 0 ? '女' : '未知') }}
          </template>
        </el-table-column>
        <!-- 修改：移除旧的‘性别’和‘年龄’列，用上面的新列替换 -->
        <el-table-column label="工号" prop="no"/>


        <el-table-column label="出生日期" prop="birthDate">
          <template #default="scope">
            {{ formatDate(scope.row.birthDate) }}
          </template>
        </el-table-column>
        <el-table-column label="个人介绍" prop="description" show-overflow-tooltip/>
        <el-table-column label="状态">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
              {{ scope.row.status === 1 ? '在职' : '离职' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template #default="scope">
            <el-button @click="handleUpdate(scope.row)" type="primary" :icon="Edit" circle></el-button>
            <el-button @click="del(scope.row.id)" type="danger" :icon="Delete" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 15px">
        <el-pagination
            @size-change="load"
            @current-change="load"
            v-model:current-page="data.pageNum"
            v-model:page-size="data.pageSize"
            :page-sizes="[5, 10, 15, 20]"
            background
            layout="total, sizes, prev, pager, next, jumper"
            :total="data.total"
        />
      </div>
    </div>

    <el-dialog title="管理员信息" v-model="data.formVisible" width="500" destroy-on-close>
      <el-form ref="formRef" :rules="data.rules" :model="data.form" label-width="80px" style="padding-right: 40px; padding-top: 20px">
        <el-form-item label="账号" prop="username">
          <el-input :disabled="data.form.id" v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item label="头像">
          <el-upload
              action="http://localhost:8080/files/upload"
              list-type="picture"
              :on-success="handleAvatarSuccess"
          >
            <el-button type="primary">上传头像</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"/>
        </el-form-item>
        <!-- 在名称表单项后添加角色字段 -->
        <el-form-item label="角色" prop="role">
          <el-select v-model="data.form.role" placeholder="请选择角色" style="width: 100%">
            <el-option label="管理员" value="admin"/>
            <el-option label="员工" value="employee"/>
            <el-option label="经理" value="manager"/>
            <!-- 根据实际情况添加更多选项 -->
          </el-select>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="data.form.sex"> <!-- 修改：绑定值改为数字 -->
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工号" prop="no">
          <el-input v-model="data.form.no" autocomplete="off" placeholder="请输入工号"/>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthDate"> <!-- 修改：替换年龄 -->
          <el-date-picker
              style="width: 100%"
              v-model="data.form.birthDate"
              type="date"
              value-format="YYYY-MM-DD"
              placeholder="请选择出生日期"
          />
        </el-form-item>
        <!-- 在个人介绍表单项前添加状态字段 -->
        <el-form-item label="状态">
          <el-radio-group v-model="data.form.status">
            <el-radio :label="1">在职</el-radio>
            <el-radio :label="0">离职</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="个人介绍">
          <el-input :rows="3" type="textarea" v-model="data.form.description" autocomplete="off" placeholder="请输入个人介绍"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.formVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">保 存</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import {Delete, Edit, Search} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
const data = reactive({
  name: null,
  tableData: [],
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  ids: [],
  rules: {
    username: [
      { required: true, message: '请输入账号', trigger: 'blur' }
    ],
    name: [
      { required: true, message: '请输入名称', trigger: 'blur' }
    ]
  }
})

const formRef = ref()

const exportData = () => {
  //导出数据 是通过流的形式下载 excel  打开流的链接，浏览器会自动帮我们下载文件
  window.open('http://localhost:8080/employee/export')
}

const load = () => {
  request.get('employee/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name
    }
  }).then(res => {
    data.tableData = res.data.list
    data.total = res.data.total
  })
}
load()

const handleAvatarSuccess = (res) => {
  console.log(res,data)
  data.form.avatar = res.data
}

const reset = () => {
  data.name = null
  load()
}


const handleAdd = () => {
  data.formVisible = true
  data.form = {}
}

const save = () => {  //在一个保存方法里面做两个操作，一个是新增，一个是编辑
  formRef.value.validate((valid) => {
    if (valid) {
      data.form.id ? update() : add()
    }
  })
}

const add = () => {
  request.post('employee/add', data.form).then(res => {   //新增的对象里面没有id
    if ( res.code === '200' ) {
      data.formVisible = false
      ElMessage.success( '新增成功' )
      load() //新增后一定要重新加载最新的数据
    } else {
      ElMessage.error( res.msg )
    }
  })
}

const update = () => {
  request.put('employee/update', data.form).then(res => {   //编辑的对象里面有id
    if ( res.code === '200' ) {
      data.formVisible = false
      ElMessage.success( '操作成功' )
      load() //新增后一定要重新加载最新的数据
    } else {
      ElMessage.error( res.msg )
    }
  })
}

const handleUpdate =(row) => {
  data.form = JSON.parse( JSON.stringify( row ) ) //深拷贝一个新的对象，用于编辑，这就不会影响行对象的数据了
  data.formVisible = true
}

const del = (id) => {
  ElMessageBox.confirm('删除数据后无法恢复，您确定删除吗？', '删除确认', { type: 'warning'}).then(()=>{   //确认删除，点击确定会执行then里面的代码
    request.delete('/employee/deleteById/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('删除成功')
        load() //删除后一定要重新加载最新的数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}

const handleSelectionChange = (rows) => {  //返回所有选中的行对象数组
  // 从选中的行数组里面去除所有行的id组成一个新的数组
  data.ids = rows.map(row => row.id)
  console.log(data.ids)
}

const delBatch = () => {
  if (data.ids.length === 0) {
    ElMessage.warning('请先选择要删除的数据')
    return
  }
  ElMessageBox.confirm('删除数据后无法恢复，您确定删除选中的数据吗？', '删除确认', { type: 'warning'}).then(()=>{   //确认删除，点击确定会执行then里面的代码
    request.delete('/employee/deleteBatch', { data: data.ids }).then(res => {
      if (res.code === '200') {
        ElMessage.success('删除成功')
        load() //删除后一定要重新加载最新的数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}
</script>