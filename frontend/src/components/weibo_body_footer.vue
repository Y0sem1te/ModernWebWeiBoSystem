<template>
    <footer>
        <p>
            U want to learn more about me? Visiting my blog!
            <a href="https://y0sem1te.github.io">Lets`Go!!!</a>
        </p>
        <p>WeiBo © 2025, Created by Y0sem1te</p>
    </footer>
</template>

<script>
    export default {
        name: 'weibo_body_footer',
    };
</script>

<style scoped>
    footer {
    background-color: #32eebc;
    color: #fff;
    text-align: center;
    padding: 5px 0;
    font-size: 14px;
}

footer a {
    color: #ed1212;
    text-decoration: none;
}

footer a:hover {
    text-decoration: underline;
}

footer .social-icons {
    margin-top: 10px;
}

footer .social-icons a {
    margin: 0 10px;
    font-size: 18px;
    color: #fff;
}

footer .social-icons a:hover {
    color: #4CAF50;
}
</style>