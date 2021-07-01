import authHeader, { HTTP } from "../http";

export default {
    sendMessage(messageDetails){
        return HTTP.post("/send-messages", messageDetails, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
    );
    }

}