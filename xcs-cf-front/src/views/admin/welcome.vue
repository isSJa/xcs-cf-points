<template>
  <div class="welcome-img">
    <div class="title">WELCOME TO 信创社</div>
    <div class="sentence">
      开启一段新的旅程，<br>
      解锁新的校园丰富生活，<br>
      代码取乐。
    </div>
    <img src="../../assets/info.jpg" class="imgImg">
  </div>
</template>

<script>
export default {
  name: "welcomeAdmin"
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
  padding: 100px;

  .title {
    font-size: 120px;
    font-weight: bold;
    margin-bottom: 30px;
  }

  .sentence {
    font-size: 30px;
    margin-bottom: 50px;
    line-height: 40px;
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