<template>
  <div class="bg">
    <div class="title">
      {{ contest.name }}
    </div>
    <div class="type" :style="{color:contest.type==='A'?'#d95e4a':'#f0be07'}">
      {{ contest.type }}
    </div>
    <div class="time">
      <div class="timeItem">正式比赛：{{ contest.time }}</div>
      <div class="timeItem">赛后补题：{{ after7() }}</div>
    </div>
    <div class="detail">
      <el-button type="default" @click="$emit('showDetail',$props.contest)">DETAIL</el-button>
    </div>
  </div>
</template>

<script>
import {ElButton} from "element-plus";
import {getContestAfterDetail, getContestDuringDetail} from "@/api";
import {ref} from "vue";

export default {
  name: "contestCard",
  props: ['contest'],
  components: {
    ElButton,
  },
  setup(props) {
    const after7=()=>{
      const date=new Date(props.contest.time)
      const date2=new Date();
      date2.setTime(date.getTime()+(7*24*60*60*1000));
      const year=date2.getFullYear();
      let month=date2.getMonth()+1;
      let day=date2.getDate();
      if(month.toString().length<2)month='0'+month
      if(day.toString().length<2)day='0'+day;
      return [year,month,day].join('-')
    }
    return {
      after7,
    }
  }
}
</script>

<style scoped lang="scss">
.bg {
  background-color: #409eff;
  width: 300px;
  height: 200px;
  border-radius: 10px;
  margin: 20px;
  padding: 20px;
  font-weight: bold;
  position: relative;
  overflow: hidden;

  .title {
    color: #c3dfff;
    font-size: 25px;
  }

  .type {
    font-size: 100px;
    position: absolute;
    right: -24px;
    top: 28px;
  }

  .time {
    margin-top: 20px;
    position: absolute;
    left: 13px;
    bottom: 23px;
    .timeItem{
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