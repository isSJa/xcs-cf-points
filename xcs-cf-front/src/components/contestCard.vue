<template>
  <el-tooltip
      effect="light"
      content="点击查看详情"
      placement="top"
  >
    <div class="bg" @click="$emit('showDetail',contest)">
      <div class="title">
        {{ contest.name.length < maxLen ? contest.name : contest.name.substr(0, maxLen) + "..." }}
      </div>
      <div class="delete" @click.stop="deleteContest" v-if="type==='admin'">
        <el-button type="danger">delete</el-button>
      </div>
      <div class="edit" @click.stop="editContest" v-if="type==='admin'">
        <el-button type="warning">edit</el-button>
      </div>
      <div class="total" v-if="contest.total">
        <span style="font-size: 12px">TOTAL</span>
        <div>{{ contest.total }}</div>
      </div>
      <div class="type" :style="{color:contest.type==='A'?'#3598da':'#00ace5'}">
        {{ contest.type }}
      </div>
      <div class="time">
        <div class="timeItem">正式比赛：{{ contest.time }}</div>
        <div class="timeItem">赛后补题：{{ after7() }}</div>
      </div>
    </div>
  </el-tooltip>
  <!--  编辑用户信息-->
  <div class="editDialog">
    <el-dialog
        v-model="editDialogVisible"
        :title="editDialogTitle"
        width="500px"
        style="position: relative;height: 490px"
        @keyup.enter="confirmEdit(formEditRef)">
      <div style="margin-bottom: 20px;line-height: 25px">
        注意修改比赛信息不会改变社员积分，若想让积分变化，请删除该比赛后重新录入
      </div>
      <el-form
          label-position="top"
          label-width="100px"
          style="max-width: 460px;margin: auto;position: relative;height: 400px"
          ref="formEditRef"
          :model="newContest"
          :rules="rulesEdit"
      >
        <!--  name  -->
        <el-form-item label="name" prop="name">
          <el-input v-model="newContest.name"/>
        </el-form-item>
        <!--  total  -->
        <el-form-item label="total" prop="total">
          <el-input v-model.number="newContest.total"/>
        </el-form-item>
        <!--  type  -->
        <el-form-item label="type" style="float: left" prop="type">
          <el-radio-group v-model="newContest.type">
            <el-radio label="A"/>
            <el-radio label="B"/>
          </el-radio-group>
        </el-form-item>
        <!--  date-picker  -->
        <el-form-item label="time" style="float: right" prop="time">
          <el-date-picker
              v-model="newContest.time"
              value-format="YYYY-MM-DD"
              type="date"
              placeholder="Pick a date"
          />
        </el-form-item>
        <div style="position: absolute;right: 10px;bottom: 100px">
          <el-button @click="cancelEdit">取消</el-button>
          <el-button type="primary" @click="confirmEdit(formEditRef)">确认</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script lang="ts">
/* eslint-disable */
import 'element-plus/dist/index.css'
import {
  ElButton, ElDatePicker,
  ElDialog,
  ElForm,
  ElFormItem,
  ElInput,
  ElMessage,
  ElMessageBox, ElRadio, ElRadioGroup,
  ElTooltip
} from "element-plus";
import {removeContest, updateContest} from "@/api";
import {reactive, ref} from "vue";
import type {FormInstance, FormRules} from 'element-plus'

export default {
  name: "contestCard",
  props: ['contest', 'type'],
  components: {
    ElTooltip,
    ElButton,
    ElDialog,
    ElForm,
    ElFormItem,
    ElInput,
    ElRadio,
    ElRadioGroup,
    ElDatePicker,
  },
  setup(props: { contest: { time: string,id:number } }, {emit}: { emit: any }) {
    const maxLen = 40;
    // 计算赛后补题（7天后）的日期
    const after7 = () => {
      const date = new Date(props.contest.time)
      const date2 = new Date();
      date2.setTime(date.getTime() + (7 * 24 * 60 * 60 * 1000));
      const year = date2.getFullYear();
      let month: number | string = date2.getMonth() + 1;
      let day: number | string = date2.getDate();
      if (month.toString().length < 2) month = '0' + month
      if (day.toString().length < 2) day = '0' + day;
      return [year, month, day].join('-')
    }
    // 显示提示框
    const showMsg = (res: any) => {
      if (res.data.code === 200) {
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: 'success',
        })
        editDialogVisible.value = false
        emit('flush')
      } else {
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: 'error',
        })
      }
    }
    // 删除比赛
    const deleteContest = () => {
      ElMessageBox.confirm(
          '确认删除该比赛?',
          '',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
          }
      ).then(() => {
        removeContest(props.contest.id).then(res => {
          showMsg(res)
        })
      })
    }
    // 修改比赛
    const editDialogVisible = ref(false)
    const editDialogTitle = ref('')
    const newContest: any = reactive({})
    Object.assign(newContest, props.contest)
    const editContest = () => {
      editDialogTitle.value = 'Edit ' + newContest.name
      editDialogVisible.value = true
    }
    const cancelEdit = () => {
      editDialogVisible.value = false
    }
    const formEditRef = ref<FormInstance>()
    const rulesEdit = reactive<FormRules>({
      name: [
        {required: true},
      ],
      type: [
        {required: true}
      ],
      time: [
        {required: true}
      ],
      total: [
        {required: true},
        {type: 'number', message: 'total must be a number'}]
    })
    const confirmEdit = async (formEl: FormInstance | undefined) => {
      if (!formEl) return
      await formEl.validate(valid => {
        if (valid) {
          console.log(newContest)
          const json = JSON.stringify(newContest)
          console.log(json)
          updateContest(json).then(res => {
            showMsg(res)
          })
        }
      })
    }

    return {
      after7,
      maxLen,
      deleteContest,
      newContest,
      editDialogVisible,
      editDialogTitle,
      editContest,
      cancelEdit,
      confirmEdit,
      formEditRef,
      rulesEdit
    }
  }
}
</script>

<style scoped lang="scss">
.bg:hover {
  transform: scale(105%);
}

.bg {
  box-shadow: rgba(0, 0, 0, 0.24) 0 3px 8px;
  background-color: #1ab6ff;
  width: 300px;
  height: 200px;
  border-radius: 10px;
  margin: 15px;
  padding: 20px;
  font-weight: bold;
  position: relative;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.5s;

  .title {
    color: #e7f1ff;
    font-size: 25px;
    width: 90%;
  }

  .type {
    font-size: 120px;
    position: absolute;
    right: 20px;
    top: 100px;
    opacity: 0.9
  }

  .delete {
    font-size: 12px;
    display: inline-block;
    position: absolute;
    top: 90px;
    right: 10px;
    z-index: 999;
  }

  .edit {
    font-size: 12px;
    display: inline-block;
    position: absolute;
    top: 90px;
    right: 85px;
    z-index: 999;
  }

  .total {
    position: absolute;
    left: 270px;
    top: 0;
    color: #1782c6;
    font-size: 25px;
    margin-left: 20px;
    text-align: center;
  }

  .time {
    margin-top: 20px;
    position: absolute;
    left: 13px;
    bottom: 23px;

    .timeItem {
      margin-top: 10px;
    }
  }

  .detail {
    position: absolute;
    right: 30px;
    top: 190px;
  }
}
</style>