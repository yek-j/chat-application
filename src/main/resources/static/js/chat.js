const msgList = document.getElementById('messages');
const msgInput = document.getElementById('input-msg');
const scriptElement = document.querySelector('script[data-th-url]');
const url = scriptElement.getAttribute('data-th-url');;

let welcomMessage = false;

const socket = new StompJs.Client({
    brokerURL: url
});
socket.activate();

socket.onConnect = (frame) => {
    console.log('Websocket 서버 연결');
    setTimeout(() => console.log("로딩 중"), 3000);
    
    if(welcomMessage === false) {
        // 입장 메시지
        socket.publish({
            destination: "/app/greetings",
        });

        socket.subscribe('/topic/greetings', (greeting) => {
            let liEle = document.createElement('li')
            let welcomEle = document.createElement('span');
            welcomEle.textContent = greeting.body;
            liEle.appendChild(welcomEle)
            msgList.appendChild(liEle);
            welcomMessage = true;
        });
    }
    // 사용자가 보낸 채팅 메시지
    socket.subscribe('/topic/message', (message) => {
        let liEle = document.createElement('li')
        let msgEle = document.createElement('span');
        msgEle.textContent = message.body
        liEle.appendChild(msgEle);
        msgList.appendChild(liEle);
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