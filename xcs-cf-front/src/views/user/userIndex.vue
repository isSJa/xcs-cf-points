<template>
  <div class="common-layout">
    <el-container>
      <!--      头部-->
      <el-header class="head">
        <div class="img-box">
          <img src="../../assets/xcs-cyan.png" alt="" :class="isCollapse?'img-small':'img-big'" @click="changeNav">
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
                <User/>
              </el-icon>
              <template #title>社员</template>
            </el-menu-item>
            <el-menu-item index="2">
              <el-icon>
                <Collection/>
              </el-icon>
              <template #title>CF赛</template>
            </el-menu-item>
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
import {ElAside, ElBacktop, ElContainer, ElHeader, ElIcon, ElMain, ElMenu, ElMenuItem} from "element-plus";
import MainMain from "@/main.vue";
import {ref} from "vue";
import {useRouter} from "vue-router";

export default {
  name: "userIndex",
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
  },
  setup() {
    const isCollapse = ref(false)
    const urls=['','student','contest']
    const changeNav = () => {
      isCollapse.value = !isCollapse.value
    }
    const router=useRouter()
    const handleSelect=(index:number)=>{
      const url='/user/'+urls[index]
      router.push(url);
    }
    return {
      isCollapse,
      changeNav,
      handleSelect,

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

}

.content {
  .aside {
    width: 120px;

    .nav-menu {
      min-height: 400px;
      font-weight: bold;
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
.el-menu-item{
  color: #646464;
}
</style>