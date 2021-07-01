import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allJobs() {
    return HTTP.get(BASE_URL + "/companies/jobs", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(job) {
    return HTTP.post(BASE_URL + "/companies/jobs", job, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(job) {
    return HTTP.patch(BASE_URL + "/companies/jobs/" + job.id, job, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  delete(job) {
      return HTTP.delete(BASE_URL + "/companies/jobs/" + job.id, { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
      );
  }
};
