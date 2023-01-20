<template>
  <router-view />
</template>

<script lang="ts">
import userIndex from "@/views/user/userIndex.vue";
import {ref} from "vue";
import {useRouter} from "vue-router";
export default {
  name: "app",
  components:{
  },
  setup() {
    const type=ref('admin')
    const router=useRouter()
    router.push(type.value)
    return {

    }
  }
}
</script>

<style lang="scss">
body{
  margin: 0;
  padding: 0;
}
</style>