<template>
  <div>
    <v-card>
      <v-card-title>
        Companies
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
        <v-btn @click="addCompany" v-show="isAdmin">Add company</v-btn>
      </v-card-title>
      <v-data-table
        :headers="headers"
        :items="companies"
        :search="search"
        @click:row="goToPage"
      ></v-data-table>
      <!-- <CompanyDialog
        :opened="dialogVisible"
        :company="selectedCompany"
        :isAdmin="isAdmin"
        @refresh="refreshList"
      ></CompanyDialog> -->
    </v-card>

    <v-card>
      <CompanyDialog
        :opened="dialogVisible"
        :company="selectedCompany"
        :isAdmin="isAdmin"
        @refresh="refreshList"
      ></CompanyDialog>
    </v-card>
  </div>
  
</template>

<script>
import api from "../api";
import CompanyDialog from "../components/CompanyDialog";

export default {
  name: "CompanyList",
  components: { CompanyDialog },
  data() {
    return {
      isAdmin : false,
      companies: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Description", value: "description" },
      ],
      dialogVisible: false,
      selectedCompany: {},
    };
  },
  methods: {
    editCompany(company) {
      this.selectedCompany = company;
      this.dialogVisible = true;
    },
    addCompany() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedCompany = {};
      this.companies = await api.companies.allCompanies();
    },
    goToPage(company){
      if(!this.isAdmin){
        localStorage.setItem("company",JSON.stringify(company));
        //console.log("In company list",company.name);
        this.$router.push({name:"Jobs"});
      }
      else{
        this.editCompany(company);
        // this.dialogVisible = true;
        // this.selectedCompany=company;
      }
    }
  },
  created() {
    const user = JSON.parse(localStorage.getItem("user"));
    const role = user.roles[0];
    console.log("In CompanyList I am ",role);
    if(role == "SEEKER"){
      this.isAdmin=false;
      //api.websocket.subscribeToNotification();
    }
    else{
      this.isAdmin=true;
    }
    console.log("I AM ADMIN ",this.isAdmin)
    this.refreshList();
  },
  
};
</script>

<style scoped></style>
