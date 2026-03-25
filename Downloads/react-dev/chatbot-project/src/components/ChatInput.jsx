import React, { useState } from 'react';
import { Chatbot } from 'supersimpledev';
import './ChatInput.css';



export function ChatInput({ chatMessages, setChatMessages }) {
    const [inputText, setInputText] = useState('');
    const clearMessages = () => {
        setChatMessages([]);
        localStorage.removeItem('chatMessages');
    };
    const saveInputText = (event) => {
        setInputText(event.target.value);
    };

    const sendMessage = () => {
        if (!inputText.trim()) return;

    const newChatMessages = [
        ...chatMessages,
        {
        message: inputText,
        sender: 'user',
        id: crypto.randomUUID(),
    },
    ];

    setChatMessages(newChatMessages);

    // Simulez la réponse du chatbot (remplacez par votre logique réelle)
    const response = Chatbot.getResponse(inputText);
    setChatMessages([
        ...newChatMessages,
        {
            message: response,
            sender: 'robot',
            id: crypto.randomUUID(),
    },
    ]);

    setInputText('');
    };

    return (
    <div className="chat-input-container">
            <input
            placeholder="Send a message to Chatbot"
            size="30"
            onChange={saveInputText}
            value={inputText}
            className="chat-input"
            onKeyDown={(e) => {
                if (e.key === 'Enter') sendMessage();
            }}
            />
            <button
            onClick={sendMessage}
            className="send-button"
        >
            Send
        </button>
        <button
            onClick={clearMessages}
            className="clear-button"
            >
            Clear
        </button>
        </div>
);
}

export default ChatInput;
