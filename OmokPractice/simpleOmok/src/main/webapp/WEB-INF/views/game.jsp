<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>오목 게임</title>
    <style>
        @font-face {
            font-family: 'Ownglyph_ParkDaHyun';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2411-3@1.0/Ownglyph_ParkDaHyun.woff2') format('woff2');
            font-weight: normal;
            font-style: normal;
        }
        canvas {
            border: 2px solid #333;
            background-color: #f9e4b7; /* 바둑판 같은 색 */
            display: block;
        }
        body {
            font-family: 'Ownglyph_ParkDaHyun', sans-serif;
            background-color: #f8f8f8;
            margin: 40px;
            text-align: center;
        }
        #title{
            font-size: 70px;
            color: #333;
            margin-bottom: 20px;
        }
        #nickname, #role {
            font-size: 30px;
            color: #666;
            margin-bottom: 20px;
            padding-right: 50px;
        }
        .container {
            display: flex;          /* 플렉스 컨테이너 선언 */
            flex-direction: row;    /* 가로 방향 정렬 (기본값) */
            justify-content: center; /* 주축 정렬 (가운데 정렬) */
            align-items: center;     /* 교차축 정렬 (세로 가운데) */
        }
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0px; /* 스크롤 방지를 위해 마진 없앰 */
        }
        .btn-wrapper {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            max-width: 1200px;
            padding: 0 40px 40px;
            box-sizing: border-box;  /* ✅ 패딩 포함해서 width 계산 */
        }

        .author {
            font-size: 15px;
            color: #555;
            flex-shrink: 0; /* ✅ 잘리지 않게 */
            white-space: nowrap;
        }
        #playBgmBtn {
            padding: 10px 20px;
            font-size: 15px;
            background-color: cadetblue;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
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

