import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allCompanies() {
    return HTTP.get(BASE_URL + "/companies", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  getByUserId(id){
    return HTTP.get(BASE_URL + "/companies/jobs/recruiter/" + id, { headers: authHeader() }).then(
      (response) => {return response.data;
      }
    );
  },
  getByCompanyId(id){
    return HTTP.get(BASE_URL + "/companies/jobs/" + id, { headers: authHeader() }).then(
      (response) => {return response.data;
      }
    );
  },
  create(company) {
    return HTTP.post(BASE_URL + "/companies", company, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(company) {
    return HTTP.patch(BASE_URL + "/companies/" + company.id, company, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  delete(company) {
    return HTTP.delete(BASE_URL + "/companies/" + company.id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
}
};
