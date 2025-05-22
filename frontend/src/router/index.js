import { createRouter, createWebHistory } from "vue-router";
import weibo_main from "@/components/weibo_main.vue";
import weibo_login from "@/components/weibo_login.vue";
import weibo_register from "@/components/weibo_register.vue";
import weibo_homepage from "@/components/weibo_homepage.vue";
import weibo_setting from "@/components/weibo_setting.vue";

const routes = [
  { path: '/', redirect: '/weibo_main' },
  { path: '/weibo_main', component: weibo_main },
  { path: '/login', component: weibo_login},
  { path: '/register', component: weibo_register},
  { path: '/homepage', component: weibo_homepage},
  { path: '/setting', component: weibo_setting},
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router