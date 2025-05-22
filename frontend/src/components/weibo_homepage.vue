<template>
  <div class="weibo-layout">
    <!-- 左侧导航 -->
    <div class="left-nav">
      <div class="nav-header">
        <i class="iconfont icon-weibo"></i>
        <span>微博</span>
      </div>
      <div class="nav-menu">
        <div v-for="item in navItems" :key="item.name" class="menu-item" @click="go(item.name)">
          <i :class="['iconfont', item.icon]"></i>
          {{ item.name }}
        </div>
      </div>
    </div>

    <!-- 主体内容 -->
    <div class="main-content" v-if="fello">
      <!-- 个人信息头部 -->
      <div class="profile-header">
        <div class="profile-banner">
          <img :src="user.banner" class="banner-image">
          <div class="profile-info">
            <img :src="user.avatar" class="user-avatar">
            <div class="user-stats">
              <h2>{{ user.name }}</h2>
              <div class="stats">
                <span v-if="this.clicked == false">{{ user.followers }} 粉丝</span>
                <span v-else>{{ Number(user.followers) + 1 }} 粉丝</span>
                <span>{{ user.following }} 关注</span>
              </div>
            </div>
          </div>
        </div>
        <div class="follow">
          <div class="follow_btn" v-if="this.clicked == false && notme" @click="ffollow">+ 关注</div>
          <div class="_follow_btn" v-else-if="this.clicked == true && notme" @click="tfollow">已关注</div>
        </div>
      </div>

      <!-- 推荐用户 -->
      <div class="recommend-box">
        <div class="recommend-header">
          <span>你可能感兴趣的人</span>
          <button>换一换</button>
        </div>
        <div v-for="user in recommendUsers" :key="user.id" class="recommend-item">
          <img :src="user.avatar" class="rec-avatar">
          <div class="rec-info">
            <div class="rec-name">
              {{ user.name }}
              <span v-if="user.official">官方</span>
            </div>
            <div class="rec-desc">{{ user.description }}</div>
          </div>
          <button class="follow-btn">+ 关注</button>
        </div>
      </div>

      

    </div>
    <p2>关注列表</p2>
    <div class="guanzhu" v-show="fello == false">
        <div class="guanPage">
          <div v-for="item in guan_list[0]" :key="item.id" class="guan">
            <img :src="item.avatar" class="rec-avatar" style="width: 50px; height: 50px;">
            <div class="rec-info">
              <div class="rec-name">
                {{ item.id }}
              </div>
            </div>
          </div>
        </div>
      </div>


    <!-- 右侧边栏 -->
    <div class="right-sidebar">
      <div class="search-box">
        <input type="text" placeholder="搜索微博">
        <i class="iconfont icon-search"></i>
      </div>
      <div class="footer-info">
        <p>IP属地：{{ user.location }}</p>
        <div class="links">
          <a v-for="link in footerLinks" :key="link">{{ link }}</a>
        </div>
        <p class="copyright">Copyright © 2009-2025</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      guan_list: [], // 关注列表
      fello: true,
      notme: false,
      user: {
        name: '未登录',
        avatar: require('@/assets/gXOn48bPe6CXLpGz-generated_image.jpg'),
        banner: require('@/assets/weibo.png'),
        followers: 0,
        following: 0,
        interactions: 0,
        location: '湖南'
      },
      navItems: [
        { name: '我的主页', icon: 'icon-home' },
        { name: '我的关注', icon: 'icon-follow' },
        { name: '我的粉丝', icon: 'icon-fans' },
        { name: '我的经常访问', icon: 'icon-history' },
        { name: '我的收藏', icon: 'icon-collect' },
        { name: '我的赞', icon: 'icon-like' },
        { name: '创作者中心', icon: 'icon-creator' }
      ],
      recommendUsers: [
        {
          id: 1,  
          name: '微天下',
          avatar: require('@/assets/blog1.jpg'),
          description: '新浪微博官方账号',
          official: true
        },
        {
          id: 2,
          name: '黑猫课堂',
          avatar: require('@/assets/boy1.jpg'),
          description: '黑猫课堂官方微博'
        }
      ],
      footerLinks: [
        '帮助中心',
        '微博客服',
        '自助服务中心',
        '常见问题',
        '合作&服务',
        '微博营销',
        '开放平台'
      ],
      clicked: false
    }
  },
  methods: {
    go(name) {
      if (name === "我的主页") this.fello = true;
      else this.fello = false;
    },
    ffollow() {
      this.clicked = !this.clicked
      axios.post("http://127.0.0.1:8088/ffollow", { userid: sessionStorage.getItem('username'), followerid: this.user.name }).then(response => {
        if (response.data == "success") {
          alert("关注成功");
        } else {
          alert("关注失败");
        }
      })
    },
    tfollow() {
      this.clicked = !this.clicked
      axios.post("http://127.0.0.1:8088/tfollow", { userid: sessionStorage.getItem('username'), followerid: this.user.name }).then(response => {
        if (response.data == "success") {
          alert("取关成功");
        } else {
          alert("取关失败");
        }
      })
    }
  },
  created() {
    if (this.$route.query.id) {
      axios.get("http://127.0.0.1:8088/fetch_fello", {
        params: {
          id: sessionStorage.getItem('username'),
          fello: this.$route.query.id
        }
      }).then(response => {
        if (response.data == "1") {
          this.clicked = true
        } else {
          this.clicked = false
        }
      })
    }
    if (!this.$route.query.id || sessionStorage.getItem('username') == this.$route.query.id) {
      this.notme = false;
    } else this.notme = true;
    if (sessionStorage.getItem('username') != null && !this.$route.query.id) {
      this.user.name = sessionStorage.getItem('username')
    }
    if (sessionStorage.getItem('photo') != "http://127.0.0.1:8088/images/" && !this.$route.query.id) {
      this.user.avatar = sessionStorage.getItem('photo')
    }
    if (sessionStorage.getItem('banner') != "http://127.0.0.1:8088/images/" && !this.$route.query.id) {
      this.user.banner = sessionStorage.getItem('banner')
    }
    if (this.$route.query.id) {
      this.user.name = this.$route.query.id;
      this.user.avatar = this.$route.query.avatar;
      if (this.$route.query.banner != "http://127.0.0.1:8088/images/null") this.user.banner = this.$route.query.banner;
      this.user.followers = this.$route.query.fans;
      this.user.following = this.$route.query.followers;
      console.log(this.$route.query);
    }
    axios.post("http://127.0.0.1:8088/find_fellos", {
      id: this.$route.query.id
    }).then(response => {
      this.guan_list.push(response.data)
      console.log(this.guan_list[0][0])
    })
  }
}
</script>

<style lang="scss">
.guanzhu {
  display:flex;
  align-items:center;
}
.follow_btn {
  background: #ff8140;
  color: white;
  width: 80px;
  padding: 6px 16px;
  border-radius: 16px;
  cursor: pointer;
}

._follow_btn {
  background: #f33c2b;
  color: white;
  width: 80px;
  padding: 6px 16px;
  border-radius: 16px;
  cursor: pointer;
}

.follow {
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
  padding: 10px;
  color: var(--textColor);
}

.weibo-layout {
  display: flex;
  min-height: 100vh;
  color: var(--textColor);

  .left-nav {
    width: 240px;
    background: var(--bgColor);
    padding: 20px;

    .nav-header {
      display: flex;
      align-items: center;
      padding: 0 12px;
      margin-bottom: 30px;

      .icon-weibo {
        color: #ff8140;
        font-size: 32px;
        margin-right: 8px;
      }

      span {
        font-size: 20px;
        font-weight: 500;
      }
    }

    .menu-item {
      padding: 12px;
      margin: 4px 0;
      border-radius: 6px;
      cursor: pointer;

      &:hover {
        background: #f0f0f0;
      }

      .iconfont {
        margin-right: 12px;
        color: #666;
      }
    }
  }

  .main-content {
    background-color: var(--bgColor);
    flex: 1;
    padding: 20px;

    .profile-header {
      background: var(--bgColor);
      border-radius: 8px;

      .banner-image {
        width: 100%;
        height: 200px;
        object-fit: cover;
        border-radius: 8px 8px 0 0;
      }

      .profile-info {
        display: flex;
        padding: 20px;

        .user-avatar {
          width: 80px;
          height: 80px;
          border-radius: 50%;
          margin-top: -40px;
          border: 3px solid white;
        }

        .user-stats {
          margin-left: 20px;

          h2 {
            margin: 0;
            font-size: 24px;
          }

          .stats {
            margin-top: 12px;
            color: #666;

            span {
              margin-right: 20px;
            }
          }
        }
      }
    }

    .recommend-box {
      margin-top: 20px;
      background: var(--bgColor);
      padding: 20px;
      border-radius: 8px;

      .recommend-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;

        button {
          background: none;
          border: 1px solid #ff8140;
          color: #ff8140;
          padding: 4px 12px;
          border-radius: 16px;
        }
      }

      .recommend-item {
        display: flex;
        align-items: center;
        padding: 12px 0;
        border-bottom: 1px solid #eee;

        .rec-avatar {
          width: 48px;
          height: 48px;
          border-radius: 50%;
          margin-right: 12px;
        }

        .rec-name {
          font-weight: 500;

          span {
            font-size: 12px;
            color: #ff8140;
            border: 1px solid #ff8140;
            padding: 0 4px;
            margin-left: 8px;
          }
        }

        .rec-desc {
          color: #666;
          font-size: 12px;
        }

        .follow-btn {
          margin-left: auto;
          background: #ff8140;
          color: white;
          border: none;
          padding: 6px 16px;
          border-radius: 16px;
        }
      }
    }
  }

  .right-sidebar {
    width: 300px;
    padding: 20px;

    .search-box {
      position: relative;
      margin-bottom: 20px;

      input {
        width: 100%;
        padding: 8px 32px 8px 16px;
        border-radius: 20px;
        border: 1px solid #ddd;
      }

      .icon-search {
        position: absolute;
        right: 12px;
        top: 50%;
        transform: translateY(-50%);
        color: #999;
      }
    }

    .footer-info {
      color: #666;
      font-size: 12px;

      .links {
        margin: 15px 0;
        display: flex;
        flex-wrap: wrap;
        gap: 12px;

        a {
          color: #666;
          text-decoration: none;

          &:hover {
            color: #ff8140;
          }
        }
      }

      .copyright {
        margin-top: 20px;
      }
    }
  }
}
</style>