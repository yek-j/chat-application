const msgList = document.getElementById('messages');
const msgInput = document.getElementById('input-msg');
let welcomMessage = false;

const socket = new StompJs.Client({
    brokerURL: 'ws://localhost:9797/chat'
});
socket.activate();

socket.onConnect = (frame) => {
    console.log('Websocket 서버 연결');

    if(welcomMessage === false) {
        // 입장 메시지
        socket.publish({
            destination: "/app/greetings",
        });

        socket.subscribe('/topic/greetings', (greeting) => {
            let welcomEle = document.createElement('li')
            welcomEle.textContent = greeting.body;
            msgList.appendChild(welcomEle);
            welcomMessage = true;
        });
    }
    // 사용자가 보낸 채팅 메시지
    socket.subscribe('/topic/message', (message) => {
        let msgEle = document.createElement('li')
        msgEle.textContent = message.body
        msgList.appendChild(msgEle);
    });
}

socket.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

socket.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

$(document).ready(function() {
    const sendBtn = $('#send');
    sendBtn.on('click', function() {
        const msg = msgInput.value;
        if (msg) {
            socket.publish({
                destination: "/app/message",
                body: msg,
                headers: { 'content-type': 'text/plain' }
            })
            msgInput.value = '';
        }
    });
});