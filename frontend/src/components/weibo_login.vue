<template>
    <div class="login-page">
        <div class="login-container">
            <div class="logo">
                <img :src="photo" alt="微博 Logo" />
            </div>
            <form class="login-form" @submit.prevent="handleLogin">
                <input type="text" v-model="username" placeholder="手机号或邮箱" required/>
                <input type="password" v-model="password" placeholder="密码" required/>
                <button type="submit">登录</button>
            </form>
            <div class="login-options">
                <div @click="register"><a>注册新的帐号</a></div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'WeiboLogin',
    data() {
        return {
            username: '',
            password: '',
            photo: require("@/assets/weibo.png")
        }
    },
    methods: {
        handleLogin() {
            axios.post(
              "http://127.0.0.1:8088/login",
              { username: this.username, password: this.password },
              { headers: { 'Content-Type': 'application/json' }}
            ).then(
                response=>{
                    console.log(response);
                    if(response.data.banner=="-1"||response.data.banner=="false"){
                        alert("用户名或密码错误，请重试！");
                        this.$router.push('/login');
                    }
                    else {
                        alert("登录成功！");
                        sessionStorage.setItem('username',this.username)
                        sessionStorage.setItem('photo',response.data.avatar)
                        sessionStorage.setItem('banner', response.data.banner)
                        this.$router.push('weibo_main');
                    }
                }
            )
            
        },
        register() {
            this.$router.push('/register');
        }
    }
}
</script>

<style scoped>
.login-page {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    
    background-image: url('@/assets/1148196_071107view18.jpg');
    background-size: cover;                   
    background-position: center;              
    background-repeat: no-repeat;             
    background-attachment: fixed; 
}

.login-container {
    background-color: #fff;
    padding: 40px;
    width: 320px;
    border: 1px solid #e1e8ed;
    box-shadow: 0 4px 12px rgba(0,0,0,0.05);
    border-radius: 4px;
    text-align: center;
}

.logo img {
    width: 50px;
    height: 50px;
    margin-bottom: 20px;
}

.login-form input {
    width: 100%;
    padding: 12px;
    margin-bottom: 15px;
    font-size: 14px;
    border: 1px solid #ccd6dd;
    border-radius: 3px;
}

.login-form button {
    width: 100%;
    padding: 12px;
    background-color: #1da1f2;
    border: none;
    color: #fff;
    font-size: 16px;
    border-radius: 3px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.login-form button:hover {
    background-color: #1991da;
}

.login-options {
    margin-top: 15px;
    display: flex;
    justify-content: space-between;
    font-size: 12px;
}

.login-options a {
    color: #1da1f2;
    text-decoration: none;
}

.login-options a:hover {
    text-decoration: underline;
}
</style>