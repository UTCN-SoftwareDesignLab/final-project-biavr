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
      >

      </v-data-table>

    </v-card>
    <ApplicationDialog
        :opened="dialogVisible"
        :application="selectedApp"
        :job="appJob"
        :eventDetails={}
        @refresh="refreshList"
    ></ApplicationDialog>
  </div>
</template>

<script>
import api from "../api";
import CompanyDetails from "../components/CompanyDetails.vue";
import ApplicationDialog from "../components/ApplicationDialog.vue";

export default {
  name: "ApplicationList",
  components: { CompanyDetails, ApplicationDialog },
  data() {
    return {
      company: {},
      applications: [],
      isRecruiter: true,
      search: "",
      headers: [
        { text: "First name", value: "firstName" },
        { text: "Last name", value: "lastName"},
        { text: "Email", value: "email"},
        { text: "Phone", value: "phoneNumber"},
        { text: "Address", value: "address"}
      ],
      dialogVisible: false,
      selectedApp: {},
      appJob: {},
    };
  },
  methods: {
    review(app){
        this.dialogVisible = true;
        this.selectedApp = app;
        this.appJob = app.job;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedApp = {};
      this.applications = await api.applications.allApplications(this.company.id);
      
    },
  },
  created() {
    const comp = JSON.parse(localStorage.getItem("company"));
    this.company=comp;
    this.refreshList();
  },
};
</script>

<style scoped></style>
