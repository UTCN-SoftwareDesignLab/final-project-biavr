<template>
  <div>
    <v-card>
      <CompanyDetails
        :company="company"
      ></CompanyDetails>
    </v-card>
    <v-card>
      <v-card-title>
        Jobs
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
        <v-btn @click="addJob" v-show="isRecruiter">Add Job</v-btn>
        <v-btn @click="seeApplications" v-show="isRecruiter">See applications</v-btn>
      </v-card-title>
      
      <v-data-table
        :headers="headers"
        :items="jobs"
        :search="search"
        @click:row="editJob"
      ></v-data-table>
      <JobDialog
        :opened="dialogVisible"
        :job="selectedJob"
        :company="company"
        :isRecruiter="isRecruiter"
        @refresh="refreshList"
      ></JobDialog>
    </v-card>
  </div>
</template>

<script>
import api from "../api";
import JobDialog from "../components/JobDialog.vue";
import CompanyDetails from "../components/CompanyDetails.vue";

const userID = Number(localStorage.getItem("id"));

export default {
  name: "JobList",
  components: { JobDialog, CompanyDetails },
  data() {
    return {
      company: {},
      jobs: [],
      isRecruiter: true,
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Description", value: "description" },
        { text: "Domain", value: "domain"},
      ],
      dialogVisible: false,
      selectedJob: {},
    };
  },
  methods: {
    editJob(job) {
      this.selectedJob = job;
      this.dialogVisible = true;
    },
    addJob() {
      console.log("Please, let me add a job");
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedJob = {};
      //this.jobs = await api.jobs.allJobs();
      //console.log("Current company", this.company.name)
      await api.companies.get(userID).then(
          (data) => {
            this.jobs = data.jobs;
            //console.log(this.jobs)
          });
      //console.log(this.jobs);
      //this.jobs = this.company.jobs;
      
    },
    getCompany(id){
      api.companies.get(id).then(
          (data) => {
            this.company = data;
            //console.log("Company in getCompany ",this.company)
          });
      
    },
    seeApplications(){
      this.$router.push({name:"Applications"});
    }
  },
  created() {
    const user = JSON.parse(localStorage.getItem("user"));
    const role = user.roles[0];
    console.log("In JobList I am ",role);

    const comp = JSON.parse(localStorage.getItem("company"));
    this.company=comp;
    console.log("I see the job list for company ",this.company.name);
    if(role == "SEEKER"){
        this.isRecruiter = false;
        
    }
    else{
      this.isRecruiter = true;
    }
    if(this.isRecruiter){
      this.getCompany(userID);
    }
    
    this.refreshList(userID);
  },
};
</script>

<style scoped></style>
