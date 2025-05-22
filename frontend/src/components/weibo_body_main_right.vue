<template>
    <aside class="right-sidebar">
        <div class="hot-topics">
            <h3>微博热搜</h3>
            <ul>
                <li><a href="https://s.weibo.com/weibo?q=%23%E5%85%A8%E7%90%83%E8%82%A1%E5%B8%82%E5%B7%A8%E9%9C%87%23&topic_ad=&t=31">全球股市巨震</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%23%E9%87%91%E4%BB%B7%E5%86%8D%E5%BC%80%E8%B7%8C%23&topic_ad=&t=31">金价再开跌</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%23%E9%9B%86%E4%B8%AD%E7%B2%BE%E5%8A%9B%E5%8A%9E%E5%A5%BD%E8%87%AA%E5%B7%B1%E7%9A%84%E4%BA%8B%23&topic_ad=&t=31">集中精力办好自己的事情</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%E7%86%94%E6%96%AD&topic_ad=&t=31">熔断</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%23%E7%BE%8E%E8%B4%A2%E9%95%BF%E7%A7%B0%E8%B6%8550%E4%B8%AA%E5%9B%BD%E5%AE%B6%E8%81%94%E7%B3%BB%E4%BA%86%E7%BE%8E%E5%9B%BD%23&topic_ad=&t=31">美财长称超50个国家联系...</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%23%E5%93%AA%E5%90%922%E4%BB%8D%E5%9C%A8%E7%A5%A8%E6%88%BF%E6%A6%9C%E5%89%8D%E4%B8%89%23&topic_ad=&t=31">哪吒2仍在票房前三</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%E7%94%B2%E4%BA%A2%E5%93%A5%E4%BD%8F%E8%BF%87%E6%9C%80%E5%A5%BD%E7%9A%84%E9%85%92%E5%BA%97%E5%9C%A8%E4%B8%AD%E5%9B%BD&topic_ad=&t=31">甲亢哥住过最好的酒店</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%23%E4%BA%94%E5%93%885%E8%89%BA%E4%BA%BA%E5%AE%98%E5%AE%A3%23&topic_ad=&t=31">五哈5艺人官宣</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%23%E7%89%B9%E6%9C%97%E6%99%AE%E5%9B%9E%E5%BA%94%E5%B8%82%E5%9C%BA%E5%A4%A7%E8%B7%8C%23&topic_ad=&t=31">特朗普回应市场大跌</a></li>
                <li><a href="https://s.weibo.com/weibo?q=%23%E7%89%B9%E6%9C%97%E6%99%AE%E5%85%B3%E7%A8%8E%E5%BC%95%E5%8F%91%E7%9A%84%E9%9C%87%E8%8D%A1%E4%BC%9A%E6%8C%81%E7%BB%AD%E5%A4%9A%E4%B9%85%23&topic_ad=&t=31">特朗普关税引发的震荡会...</a></li>
            </ul>
            <div class="advertisement">
                <h3>advertisement</h3>
                <img :src="ad" alt="advertisement">
            </div>
        </div>
    </aside>
</template>

<script>
export default {
    name: 'weibo_body_main_right',
    data() {
        return {
            ad: require("@/assets/1234.png")
        };
    },
};
</script>

<style scoped>
    .right-sidebar {
    width: 280px;
    background: var(--bgColor);
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    padding: 20px;
    position: sticky;
    top: 80px;
}

/* ===== 热门话题样式 ===== */
.hot-topics h3 {
    color: #ff8200;
    /* 微博品牌橙 */
    font-size: 18px;
    font-weight: 700;
    margin: 0 0 15px;
    padding-bottom: 12px;
    border-bottom: 2px solid #eee;
    position: relative;
}

.hot-topics h3::after {
    content: "";
    position: absolute;
    left: 0;
    bottom: -2px;
    width: 50px;
    height: 2px;
    background: #ff8200;
}

.hot-topics ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

.hot-topics li {
    margin: 8px 0;
    transition: transform 0.2s ease;
}

.hot-topics li:hover {
    transform: translateX(8px);
}

.hot-topics a {
    color: var(--textColor);
    text-decoration: none;
    display: flex;
    align-items: center;
    padding: 8px 12px;
    border-radius: 8px;
    background: var(--bgColor);
    transition: all 0.2s;
}

.hot-topics a::before {
    content: "•";
    color: #ff8200;
    margin-right: 8px;
    font-size: 18px;
}

.hot-topics a:hover {
    background: #fff2e8;
    box-shadow: 0 2px 6px rgba(255, 130, 0, 0.1);
}

/* ===== 广告样式 ===== */
.advertisement {
    margin-top: 25px;
    border-top: 1px solid #eee;
    padding-top: 20px;
}

.advertisement h3 {
    color: #666;
    font-size: 14px;
    margin: 0 0 12px;
    font-weight: 500;
}

.advertisement img {
    width: 100%;
    height: 180px;
    border-radius: 8px;
    object-fit: cover;
    transition: transform 0.3s ease;
    cursor: pointer;
}

.advertisement img:hover {
    transform: scale(1.02);
}

</style>