<template>
  <div class="btn">
    <div v-for="i in choose" :key="i" class="item">
      <el-button type="primary" size="large" @click="handleClick(i)">{{ i }}</el-button>
    </div>
  </div>
  <router-view class="view"/>
</template>

<script>
import {ElButton} from 'element-plus'
import 'element-plus/dist/index.css'
import {useRouter} from "vue-router";

export default {
  components: {
    ElButton,
  },
  props: [],
  setup() {
    const choose = ['add', 'student','contest']
    const router = useRouter();
    const handleClick = i => {
      router.push("/"+i)
    }
    return {
      router,
      choose,
      handleClick
    }
  }
}
</script>

<style lang="scss" scoped>
.btn {
  display: flex;
  justify-content: center;
  align-content: center;

  .item {
    margin: 30px;
  }
}
.view{
  margin-top: 20px;
}
</style>
