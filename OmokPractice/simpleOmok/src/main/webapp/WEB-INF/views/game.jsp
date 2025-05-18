<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>오목 게임</title>
    <style>
        canvas {
            border: 2px solid #333;
            background-color: #f9e4b7; /* 바둑판 같은 색 */
            display: block;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1>오목 게임</h1>
<h2>닉네임 : ${nickname}</h2>

<canvas id="board" width="600" height="600"></canvas>

<script>
    const canvas = document.getElementById("board");
    const ctx = canvas.getContext("2d");

    const boardSize = 15;
    const cellSize = canvas.width / (boardSize + 1); // 격자 사이 여백 포함

    let myRole = "";
    let nickname = "${nickname}";
    let socket = new WebSocket("ws://localhost:8080/omokSocket");

    // 격자판 그리기
    function drawGrid() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        for (let i = 1; i <= boardSize; i++) {
            // 가로줄
            ctx.beginPath();
            ctx.moveTo(cellSize, i * cellSize);
            ctx.lineTo(boardSize * cellSize, i * cellSize);
            ctx.stroke();

            // 세로줄
            ctx.beginPath();
            ctx.moveTo(i * cellSize, cellSize);
            ctx.lineTo(i * cellSize, boardSize * cellSize);
            ctx.stroke();
        }
    }

    // 돌 그리기
    function drawStone(x, y, color) {
        const cx = (x + 1) * cellSize;
        const cy = (y + 1) * cellSize;

        ctx.beginPath();
        ctx.arc(cx, cy, cellSize / 2.5, 0, Math.PI * 2);
        ctx.fillStyle = color === "black" ? "#000" : "#fff";
        ctx.strokeStyle = "#000";
        ctx.lineWidth = 1;
        ctx.fill();
        ctx.stroke();
    }

    // 캔버스 클릭 이벤트 처리
    canvas.addEventListener("click", (e) => {
        if (myRole === "observer") {
            alert("관전자입니다. 돌을 둘 수 없습니다.");
            return;
        }

        const rect = canvas.getBoundingClientRect();
        const clickX = e.clientX - rect.left;
        const clickY = e.clientY - rect.top;

        // 가장 가까운 셀 좌표로 변환
        const x = Math.round(clickX / cellSize) - 1;
        const y = Math.round(clickY / cellSize) - 1;

        // 유효 범위 체크
        if (x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
            socket.send(JSON.stringify({
                type: "putStone",
                x: x,
                y: y,
                color: myRole
            }));
        }
    });

    // WebSocket 수신 처리
    socket.onmessage = function (e) {
        console.log("메세지 수신됨:", e.data);
        const data = JSON.parse(e.data);

        if (data.type === "playerJoin") {
            myRole = data.role;
            console.log("내 역할:", myRole);
        }

        if (data.type === "updateBoard") {
            drawStone(data.x, data.y, data.color);
        }
    };

    drawGrid(); // 초기 렌더링
</script>
</body>
</html>

