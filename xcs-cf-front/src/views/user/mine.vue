<template>
  <div class="mine">
    <div class="avatar">
      <el-avatar :size="120" :src="user.imgurl"/>
      <el-upload
          class="avatar-uploader"
          action="http://localhost:8312/user/upload-avatar"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :data={id:user.id}
      >
        <el-button type="primary">修改头像</el-button>
      </el-upload>
    </div>
    <div class="name">
      {{ user.username }}
      <el-tag>普通用户</el-tag>
    </div>
    <div class="email">
      {{ user.email }}
    </div>
    <el-button class="changeInfo" @click="changeInfo" type="primary">修改信息</el-button>
    <el-button class="changePwd" @click="changePwd" type="primary">修改密码</el-button>
  </div>
  <!--  编辑用户信息-->
  <div class="edit">
    <el-dialog
        v-model="editDialogVisible"
        :title="editDialogTitle"
        width="500px"
        style="position: relative;height: 230px"
        @keyup.enter="confirmEdit">
      <el-form :model="newUser" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="newUser.username"/>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="newUser.email"/>
        </el-form-item>
        <div style="position: absolute;right: 20px">
          <el-button @click="cancelEdit">取消</el-button>
          <el-button type="primary" @click="confirmEdit">确认</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
  <!--  修改用户密码-->
  <div class="edit">
    <el-dialog
        v-model="pwdDialogVisible"
        :title="pwdDialogTitle"
        width="500px"
        style="position: relative;height: 230px"
        @keyup.enter="confirmPwd">
      <el-form :model="newPwd" label-width="80px">
        <el-form-item label="原始密码">
          <el-input v-model="newPwd.oPwd" type="password"/>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="newPwd.nPwd" type="password"/>
        </el-form-item>
        <div style="position: absolute;right: 20px">
          <el-button @click="cancelPwd">取消</el-button>
          <el-button type="primary" @click="confirmPwd">确认</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
  <!--  背景-->
  <div class="bg" />
  <div class="bg-blue" />
</template>

<script>
import 'element-plus/dist/index.css'
import {
  ElButton,
  ElUpload,
  ElMessage,
  ElAvatar,
  ElForm,
  ElFormItem,
  ElInput,
  ElTag,
  ElDialog
} from "element-plus";
import {ref} from "vue";
import {getUser, updatePwd, updateUser} from "@/api";
import MD5 from "@/utils/md5";

export default {
  name: "adminMine",
  components: {
    ElButton,
    ElUpload,
    ElAvatar,
    ElDialog,
    ElForm,
    ElFormItem,
    ElInput,
    ElTag,
  },
  setup(props, {emit}) {
    // 从session获取用户信息
    const user = ref(null)
    const newUser = ref({})
    const flush = () => {
      const storage = sessionStorage.getItem('user')
      if (storage != null)
        user.value = JSON.parse(storage)
      getUser(user.value.id).then(res => {
        user.value = res.data.data
        Object.assign(newUser.value, user.value)
        newPwd.value.id = user.value.id
        sessionStorage.setItem('user', JSON.stringify(user.value))
        const defaultImgUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
        if (user.value.imgurl === null || user.value.imgurl === '')
          user.value.imgurl = defaultImgUrl
        else
          user.value.imgurl = 'http://localhost:8312' + user.value.imgurl
        emit('changeAvatar')
      })
    }
    flush()
    // 显示msg
    const showMsg = (res) => {
      if (res.data.code === 200) {
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: 'success',
        })
        editDialogVisible.value = false
        flush()
      } else {
        ElMessage({
          showClose: true,
          message: res.data.msg,
          type: 'error',
        })
      }
    }
    // 修改头像
    const handleAvatarSuccess = (response) => {
      showMsg({data: response})
      flush()
    }
    const beforeAvatarUpload = (rawFile) => {
      if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('Avatar picture must be JPG format!')
        return false
      } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
      }
      return true
    }
    // 编辑个人信息
    const editDialogVisible = ref(false)
    const editDialogTitle = ref('')
    const changeInfo = () => {
      editDialogVisible.value = true;
    }
    const confirmEdit = () => {
      const json = JSON.stringify(newUser.value)
      updateUser(json).then(res => {
        showMsg(res)
        flush()
      })
    }
    const cancelEdit = () => {
      editDialogVisible.value = false
    }
    // 修改密码
    const pwdDialogVisible = ref(false)
    const pwdDialogTitle = ref('')
    const newPwd = ref({
      id: null,
      oPwd: '',
      nPwd: ''
    })
    const changePwd = () => {
      pwdDialogVisible.value = true;
    }
    const confirmPwd = () => {
      newPwd.value.nPwd=MD5.hex_md5(newPwd.value.nPwd)
      newPwd.value.oPwd=MD5.hex_md5(newPwd.value.oPwd)
      const json = JSON.stringify(newPwd.value)
      updatePwd(json).then(res => {
        showMsg(res)
        flush()
      })
    }
    const cancelPwd = () => {
      pwdDialogVisible.value = false
    }
    return {
      user,
      handleAvatarSuccess,
      beforeAvatarUpload,
      editDialogVisible,
      editDialogTitle,
      confirmEdit,
      cancelEdit,
      changeInfo,
      changePwd,
      newUser,
      pwdDialogVisible,
      pwdDialogTitle,
      confirmPwd,
      cancelPwd,
      newPwd
    }
  }
}
</script>

<style scoped lang="scss">
.mine {
  text-align: center;
  padding-top: 20px;

  .avatar {
    text-align: center;
    padding-top: 20px;

    .avatar-uploader {
      margin-top: 20px;
      display: block;
    }
  }

  .name {
    font-weight: bold;
    font-size: 80px;
    color: #fff;
  }

  .email {
    font-size: 16px;
    margin: 20px 0 30px;
    color: #fff;
  }
}
.bg-blue {
  margin-top: 50px;
  width: 92.921%;
  height: 600px;
  filter: blur(10px);
  position: absolute;
  top: 40px;
  background: #004aad center / cover no-repeat;
  z-index: -1;
}
.bg {
  margin-top: 50px;
  width: 100%;
  height: 500px;
  filter: blur(10px);
  background: url(../../assets/xcs-blue.png) center / cover no-repeat;
}
</style>