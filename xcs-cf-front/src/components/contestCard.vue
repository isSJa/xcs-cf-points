<template>
  <el-tooltip
      class="box-item"
      effect="light"
      content="点击查看详情"
      placement="top"
  >
    <div class="bg" @click="$emit('showDetail',$props.contest)">
      <div class="title">
        {{ contest.name.length < 50 ? contest.name : contest.name.substr(0, 50) + "..." }}
        <span class="total" v-if="contest.total">
        <span style="font-size: 12px">TOTAL</span>
        <div>{{ contest.total }}</div>
      </span>
      </div>
      <div class="type" :style="{color:contest.type==='A'?'#3598da':'#00ace5'}">
        {{ contest.type }}
      </div>

      <div class="time">
        <div class="timeItem">正式比赛：{{ contest.time }}</div>
        <div class="timeItem">赛后补题：{{ after7() }}</div>
      </div>
      <!--    <div class="detail">-->
      <!--      <el-button type="default" @click="$emit('showDetail',$props.contest)">DETAIL</el-button>-->
      <!--    </div>-->
    </div>
  </el-tooltip>
</template>

<script>
import 'element-plus/dist/index.css'
import {ElTooltip} from "element-plus";

export default {
  name: "contestCard",
  props: ['contest'],
  components: {
    ElTooltip,
  },
  setup(props) {
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
    return {
      after7,
    }
  }
}
</script>

<style scoped lang="scss">
.bg:hover {
  transform: scale(105%);
}

.bg {
  background-color: #1ab6ff;
  width: 300px;
  height: 200px;
  border-radius: 10px;
  margin: 20px;
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

  .total {
    position: absolute;
    left: 270px;
    top: 0px;
    color: #1782c6;
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