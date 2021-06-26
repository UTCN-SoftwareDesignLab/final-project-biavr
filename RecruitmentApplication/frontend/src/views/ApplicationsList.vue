<template>
  <div>
    <v-card>
      <CompanyDetails
        :company="company"
      ></CompanyDetails>
    </v-card>
    <v-card>
      <v-card-title>
        Applications
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      
      <v-data-table
        :headers="headers"
        :items="applications"
        :search="search"
        @click:row="review"
      ></v-data-table>
    </v-card>
  </div>
</template>

<script>
import api from "../api";
import CompanyDetails from "../components/CompanyDetails.vue";

export default {
  name: "ApplicationsList",
  components: { CompanyDetails },
  data() {
    return {
      company: {},
      applications: [],
      isRecruiter: true,
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "First name", value: "firstName" },
        { text: "Last name", value: "lastName"},
        { text: "Email", value: "email"},
        { text: "Phone", value: "phoneNumber"},
        { text: "Address", value: "address"}
      ],
      dialogVisible: false,
      selectedApp: {},
    };
  },
  methods: {
    review(){
        console.log(this.selectedApp.message);
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedApp = {};
      this.applications = await api.application.allApplications(this.company.id);
      // await api.companies.get(id).then(
      //     (data) => {
      //     //this.jobs = data.jobs;
      //     //console.log(this.jobs)
      //     });
      // //console.log(this.jobs);
      // //this.jobs = this.company.jobs;
      
    },
    getCompany(id){
      api.companies.get(id).then(
          (data) => {
            this.company = data;
          //this.jobs = data.jobs;
          //console.log(this.jobs)
          });
      
    }
  },
  created() {
    const userID = Number(localStorage.getItem("id"));
    // const user = JSON.parse(localStorage.getItem("user"));
    // const role = user.roles[0];
    // if(role == "SEEKER"){
    //     this.isRecruiter = false;
    //     const comp = JSON.parse(localStorage.getItem("company"));
    //     this.company=comp;
    // }
    // else{
    //   this.isRecruiter = true;
    // }
    // if(this.isRecruiter){
    //   this.getCompany(userID);
    // }
    const comp = JSON.parse(localStorage.getItem("company"));
    this.company=comp;
    console.log("Company from local storage", this.company.jobs);
    this.refreshList(userID);
  },
};
</script>

<style scoped></style>
