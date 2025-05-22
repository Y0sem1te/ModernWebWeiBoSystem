<template>
  <div class="reg-page">
  <div class="register-container">
    <div class="register-header">
      <div class="logo">
        <svg viewBox="0 0 1024 1024" class="weibo-logo">
          <path d="M914.432 518.144q27.648 21.504 38.912 51.712t9.216 62.976-14.336 65.536-31.744 59.392q-34.816 48.128-78.848 81.92t-91.136 56.32-94.72 35.328-89.6 18.944-75.264 7.68-51.712 1.536-49.152-2.56-68.096-10.24-78.336-21.504-79.872-36.352-74.24-55.296-59.904-78.848q-16.384-29.696-22.016-63.488t-5.632-86.016q0-22.528 7.68-51.2t27.136-63.488 53.248-75.776 86.016-90.112q51.2-48.128 105.984-85.504t117.248-57.856q28.672-10.24 63.488-11.264t57.344 11.264q10.24 11.264 19.456 23.04t12.288 29.184q3.072 14.336 0.512 27.648t-5.632 26.624-5.12 25.6 2.048 22.528q17.408 2.048 33.792-1.536t31.744-9.216 31.232-11.776 33.28-9.216q27.648-5.12 54.784-4.608t49.152 7.68 36.352 22.016 17.408 38.4q2.048 14.336-2.048 26.624t-8.704 23.04-7.168 22.016 1.536 23.552q3.072 7.168 14.848 13.312t27.136 12.288 32.256 13.312 29.184 16.384z" fill="#E6162D"/>
        </svg>
      </div>
      <h1>注册微博，发现新鲜事</h1>
    </div>

    <form @submit.prevent="handleSubmit" class="register-form">
      <div class="form-group" :class="{ 'has-error': errors.username }">
        <input
          type="text"
          v-model="form.username"
          placeholder="请输入用户名"
          @input="validateUsername"
        >
        <div class="error-tip" v-if="errors.username">{{ errors.username }}</div>
      </div>

      <div class="form-group" :class="{ 'has-error': errors.password }">
        <input
          type="password"
          v-model="form.password"
          placeholder="请输入密码"
          @input="validatePassword"
        >
        <div class="error-tip" v-if="errors.password">{{ errors.password }}</div>
        <div class="password-rules">
          <span :class="{ 'valid': passwordStrength.length }">至少8位</span>
          <span :class="{ 'valid': passwordStrength.upper }">大写字母</span>
          <span :class="{ 'valid': passwordStrength.lower }">小写字母</span>
          <span :class="{ 'valid': passwordStrength.number }">数字</span>
          <span :class="{ 'valid': passwordStrength.special }">特殊符号</span>
        </div>
      </div>

      <button
        type="submit"
        class="submit-btn"
        :disabled="!formValid"
        :class="{ 'active': formValid }"
      >
        立即注册
      </button>
    </form>

    <div class="footer-links">
      <div @click="login"><a href="#">已有账号？立即登录</a></div>
      <span>|</span>
    </div>
  </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
data() {
  return {
    photo: require("@/assets/register.jpg"),
    form: {
      username: '',
      password: ''
    },
    errors: {
      username: '',
      password: ''
    },
    passwordStrength: {
      length: false,
      upper: false,
      lower: false,
      number: false,
      special: false
    }
  }
},
computed: {
  formValid() {
    return (
      !this.errors.username &&
      !this.errors.password &&
      this.form.username.length >= 6 &&
      Object.values(this.passwordStrength).every(v => v)
    )
  }
},
methods: {
  login() {
    this.$router.push("/login");
  },
  validateUsername() {
    if (this.form.username.length < 6) {
      this.errors.username = '用户名至少需要6个字符'
    } else if (!/^[a-zA-Z0-9_]+$/.test(this.form.username)) {
      this.errors.username = '只能包含字母、数字和下划线'
    } else {
      this.errors.username = ''
    }
  },
  validatePassword() {
    this.passwordStrength = {
      length: this.form.password.length >= 8,
      upper: /[A-Z]/.test(this.form.password),
      lower: /[a-z]/.test(this.form.password),
      number: /\d/.test(this.form.password),
      special: /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/.test(this.form.password)
    }
    if (!this.passwordStrength.length) {
      this.errors.password = '密码至少需要8个字符'
    } else if (!Object.values(this.passwordStrength).every(v => v)) {
      this.errors.password = '密码不符合安全要求'
    } else {
      this.errors.password = ''
    }
  },
  handleSubmit() {
    if (this.formValid) {
      axios.post(
        "http://127.0.0.1:8088/register",{username:this.form.username,password:this.form.password}).then((response)=>{
          console.log(response);
          if(response.data==''){
            alert("用户名已存在，请重新注册");
          }
          else if(response.data=="success"){
            alert("注册成功！！");
            this.$router.push('/login');
          }
        })
      }
  }
}
}
</script>

<style scoped>
.register-container {
  width: 400px;
  margin: 40px auto;
  padding: 30px;
  background: #fff;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
}

.register-header {
  text-align: center;
  margin-bottom: 30px;
}

.weibo-logo {
  width: 60px;
  height: 60px;
  margin-bottom: 15px;
}

h1 {
  font-size: 24px;
  color: #333;
  margin: 10px 0;
}

.form-group {
  margin-bottom: 20px;
  position: relative;
}

input {
  width: 100%;
  height: 40px;
  padding: 8px 12px;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

input:focus {
  border-color: #E6162D;
  outline: none;
  box-shadow: 0 0 0 2px rgba(230,22,45,0.1);
}

.has-error input {
  border-color: #ff3b30;
}

.error-tip {
  color: #ff3b30;
  font-size: 12px;
  margin-top: 4px;
}

.password-rules {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-top: 8px;
}

.password-rules span {
  font-size: 12px;
  color: #999;
  padding: 2px 6px;
  background: #f5f5f5;
  border-radius: 2px;
}

.password-rules .valid {
  color: #09b63d;
  background: #e8f8ec;
}

.submit-btn {
  width: 100%;
  height: 40px;
  background: #E6162D;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  opacity: 0.6;
  transition: opacity 0.3s;
}

.submit-btn.active {
  opacity: 1;
}

.submit-btn:hover {
  background: #d31126;
}

.footer-links {
  margin-top: 20px;
  text-align: center;
  font-size: 13px;
}

.footer-links a {
  color: #E6162D;
  text-decoration: none;
  margin: 0 8px;
}

.footer-links a:hover {
  text-decoration: underline;
}

.footer-links span {
  color: #ddd;
}

.reg-page {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    
    background-image: url('@/assets/register.jpg');
    background-size: cover;                   
    background-position: center;              
    background-repeat: no-repeat;             
    background-attachment: fixed; 
}
</style>