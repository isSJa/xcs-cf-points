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
        <el-button>删除比赛</el-button>
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
</template>

<script>
import 'element-plus/dist/index.css'
import {ElButton, ElMessage, ElMessageBox, ElTooltip} from "element-plus";
import {removeContest} from "@/api";

export default {
  name: "contestCard",
  props: ['contest', 'type'],
  components: {
    ElTooltip,
    ElButton,
  },
  setup(props,{emit}) {
    const maxLen=40;
    // 计算赛后补题（7天后）的日期
    const after7 = () => {
      const date = new Date(props.contest.time)
      const date2 = new Date();
      date2.setTime(date.getTime() + (7 * 24 * 60 * 60 * 1000));
      const year = date2.getFullYear();
      let month = date2.getMonth() + 1;
      let day = date2.getDate();
      if (month.toString().length < 2) month = '0' + month
      if (day.toString().length < 2) day = '0' + day;
      return [year, month, day].join('-')
    }
    // 删除比赛
    const deleteContest=()=>{
      ElMessageBox.confirm(
          '确认删除该比赛?',
          '',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
          }
      ).then(() => {
        removeContest(props.contest.id).then(res=>{
          let type
          if(res.data.code===200){
            type='success'
          }else{
            type='error'
          }
          ElMessage({
            showClose:true,
            message:res.data.msg,
            type:type
          })
          emit('flush')
        })
      })
    }
    return {
      after7,
      maxLen,
      deleteContest
    }
  }
}
</script>

<style scoped lang="scss">
.bg:hover {
  transform: scale(105%);
}

.bg {
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
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
  .delete{
    font-size: 12px;
    display: inline-block;
    position: absolute;
    top: 90px;
    right: 20px;
    z-index: 999;
  }

  .total {
    position: absolute;
    left: 270px;
    top: 0px;
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