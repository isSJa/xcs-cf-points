<template>
  <router-view/>
</template>

<script lang="ts">
import {useRouter} from "vue-router";
import {getUser} from "@/api";

export default {
  name: "app",
  components: {},
  setup() {
    const router = useRouter()
    const user = window.sessionStorage.getItem("user")
    if (user == null)
      router.push('login-and-register')
    else {
      let userObject = JSON.parse(user)
      getUser(userObject.id).then(res=>{
        userObject=res.data.data
        if (userObject.type === 1)
          router.push('/admin')
        else
          router.push('/user')
      })
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