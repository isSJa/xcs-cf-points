<template>
  <div class="common-layout">
    <el-container>
      <!--      头部-->
      <el-header class="head">
        <div class="img-box">
          <img src="../../assets/xcs-cyan.png" alt="" class="img-big" @click="moveToOut">
        </div>
        <div class="hello">Hi! {{ user.username }}~</div>
        <div class="avatar">
          <el-dropdown @command="handleCommand">
            <el-avatar :size="50" :src="user.imgurl"/>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="admin/mine">个人中心</el-dropdown-item>
                <el-dropdown-item command="login-and-register" divided>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-container class="content">
        <!--        导航栏-->
        <el-aside class="aside">
          <el-menu
              class="nav-menu"
              :collapse="isCollapse"
              @select="handleSelect"
          >
            <el-menu-item index="1">
              <el-icon>
                <Plus/>
              </el-icon>
              <template #title>ADD</template>
            </el-menu-item>
            <el-menu-item index="2">
              <el-icon>
                <User/>
              </el-icon>
              <template #title>社员</template>
            </el-menu-item>
            <el-menu-item index="3">
              <el-icon>
                <Sugar/>
              </el-icon>
              <template #title>CF赛</template>
            </el-menu-item>
            <el-menu-item index="4">
              <el-icon>
                <Collection/>
              </el-icon>
              <template #title>规则</template>
            </el-menu-item>
            <!--            <div class="change" @click="changeNav">-->
            <!--              <el-icon size="large"><Expand /></el-icon>-->
            <!--              <el-icon size="large"><Fold /></el-icon>-->
            <!--            </div>-->
          </el-menu>
        </el-aside>
        <!--        内容-->
        <el-main class="main">
          <main-main @changeAvatar="changeAvatar"/>
        </el-main>
      </el-container>
    </el-container>
    <el-backtop :right="100" :bottom="100"/>
  </div>
</template>

<script>
import 'element-plus/dist/index.css'
import {
  ElAside,
  ElAvatar,
  ElBacktop,
  ElContainer,
  ElDropdown, ElDropdownItem, ElDropdownMenu,
  ElHeader,
  ElIcon,
  ElMain,
  ElMenu,
  ElMenuItem
} from "element-plus";
import MainMain from "@/main.vue";
import {ref} from "vue";
import {useRouter} from "vue-router";
import router from "@/router";

export default {
  name: "adminIndex",
  methods: {
    router() {
      return router
    }
  },
  components: {
    MainMain,
    ElContainer,
    ElHeader,
    ElAside,
    ElMain,
    ElBacktop,
    ElMenu,
    ElMenuItem,
    ElIcon,
    ElAvatar,
    ElDropdown,
    ElDropdownItem,
    ElDropdownMenu,
  },
  setup() {
    const isCollapse = ref(false)
    const urls = ['', 'add', 'student', 'contest', 'rule']
    let user = ref(null)
    const flush = () => {
      const storage = sessionStorage.getItem('user')
      if (storage != null)
        user.value = JSON.parse(storage)
      const defaultImgUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
      if (user.value.imgurl === null || user.value.imgurl === '')
        user.value.imgurl = defaultImgUrl
      else
        user.value.imgurl = 'http://localhost:8312' + user.value.imgurl
    }
    flush()
    const router = useRouter()
    const defaultUrl = '/admin/welcome'
    router.push(defaultUrl)
    const handleSelect = (index) => {
      let url = '/admin/' + urls[index]
      router.push(url);
    }
    const handleCommand = (command) => {
      if (command === 'login-and-register') {
        sessionStorage.removeItem('user')
      }
      moveToOut()
      router.push('/' + command)
    }
    const moveToOut = () => {
      let nav = document.querySelector('.nav-menu');
      if (nav != null) {
        let lis = nav.children;
        for (let i = 0; i < lis.length; i++) {
          lis[i].className = 'el-menu-item'
        }
      }
      router.push(defaultUrl)
    }
    const changeAvatar = () => {
      flush()
    }
    return {
      isCollapse,
      changeAvatar,
      handleSelect,
      user,
      handleCommand,
      moveToOut,
    }
  }
}
</script>

<style scoped lang="scss">
.head {
  height: 80px;
  position: relative;
  border-bottom: solid 1px #dbdee5;

  .img-box {
    width: 150px;
    height: 80px;
    cursor: pointer;

    .img-big {
      width: 120px;
      position: absolute;
      top: -20px;
      transition: all 300ms;
    }

    .img-small {
      width: 80px;
      position: absolute;
      top: 0;
      transition: all 300ms;
    }
  }

  .hello {
    position: absolute;
    right: 130px;
    top: 30px;
    height: 100px;
    font-weight: bold;
    color: #4d4d4d;
  }

  .avatar {
    position: absolute;
    right: 0;
    top: 15px;
    width: 100px;
    height: 100px;
  }

}

.content {
  .aside {
    width: 120px;

    .nav-menu {
      min-height: 100vh;
      font-weight: bold;

      .change {
        color: #646464;
        text-align: center;
        margin-top: 20px;
        width: 100%;
        height: 200px;
        cursor: pointer;
      }

      .change :hover {
        background-color: #ecf5ff;
      }
    }
  }

  .main {
    overflow: hidden;
  }
}

.el-header {
  --el-header-padding: 0
}

.el-main {
  --el-main-padding: 20px 0
}

.el-menu-item.is-active {
  color: #43ba86;
}

.el-menu-item {
  color: #646464;
}
</style>