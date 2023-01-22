<template>
  <div class="welcome-img">
    <div class="title">RULES</div>
    <img src="../../assets/rule.jpg" class="imgImg">
  </div>
</template>

<script>
export default {
  name: "ruleAdmin"
}
</script>

<style lang="scss">
* {
  margin: 0;
  padding: 0;
}

.welcome-img {
  margin: 0 auto;
  position: relative;
  text-align: center;
  padding: 50px;

  .title {
    font-size: 120px;
    font-weight: bold;
    margin-bottom: 50px;
  }

  .imgImg {
    width: 60%;
    border-radius: 30px;
  }
}

.el-main {
  --el-main-padding: 0;
}
</style>