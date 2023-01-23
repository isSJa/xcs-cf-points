<template>
  <div class="main-box">
    <div :class="['container', 'container-register', { 'is-txl': isLogin }]">
      <form @keyup.enter="register">
        <h2 class="title">Create Account</h2>
        <span class="text">Fill in the form below to register</span>
        <input class="form__input" type="text" placeholder="用户名" v-model="registerForm.username"/>
        <input class="form__input" type="text" placeholder="邮箱" v-model="registerForm.email"/>
        <input class="form__input" type="password" placeholder="密码需在6-12位，且含数字和英文字母"
               v-model="registerForm.password"/>
        <input class="form__input" type="password" placeholder="再次确认密码" v-model="registerForm.confirmPassword"/>
        <div class="primary-btn" @click="register">立即注册</div>
      </form>
    </div>
    <div
        :class="['container', 'container-login', { 'is-txl is-z200': isLogin }]"
    >
      <form @keyup.enter="login">
        <h2 class="title">Sign in to Website</h2>
        <span class="text">Use username or email to login</span>
        <input class="form__input" type="text" placeholder="Account" v-model="loginForm.account"/>
        <input class="form__input" type="password" placeholder="Password" v-model="loginForm.password"/>
        <div class="primary-btn" @click="login">立即登录</div>
      </form>
    </div>
    <div :class="['switch', { login: isLogin }]">
      <div class="switch__circle"></div>
      <div class="switch__circle switch__circle_top"></div>
      <div class="switch__container">
        <h2>{{ isLogin ? 'Hello Friend !' : 'Welcome Back !' }}</h2>
        <p>
          {{
            isLogin
                ? 'Enter your personal details and start journey with us'
                : 'To keep connected with us please login with your personal info'
          }}
        </p>
        <div class="primary-btn" @click="isLogin = !isLogin">
          {{ isLogin ? '立即注册' : '立即登录' }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import 'element-plus/dist/index.css'
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {userLogin, userRegister} from "@/api";
import {useRouter} from "vue-router";
import MD5 from "@/utils/md5";

export default {
  name: 'loginBox',
  components: {},
  setup() {
    const isLogin = ref(true)
    const loginForm = reactive({
      account: '',
      password: '',
    })
    const registerForm = reactive({
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    })
    const showMsg = (msg, type) => {
      ElMessage({
        showClose: true,
        message: msg,
        type: type,
      })
    }
    const router = useRouter();
    const login = () => {
      // 信息不完整
      for (let item in loginForm) {
        if (loginForm[item] === '') {
          showMsg("登录信息不完整！", "error");
          return;
        }
      }
      // 登录验证
      loginForm.password = MD5.hex_md5(loginForm.password)
      const json = JSON.stringify(loginForm)
      userLogin(json).then(res => {
        let type
        if (res.data.code === 200)
          type = 'success'
        else
          type = 'error'
        showMsg(res.data.msg, type)
        if (type === 'success') {
          const user = res.data.data
          sessionStorage.setItem('user', JSON.stringify(user))
          if (user.type === 0)
            router.push("/user")
          else
            router.push("/admin")
        }
        if (res.data.msg === '密码错误') {
          loginForm.password = ''
        }
      })
    }
    const register = () => {
      // 信息不完整
      for (let item in registerForm) {
        if (registerForm[item] === '') {
          showMsg("注册信息不完整！", "error");
          return;
        }
      }
      // 密码规则验证
      const pwd = registerForm.password;
      const conPwd = registerForm.confirmPassword;
      let re = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/
      if (!re.test(pwd)) {
        showMsg("密码需在6-12位，且含数字和英文字母", "error")
        return;
      }
      // 密码是否一致
      if (pwd !== conPwd) {
        showMsg("两次密码不一致！", "error")
        return;
      }
      // 注册
      registerForm.password = MD5.hex_md5(registerForm.password)
      const json = JSON.stringify(registerForm)
      userRegister(json).then(res => {
        let type
        if (res.data.code === 200) {
          type = 'success'
        } else {
          type = 'error'
        }
        showMsg(res.data.msg, type)
        if (type === 'success') {
          isLogin.value = !isLogin.value
        }
      })
    }

    return {
      isLogin,
      loginForm,
      registerForm,
      login,
      register
    }

  }
}
</script>

<style lang="scss" scoped>
.main-box {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 1000px;
  min-width: 1000px;
  min-height: 600px;
  height: 600px;
  padding: 25px;
  background-color: #ecf0f3;
  box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
  border-radius: 12px;
  overflow: hidden;

  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    width: 600px;
    height: 100%;
    padding: 25px;
    background-color: #ecf0f3;
    transition: all 1.25s;

    form {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      width: 100%;
      height: 100%;
      color: #a0a5a8;

      .title {
        font-size: 34px;
        font-weight: 700;
        line-height: 3;
        color: #181818;
      }

      .text {
        margin-top: 30px;
        margin-bottom: 12px;
      }

      .form__input {
        width: 350px;
        height: 40px;
        margin: 4px 0;
        padding-left: 25px;
        font-size: 13px;
        letter-spacing: 0.15px;
        border: none;
        outline: none;
        // font-family: 'Montserrat', sans-serif;
        background-color: #ecf0f3;
        transition: 0.25s ease;
        border-radius: 8px;
        box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;

        &::placeholder {
          color: #a0a5a8;
        }
      }

      .form__radio {
        width: 350px;
        height: 40px;
        margin: 4px 0;
        padding-left: 25px;
        font-size: 13px;
        line-height: 40px;
        letter-spacing: 0.15px;
        border: none;
        outline: none;
        background-color: #ecf0f3;
        transition: 0.25s ease;
        border-radius: 8px;
        //box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
      }
    }
  }

  .container-register {
    z-index: 100;
    left: calc(100% - 600px);
  }

  .container-login {
    left: calc(100% - 600px);
    z-index: 0;
  }

  .is-txl {
    left: 0;
    transition: 1.25s;
    transform-origin: right;
  }

  .is-z200 {
    z-index: 200;
    transition: 1.25s;
  }

  .switch {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 400px;
    padding: 50px;
    z-index: 200;
    transition: 1.25s;
    background-color: #ecf0f3;
    overflow: hidden;
    box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #f9f9f9;
    color: #a0a5a8;

    .switch__circle {
      position: absolute;
      width: 500px;
      height: 500px;
      border-radius: 50%;
      background-color: #ecf0f3;
      box-shadow: inset 8px 8px 12px #d1d9e6, inset -8px -8px 12px #f9f9f9;
      bottom: -60%;
      left: -60%;
      transition: 1.25s;
    }

    .switch__circle_top {
      top: -30%;
      left: 60%;
      width: 300px;
      height: 300px;
    }

    .switch__container {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      position: absolute;
      width: 400px;
      padding: 50px 55px;
      transition: 1.25s;

      h2 {
        font-size: 34px;
        font-weight: 700;
        line-height: 3;
        color: #181818;
      }

      p {
        font-size: 14px;
        letter-spacing: 0.25px;
        text-align: center;
        line-height: 1.6;
      }
    }
  }

  .login {
    left: calc(100% - 500px);

    .switch__circle {
      left: 0;
    }
  }

  .primary-btn {
    width: 180px;
    height: 50px;
    border-radius: 25px;
    margin-top: 50px;
    text-align: center;
    line-height: 50px;
    font-size: 14px;
    letter-spacing: 2px;
    background-color: #4b70e2;
    color: #f9f9f9;
    cursor: pointer;
    box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;

    &:hover {
      box-shadow: 4px 4px 6px 0 rgb(255 255 255 / 50%),
      -4px -4px 6px 0 rgb(116 125 136 / 50%),
      inset -4px -4px 6px 0 rgb(255 255 255 / 20%),
      inset 4px 4px 6px 0 rgb(0 0 0 / 40%);
    }
  }
}
</style>

