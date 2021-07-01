import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default{
    subscribeToNotification(){ 
        const URL = "http://localhost:8090/socket";
        const websocket = new SockJS(URL);
        const stompClient = Stomp.over(websocket);
        stompClient.connect(
          {"Access-Control-Allow-Origin": "*"}, () => {
            stompClient.subscribe('/topic/socket/companies', notification =>{
              let message = notification.body;
              alert(message);
            },
            {"Access-Control-Allow-Origin": "*"})
          }
        );
      }
}