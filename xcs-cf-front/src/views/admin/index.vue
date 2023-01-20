<template>
  <div class="common-layout">
    <el-container>
      <!--      头部-->
      <el-header class="head">
        <div class="img-box">
          <img src="../../assets/xcs-cyan.png" alt="" class="img-big" @click="moveToIndex">
        </div>
        <div class="hello">Hi! {{user.username}}~</div>
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
                <Collection/>
              </el-icon>
              <template #title>CF赛</template>
            </el-menu-item>
            <!--            <div class="change" @click="changeNav">-->
            <!--              <el-icon size="large"><Expand /></el-icon>-->
            <!--              <el-icon size="large"><Fold /></el-icon>-->
            <!--            </div>-->
          </el-menu>
        </el-aside>
        <!--        内容-->
        <el-main class="main">
          <main-main/>
        </el-main>
      </el-container>
    </el-container>
    <el-backtop :right="100" :bottom="100"/>
  </div>
</template>

<script lang="ts">
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
    const urls = ['', 'add', 'student', 'contest']
    const storage = sessionStorage.getItem('user')
    let user = null
    if (storage != null)
      user = JSON.parse(storage)
    const defaultImgUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    if (user.imgurl === null || user.imgurl === '')
      user.imgurl = defaultImgUrl
    else
      user.imgurl = 'http://localhost:8312' + user.imgurl
    const changeNav = () => {
      isCollapse.value = !isCollapse.value
    }
    const router = useRouter()
    const defaultUrl = '/admin/welcome'
    router.push(defaultUrl)
    const handleSelect = (index: number) => {
      let url = '/admin/' + urls[index]
      router.push(url);
    }
    const handleCommand = (command: string) => {
      if (command === 'login-and-register') {
        sessionStorage.removeItem('user')
      }
      router.push('/' + command)
    }
    const moveToIndex = () => {
      let nav = document.querySelector('.nav-menu');
      if (nav != null) {
        let lis = nav.children;
        for (let i = 0; i < lis.length; i++) {
          lis[i].className = 'el-menu-item'
        }
      }
      router.push(defaultUrl)
    }
    return {
      isCollapse,
      changeNav,
      handleSelect,
      user,
      handleCommand,
      moveToIndex,
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

  .hello{
    position: absolute;
    right: 90px;
    top: 30px;
    width: 100px;
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
      min-height: 400px;
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