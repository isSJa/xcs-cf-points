<template>
  <div class="content">
    <!--  删除和搜索框  -->
    <div class="flex">
      <!--  删除  -->
      <div class="tools-bar">
        <el-button type="info">所有详情</el-button>
        <el-button>一键删除</el-button>
      </div>
      <!--  搜索  -->
      <div class="search">
        <el-input
            v-model="searchValue"
            size="default"
            placeholder="请输入搜索内容(姓名、学号或cf账号)"
            style="width: 500px;"
            :suffix-icon="Search"
            @blur="handleSearch"
        />
      </div>
    </div>
    <!--  表格内容  -->
    <div class="table-content">
      <el-table :data="user" stripe
                max-height="75vh" style="width: 100%"
                :header-cell-style="headStyle"
                :cell-style="rowStyle">
        <!--        排序-->
        <el-table-column type="index" width="80" label="rank" v-if="isShowRank"/>
        <!--        复选框-->
        <el-table-column type="selection" width="100"/>
        <!--        id-->
        <el-table-column prop="id" label="id" width="100"
                         sortable/>
        <!--        姓名-->
        <el-table-column prop="name" label="name" width="250"
                         sortable/>
        <!--        学号-->
        <el-table-column prop="sno" label="sno" width="250"
                         sortable/>
        <!--        账号-->
        <el-table-column prop="account" label="account" width="250"
                         sortable/>
        <!--        分数-->
        <el-table-column prop="score" label="score" width="250"
                         sortable/>
        <!--        操作-->
        <el-table-column label="operations">
          <template #default>
            <el-button type="warning" size="small">edit</el-button>
            <el-button type="success" size="small" @click="showDetail">detail</el-button>
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

</template>

<script>
import {getUserPage} from "@/api";
import {ref} from "vue";
import 'element-plus/dist/index.css'
import {ElButton, ElInput, ElPagination, ElTable, ElTableColumn, ElTag} from "element-plus";
import {Search} from '@element-plus/icons-vue'

export default {
  name: "show-rate",
  components: {
    ElTable,
    ElTableColumn,
    ElButton,
    ElPagination,
    ElInput,
  },
  setup() {
    const user = ref({})
    const searchValue = ref('')
    const currentPage = ref(1)
    const pageSize = ref(15)
    const total = ref(0)
    const maxNum=ref(0)
    const pageSizeArray = ref([10, 15, 20])
    const isShowRank=ref(false)
    // 分页查询
    const getPage = () => {
      const value = searchValue.value === '' ? null : searchValue.value;
      getUserPage(currentPage.value, pageSize.value, value).then(res => {
        user.value = res.data.data.records;
        total.value = res.data.data.total;
        if(total.value>maxNum.value)
          maxNum.value=total.value
        if (pageSizeArray.value.indexOf(maxNum.value) === -1) {
          pageSizeArray.value.push(maxNum.value)
        }
      })
    }
    // 初始化页面
    getPage()
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
        'font-size':'14px',
        'padding':'10px 0'
      }
    }
    const showDetail=(val)=>{
      console.log(val)
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

  .table-content {

  }

  .table-page {
    margin: 20px;
    position: fixed;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
  }
}

</style>