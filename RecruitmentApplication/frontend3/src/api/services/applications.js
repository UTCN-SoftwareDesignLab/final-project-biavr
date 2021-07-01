import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allApplications(id) {
    return HTTP.get(BASE_URL + "/companies/jobs/applications/list/" + id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  get(id){
    return HTTP.get(BASE_URL + "/companies/jobs/applications/" + id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(application) {
    return HTTP.post(BASE_URL + "/companies/jobs/applications", application, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(application) {
    return HTTP.patch(BASE_URL + "/companies/jobs/applications/" + application.id, application, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  delete(application) {
    return HTTP.delete(BASE_URL + "/companies/jobs/applications/" + application.id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
}
};
