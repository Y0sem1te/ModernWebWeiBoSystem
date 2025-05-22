<template>
    <div class="justify-setting">
        <div class="weibo-setting">
            <h2>设置</h2>
            <div class="avatar-section">
                <h3>头像设置</h3>
                <div class="avatar-show">
                    <img :src="avatarUrl" alt="Avatar" class="avatar-preview" />
                    <input type="file" accept="image/*" @change="handleAvatarChange" />
                </div>
            </div>
            <div class="set_background">
                <h4>设置用户主页背景</h4>
                <div class="set_banner">
                    <img :src="bannerUrl" alt="Banner" class="banner-preview" />
                    <input type="file" accept="image/*" @change="handleBannerChange" />
                </div>
            </div>
            <div class="password-section">
                <h3>密码设置</h3>
                <input type="password" v-model="newPassword" placeholder="新密码" />
                <input type="password" v-model="confirmPassword" placeholder="确认新密码" />
                <div class="justify-button">
                    <button @click="logout">退出账号</button>
                    <button @click="updatePassword">修改密码</button>
                    <button @click="returnback">回到主页</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'WeiboSetting',
    data() {
        return {
            avatarUrl: require('@/assets/gXOn48bPe6CXLpGz-generated_image.jpg'), // 默认头像路径
            oldPassword: '',
            newPassword: '',
            confirmPassword: '',
            bannerUrl: require('@/assets/gXOn48bPe6CXLpGz-generated_image.jpg')
        }
    },
    methods: {
        returnback(){
            this.$router.push({ path: '/weibo_main' })
        },
        handleBannerChange(event){
            const file = event.target.files[0]
            if (!file) return
            const reader = new FileReader()
            reader.onload = (e) => {
                this.bannerUrl = e.target.result
                const formData = new FormData();
                formData.append('banner', file);
                formData.append('username', sessionStorage.getItem('username'));
                axios.post('http://127.0.0.1:8088/upload_banner', formData, {
                    headers: { 'Content-Type': 'multipart/form-data' }
                })
                .then(response => {
                    console.log("上传成功:", response);
                    sessionStorage.setItem('banner', response.data)
                    alert("背景图上传成功")
                })
                .catch(error => {
                    console.error("上传失败:", error);
                });
            }
            reader.readAsDataURL(file)
        },
        handleAvatarChange(event) {
            const file = event.target.files[0]
            if (!file) return
            const reader = new FileReader()
            reader.onload = (e) => {
                this.avatarUrl = e.target.result
                const formData = new FormData();
                formData.append('avatar', file);
                formData.append('username', sessionStorage.getItem('username'));
                axios.post('http://127.0.0.1:8088/upload_avatar', formData, {
                    headers: { 'Content-Type': 'multipart/form-data' }
                })
                .then(response => {
                    console.log("上传成功:", response);
                    sessionStorage.setItem('photo', response.data)
                    console.log(sessionStorage.getItem('photo'))
                    alert("头像上传成功")
                })
                .catch(error => {
                    console.error("上传失败:", error);
                });
            }
            reader.readAsDataURL(file)
        },
        logout(){
            sessionStorage.setItem('photo', require("@/assets/gXOn48bPe6CXLpGz-generated_image.jpg"))
            sessionStorage.setItem('username', '');
            this.$router.push('/weibo_main');
        },
        updatePassword() {
            if(this.newPassword==''){
                alert("不能使用空密码");
                return
            }
            if (this.newPassword !== this.confirmPassword) {
                alert("新密码和确认密码不一致")
                return
            }
            axios.post("http://127.0.0.1:8088/update_password", {
                username: sessionStorage.getItem('username'),
                password: this.newPassword
            }).then(response => {
                console.log("密码更新成功:", response);
                this.oldPassword = ''
                this.newPassword = ''
                this.confirmPassword = ''
                alert("密码更新成功")
            }).catch(error => {
                console.error("密码更新失败:", error);
            });
        }
    }
}
</script>

<style scoped>
.set_banner {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.set_background {
    margin-top:30px;
}

.weibo-setting {
    margin: 20px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    width: 700px;
    height: 750px;
    background-color: #fff;
}

.justify-setting {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: url('@/assets/setting.jfif') no-repeat center center;
    background-size: cover;
}

.avatar-section,
.password-section {
    margin-bottom: 20px;
}

.banner-preview{
    width: 180px;
    height: 160px;
    object-fit: cover;
    display: block;
    margin-bottom: 10px;
}
.avatar-preview {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    object-fit: cover;
    display: block;
    margin-bottom: 10px;
}

input[type="password"],
input[type="file"] {
    display: block;
    width: 100%;
    margin-bottom: 10px;
    padding: 8px;
}

.avatar-show {
    display: flex;
    flex-direction: column;
    align-items: center;
}

button {
    padding: 10px 15px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}

button:hover {
    background-color: #38a174;
}

.justify-button {
    display: flex;
    justify-content: space-around;
    margin-top: 20px;
}
</style>