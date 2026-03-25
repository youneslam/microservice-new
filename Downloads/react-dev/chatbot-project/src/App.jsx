import {useEffect, useState} from 'react'
import { Chatbot } from 'supersimpledev'

import { ChatInput } from './components/ChatInput.jsx'
import ChatMessages from './components/ChatMessages.jsx'
import './App.css'



    function App() {
        const [chatMessages, setChatMessages] = useState(JSON.parse(localStorage.getItem('chatMessages')) || []);
          useEffect(() => {
              Chatbot.addResponses({
                'goodbye': 'Goodbye. Have a great day!',
                'give me a unique id': function() {
                  return `Sure! Here's a unique ID: ${crypto.randomUUID()}`;
                }
              });
            }, []);

          useEffect(() => {
            localStorage.setItem('chatMessages', JSON.stringify(chatMessages));
          }, [chatMessages]);

        return (
          <div className="app-container">
            <ChatMessages
              chatMessages={chatMessages}
            />
            <ChatInput
              chatMessages={chatMessages}
              setChatMessages={setChatMessages}
            />
          </div>
        );
      }

export default App
