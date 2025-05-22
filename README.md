# ModernWebWeiBoSystem

### **1.**  **注册登录功能**

  登录界面：

 ![image](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img1.png)

注册界面（含前端检查）：在前端检测用户输入的用户密码是否符合安全要求。

 ![image](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img2.png)

### **2.**  **静态界面设计**

未登录时的界面：用户无法看到个人信息及任何博客，直到用户完成登录。

![img](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img3.png)

登录后界面：上侧导航栏右侧和左侧个人信息栏变成用户头像，随机在下方展示五条博客。

![img](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img4.png)

### **3.**  **发表文字和图片**

在上方填入文字，点击图片的logo选择文件输入图片，同时在文本框下部展示选中的图片，每预览4张往下拓展，点击对应位置的图片可以删除，删除后右边的图片往左填充，下面的图片往上填充。点击发布以发表。
![image](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img5.png)

发表完成之后所有登录的用户可以看到你发表的博客。

如下：
 ![img](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img6.png)

### **4.**  **点赞和评论**

点击以点赞，点赞后点赞图标变红表示已点赞，再次点击取消点赞。

点击评论展开评论区，评论会标明用户名，头像和发表时间。

![img](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img7.png)

### **5.**  **个人用户主页设计和跳转**

![img](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img8.png)

通过点击用户头像以跳转到对应用户的主页，可以通过点击界面上的主页评论区的评论的图片，博主的头像来跳转，主页的背景图设置栏中设置，用户的头像和密码都在这里修改，包括退出个人微博。

![img](https://github.com/Y0sem1te/ModernWebWeiBoSystem/blob/main/show_img/img9.png)

### **6.**  **关注功能实现**

在上面已经展示了用户的个人主页，我们注意到里面是没有关注按钮的，那是因为我们点开的是自己的主页，因此隐藏了关注按钮。点开别人的主页时会出现关注按钮，点击变成已关注，同时目标用户个人主页粉丝数加一，当前用户关注数加一。