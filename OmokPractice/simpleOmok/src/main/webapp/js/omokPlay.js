window.addEventListener("DOMContentLoaded", () => {
    const canvas = document.getElementById("board");
    const ctx = canvas.getContext("2d");

    const boardSize = 15;
    const cellSize = canvas.width / (boardSize + 1);

    let myRole = "";
    let nickname = document.getElementById("nickname").dataset.nickname;
    let socket = new WebSocket("ws://localhost:8080/omokSocket");

    function drawGrid() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        for (let i = 1; i <= boardSize; i++) {
            ctx.beginPath();
            ctx.moveTo(cellSize, i * cellSize);
            ctx.lineTo(boardSize * cellSize, i * cellSize);
            ctx.stroke();

            ctx.beginPath();
            ctx.moveTo(i * cellSize, cellSize);
            ctx.lineTo(i * cellSize, boardSize * cellSize);
            ctx.stroke();
        }
    }

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

    canvas.addEventListener("click", (e) => {
        if (myRole === "observer") {
            alert("관전자입니다. 돌을 둘 수 없습니다.");
            return;
        }

        const rect = canvas.getBoundingClientRect();
        const clickX = e.clientX - rect.left;
        const clickY = e.clientY - rect.top;

        const x = Math.round(clickX / cellSize) - 1;
        const y = Math.round(clickY / cellSize) - 1;

        if (x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
            socket.send(JSON.stringify({
                type: "putStone",
                x,
                y,
                color: myRole
            }));
        }
    });

    socket.onmessage = function (e) {
        console.log("메세지 수신됨:", e.data);
        const data = JSON.parse(e.data);

        if (data.type === "playerJoin") {
            myRole = data.role;
            console.log("내 역할:", myRole);

            // 역할 표시 업데이트
            const roleDiv = document.getElementById("role");
            if (roleDiv) {
                if (myRole === "black") {
                    roleDiv.textContent = "역할: ⚫";
                } else if (myRole === "white") {
                    roleDiv.textContent = "역할: ⚪";
                } else {
                    roleDiv.textContent = `역할: 관전자`;
                }
            }
        }

        if (data.type === "updateBoard") {
            drawStone(data.x, data.y, data.color);
        }
    };
    drawGrid(); // ✅ 안전하게 canvas가 로드된 후 실행
});
