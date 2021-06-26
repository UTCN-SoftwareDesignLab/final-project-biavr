import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default{
    subscribeToNotification(){ 
        const URL = "http://localhost:8090/socket";
        const websocket = new SockJS(URL);
        const stompClient = Stomp.over(websocket);
        stompClient.connect(
          {"Access-Control-Allow-Origin": "*"}, () => {
             //console.log("BIANCA - connect")
            stompClient.subscribe('/topic/socket/companies', notification =>{
              let message = notification.body;
              this.snackBar.open(message,'Close', {duration : 5000})
            },
            {"Access-Control-Allow-Origin": "*"})
          }
        );
      }
}