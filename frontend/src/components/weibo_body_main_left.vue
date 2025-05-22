<template>
    <aside class="left-sidebar">
            <div class="user-info">
                <div class="l-avatar"><img :src="photo" alt="photo"></div>
                <p>{{content}}</p>
            </div>
            <nav>
                <ul>
                    <li class="menu-item my_main-page" @click="tohomepage" style="opacity: 1; transform: translateY(0px);">
                        <a href="#" rel="section">
                            <i class="menu-item-icon fa fa-fw fa-home"></i> <br>
                            我的主页
                        </a>
                    </li>
                    <li class="menu-item my_blog" @click="tohomepage" style="opacity: 1; transform: translateY(0px);">
                        <a href="#" rel="section">
                            <i class="menu-item-icon fa fa-fw fa-pencil"></i> <br>
                            我的微博
                        </a>
                    </li>
                    <li class="menu-item my_like" @click="tohomepage" style="opacity: 1; transform: translateY(0px);">
                        <a href="#" rel="section">
                            <i class="menu-item-icon fa fa-fw fa-heart"></i> <br>
                            我的收藏
                        </a>
                    </li>
                    <li class="menu-item my_subscribe" @click="tohomepage" style="opacity: 1; transform: translateY(0px);">
                        <a href="#" rel="section">
                            <i class="menu-item-icon fa fa-fw fa-user-plus"></i> <br>
                            我的关注
                        </a>
                    </li>
                    <li class="menu-item my_fans" @click="tohomepage" style="opacity: 1; transform: translateY(0px);">
                        <a href="#" rel="section">
                            <i class="menu-item-icon fa fa-fw fa-user"></i> <br>
                            我的粉丝
                        </a>
                    </li>
                </ul>
            </nav>
        </aside>
</template>

<script>
export default {
    name: 'weibo_body_main_left',
    data(){
        return {
            content: sessionStorage.getItem('username'),
            photo: require("@/assets/gXOn48bPe6CXLpGz-generated_image.jpg")
        }
    },
    methods:{
        tohomepage(){
            this.$router.push('/homepage')
        }
    },
    created(){
        if(sessionStorage.getItem('photo') != "	http://127.0.0.1:8088/images/null"){
            this.photo = sessionStorage.getItem('photo')
        }
    }
};
</script>

<style scoped>
.l-avatar {
    display: flex;
    justify-content: center;
}
.left-sidebar {
    color: var(--textColor);
    width: 250px;
    background-color: var(--bgColor);
    padding: 20px;
    z-index: 1;
    position: sticky;
    top: 60px;
}
.left-sidebar .user-info {
    display: flex;
    gap: 10px;
    flex-direction: column;
    margin-bottom: 20px;
}
.left-sidebar .user-info {
    text-align: center;
}
.user-info img {
    width: 100px; 
    height: 100px;
    border-radius: 50%; 
    object-fit: cover; 
    border: 2px solid #007bff;
    justify-content: center;
}

/*menu-item的背景样式*/
.menu-item {
    display: block;
    margin: 0;
    list-style: none; /* 去掉列表默认样式 */
    opacity: 1; /* 设置透明度 */
    transform: translateY(0px); /* 设置垂直变换 */
}

.menu-item a {
    display: block;
    padding: 5px 20px; /* 添加内边距 */
    text-align: left; /* 文字左对齐 */
    line-height: inherit; /* 继承行高 */
    text-decoration: none; /* 去掉下划线 */
    color: var(--textColor); /* 设置文字颜色 */
    border-bottom: 1px solid transparent; /* 添加透明的下边框 */
    transition-property: background-color; /* 添加过渡效果 */
    transition-duration: 0.2s;
    transition-timing-function: ease-in-out;
    transition-delay: 0s;
}

.menu-item a:hover,
.menu-item-active a {
    background: #f9f9f9; /* 鼠标悬停时的背景颜色 */
    border-bottom-color: #fff; /* 鼠标悬停时的下边框颜色 */
}

.menu-item br {
    display: none; /* 隐藏换行符 */
}

.menu-item-active a:after {
    content: " ";
    position: absolute;
    top: 50%;
    margin-top: -3px;
    right: 15px;
    width: 6px;
    height: 6px;
    border-radius: 50%;
    background-color: #bbb;
}

/* 图标样式 */
.menu-item-icon {
    font-family: FontAwesome; /* 确保使用Font Awesome字体 */
    margin-right: 5px; /* 图标和文字之间的间距 */
}
</style>