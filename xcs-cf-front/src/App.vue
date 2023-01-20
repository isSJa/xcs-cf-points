<template>
  <router-view/>
</template>

<script lang="ts">
import userIndex from "@/views/user/index.vue";
import {ref} from "vue";
import {useRouter} from "vue-router";

export default {
  name: "app",
  components: {},
  setup() {
    const router = useRouter()
    const user = window.sessionStorage.getItem("user")
    if (user == null)
      router.push('login-and-register')
    else {
      const userObject = JSON.parse(user)
      if (userObject.type === 1)
        router.push('admin')
      else
        router.push('user')
    }
    return {}
  }
}
</script>

<style lang="scss">
body {
  margin: 0;
  padding: 0;
}
</style>