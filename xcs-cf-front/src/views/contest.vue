<template>
  <div class="contests">
    <div v-for="contest in contests" :key="contest.id">
      <contest-card :contest="contest" @showDetail="showDetail"/>
    </div>
  </div>
  <el-dialog
      v-model="dialogVisible"
      :title="title"
      width="800px">
    <div class="detail">
      <div class="detailItem">
        <el-tag class="subTitle" size="large">DuringContest</el-tag>
        <el-table :data="detailDuring" style="width: 100%">
          <el-table-column prop="id" label="id" width="100" />
          <el-table-column prop="name" label="name" width="150" />
          <el-table-column prop="score" label="score" width="100"/>
        </el-table>
      </div>
      <div class="detailItem">
        <el-tag class="subTitle" size="large">AfterContest</el-tag>
        <el-table :data="detailAfter" style="width: 100%">
          <el-table-column prop="id" label="id" width="100" />
          <el-table-column prop="name" label="name" width="150" />
          <el-table-column prop="score" label="score" width="100" />
        </el-table>
      </div>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">OK</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import contestCard from "@/components/contestCard.vue";
import {ref} from "vue";
import {getContest, getContestAfterDetail, getContestDuringDetail} from "@/api";
import 'element-plus/dist/index.css'
import {ElButton, ElDialog, ElTable, ElTableColumn, ElTag} from "element-plus";

export default {
  name: "contest-contest",
  components: {
    contestCard,
    ElDialog,
    ElButton,
    ElTag,
    ElTable,
    ElTableColumn,
  },
  setup() {
    const contests = ref({})
    getContest().then(res => {
      contests.value = res.data.data
    })
    const dialogVisible = ref(false)
    const title=ref('')
    const detailDuring = ref([])
    const detailAfter = ref([])
    const showDetail = (score) => {
      dialogVisible.value=true
      title.value="Detail of "+score.name
      getContestDuringDetail(score.name).then(res => {
        detailDuring.value = Object.values(res.data.data)
        detailDuring.value.sort((a,b)=>{
          return a.id-b.id
        })
      })
      getContestAfterDetail(score.name).then(res => {
        detailAfter.value = Object.values(res.data.data)
        detailAfter.value.sort((a,b)=>{
          return a.id-b.id
        })
      })
    }
    return {
      contests,
      showDetail,
      detailDuring,
      detailAfter,
      dialogVisible,
      title,
    }
  }
}
</script>

<style scoped lang="scss">
.contests {
  width: 90%;
  margin: 0 auto;
  display: flex;
  //justify-content: center;
  align-content: space-between;
  flex-wrap: wrap;
}
.detail{
  display: flex;
  .detailItem{
    font-weight: bold;
    margin: 0 20px;
    width: 400px;
    .subTitle{
      margin: 0 auto 20px;
    }
  }
}
</style>