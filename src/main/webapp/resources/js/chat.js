const chatWebSocket = new WebSocket(
	`ws://192.168.1.86:8080${getBasePath()}/websocket/chat`
);

chatWebSocket.onmessage = ({ data }) => {
	const { data: receivedMessages, eventType: event } = JSON.parse(data);

	handleIncomingMessages(receivedMessages);
};

function handleIncomingMessages(messageData) {
	if (Array.isArray(messageData)) {
		renderMessageList(messageData);
	} else {
		renderSingleMessage(messageData);
	}
}

function getBasePath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

function scrollToBottom(container) {
	container.scrollTop = container.scrollHeight;
}

function createMessageElement(senderName, messageText) {
	const messageElement = document.createElement("div");
	messageElement.className = "message";

	const senderElement = document.createElement("span");
	senderElement.innerText = `Nome: ${senderName}`;

	const textElement = document.createElement("span");
	textElement.innerText = messageText;

	messageElement.appendChild(senderElement);
	messageElement.appendChild(textElement);

	return messageElement;
}

function renderMessageList(messageArray) {
	for (const messageObject of messageArray) {
		renderSingleMessage(messageObject);
	}

}

function renderSingleMessage(messageObject) {
	const messageContainer = document.querySelector("#messages");
	console.log(messageContainer)
	const { name, message, hour } = messageObject;
//	const messageElement = createMessageElement(name, message);
	messageContainer.innerHTML += `
	<div class="message">
		<span>Nome: ${name}</span>
		<div class="message-text-hour">
			<span class="message-text">${message}</span> 
			<span class="message-hour">${hour}</span>
		</div>
	</div>`
	scrollToBottom(messageContainer);
}
