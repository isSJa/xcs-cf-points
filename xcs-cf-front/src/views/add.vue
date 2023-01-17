<template>
  <div class="add-bg">
    <!--  ADD SCORES  -->
    <div class="add" style="background: rgba(255,255,92,0.5)">
      <div class="title" style="color: #fffccb">ADD SCORE</div>
      <el-form
          label-position="top"
          label-width="100px"
          :model="formScores"
          style="max-width: 460px;margin: auto"
      >
        <el-form-item label="uAccount">
          <el-input v-model="formScores.uAccount"/>
        </el-form-item>
        <el-form-item label="cName">
          <el-input v-model="formScores.cName"/>
        </el-form-item>
        <el-form-item label="isDuring">
          <el-switch v-model="formScores.isDuring"/>
        </el-form-item>
        <el-form-item label="isAContest">
          <el-switch v-model="formScores.isAContest"/>
        </el-form-item>
        <el-form-item label="A - total">
          <el-col :span="11">
            <el-input v-model="formScores.aNum" placeholder="A"/>
          </el-col>
          <el-col :span="2" class="text-center">
            <span class="text-gray-500">-</span>
          </el-col>
          <el-col :span="11">
            <el-input v-model="formScores.total" placeholder="total"/>
          </el-col>
        </el-form-item>
      </el-form>
      <el-button style="margin: 20px" @click="handleAddScore">ADD SCORE</el-button>
    </div>
    <!--  ADD USER  -->
    <div class="add" style="background: rgba(33, 168, 109,0.5)">
      <div class="title" style="color: #97fcd2">ADD USER</div>
      <el-form
          label-position="top"
          label-width="100px"
          :model="formUser"
          style="max-width: 460px;margin: auto"
      >
        <el-form-item label="name">
          <el-input v-model="formUser.name"/>
        </el-form-item>
        <el-form-item label="sno">
          <el-input v-model="formUser.sno"/>
        </el-form-item>
        <el-form-item label="account">
          <el-input v-model="formUser.account"/>
        </el-form-item>
      </el-form>
      <el-button style="margin: 20px" @click="handleAddUser">ADD USER</el-button>
    </div>
    <!--  ADD CONTEST  -->
    <div class="add" style="background: rgba(255,99,99,0.7);position: relative">
      <div class="title" style="color: #fadedd">ADD CONTEST</div>
      <el-form
          label-position="top"
          label-width="100px"
          :model="formContest"
          style="max-width: 460px;margin: auto;position: relative;height: 240px"
      >
        <!--  name  -->
        <el-form-item label="name">
          <el-input v-model="formContest.name"/>
        </el-form-item>
        <!--  type  -->
        <el-form-item label="type" style="float: left">
          <el-radio-group v-model="formContest.type">
            <el-radio label="A"/>
            <el-radio label="B"/>
          </el-radio-group>
        </el-form-item>
        <!--  date-picker  -->
        <el-form-item label="time" style="float: right">
          <el-date-picker
              v-model="formContest.time"
              value-format="YYYY-MM-DD"
              type="date"
              placeholder="Pick a date"
          />
        </el-form-item>
      </el-form>
      <el-button
          style="position: absolute;bottom: 14%;left: 50%;transform: translateX(-50%)"
          @click="handleAddContest">
        ADD CONTEST
      </el-button>
    </div>
  </div>
</template>

<script>
import 'element-plus/dist/index.css'
import {
  ElButton,
  ElCol,
  ElDatePicker,
  ElForm,
  ElFormItem,
  ElInput, ElMessage,
  ElRadio,
  ElRadioGroup,
  ElSwitch
} from "element-plus";
import {reactive} from "vue";
import http from "@/utils/http";
import {addContest, addScore, addUser} from "@/api";

export default {
  name: "add-user-contest",
  components: {
    ElButton,
    ElForm,
    ElFormItem,
    ElInput,
    ElRadioGroup,
    ElRadio,
    ElDatePicker,
    ElSwitch,
    ElCol,
  },
  setup() {
    // 添加分数
    const formScores = reactive({
      uAccount: '',
      cName: '',
      isDuring: false,
      isAContest: false,
      score: '',
      total: '',
      aNum: '',
    });
    const handleAddScore = () => {
      // 算分
      let aNum = Number(formScores.aNum);
      let total = Number(formScores.total);
      let score = 0;
      if (formScores.isDuring) {
        score = aNum * 50;
        if (aNum >= 1) score += 150;
        if (aNum >= Math.floor(total * 2 / 3)) score += 300
        if (aNum === total) score += 750
      } else {
        if (aNum >= Math.floor(total * 2 / 3)) score += 100
        if (aNum === total) score += 150
      }
      if (formScores.isAContest === true) score *= 2
      // 计入
      formScores.score = score
      let json = JSON.stringify(formScores);
      // 写入数据库
      addScore(json).then(res => {
        let type = ''
        if (res.data.code === 200) {
          type = 'success'
        } else {
          type = 'error'
        }
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: type,
        })
      })
      // 归零
      formScores.score = 0;
    }
    // 添加社员
    const formUser = reactive({
      name: '',
      sno: '',
      account: ''
    })
    const handleAddUser = () => {
      let json = JSON.stringify(formUser);
      addUser(json).then(res => {
        let type = ''
        if (res.data.code === 200) {
          type = 'success'
        } else {
          type = 'error'
        }
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: type,
        })
      })
    }
    // 添加比赛
    const formContest = reactive({
      name: '',
      type: '',
      time: ''
    });
    const handleAddContest = () => {
      // 改了日期的value-format就不需要下面这句话了
      // formContest.time = new Date(formContest.time)
      let json = JSON.stringify(formContest);
      addContest(json).then(res => {
        let type = ''
        if (res.data.code === 200) {
          type = 'success'
        } else {
          type = 'error'
        }
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: type,
        })
      })
      console.log(json)
    }
    return {
      formUser,
      formContest,
      formScores,
      handleAddScore,
      handleAddUser,
      handleAddContest
    }
  }
}
</script>

<style lang="scss">
.add-bg::before {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background: url(../assets/xcs-blue.png) center / cover no-repeat;
  filter: blur(10px);
  z-index: -1;
}
.add-bg {
  box-sizing: border-box;
  margin: 0;
  padding: 50px;
  //毛玻璃
  background: inherit;
  position: relative;
  .add {
    text-align: center;
    width: 60%;
    margin: 0 auto 50px;
    padding: 20px;
    border-radius: 10px;
    box-shadow: rgba(255, 255, 255, 0.35) 0px 5px 15px;
    .title {
      font-size: 25px;
      font-weight: bold;
    }

    .el-form-item__label {
      color: #fff;
      font-size: 16px;
    }

    .el-radio {
      color: #ffffff;
    }

    .el-radio__input.is-checked .el-radio__inner {
      border-color: #620000;
      background-color: #620000;
    }

    .el-radio__input.is-checked + .el-radio__label {
      color: #620000;
    }
  }
}

</style>