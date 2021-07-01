import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  create(eventDetails) {
    return HTTP.post(BASE_URL + "/companies/jobs/applications/calendar" , eventDetails, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
};