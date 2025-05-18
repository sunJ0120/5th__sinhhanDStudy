<!-- 📁 chat.jsp (뷰 페이지) -->
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>WebSocket 채팅</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 30px;
        }
        h2 {
            margin-bottom: 20px;
        }
        #chatBox {
            width: 400px;
            height: 350px;
            overflow-y: auto;
            border: 1px solid #ccc;
            border-radius: 8px;
            background: #fff;
            padding: 15px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .msg {
            margin: 10px 0;
            padding: 8px 12px;
            border-radius: 20px;
            max-width: 70%;
            clear: both;
            word-wrap: break-word;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        .mine {
            background-color: #daf1ff;
            align-self: flex-end;
            float: right;
        }

        .other {
            background-color: #eee;
            float: left;
        }

        #inputArea {
            margin-top: 15px;
        }

        #msgInput {
            width: 300px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 8px 16px;
            margin-left: 8px;
            border: none;
            background-color: #3e8ed0;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #367bb5;
        }
    </style>
</head>
<body>
<h2>${nickname}님의 채팅방</h2>
<div id="chatBox"></div>
<div id="inputArea">
    <input type="text" id="msgInput" placeholder="메시지를 입력하세요"/>
    <button onclick="sendMessage()">전송</button>
</div>

<script>
    const nickname = "${nickname}";
    const socket = new WebSocket("ws://localhost:8080/chatSocket"); // 포트 및 컨텍스트는 실제 환경에 맞게 수정

    socket.onmessage = function(event) {
        const chatBox = document.getElementById("chatBox");
        const div = document.createElement("div");
        div.className = "msg other";
        div.innerText = event.data;
        chatBox.appendChild(div);
        chatBox.scrollTop = chatBox.scrollHeight;
    };

    function sendMessage() {
        const input = document.getElementById("msgInput");
        const message = nickname + ": " + input.value;
        socket.send(message);

        const chatBox = document.getElementById("chatBox");
        const div = document.createElement("div");
        div.className = "msg mine";
        div.innerText = message;
        chatBox.appendChild(div);
        chatBox.scrollTop = chatBox.scrollHeight;

        input.value = "";
    }
</script>
</body>
</html>
