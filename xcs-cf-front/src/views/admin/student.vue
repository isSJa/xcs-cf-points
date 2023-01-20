<template>
  <!--  内容详情-->
  <div class="content">
    <!--  详情、删除和搜索框  -->
    <div class="flex">
      <!--  详情和删除  -->
      <div class="tools-bar">
        <el-button type="danger" @click="showAllDetail">所有详情</el-button>
        <!--        <el-button>一键删除</el-button>-->
      </div>
      <!--  搜索  -->
      <div class="search">
        <el-input
            v-model="searchValue"
            size="default"
            placeholder="请输入搜索内容(id、姓名、学号或cf账号)"
            style="width: 500px;"
            :suffix-icon="Search"
            @change="handleSearch"
        />
      </div>
    </div>
    <!--  表格内容  -->
    <div class="table-content">
      <el-table :data="user" stripe
                max-height="75vh" style="width: 100%"
                :header-cell-style="headStyle"
                :cell-style="rowStyle"
                @row-click="handleClick">
        <!--        排序-->
        <el-table-column type="index" width="100" label="rank" v-if="isShowRank"/>
        <!--        复选框-->
        <!--        <el-table-column type="selection" width="100"/>-->
        <!--        id-->
        <el-table-column prop="id" label="id" width="100"
                         sortable/>
        <!--        姓名-->
        <el-table-column prop="name" label="name" width="250"
                         sortable/>
        <!--        学号-->
        <el-table-column prop="sno" label="sno" width="300"
                         sortable/>
        <!--        账号-->
        <el-table-column prop="account" label="account" width="300"
                         sortable/>
        <!--        分数-->
        <el-table-column prop="score" label="score" width="300"
                         sortable/>
        <!--        操作-->
        <el-table-column label="operations">
          <template #default>
            <el-button type="success" size="small">detail</el-button>
            <el-button type="warning" size="small">edit</el-button>
            <el-button type="danger" size="small">delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--  分页  -->
    <div class="table-page">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="pageSizeArray"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
  <!--  某一个用户的详情-->
  <div class="detail">
    <el-dialog
        v-model="detailDialogVisible"
        :title="detailDialogTitle"
        width="800px">
      <div class="detailItem">
        <el-tag size="large">总分</el-tag>
        <div class="totalScore">{{ totalScore }}</div>
      </div>
      <div class="detailItem">
        <el-tag size="large">详情</el-tag>
        <el-table
            :data="detail"
            style="width: 100%;margin: 20px auto"
            :header-cell-style="headStyle"
            :cell-style="rowStyle">
          <el-table-column prop="cid" label="cid" width="100"/>
          <el-table-column prop="cname" label="contest" width="300"/>
          <el-table-column prop="anum" label="total-A" width="120"/>
          <el-table-column prop="during" label="during" width="120"/>
          <el-table-column prop="after" label="after" width="120"/>
        </el-table>
      </div>
    </el-dialog>
  </div>
  <!--  所有用户的详情-->
  <div class="allDetail">
    <el-dialog
        v-model="allDetailDialogVisible"
        :title="allDetailDialogTitle"
        width="800px"
        style="position: relative">
      <div v-for="d in allDetail" :key="d.uid" class="all-flex">
        <div class="uname">{{ d.uname }}</div>
        <div class="doList">
          <div v-for="(dd,index) in d.doList" :key="dd">
            <el-tooltip
                effect="light"
                :content="d.contestDetail[index]===null?'noA':'Anum:'+d.contestDetail[index].anum+'   ---   contest:'+d.contestDetail[index].cname"
                placement="top">
              <div :class="dd?'do':'notDo'"/>
            </el-tooltip>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
  <!--  编辑用户信息-->
  <div class="edit">
    <el-dialog
        v-model="editDialogVisible"
        :title="editDialogTitle"
        width="500px"
        style="position: relative;height: 280px">
      <el-form :model="userInfo" label-width="80px">
        <el-form-item label="name">
          <el-input v-model="userInfo.name"/>
        </el-form-item>
        <el-form-item label="sno">
          <el-input v-model="userInfo.sno"/>
        </el-form-item>
        <el-form-item label="account">
          <el-input v-model="userInfo.account"/>
        </el-form-item>
        <div style="position: absolute;right: 20px">
          <el-button @click="cancelEdit">取消</el-button>
          <el-button type="primary" @click="confirmEdit">确认</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
  <!--  删除用户-->
  <div class="delete">

  </div>
</template>

<script>
import {
  deleteStudent,
  getAllStudentContests,
  getStudentContests,
  getStudentInfo,
  getStudentPage,
  updateStudent
} from "@/api";
import {ref} from "vue";
import 'element-plus/dist/index.css'
import {
  ElButton,
  ElDialog,
  ElForm, ElFormItem,
  ElInput, ElMessage,
  ElPagination,
  ElTable,
  ElTableColumn,
  ElTag,
  ElTooltip,ElMessageBox,
  ElLoading
} from "element-plus";
import {Search} from '@element-plus/icons-vue'

export default {
  name: "show-rate",
  components: {
    ElTable,
    ElTableColumn,
    ElButton,
    ElPagination,
    ElInput,
    ElDialog,
    ElTag,
    ElTooltip,
    ElForm,
    ElFormItem,
  },
  setup() {
    const allUser = ref({})
    const user = ref({})
    const searchValue = ref('')
    const currentPage = ref(1)
    const pageSize = ref(15)
    const total = ref(0)
    const maxNum = ref(0)
    const pageSizeArray = ref([10, 15, 20])
    const isShowRank = ref(false)
    // 分页查询
    const getPage = () => {
      const value = searchValue.value === '' ? null : searchValue.value;
      getStudentPage(currentPage.value, pageSize.value, value).then(res => {
        user.value = res.data.data.records;
        total.value = res.data.data.total;
        if (total.value > maxNum.value)
          maxNum.value = total.value
        if (pageSizeArray.value.indexOf(maxNum.value) === -1) {
          pageSizeArray.value.push(maxNum.value)
        }
      })
    }
    // 初始化页面
    getPage()
    // 获取所有用户信息
    getStudentPage(1, 100).then(res => {
      allUser.value = res.data.data.records;
    })
    // 分页+模糊查询
    const handleSearch = () => {
      getPage()
    }
    // 分页相关操作
    const handleSizeChange = (val) => {
      pageSize.value = val;
      isShowRank.value = pageSize.value === maxNum.value;
      getPage()
    }
    const handleCurrentChange = (val) => {
      console.log(val)
      currentPage.value = val;
      getPage()
    }
    const headStyle = () => {
      return {'text-align': 'center'}
    }
    const rowStyle = () => {
      return {
        'text-align': 'center',
        'height': '50px',
        'font-size': '14px',
        'padding': '10px 0'
      }
    }
    // operate
    const handleClick = (row, column, event) => {
      // console.log(row)
      const type = event.target.outerText;
      if (type === 'edit') {
        editUser(row.id)
      } else if (type === 'detail') {
        showDetail(row.id);
      } else if (type === 'delete') {
        deleteUser(row.id)
      }
    }
    // 显示详情
    const totalScore = ref(0)//某一个用户的总分
    const detail = ref({})//某一个用户的得分详情
    const allDetail = ref({})
    const detailDialogVisible = ref(false)
    const detailDialogTitle = ref('')
    const allDetailDialogVisible = ref(false)
    const allDetailDialogTitle = ref('The Details of all people')
    // 显示所有用户详情
    const showAllDetail = () => {
      const loadingInstance = ElLoading.service({ fullscreen: true })
      getAllStudentContests().then(res => {
        loadingInstance.close()
        allDetail.value = res.data.data
        allDetailDialogVisible.value = true;
      })
    }
    // 显示某一个用户的详情
    const showDetail = (id) => {
      totalScore.value = allUser.value[id - 1].score
      detailDialogTitle.value = 'The Detail of ' +
          allUser.value[id - 1].account +
          " - " +
          allUser.value[id - 1].name +
          allUser.value[id - 1].sno;
      getStudentContests(id).then(res => {
        detailDialogVisible.value = true
        detail.value = res.data.data
      })
    }
    // 编辑
    const editDialogVisible = ref(false)
    const editDialogTitle = ref('')
    const userInfo = ref({})
    const editUser = id => {
      editDialogTitle.value = 'Edit ' +
          allUser.value[id - 1].account +
          " - " +
          allUser.value[id - 1].name +
          allUser.value[id - 1].sno;
      getStudentInfo(id).then(res => {
        editDialogVisible.value = true;
        userInfo.value = res.data.data
      })
    }
    const cancelEdit = () => {
      editDialogVisible.value = false
    }
    const showMsg=(res)=>{
      if (res.data.code === 200) {
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: 'success',
        })
        editDialogVisible.value = false
        getPage()
      } else {
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: 'error',
        })
      }
    }
    const confirmEdit = () => {
      updateStudent(JSON.stringify(userInfo.value)).then(res => {
        showMsg(res)
      })
    }
    // 删除用户
    const deleteUser = (id) => {
      ElMessageBox.confirm(
          '确认删除该社员所有信息?',
          '',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            deleteStudent(id).then(res=>{
              showMsg(res)
            })
          })
      console.log('delete')
    }
    return {
      currentPage,
      pageSize,
      total,
      pageSizeArray,
      user,
      handleSizeChange,
      handleCurrentChange,
      Search,
      searchValue,
      handleSearch,
      headStyle,
      rowStyle,
      showDetail,
      isShowRank,
      detailDialogVisible,
      detailDialogTitle,
      allDetailDialogVisible,
      allDetailDialogTitle,
      editDialogVisible,
      editDialogTitle,
      detail,
      allDetail,
      handleClick,
      totalScore,
      showAllDetail,
      userInfo,
      cancelEdit,
      confirmEdit
    }
  }
}
</script>

<style scoped lang="scss">
.content {
  padding: 0 10px;
  width: 90vw;
  margin: 0 auto;
  border-radius: 10px;
  text-align: center;

  .flex {
    display: flex;
    align-content: center;
    justify-content: space-between;
  }

  .search {
    padding: 5px 10px;
  }

  .search-tag {
    padding: 5px 10px;
  }

  .tools-bar {
    padding: 5px 10px;
  }

  .table-page {
    margin: 20px;
    position: fixed;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
  }
}

.detail {
  font-weight: bold;

  .detailItem {
    margin-bottom: 20px;

    .totalScore {
      margin: 20px;
      font-size: 50px;
    }
  }
}

.allDetail {
  .all-flex {
    display: flex;
    padding: 0 50px;

    .uname {
      font-size: 14px;
      width: 60px;
      line-height: 30px;
    }

    .doList {
      display: flex;

      .do {

        background-color: #41b883;
      }

      .notDo {
        background-color: #909399;
      }

      .do, .notDo {
        width: 25px;
        height: 25px;
        margin: 3px;
        border-radius: 5px;
      }
    }
  }
}
</style>