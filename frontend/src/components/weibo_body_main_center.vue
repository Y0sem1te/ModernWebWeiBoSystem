<template>
    <section class="content">
        <div class="top-share-bar">
            <div class="justify-post-form">
                <div class="post-form">
                    <textarea v-model="content" placeholder="有什么新鲜事想分享给大家？"></textarea>
                </div>
            </div>
            <!--预览图片部分-->
            <div class="justify_preview">
                <div class="father_preview">
                    <div v-if="previewUrl.length != 0" class="preview-container">
                        <img :src="item" alt="Preview" class="preview-image" v-for="item in previewUrl" :key="item"
                            @click="removeImage(item)" />
                    </div>
                </div>
            </div>
            <div class="post-part-tot">
                <div class="interactive-image">
                    <!--其实用的是input type=“file”，伪装一下-->
                    <input type="file" ref="fileInput" accept="image/*" @change="handleFileChange($event)"
                        style="display: none;" />
                    <!--输入多张图片-->
                    <div class="custom-upload-button" @click="triggerFileInput">
                        <svg t="1743826541712" class="icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="8328" width="30" height="30">
                            <path
                                d="M853.161077 892.549156 362.595248 892.549156l-209.432916-0.413416c-0.605797-0.001023-1.210571-0.031722-1.813299-0.092098-24.848944-2.484587-47.825238-14.060227-64.696488-32.594349-16.990976-18.665105-26.349111-42.85504-26.349111-68.112284L60.303434 264.62596c0-55.80805 45.403073-101.211123 101.211123-101.211123l691.645496 0c55.80805 0 101.2101 45.403073 101.2101 101.211123l0 225.51315c0 0.275269-0.00614 0.551562-0.01842 0.825808-0.021489 0.494257-1.971911 51.723012 15.481599 85.46244 4.716418 9.118682 1.14815 20.335141-7.970532 25.052582-9.116635 4.714372-20.335141 1.149173-25.052582-7.970532-21.300119-41.176818-19.844977-97.642854-19.618826-103.738689L917.191392 264.62596c0-35.307134-28.724205-64.031339-64.031339-64.031339L161.51558 200.594621c-35.307134 0-64.031339 28.724205-64.031339 64.031339l0 526.71105c0 32.755008 24.320918 59.957557 56.717769 63.61997l208.4311 0.412392 490.528989 0c35.307134 0 64.031339-28.725228 64.031339-64.032362l-0.382717-93.676519c-0.104377-1.749854-1.587148-19.548218-19.549242-42.499953-0.050142-0.063445-0.098237-0.125867-0.147356-0.190335L875.401614 626.481358 758.174726 471.362464c-0.415462-0.550539-38.995129-50.852178-86.271876-45.534056-38.335097 4.314259-75.954903 45.163619-108.789729 118.131491-17.615193 39.141462-34.650171 68.26885-52.082192 89.046059-17.607006 20.985964-35.679617 33.519418-55.251372 38.316677-43.422975 10.638291-81.049944-18.99461-120.886231-50.372248l-5.057179-3.980661c-46.555315-36.57808-68.750827-28.223808-158.330028 59.60247-7.330966 7.187703-19.101033 7.071046-26.288736-0.25992-7.187703-7.330966-7.071046-19.101033 0.25992-26.287713 46.658669-45.74588 77.544097-72.726372 107.085924-84.282568 33.357735-13.048177 64.274886-6.266727 100.242052 21.99392l5.092995 4.00829c33.9226 26.719548 63.219857 49.795103 89.028663 43.466977 25.618471-6.279007 53.30095-42.114167 82.279958-106.508779 39.139415-86.97591 85.837994-134.027529 138.79716-139.849118 68.454068-7.515161 117.823476 57.404408 119.891578 60.171428l117.122511 154.980747 21.599947 28.343535c26.276457 33.630958 27.333532 61.638849 27.367301 64.72514 0.001023 0.042979 0.001023 0.084934 0.001023 0.127913l0.38374 94.059236C954.371176 847.146083 908.969127 892.549156 853.161077 892.549156z"
                                fill="#d81e06" p-id="8329"></path>
                            <path
                                d="M312.328401 446.967868c-42.324968 0-76.759221-34.434254-76.759221-76.759221s34.434254-76.759221 76.759221-76.759221 76.759221 34.434254 76.759221 76.759221S354.654392 446.967868 312.328401 446.967868zM312.328401 330.628186c-21.824051 0-39.579437 17.755386-39.579437 39.579437s17.755386 39.579437 39.579437 39.579437 39.579437-17.755386 39.579437-39.579437S334.153476 330.628186 312.328401 330.628186z"
                                fill="#d81e06" p-id="8330"></path>
                        </svg>
                    </div>
                    <div class="add_#" @click="addHash">
                        <svg t="1743826638251" class="icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="10321" width="30" height="30">
                            <path
                                d="M476.021333 544h63.424l8.533334-64h-63.424l-8.533334 64z m-8.533333 64l-9.098667 68.224a32 32 0 0 1-63.445333-8.448L402.912 608H352a32 32 0 0 1 0-64h59.445333l8.533334-64H352a32 32 0 0 1 0-64h76.512l9.098667-68.224a32 32 0 0 1 63.445333 8.448L493.088 416h63.424l9.098667-68.224a32 32 0 0 1 63.445333 8.448L621.088 416H672a32 32 0 0 1 0 64h-59.445333l-8.533334 64H672a32 32 0 0 1 0 64h-76.512l-9.098667 68.224a32 32 0 0 1-63.445333-8.448L530.912 608h-63.424zM157.568 751.296c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667-191.029333 426.666667-426.666667 426.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288z m65.301333 3.786667l-17.258666 63.306666 63.306666-17.258666a32 32 0 0 1 24.522667 3.210666 4515.84 4515.84 0 0 1 32.352 18.944A360.789333 360.789333 0 0 0 512 874.666667c200.298667 0 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333 149.333333 311.701333 149.333333 512c0 60.586667 14.848 118.954667 42.826667 171.136 3.712 6.912 12.928 22.826667 27.370667 47.232a32 32 0 0 1 3.338666 24.714667z"
                                fill="#d81e06" p-id="10322"></path>
                        </svg>
                    </div>
                </div>
                <div class="post-part">
                    <!---->
                    <div class="btn-post" tabindex="0" data-focus-visible="true" @click="post_weibo"> 发布 </div>
                </div>
            </div>
        </div>
        <div class="wbpro-tab2">
            <div class="woo-box-item-inlineBlock cur">
                <div class="woo-box-flex woo-box-alignCenter woo-box-justifyCenter">
                    <div class="wbpro-textcut" tabindex="0" data-focus-visible="true"> 全部 </div>
                </div><!---->
            </div>
            <div class="woo-box-item-inlineBlock">
                <div class="woo-box-flex woo-box-alignCenter woo-box-justifyCenter">
                    <div class="wbpro-textcut" tabindex="0" data-focus-visible="true"> 原创 </div>
                </div><!---->
            </div>
            <div class="woo-box-item-inlineBlock">
                <div class="woo-box-flex woo-box-alignCenter woo-box-justifyCenter">
                    <div class="wbpro-textcut" tabindex="0" data-focus-visible="true"> 视频 </div>
                </div><!---->
            </div>
            <div class="woo-box-item-inlineBlock">
                <div class="woo-box-flex woo-box-alignCenter woo-box-justifyCenter">
                    <div class="wbpro-textcut" tabindex="0" data-focus-visible="true"> 超话社区 </div>
                </div><!---->
            </div>
            <div class="woo-box-item-inlineBlock">
                <div class="woo-box-flex woo-box-alignCenter woo-box-justifyCenter">
                    <div class="wbpro-textcut" tabindex="0" data-focus-visible="true"> V+微博 </div>
                </div><!---->
            </div>
            <div class="woo-box-item-inlineBlock">
                <div class="woo-box-flex woo-box-alignCenter woo-box-justifyCenter">
                    <div class="wbpro-textcut" tabindex="0" data-focus-visible="true"> 群微博 </div>
                </div><!---->
            </div>
            <div class="woo-box-item-inlineBlock"></div>
        </div>
        <div class="WeiBo-list">
            <div class="WeiBo-item" v-for="weibo in weibos" :key="weibo.id">
                <div class="justify-WeiBo-item">
                    <div class="show-item">
                        <div class="pic-describe" @click="showUser(weibo.uid)">
                            <div class="pic"><!--avatar-->
                                <img :src="weibo.avatar" alt="">
                            </div>
                        </div>
                        <div class="discribe">
                            <span>
                                <p>{{ weibo.uid }}</p>
                            </span>
                            <div class="post-time">{{ weibo.time }}</div>
                        </div>
                    </div>
                    <div class="user-content">
                        <div class="character">
                            {{ weibo.content }}
                        </div>
                        <div class="user-attach-pic" v-if="weibo.photos[0]!='http://127.0.0.1:8088/images/'">
                            <div class="modify_pic">
                                <img :src="item" v-for="item in weibo.photos" :key="item">
                            </div>
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
                            <div class="likes" @click="like(weibo.id)" v-if="weibo.liked == false">
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
                            </div>
                        </div>
                        <div class="showcomments" v-if="weibo.showComments">
                            <div class="post_commment">
                                <div class="userscomment">
                                    <div class="userscommment_img"><img id="1243" :src="userphoto" alt=""></div>
                                    <div class="userscomment_txt">
                                        <textarea class="user-post-comment" placeholder="说点什么吧......"
                                            v-model="commentContent"></textarea>
                                    </div>
                                </div>
                                <div class="justify_post_button">
                                    <div class="commment_btn" tabindex="0" data-focus-visible="true"
                                        @click="postComment(weibo.id)"> 发布 </div>
                                </div>
                            </div>
                            <div class="rshowcomments" v-for="comment in commentsMap.get(weibo.id)" :key="comment.id">
                                <div class="user_comments">
                                    <div class="ravatar">
                                        <img :src="comment.avatar"
                                            style="height: 40px; width: 40px; border-radius: 50%;" alt="">
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
    </section>
</template>

<script>
import axios from 'axios'
import moment from 'moment'
export default {
    name: "weibo_body_main_center",
    data() {
        return {
            commentContent: '',
            if_show_comments: false,
            commentsMap: new Map(),
            weibos: [],
            comments: [],
            weiboid: null,
            loading: false,
            error: null,
            content: '',
            selectedFile: [],
            previewUrl: [],
            userphoto: require("@/assets/gXOn48bPe6CXLpGz-generated_image.jpg"),
            picture: require("@/assets/gXOn48bPe6CXLpGz-generated_image.jpg"),
        };
    },
    methods: {
        showUser(uid){
            axios.post("http://127.0.0.1:8088/homepage", {id: uid}).then(response=>{
                console.log(response)
                const params = {
                    id: response.data.id,
                    fans: response.data.fans,
                    followers: response.data.followers,
                    avatar: response.data.photo,
                    banner: response.data.banner,
                }
                this.$router.push({path: '/homepage', query: params});
            })
        },
        addHash(){
            this.content += '#';
        },
        postComment(weiboid) {
            axios.post("http://127.0.0.1:8088/postComment", { userId: sessionStorage.getItem('username'), weiboId: weiboid, content: this.commentContent }).then(response => {
                console.log(response)
                if (response.data == "success") {
                    alert("评论成功");
                    const newComment = {
                        id: weiboid,
                        username: sessionStorage.getItem('username'),
                        content: this.commentContent,
                        date: moment().format('YYYY-MM-DD HH:mm'),
                        avatar: sessionStorage.getItem('photo'),
                    }
                    const weibo = this.weibos.find(weibo => weibo.id === weiboid);
                    if (weibo) {
                        if (!this.commentsMap.has(weiboid)) {
                            this.commentsMap.set(weiboid, []);
                        }
                        this.commentsMap.get(weiboid).push(newComment);

                        this.commentContent = '';
                    }
                }
                else alert("评论失败");
            })
        },
        _like(weiboid) {
            axios.post("http://127.0.0.1:8088/subLike", { id: weiboid, userid: sessionStorage.getItem('username') }).then(() => {
                // console.log(response);
            })
            this.weibos = this.weibos.map(weibo => {
                if (weibo.id === weiboid) {
                    return { ...weibo, liked: !weibo.liked };
                }
                else return weibo;
            })
        },
        like(weiboid) {
            axios.post("http://127.0.0.1:8088/addLike", { id: weiboid, userid: sessionStorage.getItem('username') }).then(() => {
                // console.log(response);
            })
            this.weibos = this.weibos.map(weibo => {
                if (weibo.id === weiboid) {
                    return { ...weibo, liked: !weibo.liked };
                }
                else return weibo;
            })
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
        triggerFileInput() {
            this.$refs.fileInput.click();
        },
        handleFileChange(event) {
            const file = event.target.files[0];
            if (!file) return;
            this.selectedFile.push(file);
            this.previewUrl.push(URL.createObjectURL(file).toString());
            //console.log(URL.createObjectURL(file).toString())
        },
        removeImage(item) {
            this.selectedFile = this.selectedFile.filter(it => it !== item);
            this.previewUrl = this.previewUrl.filter(it => it !== item);
            //console.log(this.previewUrl);
            this.$refs.fileInput.value = '';
        },
        post_weibo() {
            // photo: this.previewUrl; text: content;
            if (this.previewUrl.length == 0 && this.content == '') {
                alert("不能发送空微博哦......");
                return;
            }
            const formData = new FormData();
            formData.append('content', this.content);
            formData.append('uid', sessionStorage.getItem('username'));
            formData.append('time', moment().format('YYYY-MM-DD HH:mm'));
            if(this.selectedFile.length > 0){
                this.selectedFile.forEach((file) => {
                    formData.append('images[]', file);
                });
            }
            axios.post("http://127.0.0.1:8088/upload_weibo", formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            }).then((response) => {
                alert("成功上传");
                console.log("上传了" + response + "个图片文件");
            })
            this.content = '';
            this.selectedFile = [];
            this.previewUrl = [];
            this.$refs.fileInput.value = '';
        },
        radomly_fetch() {
            this.loading = true;
            this.error = null;
            const username = sessionStorage.getItem('username');
            if (!username) {
                console.error("用户未登录");
                alert("用户未登录");
                return;
            }
            try {
                axios.post("http://127.0.0.1:8088/fetch_weibo", { id: sessionStorage.getItem('username') }).then((response) => {
                    this.weibos = response.data.map(weibo => ({
                        ...weibo,
                        showComments: false,
                    }));
                    console.log(response);
                })
            } catch (err) {
                this.error = err.message;
                console.error("获取微博失败：", err)
            } finally {
                this.loading = false;
            }
        },

    },
    mounted() {
        this.radomly_fetch();
        const photo = sessionStorage.getItem('photo');
        if (photo) this.userphoto = photo;
    }
};
</script>

<style scoped>
.user_comments {
    display: flex;
}

.rshowcomments {
    display: flex;
    align-items: center;
    padding: 10px;
}

.rcontent_time {
    display: flex;
    flex-direction: column;
    margin-left: 10px;
    color: var(--textColor);
}

.commment_btn {
    background-color: orange;
    color: #fff;
    border: none;
    border-radius: 2.25rem;
    padding: 8px 16px;
    text-align: center;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
    width: 75px;
    margin-left: 700px;
}

.commment_btn:hover {
    background-color: red;
}

.justify_post_button {
    display: flex;
}

.userscommment_img img {
    width: 48px;
    height: 48px;
    border-radius: 50%;
}

.userscomment_txt {
    width: 100%;
    height: 100%;
    margin-left: 10px;
}

.userscomment {
    display: flex;
    align-items: center;
    margin-top: 10px;
    padding: 10px;
    background-color: var(--bgColor);
}

.user_comment {
    display: flex;
    align-items: center;
    margin-top: 10px;
    padding: 10px;
    background-color: var(--bgColor);
    color: var(--textColor);
}

.likes {
    margin-left: 60px;
}

._likes {
    margin-left: 60px;
    color: red;
}

.content {
    width: 1000px;
    position: sticky;
    top: 0;
    min-height: calc(100vh - 40px);
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: var(--main-grd);
}

/*输入框样式*/
.content textarea {
    border: 2px solid black;
    border-radius: 8px;
    width: 100%;
    height: 61.6px;
    color: var(--textColor);
    resize: none;
    padding: 10px;
    font-size: 16px;
    transition: border-color 0.3s ease;
    background-color: var(--bgColor);
}

.content textarea:focus {
    border-color: orange;
    outline: none;
}

/*post条的装饰*/

.interactive-image a {
    text-decoration: none;
    /* 去掉下划线 */
}

.post-part {
    display: flex;
    gap: 10px;
    padding: 10px;
    justify-content: space-around;
    align-items: center;
}

.post-part-tot {
    display: flex;
    gap: 10px;
    padding: 10px;
    justify-content: space-around;
    align-items: center;
}

/*post块设计*/
.top-share-bar {
    background-color: var(--bgColor);
    width: 100%;
    margin-top: 0px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

.post-form {
    display: flex;
    width: 90%;
}

.justify-post-form {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-top: 20px;
}

/*post条下面的选项设计*/
.wbpro-tab2 {
    display: flex;
    justify-content: space-around;
    align-items: center;
    width: 100%;
    margin-top: 10px;
    margin: 10px;
    background-color: var(--bgColor);
}

.wbpro-textcut {
    padding: 10px 20px;
    background-color: var(--bgColor);
    border: 1px solid #ccc;
    border-radius: 50px;
    font-size: 14px;
    color: var(--textColor);
    cursor: pointer;
    transition: background-color 0.3s, border-color 0.3s;
    border: 0;
}

.wbpro-textcut:hover {
    background-color: #e12525;
    border-color: #ea841e;
}

.woo-box-item-inlineBlock {
    margin: 10px;
}

.wbpro-textcut:focus-visible {
    outline: none;
    border-color: #2d8ef6;
    box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.5);
}

.WeiBo-list {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    width: 100%;
    /* padding-left: 62px; */
}


.user-content {
    padding-left: 62px;
}

.interactive-image {
    display: flex;
    justify-content: space-between;
    gap: 20px;
    margin-right: 350px;
}

.btn-public,
.btn-post {
    background-color: orange;
    color: #fff;
    border: none;
    border-radius: 2.25rem;
    padding: 8px 16px;
    text-align: center;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
    width: 75px;
}

.btn-public:hover,
.btn-post:hover {
    background-color: red;
}

/*weibo true part*/
/*
发布的微博设置
*/
.WeiBo-item {
    display: block;
    width: auto;
    height: auto;
    background-color: var(--bgColor);
    margin-top: 10px;
    min-width: 900px;
}

.WeiBo-item .show-item {
    display: flex;
    align-items: top;
}

.WeiBo-item .pic img {
    width: 52px;
    height: 52px;
    object-fit: cover;
    border-radius: 50%;
}

.WeiBo-item .discribe {
    display: flex;
    flex-direction: column;
    align-items: top;
    margin-left: 10px;
}

.WeiBo-item .discribe p {
    margin: 0;
    font-size: 16px;
    font-weight: bold;
    color: var(--textColor)
}

.WeiBo-item .discribe .post-time {
    font-size: 12px;
    color: var(--textColor);
    margin-left: 10px;
}

.WeiBo-item .user-content {
    margin-top: 10px;
}

.WeiBo-item .user-content .character {
    margin-bottom: 10px;
    /* 发帖文字样式可以在这里调整 */
    font-size: 14px;
    color: var(--textColor);
    ;
}

.WeiBo-item .user-content .user-attach-pic {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}

.WeiBo-item .user-content .user-attach-pic img {
    width: 132px;
    height: 132px;
    object-fit: cover;
}

.justify-WeiBo-item {
    margin: 20px;
}

.uploaded-images {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    margin-top: 10px;
}

.preview-img {
    width: 100px;
    height: 100px;
    object-fit: cover;
}

/*预览图片样式*/
.preview-container {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    /* 4列 */
    grid-template-rows: repeat(1, 1fr);
    gap: 4px;
    /* 图片之间的间距 */
}

.preview-image {
    width: 98.063px;
    height: 98.063px;
    border-radius: 6px;

}

.justify_preview {
    display: flex;
    width: 90%;
}

.father_preview {
    padding-top: 0.25rem;
    padding-left: 0.25rem;
}

/* 博客里面图片的摆放要求 */
.modify_pic {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    /* 4列 */
    grid-template-rows: repeat(1, 1fr);
    gap: 4px;
}

.modify_pic img {
    border-radius: 4px;
}
</style>