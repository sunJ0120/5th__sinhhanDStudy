<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>오목 게임</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%--bgm 삽입--%>
    <div class = "btn-wrapper">
        <div class="author">👨‍💻 만든 사람: sunJ</div>
        <button id="playBgmBtn">BGM 켜기</button>
    </div>
    <audio id="bgm" src="/music/미래도시라솔파.mp3" autoplay loop></audio>
    <div id="title">오목 게임</div>
    <div class="container">
        <div id="nickname" data-nickname="${nickname}">닉네임: ${nickname}</div>
        <div id="role">역할: (확인 중...)</div>
    </div>
    <canvas id="board" width="600" height="600"></canvas>

    <script src="/js/omokPlay.js"></script>
    <script src="/js/bgmControl.js"></script>
</body>
</html>

