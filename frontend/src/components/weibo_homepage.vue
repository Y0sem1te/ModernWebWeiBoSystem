<template>
  <div class="weibo-layout">
    <!-- 左侧导航 -->
    <div class="left-nav">
      <div class="nav-header">
        <i class="iconfont icon-weibo"></i>
        <svg t="1748481777756" class="icon" viewBox="0 0 1138 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
          p-id="6970" width="50" height="50">
          <path
            d="M914.432 518.144q27.648 21.504 38.912 51.712t9.216 62.976-14.336 65.536-31.744 59.392q-34.816 48.128-78.848 81.92t-91.136 56.32-94.72 35.328-89.6 18.944-75.264 7.68-51.712 1.536-49.152-2.56-68.096-10.24-78.336-21.504-79.872-36.352-74.24-55.296-59.904-78.848q-16.384-29.696-22.016-63.488t-5.632-86.016q0-22.528 7.68-51.2t27.136-63.488 53.248-75.776 86.016-90.112q51.2-48.128 105.984-85.504t117.248-57.856q28.672-10.24 63.488-11.264t57.344 11.264q10.24 11.264 19.456 23.04t12.288 29.184q3.072 14.336 0.512 27.648t-5.632 26.624-5.12 25.6 2.048 22.528q17.408 2.048 33.792-1.536t31.744-9.216 31.232-11.776 33.28-9.216q27.648-5.12 54.784-4.608t49.152 7.68 36.352 22.016 17.408 38.4q2.048 14.336-2.048 26.624t-8.704 23.04-7.168 22.016 1.536 23.552q3.072 7.168 14.848 13.312t27.136 12.288 32.256 13.312 29.184 16.384zM656.384 836.608q26.624-16.384 53.76-45.056t44.032-64 18.944-75.776-20.48-81.408q-19.456-33.792-47.616-57.344t-62.976-37.376-74.24-19.968-80.384-6.144q-78.848 0-139.776 16.384t-105.472 43.008-72.192 60.416-38.912 68.608q-11.264 33.792-6.656 67.072t20.992 62.976 42.496 53.248 57.856 37.888q58.368 25.6 119.296 32.256t116.224 0.512 100.864-21.504 74.24-33.792zM522.24 513.024q20.48 8.192 38.912 18.432t32.768 27.648q10.24 12.288 17.92 30.72t10.752 39.424 1.536 42.496-9.728 38.912q-8.192 18.432-19.968 37.376t-28.672 35.328-40.448 29.184-57.344 18.944q-61.44 11.264-117.76-11.264t-88.064-74.752q-12.288-39.936-13.312-70.656t16.384-66.56q13.312-27.648 40.448-51.712t62.464-38.912 75.264-17.408 78.848 12.8zM359.424 764.928q37.888 3.072 57.856-18.432t21.504-48.128-15.36-47.616-52.736-16.896q-27.648 3.072-43.008 23.552t-17.408 43.52 9.728 42.496 39.424 21.504zM778.24 6.144q74.752 0 139.776 19.968t113.664 57.856 76.288 92.16 27.648 122.88q0 33.792-16.384 50.688t-35.328 17.408-35.328-14.336-16.384-45.568q0-40.96-22.528-77.824t-59.392-64.512-84.48-43.52-96.768-15.872q-31.744 0-47.104-15.36t-14.336-34.304 18.944-34.304 51.712-15.36zM778.24 169.984q95.232 0 144.384 48.64t49.152 146.944q0 30.72-10.24 43.52t-22.528 11.264-22.528-14.848-10.24-35.84q0-60.416-34.816-96.256t-93.184-35.84q-19.456 0-28.672-10.752t-9.216-23.04 9.728-23.04 28.16-10.752z"
            p-id="6971" fill="#d81e06"></path>
        </svg>
      </div>
      <div class="nav-menu">
        <div v-for="item in navItems" :key="item.name" class="menu-item" @click="go(item.name)">
          <i :class="['iconfont', item.icon]"></i>
          {{ item.name }}
        </div>
      </div>
    </div>

    <!-- 主体内容 -->
    <div class="key-part">
      <div class="main-content" v-if="fello === 1">
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
            <div class="_follow_btn" v-else-if="this.clicked == true && notme" @click="tfollow(user.name)">已关注</div>
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
      <div class="fello-list" v-if="fello === 3">
        <h3 class="fello-title">我的关注</h3>
        <div v-if="guan_list && guan_list.length" class="fello-container">
          <div v-for="user in guan_list" :key="user.followerid" class="fello-user">
            <img :src="user.photo" alt="头像" class="fello-avatar" />
            <div class="fello-info">
              <p class="fello-name">{{ user.followerid }}</p>
            </div>
            <button class="unfollow-btn" @click="tfollow(user.followerid)">
              取消关注
            </button>
          </div>
        </div>

        <div v-else class="empty-state">
          暂无关注的人
        </div>
      </div>
      <!--这里放用户的微博-->
      <!--wzt-->
      <div class="weibo_list" v-if="fello === 2">
        <h2>我的微博</h2>
        <div class="WeiBo-list">
          <div class="WeiBo-item" v-for="weibo in weibos" :key="weibo.id">
            <div class="justify-WeiBo-item">
              <div class="show-item">
                <div class="pic-describe">
                  <div class="pic"><!--avatar-->
                    <img :src="weibo.avatar" alt="" style="height:48px;width:48px;border-radius:50%">
                  </div>
                </div>
                <div class="discribe">
                  <span>
                    <p>{{ weibo.uid }}</p>
                  </span>
                  <div class="post-time">{{ weibo.time }}</div>
                </div>
                <el-button type="primary" :icon="Delete" style="margin-left:30px;" v-if="checkDelete(weibo.uid)" @click="delete_weibo(weibo.id)" />
              </div>
              <div class="user-content">
                <div class="character">
                  {{ weibo.content }}
                </div>
                <div class="user-attach-pic" v-if="weibo.photos[0] != 'http://127.0.0.1:8088/images/'">
                  <div class="modify_pic">
                    <img :src="item" v-for="item in weibo.photos" :key="item" style="height:132px;width:132px;">
                  </div>
                </div>
                <div>
                  <video width="735" v-if="weibo.video != 'http://127.0.0.1:8088/images/'" controls
                    :src="weibo.video" style="margin-top:5px;"></video>
                </div>
                <div class="user_comment">
                  <div class="comments" @click="cshowcomment(weibo.id)">
                    <svg t="1743999501010" class="icon" viewBox="0 0 1024 1024" version="1.1"
                      xmlns="http://www.w3.org/2000/svg" p-id="4754" width="30" height="30">
                      <path
                        d="M850.879104 96.41591l-676.303067 0c-60.681034 0-110.049418 49.367361-110.049418 110.049418l0 446.200388c0 60.681034 49.367361 110.049418 110.049418 110.049418l90.307795 0L396.936381 931.129846c3.793396 4.838192 9.598612 7.66354 15.746636 7.66354s11.952216-2.825348 15.746636-7.66354l132.052548-168.414711 290.396903 0c60.681034 0 110.049418-49.367361 110.049418-110.049418L960.928522 206.465329C960.928522 145.784294 911.561162 96.41591 850.879104 96.41591zM920.91111 652.665717c0 38.614459-31.416524 70.030983-70.030983 70.030983L550.744419 722.6967c-6.147 0-11.952216 2.825348-15.745612 7.66354L412.683017 886.356107l-122.31579-155.995867c-3.792373-4.838192-9.597589-7.66354-15.745612-7.66354l-100.045577 0c-38.614459 0-70.030983-31.416524-70.030983-70.030983L104.545054 206.465329c0-38.614459 31.416524-70.030983 70.030983-70.030983l676.303067 0c38.614459 0 70.030983 31.416524 70.030983 70.030983L920.910087 652.665717z"
                        fill="#d81e06" p-id="4755"></path>
                      <path
                        d="M272.621051 344.526731c-44.132126 0-80.035848 35.903721-80.035848 80.035848 0 44.132126 35.903721 80.036871 80.035848 80.036871s80.035848-35.904745 80.035848-80.036871C352.655875 380.430452 316.752154 344.526731 272.621051 344.526731zM272.621051 464.582037c-22.065552 0-40.017412-17.951861-40.017412-40.018436 0-22.065552 17.952884-40.017412 40.017412-40.017412 22.065552 0 40.017412 17.951861 40.017412 40.017412C312.638463 446.629153 294.686602 464.582037 272.621051 464.582037z"
                        fill="#d81e06" p-id="4756"></path>
                      <path
                        d="M512.727571 344.526731c-44.132126 0-80.035848 35.903721-80.035848 80.035848 0 44.132126 35.903721 80.036871 80.035848 80.036871 44.132126 0 80.035848-35.904745 80.035848-80.036871C592.763418 380.430452 556.859697 344.526731 512.727571 344.526731zM512.727571 464.582037c-22.065552 0-40.017412-17.951861-40.017412-40.018436 0-22.065552 17.951861-40.017412 40.017412-40.017412 22.065552 0 40.017412 17.951861 40.017412 40.017412C552.746006 446.629153 534.793122 464.582037 512.727571 464.582037z"
                        fill="#d81e06" p-id="4757"></path>
                      <path
                        d="M752.836137 344.526731c-44.131103 0-80.035848 35.903721-80.035848 80.035848 0 44.132126 35.904745 80.036871 80.035848 80.036871s80.035848-35.904745 80.035848-80.036871C832.871985 380.430452 796.96724 344.526731 752.836137 344.526731zM752.836137 464.582037c-22.066575 0-40.017412-17.951861-40.017412-40.018436 0-22.065552 17.951861-40.017412 40.017412-40.017412s40.017412 17.951861 40.017412 40.017412C792.853549 446.629153 774.902712 464.582037 752.836137 464.582037z"
                        fill="#d81e06" p-id="4758"></path>
                    </svg>
                    <span>{{ weibo.comment }}</span>
                  </div>
                  <!-- <div class="likes" @click="like(weibo.id)" v-if="weibo.liked == false">
                    <svg t="1743999556693" class="icon" viewBox="0 0 1024 1024" version="1.1"
                      xmlns="http://www.w3.org/2000/svg" p-id="5936" width="30" height="30">
                      <path
                        d="M190.193225 471.411583c14.446014 0 26.139334-11.718903 26.139334-26.13831 0-14.44499-11.69332-26.164916-26.139334-26.164916-0.271176 0-0.490164 0.149403-0.73678 0.149403l-62.496379 0.146333c-1.425466-0.195451-2.90005-0.295735-4.373611-0.295735-19.677155 0-35.621289 16.141632-35.621289 36.114522L86.622358 888.550075c0 19.949354 15.96767 35.597753 35.670407 35.597753 1.916653 0 3.808746 0.292666 5.649674 0l61.022819 0.022513c0.099261 0 0.148379 0.048095 0.24764 0.048095 0.097214 0 0.146333-0.048095 0.24457-0.048095l0.73678 0 0-0.148379c13.413498-0.540306 24.174586-11.422144 24.174586-24.960485 0-13.55983-10.760065-24.441669-24.174586-24.981974l0-0.393973-50.949392 0 1.450025-402.275993L190.193225 471.409536z"
                        fill="#d81e06" p-id="5937"></path>
                      <path
                        d="M926.52241 433.948343c-19.283182-31.445176-47.339168-44.172035-81.289398-45.546336-1.77032-0.246617-3.536546-0.39295-5.380544-0.39295l-205.447139-0.688685c13.462616-39.059598 22.698978-85.58933 22.698978-129.317251 0-28.349675-3.193739-55.962569-9.041934-82.542948l-0.490164 0.049119c-10.638291-46.578852-51.736315-81.31498-100.966553-81.31498-57.264215 0-95.466282 48.15065-95.466282 106.126063 0 3.241834-0.294712 6.387477 0 9.532097-2.996241 108.386546-91.240027 195.548698-196.23636 207.513194l0 54.881958-0.785899 222.227314 0 229.744521 10.709923 0 500.025271 0.222057 8.746198-0.243547c19.35686 0.049119 30.239721-4.817726 47.803749-16.116049 16.682961-10.761088 29.236881-25.50079 37.490869-42.156122 2.260483-3.341095 4.028757-7.075139 5.106298-11.20111l77.018118-344.324116c1.056052-4.053316 1.348718-8.181333 1.056052-12.160971C943.643346 476.446249 938.781618 453.944769 926.52241 433.948343zM893.82573 486.837924l-82.983993 367.783411-0.099261-0.049119c-2.555196 6.141884-6.879688 11.596106-12.872169 15.427364-4.177136 2.727111-8.773827 4.351098-13.414521 4.964058-1.49812-0.195451-3.046383 0-4.620227 0l-477.028511-0.540306-0.171915-407.408897c89.323375-40.266076 154.841577-79.670527 188.596356-173.661202 0.072655 0.024559 0.124843 0.049119 0.195451 0.072655 2.99931-9.137101 6.313799-20.73423 8.697079-33.164331 5.551436-29.185716 5.258771-58.123792 5.258771-58.123792-4.937452-37.98001 25.940812-52.965306 44.364417-52.965306 25.304316 0.860601 50.263777 33.656541 50.263777 52.326762 0 0 5.600555 27.563776 5.649674 57.190537 0.048095 37.366026-4.6673 56.847729-4.6673 56.847729l-0.466628 0c-5.872754 30.879288-16.214287 60.138682-30.464849 86.964654l0.36839 0.342808c-2.358721 4.815679-3.709485 10.220782-3.709485 15.943111 0 19.922748 19.088754 21.742187 38.765909 21.742187l238.761895 0.270153c0 0 14.666024 0.465604 14.690584 0.465604l0 0.100284c12.132318-0.638543 24.221658 5.207605 31.100322 16.409738 5.504364 9.016351 6.437619 19.6045 3.486404 28.988218L893.82573 486.837924z"
                        fill="#d81e06" p-id="5938"></path>
                      <path
                        d="M264.827039 924.31872c0.319272 0.024559 0.441045 0.024559 0.295735-0.024559 0.243547-0.048095 0.367367-0.074701-0.295735-0.074701s-0.539282 0.026606-0.271176 0.074701C264.43409 924.343279 264.532327 924.343279 264.827039 924.31872z"
                        fill="#d81e06" p-id="5939"></path>
                    </svg>
                    <span>{{ weibo.like }}</span>
                  </div>
                  <div class="_likes" @click="_like(weibo.id)" v-else-if="weibo.liked === true">
                    <svg t="1744095727208" class="icon" viewBox="0 0 1024 1024" version="1.1"
                      xmlns="http://www.w3.org/2000/svg" p-id="5149" width="30" height="30">
                      <path
                        d="M64 483.04V872c0 37.216 30.144 67.36 67.36 67.36H192V416.32l-60.64-0.64A67.36 67.36 0 0 0 64 483.04zM857.28 344.992l-267.808 1.696c12.576-44.256 18.944-83.584 18.944-118.208 0-78.56-68.832-155.488-137.568-145.504-60.608 8.8-67.264 61.184-67.264 126.816v59.264c0 76.064-63.84 140.864-137.856 148L256 416.96v522.4h527.552a102.72 102.72 0 0 0 100.928-83.584l73.728-388.96a102.72 102.72 0 0 0-100.928-121.824z"
                        p-id="5150" fill="#d81e06"></path>
                    </svg>
                    {{ weibo.like + 1 }}
                  </div> -->
                </div>
                <div class="showcomments" v-if="weibo.showComments">
                  <div class="post_commment">
                    <div class="userscomment">
                      <div class="userscommment_img"><img id="1243" :src="userphoto" alt=""></div>
                      <div class="userscomment_txt">
                        <!-- <textarea class="user-post-comment" placeholder="说点什么吧......"
                          v-model="commentContent"></textarea> -->
                        <div></div>
                      </div>
                    </div>
                    <div class="justify_post_button">
                      <!-- <div class="commment_btn" tabindex="0" data-focus-visible="true" @click="postComment(weibo.id)">
                        发布 </div> -->
                      <div></div>
                    </div>
                  </div>
                  <div class="rshowcomments" v-for="comment in commentsMap.get(weibo.id)" :key="comment.id">
                    <div class="user_comments">
                      <div class="ravatar">
                        <img :src="comment.avatar" style="height: 40px; width: 40px; border-radius: 50%;" alt="">
                      </div>
                      <div class="rcontent_time">
                        <div class="rusername_info">
                          <div class="rusername">
                            <span>{{ comment.username }}</span>
                          </div>
                          <div class="rcontent">
                            {{ comment.content }}
                          </div>
                        </div>
                        <div class="time">
                          <span>{{ comment.date }}</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="fans_list" v-if="fello === 3">

      </div>
      <div class="like_comment_list" v-if="fello === 4">

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
import { Delete } from '@element-plus/icons-vue'
export default {
  data() {
    return {
      Delete,
      commentContent: '',
      if_show_comments: false,
      error: null,
      commentsMap: new Map(),
      comments: [],
      weibos: [],
      guan_list: [], // 关注列表
      fello: 1,
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
        { name: '我的微博', icon: 'icon-weibo' },
        { name: '我的关注', icon: 'icon-follow' },
        { name: '我的粉丝', icon: 'icon-fans' },
        { name: '赞和评论', icon: 'icon-like' },
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
      if (name === "我的主页") this.fello = 1;
      else if (name === "我的微博") this.fello = 2;
      else if (name === "我的关注") {
        this.fello = 3;
        axios.get("http://127.0.0.1:8088/guan", {
          params: {
            id: this.user.name
          }
        }).then(response => {
          // this.guan_list.push(response.data.target);
          this.guan_list = response.data
          console.log(this.guan_list)
          // console.log(this.guan_list[0].followerid)
        })
          .catch(err => console.error(err));
      }
      else if (name === "我的粉丝") this.fello = 4;
      else if (name === "赞和评论") this.fello = 5;
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
    tfollow(username) {
      this.clicked = !this.clicked
      axios.post("http://127.0.0.1:8088/tfollow", { userid: sessionStorage.getItem('username'), followerid: username }).then(response => {
        if (response.data == "success") {
          alert("取关成功");
        } else {
          alert("取关失败");
        }
      })
    },
    purposely_fetch(username) {
      try {
        axios.post("http://127.0.0.1:8088/purposely_fetch", { id: username }).then((response) => {
          this.weibos = response.data.map(weibo => ({
            ...weibo,
            showComments: false,
          }));
          console.log(response);
        })
      } catch (err) {
        this.error = err.message;
        console.error("获取微博失败：", err)
      }
    },
    cshowcomment(weiboid) {
      this.weibos = this.weibos.map(weibo => {
        if (weibo.id === weiboid) {
          return { ...weibo, showComments: !weibo.showComments };
        }
        else return weibo;
      })
      axios.post("http://127.0.0.1:8088/fetch_comments", { id: weiboid }).then((response) => {
        this.comments = response.data;
        this.commentsMap.set(weiboid, response.data);
      })
    },
    checkDelete(uid){
        if(uid.toString() === sessionStorage.getItem('username')){return true;}
        return false;
    },
    delete_weibo(wid){
      axios.get("http://localhost:8088/delete_weibo", {
        params:{
          id:wid
        }
      }).then((response) => {
        alert(response.data);
      })
    },
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
    }
    axios.post("http://127.0.0.1:8088/find_fellos", {
      id: this.$route.query.id
    }).then(response => {
      this.guan_list.push(response.data)
    })
    this.purposely_fetch(this.user.name);
  }
}
</script>

<style lang="scss">
.key-part {
  flex: 8;
}

.guanzhu {
  display: flex;
  align-items: center;
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
    flex: 1;

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
    flex: 2;

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

//开始渲染关注列表

.fello-title {
  font-size: 20px;
  margin-bottom: 16px;
  color: var(--textColor);
  border-left: 4px solid #ff8140;
  padding-left: 8px;
}

/* 列表容器：多行网格布局 */
.fello-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 16px;
}

/* 单个卡片样式 */
.fello-user {
  display: flex;
  align-items: center;
  background: var(--bgColorContrast, #fff);
  padding: 12px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease, box-shadow 0.2s ease;

  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
}

/* 头像 */
.fello-avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 12px;
}

/* 用户信息 */
.fello-info {
  flex: 1;

  .fello-name {
    margin: 0;
    font-size: 16px;
    font-weight: 500;
    color: var(--textColor);
  }
}

/* 取消关注按钮 */
.unfollow-btn {
  background: none;
  border: 1px solid #ccc;
  color: #666;
  padding: 4px 10px;
  border-radius: 12px;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.2s ease, border-color 0.2s ease, color 0.2s ease;

  &:hover {
    background: #f5f5f5;
    border-color: #bbb;
    color: #333;
  }
}

/* 空状态 */
.empty-state {
  padding: 24px;
  text-align: center;
  color: #999;
  font-size: 14px;
}

//停止对关注列表渲染

//开始博客渲染
.WeiBo-item {
  border: 2px solid #333;
}
.justify-WeiBo-item {
  display:flex;
  flex-direction: column;
  padding-top:10px;
  margin-top:15px;
  margin-left:50px;
}
.show-item {
  display: flex;
  margin-top: 5px;
  margin-left: 5px;
}

.show-item .discribe {
  display: felx;
  margin-left:10px;
  padding-bottom: 5px;
}
.rshowcomments {
  border: 1px solid #333;
}
.user_comments {
  margin-left: 20px;
  margin-top:5px;
  margin-bottom: 5px;
}
//解释博客渲染
</style>