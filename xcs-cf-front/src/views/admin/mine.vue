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
        <el-button>修改头像</el-button>
      </el-upload>
    </div>
    <div class="name">
      {{ user.username }}
      <el-tag type="success">管理员</el-tag>
    </div>
    <div class="email">
      {{ user.email }}
    </div>
    <el-button>修改个人信息</el-button>
    <!--    <div class="info">-->
    <!--      <el-form-->
    <!--          label-width="100px"-->
    <!--          :model="user"-->
    <!--          style="max-width: 460px;margin: 50px auto 0;"-->
    <!--      >-->
    <!--        <el-form-item label="用户名">-->
    <!--          <el-input v-model="user.username"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="邮箱">-->
    <!--          <el-input v-model="user.email"/>-->
    <!--        </el-form-item>-->
    <!--      </el-form>-->
    <!--    </div>-->
  </div>
</template>

<script>
import 'element-plus/dist/index.css'
import {ElButton, ElUpload, ElMessage, ElMessageBox, ElAvatar, ElForm, ElFormItem, ElInput, ElTag} from "element-plus";
import {ref} from "vue";
import {UploadProps} from "element-plus";
import {getUser} from "@/api";

export default {
  name: "adminMine",
  components: {
    ElButton,
    ElUpload,
    ElAvatar,
    // ElForm,
    // ElFormItem,
    // ElInput,
    ElTag,
  },
  setup() {
    const storage = sessionStorage.getItem('user')
    let user = null
    if (storage != null)
      user = JSON.parse(storage)
    const defaultImgUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    if (user.imgurl === null || user.imgurl === '')
      user.imgurl = defaultImgUrl
    else
      user.imgurl = 'http://localhost:8312' + user.imgurl
    const handleAvatarSuccess = (
        response
    ) => {
      let type;
      if (response.code === 200)
        type = 'success'
      else
        type = 'error'
      ElMessage({
        showClose: true,
        message: response.msg,
        type: type
      })
      getUser(user.id).then(res => {
        user = res.data.data
        sessionStorage.setItem('user', JSON.stringify(user))
      })
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
    return {
      user,
      handleAvatarSuccess,
      beforeAvatarUpload
    }
  }
}
</script>

<style scoped lang="scss">
.mine {
  text-align: center;

  .avatar {
    text-align: center;
    margin-top: 100px;

    .avatar-uploader {
      margin: 20px;
      display: block;
    }
  }

  .name {
    font-weight: bold;
    font-size: 50px;
    margin-top: 30px;
  }

  .email {
    font-size: 16px;
    margin: 20px;
  }

}

</style>