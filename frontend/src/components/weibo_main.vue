<template>
    <!DOCTYPE html>
    <html lang="en">
    <weibo_head/>
    <body>
      <weibo_body_header/>
      <main>
        <weibo_body_main_left/>
        <weibo_body_main_center/>
        <weibo_body_main_right/>
      </main>
      <weibo_body_footer/>
    </body>
    </html>
  </template>
  
  <script>
  import weibo_body_footer from './weibo_body_footer.vue';
  import weibo_body_main_left from './weibo_body_main_left.vue';
  import weibo_body_header from './weibo_body_header.vue';
  import weibo_head from './weibo_head.vue';
  import weibo_body_main_center from './weibo_body_main_center.vue';
  import weibo_body_main_right from './weibo_body_main_right.vue';
  
  export default {
    name: 'weibo_main',
    components: {
      weibo_head,
      weibo_body_header,
      weibo_body_main_left,
      weibo_body_main_center,
      weibo_body_main_right,
      weibo_body_footer
    }
  }
  </script>
  
  <style>
  * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
  }
  body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      background-color: var(--main-grd);
      color: #333;
  }
  main {
      display: flex;
      gap: 20px;
      padding: 10px;
      justify-content: center;
      align-items: flex-start;
  }
  </style>