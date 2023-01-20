<template>
  <div class="add-bg">
    <!--  ADD SCORES  -->
    <div class="add" style="background: rgba(255, 185, 2,0.9)">
      <div class="title" style="color: #fffccb">ADD SCORE</div>
      <el-form
          label-position="top"
          label-width="100px"
          style="max-width: 460px;margin: auto"
          :model="formScores"
          :rules="ruleScore"
          ref="formScoreRef"
      >
        <el-form-item label="student account" prop="uAccount">
          <el-input v-model="formScores.uAccount"/>
        </el-form-item>
        <el-form-item label="contest name" prop="cName">
          <el-input v-model="formScores.cName"/>
        </el-form-item>
        <el-form-item label="'A' Num" prop="aNum">
          <el-input v-model.number="formScores.aNum"/>
        </el-form-item>
        <el-form-item label="isDuring" prop="isDuring">
          <el-switch v-model="formScores.isDuring"/>
        </el-form-item>
      </el-form>
      <el-button style="margin: 20px" @click="handleAddScore(formScoreRef)">ADD SCORE</el-button>
    </div>
    <!--  ADD USER  -->
    <div class="add" style="background: rgba(33, 168, 109,0.9)">
      <div class="title" style="color: #97fcd2">ADD USER</div>
      <el-form
          label-position="top"
          label-width="100px"
          style="max-width: 460px;margin: auto"
          ref="formUserRef"
          :model="formUser"
          :rules="rulesUser"
      >
        <el-form-item label="name" prop="name">
          <el-input v-model="formUser.name"/>
        </el-form-item>
        <el-form-item label="sno" prop="sno">
          <el-input v-model="formUser.sno"/>
        </el-form-item>
        <el-form-item label="account" prop="account">
          <el-input v-model="formUser.account"/>
        </el-form-item>
      </el-form>
      <el-button style="margin: 20px" @click="handleAddUser(formUserRef)">ADD USER</el-button>
    </div>
    <!--  ADD CONTEST  -->
    <div class="add" style="background: rgba(217, 94, 74,0.9);position: relative">
      <div class="title" style="color: #fadedd">ADD CONTEST</div>
      <el-form
          label-position="top"
          label-width="100px"
          style="max-width: 460px;margin: auto;position: relative;height: 310px"
          ref="formContestRef"
          :model="formContest"
          :rules="rulesContest"
      >
        <!--  name  -->
        <el-form-item label="name" prop="name">
          <el-input v-model="formContest.name"/>
        </el-form-item>
        <!--  total  -->
        <el-form-item label="total" prop="total">
          <el-input v-model.number="formContest.total"/>
        </el-form-item>
        <!--  type  -->
        <el-form-item label="type" style="float: left" prop="type">
          <el-radio-group v-model="formContest.type">
            <el-radio label="A"/>
            <el-radio label="B"/>
          </el-radio-group>
        </el-form-item>
        <!--  date-picker  -->
        <el-form-item label="time" style="float: right" prop="time">
          <el-date-picker
              v-model="formContest.time"
              value-format="YYYY-MM-DD"
              type="date"
              placeholder="Pick a date"
          />
        </el-form-item>
      </el-form>
      <el-button
          style="position: absolute;bottom: 10%;left: 50%;transform: translateX(-50%)"
          @click="handleAddContest(formContestRef)">
        ADD CONTEST
      </el-button>
    </div>
  </div>
</template>

<script lang="ts">
import 'element-plus/dist/index.css'
import {
  ElButton,
  ElDatePicker,
  ElForm,
  ElFormItem,
  ElInput, ElMessage,
  ElRadio,
  ElRadioGroup,
  ElSwitch
} from "element-plus";
import {ref, reactive} from "vue";
import {addContest, addScore, addUser} from "@/api";
import type {FormInstance, FormRules} from 'element-plus'


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
  },
  setup() {
    // 添加分数
    const formScores = reactive({
      uAccount: null,
      cName: null,
      aNum: null,
      isDuring: false,
    });
    const formScoreRef=ref<FormInstance>()
    const ruleScore=reactive<FormRules>({
      uAccount:[{
        required:true
      }],
      cName:[{
        required:true
      }],
      aNum:[{
        required:true
      },{
        type:"number",message:'aNum must be a number'
      }]
    })
    const handleAddScore = async (formEl:FormInstance|undefined) => {
      if(!formEl)return;
      await formEl.validate((valid)=>{
        if(valid){
          // // 计入
          let json = JSON.stringify(formScores);
          // console.log(json)
          addScore(json).then(res => {
            if (res.data.code === 200) {
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'success',
              })
            } else {
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'error',
              })
            }
          })
        }
      })
    }

    /**
     * 添加社员
     */
    const formUser = reactive({
      name: null,
      sno: null,
      account: null
    })
    const formUserRef = ref<FormInstance>()
    const rulesUser = reactive<FormRules>({
      name: [
        {required: true},
      ],
      sno: [
        {required: true},
      ],
      account: [
        {required: true},
      ]
    })
    const handleAddUser = async (formEl: FormInstance | undefined) => {
      if (!formEl) return
      await formEl.validate((valid) => {
        // 验证成功
        if (valid) {
          let json = JSON.stringify(formUser);
          // console.log(json)
          addUser(json).then(res => {
            if (res.data.code === 200) {
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'success',
              })
            } else {
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'error',
              })
            }
          })
        }
      })
    }

    /**
     * 添加比赛
     */
    const formContest = reactive({
      name: null,
      type: null,
      time: null,
      total: null,
    });
    const formContestRef = ref<FormInstance>()
    const rulesContest = reactive<FormRules>({
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
    const handleAddContest = async (formEl: FormInstance | undefined) => {
      if (!formEl) return
      await formEl.validate(valid => {
        if (valid) {
          // 改了日期的value-format就不需要下面这句话了
          // formContest.time = new Date(formContest.time)
          let json = JSON.stringify(formContest);
          // console.log(json)
          addContest(json).then(res => {
            if (res.data.code === 200) {
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'success',
              })
            } else {
              ElMessage({
                showClose: true,
                message: res.data.msg,
                type: 'error',
              })
            }
          })
        }
      })
    }
    return {
      formUser,
      formContest,
      formScores,
      handleAddScore,
      handleAddUser,
      handleAddContest,
      ruleScore,
      formScoreRef,
      rulesUser,
      formUserRef,
      rulesContest,
      formContestRef,
    }
  }
}
</script>

<style lang="scss">
.add-bg::before {
  content: "";
  position: absolute;
  width: 105%;
  height: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  background: url(../../assets/xcs-blue.png) center / cover no-repeat;
  filter: blur(10px);
  margin: -35px 0;
  z-index: -1;
}

.add-bg {
  box-sizing: border-box;
  margin: 0;
  padding: 50px;
  height:103%;
  width: 100%;
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